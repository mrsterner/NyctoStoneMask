package dev.sterner.registry

import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Uuids
import java.util.UUID

object NSMComponents {

    private fun <T : Any> register(
        name: String,
        configure: (ComponentType.Builder<T>) -> ComponentType.Builder<T>
    ): ComponentType<T> {
        val builder = ComponentType.builder<T>()
        val built = configure(builder).build()
        return Registry.register(Registries.DATA_COMPONENT_TYPE, name, built)
    }

    val UUID_DATA: ComponentType<UUID> = register(
        "uuid_data"
    ) { builder ->
        builder.codec(Uuids.CODEC)
    }
}