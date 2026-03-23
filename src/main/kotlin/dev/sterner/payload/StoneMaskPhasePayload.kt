package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import dev.sterner.stone_mask.StoneMaskPhase
import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import java.util.UUID


data class StoneMaskPhasePayload(
    val playerUuid: UUID,
    val phase: StoneMaskPhase
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskPhasePayload>(
            Identifier.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask_phase")
        )
        val CODEC: StreamCodec<ByteBuf, StoneMaskPhasePayload> = object : StreamCodec<ByteBuf, StoneMaskPhasePayload> {
            override fun decode(buffer: ByteBuf): StoneMaskPhasePayload {
                val buf = FriendlyByteBuf(buffer)
                val uuid = buf.readUUID()
                val phase = StoneMaskPhase.entries[buf.readVarInt()]
                return StoneMaskPhasePayload(uuid, phase)
            }
            override fun encode(buffer: ByteBuf, payload: StoneMaskPhasePayload) {
                val buf = FriendlyByteBuf(buffer)
                buf.writeUUID(payload.playerUuid)
                buf.writeVarInt(payload.phase.ordinal)
            }
        }
    }

    override fun type() = ID
}