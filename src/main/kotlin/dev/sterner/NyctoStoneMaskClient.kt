package dev.sterner

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.entity.EquipmentSlot

object NyctoStoneMaskClient : ClientModInitializer {

	override fun onInitializeClient() {

		StoneMaskNetworkHandler.registerClient()

		EntityModelLayerRegistry.registerModelLayer(StoneMaskModel.MODEL_LAYERS.getModelData(EquipmentSlot.HEAD)) { StoneMaskModel.createBodyLayer() }
		ArmorRenderer.register(
			{ context ->
				StoneMaskArmorRenderer(context, EquipmentSlot.HEAD)
			},
			ModItems.STONE_MASK
		)
	}
}

