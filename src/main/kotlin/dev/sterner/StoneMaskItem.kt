package dev.sterner

import moriyashiine.nycto.api.NyctoAPI
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

        if (entity is PlayerEntity) {
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