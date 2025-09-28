package dev.sterner

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.util.Mth
import org.joml.Vector3f

class StoneMaskAnimationState {
    private var animationTick = 0L
    private var currentAnimation: AnimationDefinition? = null
    private var animationStartTime = 0L


    private fun applyChannelToPart(part: ModelPart, channel: AnimationChannel, time: Float) {
            when (channel.target()) {
                AnimationChannel.Targets.ROTATION -> {
                    val rotation = interpolateKeyframes(channel, time)
                    part.xRot = rotation.x() * (Math.PI.toFloat() / 180f)
                    part.yRot = rotation.y() * (Math.PI.toFloat() / 180f)
                    part.zRot = rotation.z() * (Math.PI.toFloat() / 180f)
                }

                AnimationChannel.Targets.SCALE -> {
                    val scale = interpolateKeyframes(channel, time)
                    part.xScale = scale.x()
                    part.yScale = scale.y()
                    part.zScale = scale.z()
                }

                AnimationChannel.Targets.POSITION -> {
                    val position = interpolateKeyframes(channel, time)
                    part.x = position.x()
                    part.y = position.y()
                    part.z = position.z()
                }
            }
        }

        fun startAnimation(type: StoneMaskAnimationType) {
            currentAnimation = when (type) {
                StoneMaskAnimationType.INACTIVE -> StoneMaskAnimations.INACTIVE
                StoneMaskAnimationType.AWAKEN -> StoneMaskAnimations.AWAKEN
                StoneMaskAnimationType.RETRACT -> StoneMaskAnimations.RETRACT
                StoneMaskAnimationType.PIERCED -> null
            }
            animationStartTime = System.currentTimeMillis()
            animationTick = 0
        }

        fun animate(model: StoneMaskModel) {
            currentAnimation?.let { anim ->
                val elapsedSeconds = (System.currentTimeMillis() - animationStartTime) / 1000f

                if (!anim.looping() && elapsedSeconds > anim.lengthInSeconds()) {
                    when (currentAnimation) {
                        StoneMaskAnimations.AWAKEN -> {
                            currentAnimation = null
                            isPierced = true
                        }

                        StoneMaskAnimations.RETRACT -> {
                            currentAnimation = StoneMaskAnimations.INACTIVE
                            isPierced = false
                        }

                        else -> {}
                    }
                    return
                }

                val animationTime = if (anim.looping()) {
                    elapsedSeconds % anim.lengthInSeconds()
                } else {
                    elapsedSeconds.coerceAtMost(anim.lengthInSeconds())
                }

                anim.boneAnimations().forEach { (boneName, channels) ->
                    val part = getPartByName(model, boneName)
                    part?.let { modelPart ->
                        channels.forEach { channel ->
                            applyChannelToPart(modelPart, channel, animationTime)
                        }
                    }
                }
            }
        }

        private fun getPartByName(model: StoneMaskModel, name: String): ModelPart? {
            return when (name) {
                "lClaw01" -> model.lClaw01
                "lClaw02" -> model.lClaw02
                "lClaw03" -> model.lClaw03
                "lClaw04" -> model.lClaw04
                "rClaw01" -> model.rClaw01
                "rClaw02" -> model.rClaw02
                "rClaw03" -> model.rClaw03
                "rClaw04" -> model.rClaw04
                "mClaw" -> model.mClaw
                "blood" -> model.blood
                "armorHead" -> model.armorHead
                else -> null
            }
        }

        private fun interpolateKeyframes(channel: AnimationChannel, time: Float): Vector3f {
            val keyframes = channel.keyframes()

            if (keyframes.isEmpty()) {
                return Vector3f()
            }

            var prevKeyframe: Keyframe? = null
            var nextKeyframe: Keyframe? = null

            for (keyframe in keyframes) {
                if (keyframe.timestamp() <= time) {
                    prevKeyframe = keyframe
                } else {
                    nextKeyframe = keyframe
                    break
                }
            }

            return when {
                prevKeyframe == null -> keyframes.first().target()
                nextKeyframe == null -> prevKeyframe.target()
                else -> {
                    val progress =
                        (time - prevKeyframe.timestamp()) / (nextKeyframe.timestamp() - prevKeyframe.timestamp())
                    val interpolation = AnimationChannel.Interpolations.CATMULLROM//channel.interpolation()

                    when (interpolation) {
                        AnimationChannel.Interpolations.LINEAR -> {
                            Vector3f(
                                Mth.lerp(progress, prevKeyframe.target().x(), nextKeyframe.target().x()),
                                Mth.lerp(progress, prevKeyframe.target().y(), nextKeyframe.target().y()),
                                Mth.lerp(progress, prevKeyframe.target().z(), nextKeyframe.target().z())
                            )
                        }

                        AnimationChannel.Interpolations.CATMULLROM -> {
                            Vector3f(
                                Mth.lerp(progress, prevKeyframe.target().x(), nextKeyframe.target().x()),
                                Mth.lerp(progress, prevKeyframe.target().y(), nextKeyframe.target().y()),
                                Mth.lerp(progress, prevKeyframe.target().z(), nextKeyframe.target().z())
                            )
                        }

                        else -> {
                            Vector3f(
                                Mth.lerp(progress, prevKeyframe.target().x(), nextKeyframe.target().x()),
                                Mth.lerp(progress, prevKeyframe.target().y(), nextKeyframe.target().y()),
                                Mth.lerp(progress, prevKeyframe.target().z(), nextKeyframe.target().z())
                            )
                        }
                    }
                }
            }
        }

        private var isPierced = false
    }
