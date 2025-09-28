package dev.sterner

import net.minecraft.util.StringRepresentable

enum class StoneMaskAnimationType : StringRepresentable {
    INACTIVE, AWAKEN, PIERCED, RETRACT;

    override fun getSerializedName(): String? {
        return name.lowercase()
    }
}