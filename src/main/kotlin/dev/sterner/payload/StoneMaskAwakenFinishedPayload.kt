package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import java.util.UUID


data class StoneMaskAwakenFinishedPayload(
    val playerUuid: UUID
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<StoneMaskAwakenFinishedPayload>(
            Identifier.of(NyctoStoneMask.MODID, "stone_mask_awaken_finished")
        )
        val CODEC: PacketCodec<ByteBuf, StoneMaskAwakenFinishedPayload> = object : PacketCodec<ByteBuf, StoneMaskAwakenFinishedPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAwakenFinishedPayload(PacketByteBuf(buffer).readUuid())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAwakenFinishedPayload) {
                PacketByteBuf(buffer).writeUuid(payload.playerUuid)
            }
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?>? {
        return ID
    }
}