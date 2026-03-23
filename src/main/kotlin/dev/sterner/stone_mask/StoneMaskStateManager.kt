package dev.sterner.stone_mask

import dev.sterner.StoneMaskNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskStateManager {

    private val states = mutableMapOf<java.util.UUID, StoneMaskAnimationState>()

    fun getOrCreate(player: ServerPlayerEntity): StoneMaskAnimationState =
        states.getOrPut(player.uuid) { StoneMaskAnimationState() }

    fun get(player: ServerPlayerEntity): StoneMaskAnimationState? = states[player.uuid]

    fun remove(player: ServerPlayerEntity) = states.remove(player.uuid)

    fun tick(player: ServerPlayerEntity) {
        val state = getOrCreate(player)
        state.phaseTicks++

        when (state.phase) {

            StoneMaskPhase.AWAKEN -> {
                if (state.phaseTicks >= StoneMaskAnimationState.AWAKEN_DURATION_TICKS) {
                    StoneMaskNetworkHandler.sendAwakenFinished(player)
                    advanceTo(player, state, StoneMaskPhase.PIERCED)
                }
            }

            StoneMaskPhase.PIERCED -> {
                if (state.phaseTicks >= StoneMaskAnimationState.PIERCED_DURATION_TICKS) {
                    advanceTo(player, state, StoneMaskPhase.RETRACT)
                }
            }

            StoneMaskPhase.RETRACT -> {
                if (state.phaseTicks >= StoneMaskAnimationState.RETRACT_DURATION_TICKS) {
                    advanceTo(player, state, StoneMaskPhase.INACTIVE)
                }
            }

            else -> { }
        }
    }

    fun triggerAwaken(player: ServerPlayerEntity) {
        val state = getOrCreate(player)
        if (state.phase == StoneMaskPhase.INACTIVE) {
            advanceTo(player, state, StoneMaskPhase.AWAKEN)
        }
    }

    private fun advanceTo(player: ServerPlayerEntity, state: StoneMaskAnimationState, next: StoneMaskPhase) {
        println("Advancing to $next")
        state.phase = next
        state.phaseTicks = 0
        StoneMaskNetworkHandler.sendPhaseUpdate(player, next)
    }
}