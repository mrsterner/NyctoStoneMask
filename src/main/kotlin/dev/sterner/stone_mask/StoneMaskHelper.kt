package dev.sterner.stone_mask

import dev.sterner.item.StoneMaskItem
import net.minecraft.item.ItemStack

object StoneMaskHelper {

    fun isStackLocked(stack: ItemStack): Boolean {
        if (stack.item !is StoneMaskItem) return false
        val uuid = StoneMaskItem.getMaskUUID(stack) ?: return false
        return StoneMaskStateManager.states[uuid]?.phase
            .let { it == StoneMaskPhase.AWAKEN || it == StoneMaskPhase.PIERCED }
    }

    fun isHelmetSlot(slot: Int) = slot == 39
    fun isHelmetScreenSlot(slotIndex: Int) = slotIndex == 5
}