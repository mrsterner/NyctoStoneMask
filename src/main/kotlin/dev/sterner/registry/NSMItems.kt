package dev.sterner.registry

import dev.sterner.NyctoStoneMask
import dev.sterner.item.StoneMaskItem
import moriyashiine.nycto.common.init.ModArmorMaterials
import moriyashiine.strawberrylib.api.module.SLibRegistries
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.equipment.EquipmentType

class NSMItems {

    companion object {

        val STONE_MASK: StoneMaskItem = SLibRegistries.registerItem(
            "stone_mask",
            ::StoneMaskItem,
            Item.Settings()
                .armor(
                    ModArmorMaterials.VAMPIRE, EquipmentType.HELMET
                )
                .maxCount(1)
                .component(DataComponentTypes.ITEM_MODEL, NyctoStoneMask.id("stone_mask"))
        ) as StoneMaskItem
    }
}