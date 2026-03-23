package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import dev.sterner.stone_mask.StoneMaskPhase
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import java.util.UUID


data class StoneMaskPhasePayload(
    val playerUuid: UUID,
    val phase: StoneMaskPhase
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<StoneMaskPhasePayload>(
            Identifier.of(NyctoStoneMask.MODID, "stone_mask_phase")
        )
        val CODEC: PacketCodec<ByteBuf, StoneMaskPhasePayload> = object : PacketCodec<ByteBuf, StoneMaskPhasePayload> {
            override fun decode(buffer: ByteBuf): StoneMaskPhasePayload {
                val buf = PacketByteBuf(buffer)
                val uuid = buf.readUuid()
                val phase = StoneMaskPhase.entries[buf.readVarInt()]
                return StoneMaskPhasePayload(uuid, phase)
            }
            override fun encode(buffer: ByteBuf, payload: StoneMaskPhasePayload) {
                val buf = PacketByteBuf(buffer)
                buf.writeUuid(payload.playerUuid)
                buf.writeVarInt(payload.phase.ordinal)
            }
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?>? {
        return ID
    }
}