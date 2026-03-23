package dev.sterner

import moriyashiine.nycto.common.init.ModArmorMaterials
import moriyashiine.strawberrylib.api.module.SLibRegistries.registerItem
import moriyashiine.strawberrylib.impl.common.StrawberryLib
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys


class ModItems {


    companion object {

        val STONE_MASK: StoneMaskItem = registerItem(
            "stone_mask",
            ::StoneMaskItem,
            Item.Settings()
                .armor(
                    ModArmorMaterials.VAMPIRE, EquipmentType.HELMET)
                .maxCount(1)
        ) as StoneMaskItem
    }


}