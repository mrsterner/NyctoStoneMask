package dev.sterner

import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.model.geom.ModelPart
import org.joml.Vector3f


class StoneMaskAnimationState {
    private var animationTick = 0L
    private var currentAnimation: AnimationDefinition? = null
    private var animationStartTime = 0L

    enum class AnimationType {
        INACTIVE, AWAKEN, PIERCED, RETRACT
    }

    fun startAnimation(type: AnimationType) {
        currentAnimation = when (type) {
            AnimationType.INACTIVE -> StoneMaskAnimations.INACTIVE
            AnimationType.AWAKEN -> StoneMaskAnimations.AWAKEN
            AnimationType.RETRACT -> StoneMaskAnimations.RETRACT
            AnimationType.PIERCED -> null // Loop animation, handle separately
        }
        animationStartTime = System.currentTimeMillis()
        animationTick = 0
    }

    fun animate(model: StoneMaskModel, partialTicks: Float) {


        currentAnimation?.let { anim ->
            val elapsedSeconds = (System.currentTimeMillis() - animationStartTime) / 1000.0
            model.root().getAllParts().forEach { part ->
                val partName = getPartName(part, model)
                partName?.let { name ->
                    //val vector = anim.getAnimationVector(name, elapsedSeconds, 1f, partialTicks)
                    // Apply transformations from animation
                    //applyVector(part, vector)
                }
            }
        }
    }

    private fun getPartName(part: ModelPart, model: StoneMaskModel): String? {
        return when (part) {
            model.lClaw01 -> "lClaw01"
            model.lClaw02 -> "lClaw02"
            model.lClaw03 -> "lClaw03"
            model.lClaw04 -> "lClaw04"
            model.rClaw01 -> "rClaw01"
            model.rClaw02 -> "rClaw02"
            model.rClaw03 -> "rClaw03"
            model.rClaw04 -> "rClaw04"
            model.mClaw -> "mClaw"
            model.blood -> "blood"
            model.armorHead -> "armorHead"
            else -> null
        }
    }

    private fun applyVector(part: ModelPart, vector: Vector3f) {
        // Apply the animation vector to the model part
        part.xRot = vector.x()
        part.yRot = vector.y()
        part.zRot = vector.z()
    }
}