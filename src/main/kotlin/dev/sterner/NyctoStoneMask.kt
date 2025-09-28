package dev.sterner

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.equipment.ArmorMaterials
import net.minecraft.world.item.equipment.ArmorType

object NyctoStoneMask : ModInitializer {
	const val MODID = "nycto-stone-mask"

	lateinit var STONE_MASK: Item

	private fun itemId(string: String): ResourceKey<Item?> {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, string))
	}


	override fun onInitialize() {
		// Register item inside onInitialize
		STONE_MASK = Registry.register(
			BuiltInRegistries.ITEM,
			ResourceLocation.fromNamespaceAndPath(MODID, "stone_mask"),
			StoneMaskItem(
				Item.Properties().setId(itemId("stone_mask"))
					.humanoidArmor(ArmorMaterials.IRON, ArmorType.HELMET)
					.stacksTo(1)
					.rarity(Rarity.RARE)
			)
		)

		// Add to creative tab
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
			entries.accept(STONE_MASK)
		}

		StoneMaskNetworkHandler.registerCommon()
	}
}
