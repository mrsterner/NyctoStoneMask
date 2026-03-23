package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import java.util.UUID


data class StoneMaskAwakenFinishedPayload(
    val playerUuid: UUID
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskAwakenFinishedPayload>(
            Identifier.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask_awaken_finished")
        )
        val CODEC: StreamCodec<ByteBuf, StoneMaskAwakenFinishedPayload> = object : StreamCodec<ByteBuf, StoneMaskAwakenFinishedPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAwakenFinishedPayload(FriendlyByteBuf(buffer).readUUID())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAwakenFinishedPayload) {
                FriendlyByteBuf(buffer).writeUUID(payload.playerUuid)
            }
        }
    }

    override fun type() = ID
}