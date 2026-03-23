package dev.sterner

import com.mojang.blaze3d.vertex.PoseStack
import dev.sterner.stone_mask.StoneMaskClientStateManager
import dev.sterner.stone_mask.StoneMaskKeyframeAnimation
import dev.sterner.stone_mask.StoneMaskPhase
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.OrderedSubmitNodeCollector
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import java.util.UUID

class StoneMaskArmorRenderer(
    var armorModel: StoneMaskModel<HumanoidRenderState>
) : ArmorRenderer {

    private val TEXTURE: Identifier = NyctoStoneMask.id("textures/entity/equipment/vampire_armor.png")

    // Baked animations — created once after the model is constructed
    private val inactiveAnim: StoneMaskKeyframeAnimation
    private val awakenAnim: StoneMaskKeyframeAnimation
    private val piercedAnim: StoneMaskKeyframeAnimation
    private val retractAnim: StoneMaskKeyframeAnimation

    constructor(context: EntityRendererProvider.Context, slot: EquipmentSlot) : this(
        StoneMaskModel(context.bakeLayer(StoneMaskModel.MODEL_LAYERS.get(slot)))
    )

    init {
        val defs = StoneMaskAnimation()
        val root = armorModel.root()
        inactiveAnim = StoneMaskKeyframeAnimation.bake(root, defs.inactive)
        awakenAnim   = StoneMaskKeyframeAnimation.bake(root, defs.awaken)
        piercedAnim  = StoneMaskKeyframeAnimation.bake(root, defs.pierced)
        retractAnim  = StoneMaskKeyframeAnimation.bake(root, defs.retract)
    }

    override fun render(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        itemStack: ItemStack,
        humanoidRenderState: HumanoidRenderState,
        equipmentSlot: EquipmentSlot,
        light: Int,
        humanoidModel: HumanoidModel<HumanoidRenderState>
    ) {
        if (equipmentSlot != EquipmentSlot.HEAD) return

        val uuid: UUID = StoneMaskItem.getOwnerUUID(itemStack) ?: return
        val clientState = StoneMaskClientStateManager.getOrCreate(uuid)
        val age = humanoidRenderState.ageInTicks

        val anim = when (clientState.phase) {
            StoneMaskPhase.INACTIVE -> inactiveAnim
            StoneMaskPhase.AWAKEN   -> awakenAnim
            StoneMaskPhase.PIERCED  -> piercedAnim
            StoneMaskPhase.RETRACT  -> retractAnim
        }

        if (clientState.phase == StoneMaskPhase.INACTIVE) {
            anim.applyStatic()
        } else {
            anim.apply(clientState.animationState, age)
        }

        val queue: OrderedSubmitNodeCollector = submitNodeCollector.order(0)
        ArmorRenderer.submitTransformCopyingModel(
            humanoidModel,
            humanoidRenderState,
            armorModel,
            humanoidRenderState,
            true,
            queue,
            poseStack,
            RenderTypes.armorCutoutNoCull(TEXTURE),
            light,
            OverlayTexture.NO_OVERLAY,
            humanoidRenderState.outlineColor,
            null
        )
    }
}