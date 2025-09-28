package dev.sterner

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.player.Player
import java.util.UUID

@Environment(EnvType.CLIENT)
object StoneMaskClientAnimationManager {
    private val playerAnimations = mutableMapOf<UUID, AnimationData>()

    data class AnimationData(
        val type: StoneMaskAnimationType,
        val startTime: Long
    )

    fun setAnimation(player: Player, type: StoneMaskAnimationType) {
        playerAnimations[player.uuid] = AnimationData(type, System.currentTimeMillis())

        // Play sound effects based on animation type
        when (type) {
            StoneMaskAnimationType.AWAKEN -> {
                player.level().playLocalSound(
                    player.x, player.y, player.z,
                    SoundEvents.ARMOR_EQUIP_IRON.value(), // Replace with custom sound
                    SoundSource.PLAYERS,
                    1.0f, 1.0f,
                    false
                )
            }
            StoneMaskAnimationType.RETRACT -> {
                player.level().playLocalSound(
                    player.x, player.y, player.z,
                    SoundEvents.ARMOR_EQUIP_CHAIN.value(), // Replace with custom sound
                    SoundSource.PLAYERS,
                    1.0f, 0.8f,
                    false
                )
            }
            else -> {}
        }
    }

    fun getAnimation(player: Player): AnimationData? {
        return playerAnimations[player.uuid]
    }

    fun clearOldAnimations() {
        val currentTime = System.currentTimeMillis()
        playerAnimations.entries.removeIf {
            currentTime - it.value.startTime > 5000 // Remove animations older than 5 seconds
        }
    }
}