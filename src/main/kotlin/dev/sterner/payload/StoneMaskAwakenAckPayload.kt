package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import java.util.UUID


data class StoneMaskAwakenAckPayload(
    val playerUuid: UUID
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskAwakenAckPayload>(
            Identifier.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask_awaken_ack")
        )
        val CODEC: StreamCodec<ByteBuf, StoneMaskAwakenAckPayload> = object : StreamCodec<ByteBuf, StoneMaskAwakenAckPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAwakenAckPayload(FriendlyByteBuf(buffer).readUUID())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAwakenAckPayload) {
                FriendlyByteBuf(buffer).writeUUID(payload.playerUuid)
            }
        }
    }

    override fun type() = ID
}