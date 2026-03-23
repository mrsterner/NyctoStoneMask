package dev.sterner

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.UUIDUtil
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.world.item.CreativeModeTabs
import java.util.UUID
import java.util.function.UnaryOperator

object NyctoStoneMask : ModInitializer {
	const val MODID = "nycto-stone-mask"


	private fun <T : Any> register(
		name: String,
		configure: (DataComponentType.Builder<T>) -> DataComponentType.Builder<T>
	): DataComponentType<T> {
		val builder = DataComponentType.builder<T>()
		val built = configure(builder).build()
		return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, name, built)
	}

	val UUID_DATA: DataComponentType<UUID> = register(
		"uuid_data"
	) { builder ->
		builder.persistent(UUIDUtil.CODEC)
	}

	override fun onInitialize() {

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
			entries.accept(ModItems.STONE_MASK)
		}
		StoneMaskEvents.register()
		StoneMaskNetworkHandler.registerCommon()
	}

	fun id(string: String): Identifier {
		return Identifier.fromNamespaceAndPath(MODID, string)
	}
}
