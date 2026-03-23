package dev.sterner

import moriyashiine.nycto.common.init.ModArmorMaterials
import moriyashiine.strawberrylib.api.module.SLibRegistries.registerItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType


class ModItems {

    var GROUP: CreativeModeTab? = null

    companion object {
        public val STONE_MASK: Item =
            registerItem("stone_mask", Item.Properties().humanoidArmor(ModArmorMaterials.VAMPIRE, ArmorType.HELMET))

    }


}