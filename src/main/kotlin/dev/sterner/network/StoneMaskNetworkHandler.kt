package dev.sterner.network

import dev.sterner.network.payload.StoneMaskPhasePayload
import dev.sterner.stone_mask.client.StoneMaskClientStateManager
import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.network.ServerPlayerEntity
import java.util.UUID

object StoneMaskNetworkHandler {

    fun registerCommon() {
        PayloadTypeRegistry.playS2C().register(StoneMaskPhasePayload.ID, StoneMaskPhasePayload.CODEC)
    }

    fun sendPhaseUpdate(wearer: ServerPlayerEntity, maskUuid: UUID, phase: StoneMaskPhase) {
        val payload = StoneMaskPhasePayload(maskUuid, phase)
        wearer.entityWorld.players.forEach { ServerPlayNetworking.send(it, payload) }
    }

    @Environment(EnvType.CLIENT)
    fun registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(StoneMaskPhasePayload.ID) { payload, context ->
            context.client().execute {
                StoneMaskClientStateManager.setPhase(payload.maskUuid, payload.phase)
            }
        }
    }
}