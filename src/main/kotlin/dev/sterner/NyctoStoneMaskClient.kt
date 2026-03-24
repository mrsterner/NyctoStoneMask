package dev.sterner

import dev.sterner.NyctoStoneMask.id
import dev.sterner.network.StoneMaskNetworkHandler
import dev.sterner.registry.NSMItems
import dev.sterner.stone_mask.client.StoneMaskArmorRenderer
import dev.sterner.stone_mask.client.StoneMaskItemRenderer.Unbaked.Companion.CODEC
import dev.sterner.stone_mask.client.StoneMaskModel
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.render.item.model.special.SpecialModelTypes
import net.minecraft.entity.EquipmentSlot


object NyctoStoneMaskClient : ClientModInitializer {

	override fun onInitializeClient() {

		StoneMaskNetworkHandler.registerClient()
		SpecialModelTypes.ID_MAPPER.put(
			id("stone_mask"),
			CODEC
		)
		//SpecialModelTypes.ID_MAPPER.put(NyctoStoneMask.id("stone_mask"), StoneMaskItemRenderer.Unbaked.CODEC)
		//println("### Keys: ${SpecialModelTypes.ID_MAPPER.entries.map { "${it.key} -> ${it.value}" }} ###")

		EntityModelLayerRegistry.registerModelLayer(StoneMaskModel.MODEL_LAYERS.getModelData(EquipmentSlot.HEAD)) { StoneMaskModel.createBodyLayer() }
		ArmorRenderer.register(
			{ context ->
				StoneMaskArmorRenderer(context, EquipmentSlot.HEAD)
			},
			NSMItems.STONE_MASK
		)
	}
}

