package dev.sterner

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.resources.ResourceLocation

class StoneMaskModel(root: ModelPart) : HumanoidModel<HumanoidRenderState>(root) {

    val armorHead: ModelPart = root.getChild("armorHead")
    private val mask: ModelPart = armorHead.getChild("mask")
    private val lPlate: ModelPart = mask.getChild("lPlate")
    val blood: ModelPart = lPlate.getChild("blood")
    private val rPlate: ModelPart = mask.getChild("rPlate")
    private val decorations: ModelPart = mask.getChild("decorations")
    private val claws: ModelPart = armorHead.getChild("claws")
    val rClaw04: ModelPart = claws.getChild("rClaw04")
    val rClaw03: ModelPart = claws.getChild("rClaw03")
    val lClaw04: ModelPart = claws.getChild("lClaw04")
    val lClaw03: ModelPart = claws.getChild("lClaw03")
    val rClaw01: ModelPart = claws.getChild("rClaw01")
    val lClaw01: ModelPart = claws.getChild("lClaw01")
    val rClaw02: ModelPart = claws.getChild("rClaw02")
    val lClaw02: ModelPart = claws.getChild("lClaw02")
    val mClaw: ModelPart = claws.getChild("mClaw")

    // Animation state
    private var animationState = StoneMaskAnimationState()
    private var currentAnimationTick = 0f

    init {
        // Hide all default humanoid parts since we're only rendering the mask
        head.visible = false
        hat.visible = false
        body.visible = false
        leftArm.visible = false
        rightArm.visible = false
        leftLeg.visible = false
        rightLeg.visible = false
    }

    companion object {
        val LAYER_LOCATION: ModelLayerLocation = ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(NyctoStoneMask.MODID, "stone_mask"), "main")

