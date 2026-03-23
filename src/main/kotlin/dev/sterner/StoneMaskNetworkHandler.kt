package dev.sterner

import dev.sterner.payload.StoneMaskAnimationPayload
import dev.sterner.payload.StoneMaskAwakenAckPayload
import dev.sterner.payload.StoneMaskAwakenFinishedPayload
import dev.sterner.payload.StoneMaskPhasePayload
import dev.sterner.stone_mask.StoneMaskClientStateManager
import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskNetworkHandler {

    fun registerCommon() {
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAnimationPayload.ID,
            StoneMaskAnimationPayload.CODEC
        )
        PayloadTypeRegistry.playS2C().register(
            StoneMaskPhasePayload.ID,
            StoneMaskPhasePayload.CODEC
        )
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAwakenFinishedPayload.ID,
            StoneMaskAwakenFinishedPayload.CODEC
        )
        PayloadTypeRegistry.playC2S().register(
            StoneMaskAwakenAckPayload.ID,
            StoneMaskAwakenAckPayload.CODEC
        )

        ServerPlayNetworking.registerGlobalReceiver(StoneMaskAwakenAckPayload.ID) { payload, context ->
            val player: ServerPlayerEntity = context.player()

        }
    }

    fun sendPhaseUpdate(wearer: ServerPlayerEntity, phase: StoneMaskPhase) {
        val payload = StoneMaskPhasePayload(wearer.uuid, phase)
        wearer.entityWorld.players.forEach { p ->
            ServerPlayNetworking.send(p, payload)
        }
    }

    fun sendAwakenFinished(wearer: ServerPlayerEntity) {
        ServerPlayNetworking.send(wearer, StoneMaskAwakenFinishedPayload(wearer.uuid))
    }

    @Environment(EnvType.CLIENT)
    fun registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAnimationPayload.ID) { payload, context ->
            context.client().execute {
                val level = MinecraftClient.getInstance().world ?: return@execute
                level.getPlayerByUuid(payload.playerUuid) ?: return@execute
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(StoneMaskPhasePayload.ID) { payload, context ->
            context.client().execute {
                StoneMaskClientStateManager.setPhase(payload.playerUuid, payload.phase)
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAwakenFinishedPayload.ID) { payload, context ->
            context.client().execute {
                ClientPlayNetworking.send(StoneMaskAwakenAckPayload(payload.playerUuid))
            }
        }
    }
}