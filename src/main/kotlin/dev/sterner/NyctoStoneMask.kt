package dev.sterner

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.component.ComponentType
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.Uuids
import java.util.UUID
import java.util.function.UnaryOperator

object NyctoStoneMask : ModInitializer {
	const val MODID = "nycto-stone-mask"


	private fun <T : Any> register(
		name: String,
		configure: (ComponentType.Builder<T>) -> ComponentType.Builder<T>
	): ComponentType<T> {
		val builder = ComponentType.builder<T>()
		val built = configure(builder).build()
		return Registry.register(Registries.DATA_COMPONENT_TYPE, name, built)
	}

	val UUID_DATA: ComponentType<UUID> = register(
		"uuid_data"
	) { builder ->
		builder.codec(Uuids.CODEC)
	}

	override fun onInitialize() {

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { entries ->
			entries.add(ModItems.STONE_MASK)
		}
		StoneMaskNetworkHandler.registerCommon()
		StoneMaskEvents.register()
	}

	fun id(string: String): Identifier {
		return Identifier.of(MODID, string)
	}
}
