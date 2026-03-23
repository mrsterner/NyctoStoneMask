package dev.sterner.stone_mask

import dev.sterner.item.StoneMaskItem
import dev.sterner.network.StoneMaskNetworkHandler
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.Vec3d
import java.util.UUID

object StoneMaskStateManager {

    val states = mutableMapOf<UUID, StoneMaskAnimationState>()
    private val maskToPlayer = mutableMapOf<UUID, UUID>()

    fun getActiveMaskForPlayer(playerUuid: UUID): UUID? =
        maskToPlayer.entries.firstOrNull { it.value == playerUuid }?.key

    fun tick(player: ServerPlayerEntity) {
        val stack = player.getEquippedStack(EquipmentSlot.HEAD)
        if (stack.item !is StoneMaskItem) return
        val maskUuid = StoneMaskItem.getMaskUUID(stack) ?: return

        maskToPlayer[maskUuid] = player.uuid

        val state = states.getOrPut(maskUuid) { StoneMaskAnimationState() }
        state.phaseTicks++

        when (state.phase) {
            StoneMaskPhase.AWAKEN -> {
                if (state.phaseTicks >= StoneMaskAnimationState.AWAKEN_DURATION_TICKS) {
                    StoneMaskNetworkHandler.sendAwakenFinished(player, maskUuid)
                    advanceTo(player, maskUuid, state, StoneMaskPhase.PIERCED)
                }
            }
            StoneMaskPhase.PIERCED -> {
                if (state.phaseTicks >= StoneMaskAnimationState.PIERCED_DURATION_TICKS) {
                    advanceTo(player, maskUuid, state, StoneMaskPhase.RETRACT)
                }
            }
            StoneMaskPhase.RETRACT -> {
                if (state.phaseTicks >= StoneMaskAnimationState.RETRACT_DURATION_TICKS) {
                    advanceTo(player, maskUuid, state, StoneMaskPhase.INACTIVE)
                }
            }
            else -> {}
        }
    }

    fun remove(maskUuid: UUID, player: ServerPlayerEntity) {
        if (states.containsKey(maskUuid)) {

            if (states[maskUuid]?.phase != StoneMaskPhase.INACTIVE) {
                StoneMaskNetworkHandler.sendPhaseUpdate(player, maskUuid, StoneMaskPhase.INACTIVE)
            }
            states.remove(maskUuid)
            maskToPlayer.remove(maskUuid)
        }
    }

    fun triggerAwaken(player: ServerPlayerEntity) {
        val stack = player.getEquippedStack(EquipmentSlot.HEAD)
        if (stack.item !is StoneMaskItem) return
        val maskUuid = StoneMaskItem.getMaskUUID(stack) ?: return
        val state = states.getOrPut(maskUuid) { StoneMaskAnimationState() }
        if (state.phase == StoneMaskPhase.INACTIVE) {
            advanceTo(player, maskUuid, state, StoneMaskPhase.AWAKEN)
        }
    }

    private fun advanceTo(
        player: ServerPlayerEntity,
        maskUuid: UUID,
        state: StoneMaskAnimationState,
        next: StoneMaskPhase
    ) {
        state.phase = next
        state.phaseTicks = 0
        StoneMaskNetworkHandler.sendPhaseUpdate(player, maskUuid, next)

        if (next == StoneMaskPhase.INACTIVE) {
            popMaskOff(player)
        }
    }

    private fun popMaskOff(player: ServerPlayerEntity) {
        val stack = player.getEquippedStack(EquipmentSlot.HEAD)
        if (stack.item !is StoneMaskItem) return

        player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY)

        val itemEntity = ItemEntity(
            player.entityWorld,
            player.x,
            player.y + player.standingEyeHeight - 0.3,
            player.z,
            stack
        )
        itemEntity.setPickupDelay(40)
        itemEntity.velocity = Vec3d(
            (player.random.nextDouble() - 0.5) * 0.1,
            0.2,
            (player.random.nextDouble() - 0.5) * 0.1
        )
        player.entityWorld.spawnEntity(itemEntity)
    }
}