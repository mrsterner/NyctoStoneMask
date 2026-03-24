package dev.sterner.stone_mask.client

import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.entity.animation.AnimationDefinition
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation
import net.minecraft.entity.AnimationState
import net.minecraft.util.math.MathHelper
import org.joml.Vector3f
import kotlin.collections.iterator

class StoneMaskKeyframeAnimation(
    private val definition: AnimationDefinition,
    private val entries: List<Entry>
) {
    companion object {
        fun bake(rootPart: ModelPart, definition: AnimationDefinition): StoneMaskKeyframeAnimation {
            val lookup = rootPart.createPartGetter()
            val entries = mutableListOf<Entry>()
            for ((boneName, channels) in definition.boneAnimations()) {
                val part = lookup.apply(boneName)
                if (part != null) {
                    for (channel in channels) {
                        entries.add(Entry(part, channel.target(), channel.keyframes()))
                    }
                }
            }
            return StoneMaskKeyframeAnimation(definition, entries.toList())
        }
    }

    fun apply(animationState: AnimationState, age: Float) {
        animationState.run { state ->
            val ms = state.getTimeInMilliseconds(age)
            apply(ms, 1.0f)
        }
    }

    fun applyStatic() = apply(0L, 1.0f)

    fun apply(elapsedMs: Long, scale: Float) {
        val elapsedSeconds = getElapsedSeconds(elapsedMs)
        val vec = Vector3f()
        for (entry in entries) {
            entry.apply(elapsedSeconds, scale, vec)
        }
    }

    private fun getElapsedSeconds(elapsedMs: Long): Float {
        val seconds = elapsedMs / 1000f
        return if (definition.looping()) seconds % definition.lengthInSeconds() else seconds
    }

    class Entry(
        private val part: ModelPart,
        private val target: Transformation.Target,
        private val keyframes: Array<Keyframe>
    ) {
        fun apply(elapsed: Float, scale: Float, vec: Vector3f) {
            val i = maxOf(0, MathHelper.binarySearch(0, keyframes.size) { n -> elapsed <= keyframes[n].timestamp() } - 1)
            val j = minOf(keyframes.size - 1, i + 1)
            val kf1 = keyframes[i]
            val kf2 = keyframes[j]
            val delta = elapsed - kf1.timestamp()
            val t = if (j != i) MathHelper.clamp(delta / (kf2.timestamp() - kf1.timestamp()), 0f, 1f) else 0f
            kf2.interpolation().apply(vec, t, keyframes, i, j, scale)
            target.apply(part, vec)
        }
    }
}