package dev.sterner.event

import dev.sterner.item.StoneMaskItem
import dev.sterner.registry.NSMItems
import dev.sterner.stone_mask.StoneMaskStateManager
import moriyashiine.nycto.common.tag.ModEntityTypeTags
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.EmptyEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.server.network.ServerPlayerEntity

object StoneMaskEvents {

    fun register() {
        registerTickEvent()
        registerDamageGoodBloodsEvent()
        registerLootInjection()
    }

    private fun registerTickEvent() {
        ServerTickEvents.END_SERVER_TICK.register { server ->
            for (player in server.playerManager.playerList) {
                val stack = player.getEquippedStack(EquipmentSlot.HEAD)
                val maskUuid = (stack.item as? StoneMaskItem)?.let { StoneMaskItem.Companion.getMaskUUID(stack) }

                if (maskUuid != null) {
                    StoneMaskStateManager.tick(player)
                } else {

                    val previousMaskUuid = StoneMaskStateManager.getActiveMaskForPlayer(player.uuid)
                    if (previousMaskUuid != null) {
                        StoneMaskStateManager.remove(previousMaskUuid, player)
                    }
                }
            }
        }
    }

    private fun registerDamageGoodBloodsEvent() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register { living: LivingEntity, source: DamageSource, baseDamageTaken: Float, damageTaken: Float, blocked ->
            val sourceEntity = source.source

            if (sourceEntity is ServerPlayerEntity) {

                if (living.type.isIn(ModEntityTypeTags.HAS_QUALITY_BLOOD)) {

                    StoneMaskStateManager.triggerAwaken(sourceEntity)
                }
            }
        }
    }

    private fun registerLootInjection() {
        val stoneMask = NSMItems.STONE_MASK

        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (!source.isBuiltin) return@register

            val targets = setOf(
                LootTables.JUNGLE_TEMPLE_CHEST
            )

            if (key !in targets) return@register

            val pool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(EmptyEntry.builder().weight(3))
                .with(
                    ItemEntry.builder(stoneMask)
                        .weight(1)
                )
                .build()

            tableBuilder.pool(pool)
        }
    }
}