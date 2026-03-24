package dev.sterner.item

import dev.sterner.registry.NSMComponents
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import java.util.UUID

class StoneMaskItem(settings: Settings) : Item(settings) {

    override fun inventoryTick(
        itemStack: ItemStack,
        serverLevel: ServerWorld,
        entity: Entity,
        equipmentSlot: EquipmentSlot?
    ) {
        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot)

        if (equipmentSlot == EquipmentSlot.HEAD && entity is PlayerEntity) {
            if (getMaskUUID(itemStack) == null) {
                itemStack.set(NSMComponents.UUID_DATA, UUID.randomUUID())
            }
        }
    }

    companion object {
        fun getMaskUUID(stack: ItemStack): UUID? = stack.get(NSMComponents.UUID_DATA)
    }
}