        fun createBodyLayer(): LayerDefinition {
            val meshDefinition = createMesh(CubeDeformation.NONE, 0.0f)
            val partdefinition = meshDefinition.root

            // Add required humanoid parts (they will be hidden)
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO)
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO)

            val hat = partdefinition.addOrReplaceChild(
                "hat",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val armorHead = partdefinition.addOrReplaceChild(
                "armorHead",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val mask = armorHead.addOrReplaceChild("mask", CubeListBuilder.create(), PartPose.offset(0.0f, 0.0f, 0.0f))

            val lPlate = mask.addOrReplaceChild(
                "lPlate",
                CubeListBuilder.create().texOffs(0, 17).mirror()
                    .addBox(-0.95f, -8.25f, -5.0f, 5.0f, 6.0f, 2.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offsetAndRotation(0.25f, 0.25f, -0.25f, -0.0436f, -0.1309f, 0.0f)
            )

            val cube_r1 = lPlate.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create().texOffs(24, 21).mirror()
                    .addBox(-1.2f, -1.5f, -1.25f, 4.0f, 3.0f, 2.0f, CubeDeformation(0.1f)).mirror(false),
                PartPose.offsetAndRotation(0.45f, -1.1539f, -3.1819f, 0.2618f, 0.0f, 0.0f)
            )

            val blood = lPlate.addOrReplaceChild(
                "blood",
                CubeListBuilder.create().texOffs(16, 0)
                    .addBox(-0.95f, -8.25f, -5.0f, 5.0f, 6.0f, 2.0f, CubeDeformation(-0.05f)),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )

            val cube_r2 = blood.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create().texOffs(24, 8)
                    .addBox(-1.2f, -1.5f, -1.5f, 4.0f, 3.0f, 2.0f, CubeDeformation(-0.05f)),
                PartPose.offsetAndRotation(0.25f, -1.2747f, -3.1741f, 0.2618f, 0.0f, 0.0f)
            )

            val rPlate = mask.addOrReplaceChild(
                "rPlate",
                CubeListBuilder.create().texOffs(0, 17)
                    .addBox(-4.05f, -8.25f, -5.0f, 5.0f, 6.0f, 2.0f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(-0.25f, 0.25f, -0.25f, -0.0436f, 0.1309f, 0.0f)
            )

            val cube_r3 = rPlate.addOrReplaceChild(
                "cube_r3",
                CubeListBuilder.create().texOffs(24, 21)
                    .addBox(-2.8f, -1.5f, -1.25f, 4.0f, 3.0f, 2.0f, CubeDeformation(0.1f)),
                PartPose.offsetAndRotation(-0.45f, -1.1539f, -3.1819f, 0.2618f, 0.0f, 0.0f)
            )

            val decorations = mask.addOrReplaceChild("decorations", CubeListBuilder.create(), PartPose.offset(0.0f, 0.0f, 0.0f))

            val cube_r4 = decorations.addOrReplaceChild(
                "cube_r4",
                CubeListBuilder.create().texOffs(14, 21)
                    .addBox(-1.0f, -6.0f, -1.25f, 2.0f, 7.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -4.0f, -5.0f, -0.3054f, 0.0f, 0.0f)
            )

            val cube_r5 = decorations.addOrReplaceChild(
                "cube_r5",
                CubeListBuilder.create().texOffs(0, 25)
                    .addBox(-4.25f, -7.5f, -7.25f, 4.0f, 4.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.3054f, 0.1309f, 0.0f)
            )

            val claws = armorHead.addOrReplaceChild("claws", CubeListBuilder.create(), PartPose.offset(0.0f, 0.0f, 0.0f))

            val rClaw04 = claws.addOrReplaceChild(
                "rClaw04",
                CubeListBuilder.create().texOffs(15, 10).mirror()
                    .addBox(0.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-2.5f, -8.0f, -2.75f)
            )

            val rClaw03 = claws.addOrReplaceChild(
                "rClaw03",
                CubeListBuilder.create().texOffs(0, 9).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -6.0f, -2.75f)
            )

            val lClaw04 = claws.addOrReplaceChild(
                "lClaw04",
                CubeListBuilder.create().texOffs(15, 10)
                    .addBox(-1.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(2.5f, -8.0f, -2.75f)
            )

            val lClaw03 = claws.addOrReplaceChild(
                "lClaw03",
                CubeListBuilder.create().texOffs(0, 9)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -6.0f, -2.75f)
            )

            val rClaw01 = claws.addOrReplaceChild(
                "rClaw01",
                CubeListBuilder.create().texOffs(0, 0).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -1.0f, -3.75f)
            )

            val lClaw01 = claws.addOrReplaceChild(
                "lClaw01",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -1.0f, -3.75f)
            )

            val rClaw02 = claws.addOrReplaceChild(
                "rClaw02",
                CubeListBuilder.create().texOffs(0, 9).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -3.5f, -2.75f)
            )

            val lClaw02 = claws.addOrReplaceChild(
                "lClaw02",
                CubeListBuilder.create().texOffs(0, 9)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -3.5f, -2.75f)
            )

            val mClaw = claws.addOrReplaceChild(
                "mClaw",
                CubeListBuilder.create().texOffs(15, 10)
                    .addBox(-0.5f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(0.0f, -8.0f, -2.75f)
            )

            return LayerDefinition.create(meshDefinition, 64, 64)
        }
    }

    fun playAnimation(type: StoneMaskAnimationState.AnimationType) {
        animationState.startAnimation(type)
    }

    override fun setupAnim(state: HumanoidRenderState) {
        super.setupAnim(state)

        // Apply head rotation to armor
        armorHead.xRot = state.xRot * (Math.PI.toFloat() / 180f)
        armorHead.yRot = state.yRot * (Math.PI.toFloat() / 180f)

        // Update animation tick
        currentAnimationTick = state.ageInTicks

        // Apply animations with the render state
        animationState.animate(this, 0f)
    }

    fun copyFrom(source: HumanoidModel<HumanoidRenderState>) {
        this.head.copyFrom(source.head)
        this.hat.copyFrom(source.hat)
        this.body.copyFrom(source.body)
        this.rightArm.copyFrom(source.rightArm)
        this.leftArm.copyFrom(source.leftArm)
        this.rightLeg.copyFrom(source.rightLeg)
        this.leftLeg.copyFrom(source.leftLeg)
    }

    fun renderMask(poseStack: PoseStack, buffer: VertexConsumer, packedLight: Int, packedOverlay: Int, color: Int) {
        armorHead.render(poseStack, buffer, packedLight, packedOverlay, color)
    }
}




