package dev.sterner

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.UUID

class StoneMaskItem : Item(Properties()) {

    override fun inventoryTick(
        itemStack: ItemStack,
        serverLevel: ServerLevel,
        entity: Entity,
        equipmentSlot: EquipmentSlot?
    ) {
        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot)

        if (entity is Player) {
            val current = getOwnerUUID(itemStack)
            if (current != entity.uuid) {
                itemStack.set(NyctoStoneMask.UUID_DATA, entity.uuid)
            }
        }
    }

    companion object {
        fun getOwnerUUID(stack: ItemStack): UUID? = stack.get(NyctoStoneMask.UUID_DATA)
    }
}