package dev.sterner.stone_mask

import dev.sterner.item.StoneMaskItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskHelper {

    fun isMaskLocked(player: PlayerEntity): Boolean {
        if (player !is ServerPlayerEntity) return false
        val equipped = player.getEquippedStack(EquipmentSlot.HEAD)
        return isStackLocked(equipped)
    }

    fun isStackLocked(stack: ItemStack): Boolean {
        if (stack.item !is StoneMaskItem) return false
        val uuid = StoneMaskItem.Companion.getMaskUUID(stack) ?: return false
        val state = StoneMaskStateManager.states[uuid] ?: return false
        return state.phase == StoneMaskPhase.AWAKEN || state.phase == StoneMaskPhase.PIERCED
    }

    fun isHelmetSlot(slot: Int): Boolean = slot == 39

    fun isHelmetScreenSlot(slotIndex: Int): Boolean = slotIndex == 5
}