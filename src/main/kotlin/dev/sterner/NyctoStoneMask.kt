package dev.sterner

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.OutgoingChatMessage
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.equipment.ArmorMaterials
import net.minecraft.world.item.equipment.ArmorType
import java.util.*

object NyctoStoneMask : ModInitializer {
	const val MODID = "nycto-stone-mask"

	lateinit var STONE_MASK: Item

	private fun <T> register(
		name: String,
		unaryOperator: (DataComponentType.Builder<T>) -> DataComponentType.Builder<T>
	): DataComponentType<T> {
		val builder = unaryOperator(DataComponentType.builder<T>())
		return Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			name,
			builder.build()
		)
	}

	private fun itemId(string: String): ResourceKey<Item> {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, string))
	}

	val MASK_STATE = register("mask_state") { builder ->
		builder.persistent(MaskData.CODEC)
			.networkSynchronized(MaskData.STREAM_CODEC)
			.cacheEncoding()
	}

	@JvmRecord
	data class MaskData(
		val maskState: StoneMaskAnimationType = StoneMaskAnimationType.INACTIVE
	) {
		companion object {

			val CODEC: Codec<MaskData> = RecordCodecBuilder.create { instance ->
				instance.group(
					StringRepresentable.fromEnum { StoneMaskAnimationType.entries.toTypedArray() }
						.fieldOf("maskState")
						.forGetter { it.maskState }
				).apply(instance, ::MaskData)
			}
			val STREAM_CODEC = StreamCodec.of(
				{ buf: FriendlyByteBuf, data: MaskData ->
					buf.writeVarInt(data.maskState.ordinal)
				},
				{ buf: FriendlyByteBuf ->
					val ordinal = buf.readVarInt()
					MaskData(StoneMaskAnimationType.entries[ordinal])
				}
			)
		}
	}


	override fun onInitialize() {
		STONE_MASK = Registry.register(
			BuiltInRegistries.ITEM,
			ResourceLocation.fromNamespaceAndPath(MODID, "stone_mask"),
			StoneMaskItem(
				Item.Properties()
					.setId(itemId("stone_mask"))
					.humanoidArmor(ArmorMaterials.IRON, ArmorType.HELMET)
					.stacksTo(1)
					.rarity(Rarity.RARE)
			)
		)

		UseBlockCallback.EVENT.register { player, level, hand, blockHit ->
			val stack = player.getItemBySlot(EquipmentSlot.HEAD)

			if (stack.`is`(STONE_MASK)) {
				if (!level.isClientSide && player is ServerPlayer) {
					val state = stack.getOrDefault(MASK_STATE, MaskData())
					val currentOrdinal = state.maskState.ordinal
					val nextOrdinal = (currentOrdinal + 1) % StoneMaskAnimationType.values().size
					val nextAnimation = StoneMaskAnimationType.values()[nextOrdinal]

					ServerPlayNetworking.send(player, StoneMaskAnimationPayload(player.uuid, nextAnimation))

					stack.set(MASK_STATE, state.copy(maskState = nextAnimation))

					player.displayClientMessage(
						Component.literal("Mask State: ${nextAnimation.name}"),
						true
					)
				}
			}



			return@register InteractionResult.PASS
		}

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
			entries.accept(STONE_MASK)
		}

		StoneMaskNetworkHandler.registerCommon()
	}
}
