package dev.sterner.stone_mask

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.util.Mth
import net.minecraft.world.entity.AnimationState
import org.joml.Vector3f

/**
 * A copy of the package-private KeyframeAnimation logic so we can use it
 * outside of net.minecraft.client.animation.
 */
class StoneMaskKeyframeAnimation(
    private val definition: AnimationDefinition,
    private val entries: List<Entry>
) {
    companion object {
        fun bake(rootPart: ModelPart, definition: AnimationDefinition): StoneMaskKeyframeAnimation {
            val lookup = rootPart.createPartLookup()
            val entries = mutableListOf<Entry>()
            for ((boneName, channels) in definition.boneAnimations()) {
                val part = lookup.apply(boneName)
                    ?: throw IllegalArgumentException("Cannot animate $boneName, which does not exist in model")
                for (channel in channels) {
                    entries.add(Entry(part, channel.target(), channel.keyframes()))
                }
            }
            return StoneMaskKeyframeAnimation(definition, entries.toList())
        }
    }

    fun apply(animationState: AnimationState, age: Float) {
        animationState.ifStarted { state ->
            apply(state.getTimeInMillis(age).toLong(), 1.0f)
        }
    }

    fun applyStatic() = apply(0L, 1.0f)

    private fun apply(elapsedMs: Long, scale: Float) {
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
        private val target: AnimationChannel.Target,
        private val keyframes: Array<Keyframe>
    ) {
        fun apply(elapsed: Float, scale: Float, vec: Vector3f) {
            val i = maxOf(0, Mth.binarySearch(0, keyframes.size) { n -> elapsed <= keyframes[n].timestamp() } - 1)
            val j = minOf(keyframes.size - 1, i + 1)
            val kf1 = keyframes[i]
            val kf2 = keyframes[j]
            val delta = elapsed - kf1.timestamp()
            val t = if (j != i) Mth.clamp(delta / (kf2.timestamp() - kf1.timestamp()), 0f, 1f) else 0f
            kf2.interpolation().apply(vec, t, keyframes, i, j, scale)
            target.apply(part, vec)
        }
    }
}