package dev.sterner


import dev.sterner.payload.StoneMaskAwakenAckPayload
import dev.sterner.stone_mask.StoneMaskStateManager
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.animal.sheep.Sheep

object StoneMaskEvents {

    fun register() {
        registerTickEvent()
        registerSheepKillEvent()
        registerAwakenAckHandler()
    }

    private fun registerTickEvent() {
        ServerTickEvents.END_SERVER_TICK.register { server ->
            for (player in server.playerList.players) {
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
            if (killed is Sheep && killer is ServerPlayer && isWearingMask(killer)) {
                StoneMaskStateManager.triggerAwaken(killer)
            }
        }
    }

    private fun registerAwakenAckHandler() {
        ServerPlayNetworking.registerGlobalReceiver(StoneMaskAwakenAckPayload.ID) { payload, context ->
            val player = context.player()

        }
    }

    private fun isWearingMask(player: Player): Boolean {
        val helmet: ItemStack = player.getItemBySlot(EquipmentSlot.HEAD)
        return helmet.item is StoneMaskItem
    }
}