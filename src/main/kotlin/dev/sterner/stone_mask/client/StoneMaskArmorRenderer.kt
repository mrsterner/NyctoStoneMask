package dev.sterner.stone_mask.client

import dev.sterner.NyctoStoneMask
import dev.sterner.item.StoneMaskItem
import dev.sterner.mixin.AnimationAccessor
import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.command.RenderCommandQueue
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class StoneMaskArmorRenderer(
    var armorModel: StoneMaskModel<BipedEntityRenderState>
) : ArmorRenderer {

    private val TEXTURE: Identifier = NyctoStoneMask.id("textures/armor/stone_mask.png")

    private val inactiveAnim: Animation
    private val awakenAnim: Animation
    private val piercedAnim: Animation
    private val retractAnim: Animation

    constructor(context: EntityRendererFactory.Context, slot: EquipmentSlot) : this(
        StoneMaskModel(context.getPart(StoneMaskModel.MODEL_LAYERS.getModelData(slot)))
    )

    init {
        val defs = StoneMaskAnimation()
        val root = armorModel.rootPart
        inactiveAnim = AnimationAccessor.of(root, defs.inactive)
        awakenAnim   = AnimationAccessor.of(root, defs.awaken)
        piercedAnim  = AnimationAccessor.of(root, defs.pierced)
        retractAnim  = AnimationAccessor.of(root, defs.retract)
    }

    override fun render(
        poseStack: MatrixStack,
        submitNodeCollector: OrderedRenderCommandQueue,
        itemStack: ItemStack,
        humanoidRenderState: BipedEntityRenderState,
        equipmentSlot: EquipmentSlot,
        light: Int,
        humanoidModel: BipedEntityModel<BipedEntityRenderState>
    ) {
        if (equipmentSlot != EquipmentSlot.HEAD) return

        val maskUuid = StoneMaskItem.getMaskUUID(itemStack)

        if (maskUuid == null) {
            armorModel.animationApplier = { inactiveAnim.applyStatic() }
            submitModel(humanoidModel, humanoidRenderState, poseStack, submitNodeCollector, light)
            return
        }

        val clientState = StoneMaskClientStateManager.getOrCreate(maskUuid)
        val age = humanoidRenderState.age

        if (clientState.phase != StoneMaskPhase.INACTIVE && !clientState.animationState.isRunning) {
            clientState.animationState.start(age.toInt())
        }

        armorModel.animationApplier = {
            when (clientState.phase) {
                StoneMaskPhase.INACTIVE -> inactiveAnim.applyStatic()
                StoneMaskPhase.AWAKEN   -> awakenAnim.apply(clientState.animationState, age)
                StoneMaskPhase.PIERCED  -> piercedAnim.apply(clientState.animationState, age)
                StoneMaskPhase.RETRACT  -> retractAnim.apply(clientState.animationState, age)
            }
        }

        submitModel(humanoidModel, humanoidRenderState, poseStack, submitNodeCollector, light)
    }

    private fun submitModel(
        humanoidModel: BipedEntityModel<BipedEntityRenderState>,
        humanoidRenderState: BipedEntityRenderState,
        poseStack: MatrixStack,
        submitNodeCollector: OrderedRenderCommandQueue,
        light: Int
    ) {
        val queue: RenderCommandQueue = submitNodeCollector.getBatchingQueue(0)
        ArmorRenderer.submitTransformCopyingModel(
            humanoidModel, humanoidRenderState, armorModel, humanoidRenderState,
            true, queue, poseStack, RenderLayers.armorCutoutNoCull(TEXTURE),
            light, OverlayTexture.DEFAULT_UV, humanoidRenderState.outlineColor, null
        )
    }
}