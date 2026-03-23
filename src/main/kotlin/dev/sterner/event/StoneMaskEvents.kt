package dev.sterner.event

import dev.sterner.item.StoneMaskItem
import dev.sterner.stone_mask.StoneMaskStateManager
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskEvents {

    fun register() {
        registerTickEvent()
        registerSheepKillEvent()
    }

    private fun registerTickEvent() {
        ServerTickEvents.END_SERVER_TICK.register { server ->
            for (player in server.playerManager.playerList) {
                val stack = player.getEquippedStack(EquipmentSlot.HEAD)
                val maskUuid = (stack.item as? StoneMaskItem)?.let { StoneMaskItem.Companion.getMaskUUID(stack) }

                if (maskUuid != null) {
                    StoneMaskStateManager.tick(player)
                } else {

                    val previousMaskUuid = StoneMaskStateManager.getActiveMaskForPlayer(player.uuid)
                    if (previousMaskUuid != null) {
                        StoneMaskStateManager.remove(previousMaskUuid, player)
                    }
                }
            }
        }
    }

    private fun registerSheepKillEvent() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register { _, killer, killed, _ ->
            if (killed is SheepEntity && killer is ServerPlayerEntity) {
                StoneMaskStateManager.triggerAwaken(killer)
            }
        }
    }
}