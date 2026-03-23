package dev.sterner.payload

import dev.sterner.NyctoStoneMask
import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import java.util.UUID

data class StoneMaskAnimationPayload(
    val playerUuid: UUID
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskAnimationPayload>(
            Identifier.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask_animation")
        )
        val CODEC: StreamCodec<ByteBuf, StoneMaskAnimationPayload> = object : StreamCodec<ByteBuf, StoneMaskAnimationPayload> {
            override fun decode(buffer: ByteBuf) = StoneMaskAnimationPayload(FriendlyByteBuf(buffer).readUUID())
            override fun encode(buffer: ByteBuf, payload: StoneMaskAnimationPayload) {
                FriendlyByteBuf(buffer).writeUUID(payload.playerUuid)
            }
        }
    }

    override fun type() = ID
}