/*
package com.yourmod.item

import com.yourmod.network.StoneMaskAnimationPayload
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Item
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.level.Level
import net.minecraft.server.level.ServerPlayer
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking

class StoneMaskItem : ArmorItem(
    StoneMaskArmorMaterial.INSTANCE,
    Type.HELMET,
    Properties().stacksTo(1).durability(500)
) {

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)

        // Check if player is wearing the mask
        if (player.getItemBySlot(EquipmentSlot.HEAD).item == this) {
            // Trigger animation
            if (!level.isClientSide && player is ServerPlayer) {
                val animationType = if (stack.orCreateTag.getBoolean("activated")) {
                    StoneMaskAnimationType.RETRACT
                } else {
                    StoneMaskAnimationType.AWAKEN
                }

                // Send animation packet to all tracking clients
                ServerPlayNetworking.send(player, StoneMaskAnimationPayload(player.uuid, animationType))

                // Toggle activation state
                stack.orCreateTag.putBoolean("activated", !stack.orCreateTag.getBoolean("activated"))
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
        }

        return super.use(level, player, hand)
    }

    override fun getEquipmentSlot(stack: ItemStack): EquipmentSlot {
        return EquipmentSlot.HEAD
    }

    override fun inventoryTick(stack: ItemStack, level: Level, entity: Entity, slotId: Int, isSelected: Boolean) {
        super.inventoryTick(stack, level, entity, slotId, isSelected)

        if (entity is Player && entity.getItemBySlot(EquipmentSlot.HEAD) == stack) {
            val activated = stack.orCreateTag.getBoolean("activated")

            if (activated) {
                // Apply effects when mask is activated
                // You can add vampire-like effects here
            }
        }
    }
}

enum class StoneMaskAnimationType {
    INACTIVE, AWAKEN, PIERCED, RETRACT
}

// Custom armor material for the Stone Mask
object StoneMaskArmorMaterial : ArmorMaterial {
    override fun getDurabilityForType(type: ArmorItem.Type): Int = when(type) {
        ArmorItem.Type.HELMET -> 300
        else -> 0
    }

    override fun getDefenseForType(type: ArmorItem.Type): Int = when(type) {
        ArmorItem.Type.HELMET -> 3
        else -> 0
    }

    override fun getEnchantmentValue(): Int = 15

    override fun getEquipSound(): SoundEvent = SoundEvents.ARMOR_EQUIP_IRON

    override fun getRepairIngredient(): Ingredient = Ingredient.of(Items.IRON_INGOT)

    override fun getName(): String = "stone_mask"

    override fun getToughness(): Float = 2.0.0

    override fun getKnockbackResistance(): Float = 0.1f
}
 */

/*
package com.yourmod.client.renderer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.yourmod.client.model.StoneMaskModel
import com.yourmod.item.StoneMaskItem
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

class StoneMaskArmorRenderer : ArmorRenderer {
    companion object {
        private val TEXTURE = ResourceLocation("yourmod", "textures/armor/stone_mask.png")
        private val BLOOD_TEXTURE = ResourceLocation("yourmod", "textures/armor/stone_mask_blood.png")
        private var model: StoneMaskModel? = null

        fun getModel(): StoneMaskModel {
            if (model == null) {
                val minecraft = Minecraft.getInstance()
                val modelPart = minecraft.entityModels.bakeLayer(StoneMaskModel.LAYER_LOCATION)
                model = StoneMaskModel(modelPart)
            }
            return model!!
        }
    }

    override fun render(
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        stack: ItemStack,
        entity: LivingEntity,
        slot: EquipmentSlot,
        light: Int,
        contextModel: HumanoidModel<LivingEntity>
    ) {
        if (slot != EquipmentSlot.HEAD || stack.item !is StoneMaskItem) {
            return
        }

        val maskModel = getModel()

        // Copy humanoid model properties
        contextModel.copyPropertiesTo(maskModel)

        // Setup animation based on item NBT
        val activated = stack.orCreateTag.getBoolean("activated")
        if (activated) {
            // The animation state is managed client-side through network packets
        }

        // Set up the model for rendering
        maskModel.setupAnim(entity, 0.0, 0.0, entity.tickCount.toFloat(), entity.yHeadRot, entity.xRot)

        // Render the main mask
        val vertexConsumer = ItemRenderer.getArmorFoilBuffer(
            vertexConsumers,
            RenderType.armorCutoutNoCull(TEXTURE),
            false,
            stack.hasFoil()
        )

        maskModel.renderToBuffer(
            matrices,
            vertexConsumer,
            light,
            OverlayTexture.NO_OVERLAY,
            1.0.0, 1.0.0, 1.0.0, 1.0.0
        )

        // Render blood overlay if activated
        if (activated) {
            val bloodConsumer = ItemRenderer.getArmorFoilBuffer(
                vertexConsumers,
                RenderType.armorCutoutNoCull(BLOOD_TEXTURE),
                false,
                false
            )

            // Only render the blood part
            maskModel.blood.render(
                matrices,
                bloodConsumer,
                light,
                OverlayTexture.NO_OVERLAY,
                1.0.0, 0.0.0, 0.0.0, 0.8f
            )
        }
    }
}
 */

