package dev.sterner

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import org.slf4j.LoggerFactory

object NyctoStoneMaskClient : ClientModInitializer {
	private val logger = LoggerFactory.getLogger("nycto-stone-mask")

	override fun onInitializeClient() {
		// Register model layers
		EntityModelLayerRegistry.registerModelLayer(StoneMaskModel.LAYER_LOCATION) {
			StoneMaskModel.createBodyLayer()
		}

		// Register armor renderer
		ArmorRenderer.register(StoneMaskArmorRenderer(), NyctoStoneMask.STONE_MASK)

		// Register client network handlers
		StoneMaskNetworkHandler.registerClient()
	}
}