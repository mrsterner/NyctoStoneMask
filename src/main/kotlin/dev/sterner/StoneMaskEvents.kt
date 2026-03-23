package dev.sterner


import dev.sterner.payload.StoneMaskAwakenAckPayload
import dev.sterner.stone_mask.StoneMaskStateManager
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskEvents {

    fun register() {
        registerTickEvent()
        registerSheepKillEvent()
    }

    private fun registerTickEvent() {
        ServerTickEvents.END_SERVER_TICK.register { server ->
            for (player in server.playerManager.playerList) {
                if (isWearingMask(player)) {
                    StoneMaskStateManager.tick(player)
                } else {
                    StoneMaskStateManager.remove(player)
                }
            }
        }
    }

    private fun registerSheepKillEvent() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register { world, killer, killed, source ->
            if (killed is SheepEntity && killer is ServerPlayerEntity && isWearingMask(killer)) {
                StoneMaskStateManager.triggerAwaken(killer)
            }
        }
    }

    private fun isWearingMask(player: PlayerEntity): Boolean {
        val helmet: ItemStack = player.getEquippedStack(EquipmentSlot.HEAD)
        return helmet.item is StoneMaskItem
    }
}