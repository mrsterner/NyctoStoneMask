package dev.sterner.network

import dev.sterner.network.payload.StoneMaskAnimationPayload
import dev.sterner.network.payload.StoneMaskAwakenAckPayload
import dev.sterner.network.payload.StoneMaskAwakenFinishedPayload
import dev.sterner.network.payload.StoneMaskPhasePayload
import dev.sterner.stone_mask.client.StoneMaskClientStateManager
import dev.sterner.stone_mask.StoneMaskPhase
import moriyashiine.nycto.common.init.ModStatusEffects
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.server.network.ServerPlayerEntity
import java.util.UUID

object StoneMaskNetworkHandler {

    fun registerCommon() {
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAnimationPayload.Companion.ID,
            StoneMaskAnimationPayload.Companion.CODEC
        )
        PayloadTypeRegistry.playS2C().register(
            StoneMaskPhasePayload.Companion.ID,
            StoneMaskPhasePayload.Companion.CODEC
        )
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAwakenFinishedPayload.Companion.ID,
            StoneMaskAwakenFinishedPayload.Companion.CODEC
        )
        PayloadTypeRegistry.playC2S().register(
            StoneMaskAwakenAckPayload.Companion.ID,
            StoneMaskAwakenAckPayload.Companion.CODEC
        )

        ServerPlayNetworking.registerGlobalReceiver(StoneMaskAwakenAckPayload.Companion.ID) { payload, context ->
            val player: ServerPlayerEntity = context.player()

        }
    }

    fun sendPhaseUpdate(wearer: ServerPlayerEntity, maskUuid: UUID, phase: StoneMaskPhase) {
        val payload = StoneMaskPhasePayload(maskUuid, phase)
        wearer.entityWorld.players.forEach { p ->
            ServerPlayNetworking.send(p, payload)
        }
    }

    fun sendAwakenFinished(wearer: ServerPlayerEntity, maskUuid: UUID) {
        ServerPlayNetworking.send(wearer, StoneMaskAwakenFinishedPayload(maskUuid))
    }

    @Environment(EnvType.CLIENT)
    fun registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAnimationPayload.Companion.ID) { payload, context ->
            context.client().execute {
                val level = MinecraftClient.getInstance().world ?: return@execute
                level.getPlayerByUuid(payload.maskUuid) ?: return@execute
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(StoneMaskPhasePayload.Companion.ID) { payload, context ->
            context.client().execute {
                StoneMaskClientStateManager.setPhase(payload.maskUuid, payload.phase)
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAwakenFinishedPayload.Companion.ID) { payload, context ->
            context.client().execute {
                ClientPlayNetworking.send(StoneMaskAwakenAckPayload(payload.maskUuid))
            }
        }
    }
}