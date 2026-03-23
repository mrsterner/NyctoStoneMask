package dev.sterner

import dev.sterner.stone_mask.StoneMaskClientStateManager
import dev.sterner.stone_mask.StoneMaskKeyframeAnimation
import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.command.RenderCommandQueue
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import java.util.UUID

class StoneMaskArmorRenderer(
    var armorModel: StoneMaskModel<BipedEntityRenderState>
) : ArmorRenderer {

    private val TEXTURE: Identifier = NyctoStoneMask.id("textures/armor/stone_mask.png")

    private val inactiveAnim: StoneMaskKeyframeAnimation
    private val awakenAnim: StoneMaskKeyframeAnimation
    private val piercedAnim: StoneMaskKeyframeAnimation
    private val retractAnim: StoneMaskKeyframeAnimation

    constructor(context: EntityRendererFactory.Context, slot: EquipmentSlot) : this(
        StoneMaskModel(context.getPart(StoneMaskModel.MODEL_LAYERS.getModelData(slot)))
    )

    init {
        val defs = StoneMaskAnimation()
        val root = armorModel.rootPart
        inactiveAnim = StoneMaskKeyframeAnimation.bake(root, defs.inactive)
        awakenAnim   = StoneMaskKeyframeAnimation.bake(root, defs.awaken)
        piercedAnim  = StoneMaskKeyframeAnimation.bake(root, defs.pierced)
        retractAnim  = StoneMaskKeyframeAnimation.bake(root, defs.retract)
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

        val uuid: UUID = StoneMaskItem.getOwnerUUID(itemStack) ?: return
        val clientState = StoneMaskClientStateManager.getOrCreate(uuid)
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

        val queue: RenderCommandQueue = submitNodeCollector.getBatchingQueue(0)
        ArmorRenderer.submitTransformCopyingModel(
            humanoidModel,
            humanoidRenderState,
            armorModel,
            humanoidRenderState,
            true,
            queue,
            poseStack,
            RenderLayers.armorCutoutNoCull(TEXTURE),
            light,
            OverlayTexture.DEFAULT_UV,
            humanoidRenderState.outlineColor,
            null
        )
    }
}