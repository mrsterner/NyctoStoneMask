package dev.sterner.registry

import dev.sterner.NyctoStoneMask
import dev.sterner.item.StoneMaskItem
import moriyashiine.nycto.common.init.ModArmorMaterials
import moriyashiine.nycto.common.init.ModItems
import moriyashiine.strawberrylib.impl.common.StrawberryLib
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import java.util.function.Function

class NSMItems {

    companion object {

        val STONE_MASK: StoneMaskItem = registerItem(
            "stone_mask",
            ::StoneMaskItem, Item.Settings()
                .maxCount(1)
                .armor(ModArmorMaterials.VAMPIRE, EquipmentType.HELMET)

        ) as StoneMaskItem

        fun registerItem(name: String, factory: Function<Item.Settings, Item>, settings: Item.Settings): Item {
            return Items.register(RegistryKey.of<Item?>(RegistryKeys.ITEM, NyctoStoneMask.id(name)), factory, settings)
        }
    }
}