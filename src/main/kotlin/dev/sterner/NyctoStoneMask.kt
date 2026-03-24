package dev.sterner

import dev.sterner.event.StoneMaskEvents
import dev.sterner.network.StoneMaskNetworkHandler
import dev.sterner.registry.NSMComponents
import dev.sterner.registry.NSMItems
import dev.sterner.registry.NSMSounds
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroups
import net.minecraft.util.Identifier

object NyctoStoneMask : ModInitializer {
	const val MODID = "nycto-stone-mask"

	override fun onInitialize() {

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { entries ->
			entries.add(NSMItems.STONE_MASK)
		}
		NSMSounds.init()
		NSMComponents.init()
		StoneMaskNetworkHandler.registerCommon()
		StoneMaskEvents.register()
	}

	fun id(string: String): Identifier {
		return Identifier.of(MODID, string)
	}
}
