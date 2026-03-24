package dev.sterner.registry

import dev.sterner.NyctoStoneMask
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent

object NSMSounds {

    val STONE_MASK_ACTIVATES: SoundEvent = register("stone_mask.activates")
    val STONE_MASK_CLAWS: SoundEvent     = register("stone_mask.claws")
    val STONE_MASK_PIERCE: SoundEvent    = register("stone_mask.pierce")
    val STONE_MASK_RETRACT: SoundEvent   = register("stone_mask.retract")

    private fun register(name: String): SoundEvent {
        val id = NyctoStoneMask.id(name)
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id))
    }

    fun init() {

    }
}