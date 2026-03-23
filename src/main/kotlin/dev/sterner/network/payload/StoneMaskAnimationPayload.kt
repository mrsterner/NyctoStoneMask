package dev.sterner.network.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier
import java.util.UUID

data class StoneMaskAnimationPayload(
    val maskUuid: UUID
) : CustomPayload {

    companion object {
        val ID = CustomPayload.Id<StoneMaskAnimationPayload>(
            Identifier.of(NyctoStoneMask.MODID, "stone_mask_animation")
        )
        val CODEC: PacketCodec<ByteBuf, StoneMaskAnimationPayload> = object : PacketCodec<ByteBuf, StoneMaskAnimationPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAnimationPayload(PacketByteBuf(buffer).readUuid())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAnimationPayload) {
                PacketByteBuf(buffer).writeUuid(payload.maskUuid)
            }
        }
    }

    override fun getId(): CustomPayload.Id<out CustomPayload?>? {
        return ID
    }
}