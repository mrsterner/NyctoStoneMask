package dev.sterner

import moriyashiine.nycto.mixin.power.vampire.batform.client.ClientPlayerEntityMixin
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer

object NyctoStoneMaskClient : ClientModInitializer {

	override fun onInitializeClient() {

		StoneMaskNetworkHandler.registerClient()

		ArmorRenderer.register(
			{ poseStack, submitNodeCollector, itemStack, humanoidRenderState, equipmentSlot, light, humanoidModel ->
				StoneMaskRendererHolder.renderer?.render(
					poseStack, submitNodeCollector, itemStack,
					humanoidRenderState, equipmentSlot, light, humanoidModel
				)
			},
			ModItems.STONE_MASK
		)
	}
}