/*
package com.yourmod.network

import com.yourmod.client.animation.StoneMaskAnimationState
import com.yourmod.item.StoneMaskAnimationType
import io.netty.buffer.ByteBuf
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import java.util.UUID

// Animation Payload
data class StoneMaskAnimationPayload(
    val playerUuid: UUID,
    val animationType: StoneMaskAnimationType
) : CustomPacketPayload {

    companion object {
        val ID = CustomPacketPayload.Type<StoneMaskAnimationPayload>(
            ResourceLocation("yourmod", "stone_mask_animation")
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

// Network Handler
object StoneMaskNetworkHandler {

    fun registerCommon() {
        // Register the payload types
        PayloadTypeRegistry.playS2C().register(
            StoneMaskAnimationPayload.ID,
            StoneMaskAnimationPayload.CODEC
        )
    }

    @Environment(EnvType.CLIENT)
    fun registerClient() {
        // Handle animation packets on client
        ClientPlayNetworking.registerGlobalReceiver(StoneMaskAnimationPayload.ID) { payload, context ->
            context.client().execute {
                val minecraft = Minecraft.getInstance()
                val level = minecraft.level ?: return@execute
                val player = level.getPlayerByUUID(payload.playerUuid) ?: return@execute

                // Store animation state for the player
                StoneMaskClientAnimationManager.setAnimation(
                    player,
                    payload.animationType
                )
            }
        }
    }
}

// Client-side animation manager
@Environment(EnvType.CLIENT)
object StoneMaskClientAnimationManager {
    private val playerAnimations = mutableMapOf<UUID, AnimationData>()

    data class AnimationData(
        val type: StoneMaskAnimationType,
        val startTime: Long
    )

    fun setAnimation(player: Player, type: StoneMaskAnimationType) {
        playerAnimations[player.uuid] = AnimationData(type, System.currentTimeMillis())

        // Play sound effects based on animation type
        when (type) {
            StoneMaskAnimationType.AWAKEN -> {
                player.level().playLocalSound(
                    player.x, player.y, player.z,
                    SoundEvents.ARMOR_EQUIP_IRON, // Replace with custom sound
                    SoundSource.PLAYERS,
                    1.0.0, 1.0.0,
                    false
                )
            }
            StoneMaskAnimationType.RETRACT -> {
                player.level().playLocalSound(
                    player.x, player.y, player.z,
                    SoundEvents.ARMOR_EQUIP_CHAIN, // Replace with custom sound
                    SoundSource.PLAYERS,
                    1.0.0, 0.8f,
                    false
                )
            }
            else -> {}
        }
    }

    fun getAnimation(player: Player): AnimationData? {
        return playerAnimations[player.uuid]
    }

    fun clearOldAnimations() {
        val currentTime = System.currentTimeMillis()
        playerAnimations.entries.removeIf {
            currentTime - it.value.startTime > 5000 // Remove animations older than 5 seconds
        }
    }
}
 */

/*
package com.yourmod

import com.yourmod.client.model.StoneMaskModel
import com.yourmod.client.renderer.StoneMaskArmorRenderer
import com.yourmod.item.StoneMaskItem
import com.yourmod.network.StoneMaskNetworkHandler
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item

object StoneMaskMod : ModInitializer {
    const val MOD_ID = "yourmod"

    // Items
    lateinit var STONE_MASK: Item

    override fun onInitialize() {
        // Register items
        STONE_MASK = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation(MOD_ID, "stone_mask"),
            StoneMaskItem()
        )

        // Add to creative tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register { entries ->
            entries.accept(STONE_MASK)
        }

        // Register network handlers
        StoneMaskNetworkHandler.registerCommon()
    }
}

object StoneMaskClientMod : ClientModInitializer {
    override fun onInitializeClient() {
        // Register model layers
        EntityModelLayerRegistry.registerModelLayer(StoneMaskModel.LAYER_LOCATION) {
            StoneMaskModel.createBodyLayer()
        }

        // Register armor renderer
        ArmorRenderer.register(StoneMaskArmorRenderer(), StoneMaskMod.STONE_MASK)

        // Register client network handlers
        StoneMaskNetworkHandler.registerClient()
    }
}

// fabric.mod.json additions needed:
/*
{
  "schemaVersion": 1,
  "id": "yourmod",
  "version": "1.0.0",
  "name": "Stone Mask Mod",
  "description": "Adds the Stone Mask with animations",
  "authors": ["YourName"],
  "contact": {},
  "license": "MIT",
  "icon": "assets/yourmod/icon.png",
  "environment": "*",
  "entrypoints": {
    "main": ["com.yourmod.StoneMaskMod"],
    "client": ["com.yourmod.StoneMaskClientMod"]
  },
  "depends": {
    "fabricloader": ">=0.15.0",
    "fabric": "*",
    "minecraft": "1.21.x",
    "fabric-language-kotlin": "*"
  }
}
*/

// build.gradle.kts additions needed:
/*
dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings("net.fabricmc:yarn:1.21.1+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.11")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.2+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.11.0+kotlin.2.0.0")
}
*/
 */