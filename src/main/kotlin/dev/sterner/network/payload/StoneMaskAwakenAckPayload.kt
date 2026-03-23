package dev.sterner.network.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import java.util.UUID


data class StoneMaskAwakenAckPayload(
    val maskUuid: UUID
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<StoneMaskAwakenAckPayload>(
            Identifier.of(NyctoStoneMask.MODID, "stone_mask_awaken_ack")
        )
        val CODEC: PacketCodec<ByteBuf, StoneMaskAwakenAckPayload> = object : PacketCodec<ByteBuf, StoneMaskAwakenAckPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAwakenAckPayload(PacketByteBuf(buffer).readUuid())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAwakenAckPayload) {
                PacketByteBuf(buffer).writeUuid(payload.maskUuid)
            }
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?>? {
       return ID;
    }
}