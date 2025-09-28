package dev.sterner

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import org.slf4j.LoggerFactory

object NyctoStoneMaskClient : ClientModInitializer {

	override fun onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(StoneMaskModel.LAYER_LOCATION) {
			StoneMaskModel.createBodyLayer()
		}
		ArmorRenderer.register(StoneMaskArmorRenderer(), NyctoStoneMask.STONE_MASK)
		StoneMaskNetworkHandler.registerClient()
	}
}