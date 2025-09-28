package dev.sterner

import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import java.util.UUID

data class StoneMaskAnimationPayload(
    val playerUuid: UUID,
    val animationType: StoneMaskAnimationType
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskAnimationPayload>(
            ResourceLocation.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask_animation")
        )

        val CODEC: StreamCodec<ByteBuf, StoneMaskAnimationPayload> = object : StreamCodec<ByteBuf, StoneMaskAnimationPayload> {
            override fun decode(buffer: ByteBuf): StoneMaskAnimationPayload {
                val buf = FriendlyByteBuf(buffer)
                val uuid = buf.readUUID()
                val animType = StoneMaskAnimationType.values()[buf.readVarInt()]
                return StoneMaskAnimationPayload(uuid, animType)
            }

            override fun encode(buffer: ByteBuf, payload: StoneMaskAnimationPayload) {
                val buf = FriendlyByteBuf(buffer)
                buf.writeUUID(payload.playerUuid)
                buf.writeVarInt(payload.animationType.ordinal)
            }
        }
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = ID
}