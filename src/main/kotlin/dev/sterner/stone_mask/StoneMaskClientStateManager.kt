package dev.sterner.stone_mask

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.world.entity.AnimationState
import java.util.UUID

@Environment(EnvType.CLIENT)
object StoneMaskClientStateManager {

    data class ClientMaskState(
        var phase: StoneMaskPhase = StoneMaskPhase.INACTIVE,
        val animationState: AnimationState = AnimationState()
    )

    private val states = mutableMapOf<UUID, ClientMaskState>()

    fun getOrCreate(uuid: UUID): ClientMaskState = states.getOrPut(uuid) { ClientMaskState() }

    fun setPhase(uuid: UUID, phase: StoneMaskPhase) {
        val s = getOrCreate(uuid)
        if (s.phase == phase) return
        s.phase = phase
        s.animationState.stop()
        if (phase != StoneMaskPhase.INACTIVE) {
            val tickCount = net.minecraft.client.Minecraft.getInstance().player?.tickCount ?: 0
            s.animationState.start(tickCount)
        }
    }
}