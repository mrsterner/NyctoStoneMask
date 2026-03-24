package dev.sterner.stone_mask

data class StoneMaskAnimationState(
    var phase: StoneMaskPhase = StoneMaskPhase.INACTIVE,
    var phaseTicks: Int = 0
) {
    companion object {
        const val PIERCED_DURATION_TICKS = 400
        const val AWAKEN_DURATION_TICKS = 32
        const val RETRACT_DURATION_TICKS = 20
    }
}