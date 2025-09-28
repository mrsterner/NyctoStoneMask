package dev.sterner

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.client.Minecraft

object StoneMaskNetworkHandler {

    fun registerCommon() {
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAnimationPayload.ID,
            StoneMaskAnimationPayload.CODEC
        )
    }

    @Environment(EnvType.CLIENT)
    fun registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAnimationPayload.ID) { payload, context ->
            context.client().execute {
                val minecraft = Minecraft.getInstance()
                val level = minecraft.level ?: return@execute
                val player = level.getPlayerByUUID(payload.playerUuid) ?: return@execute

                StoneMaskClientAnimationManager.setAnimation(
                    player,
                    payload.animationType
                )
            }
        }
    }
}