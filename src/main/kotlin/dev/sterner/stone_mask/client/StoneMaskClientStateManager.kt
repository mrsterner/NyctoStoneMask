package dev.sterner.stone_mask.client

import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.AnimationState
import java.util.UUID

@Environment(EnvType.CLIENT)
object StoneMaskClientStateManager {

    data class ClientMaskState(
        var phase: StoneMaskPhase = StoneMaskPhase.INACTIVE,
        val animationState: AnimationState = AnimationState()
    )

    private val states = mutableMapOf<UUID, ClientMaskState>()

    fun getOrCreate(maskUuid: UUID): ClientMaskState = states.getOrPut(maskUuid) { ClientMaskState() }

    fun setPhase(maskUuid: UUID, phase: StoneMaskPhase) {
        if (phase == StoneMaskPhase.INACTIVE) {
            states.remove(maskUuid)
            return
        }
        val s = getOrCreate(maskUuid)
        if (s.phase == phase) return
        s.phase = phase
        s.animationState.stop()
        val tickCount = MinecraftClient.getInstance().player?.age ?: 0
        s.animationState.start(tickCount)
    }
}