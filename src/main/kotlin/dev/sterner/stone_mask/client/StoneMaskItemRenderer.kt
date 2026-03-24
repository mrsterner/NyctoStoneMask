package dev.sterner.stone_mask.client

import com.mojang.serialization.MapCodec
import dev.sterner.NyctoStoneMask
import dev.sterner.item.StoneMaskItem
import dev.sterner.stone_mask.StoneMaskPhase
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.model.special.SimpleSpecialModelRenderer
import net.minecraft.client.render.item.model.special.SpecialModelRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemDisplayContext
import net.minecraft.item.ItemStack
import org.joml.Vector3fc
import java.util.function.Consumer

class StoneMaskItemRenderer(
    private val model: StoneMaskModel<BipedEntityRenderState>,
    private val inactiveAnim: StoneMaskKeyframeAnimation,
    private val awakenAnim: StoneMaskKeyframeAnimation,
    private val piercedAnim: StoneMaskKeyframeAnimation,
    private val retractAnim: StoneMaskKeyframeAnimation,
) : SimpleSpecialModelRenderer {

    private val TEXTURE = NyctoStoneMask.id("textures/armor/stone_mask.png")

    override fun render(
        displayContext: ItemDisplayContext?,
        matrices: MatrixStack?,
        queue: OrderedRenderCommandQueue?,
        light: Int,
        overlay: Int,
        glint: Boolean,
        i: Int
    ) {
        matrices ?: return
        queue ?: return

        matrices.push()

        model.resetTransforms()
        val dummyState = BipedEntityRenderState()
        model.setAngles(dummyState)

        applyCurrentAnimation()

        queue.submitModelPart(
            model.rootPart,
            matrices,
            RenderLayers.armorCutoutNoCull(TEXTURE),
            light,
            OverlayTexture.DEFAULT_UV,
            null,
            false,
            glint,
            -1,
            null,
            i
        )

        matrices.pop()
    }

    override fun collectVertices(consumer: Consumer<Vector3fc?>?) {
        val lv = MatrixStack()
        model.resetTransforms()
        applyCurrentAnimation()
        model.getRootPart().collectVertices(lv, consumer)
    }

    private fun applyCurrentAnimation() {
        val client = MinecraftClient.getInstance()
        val player = client.player
        val equippedStack: ItemStack? = player?.getEquippedStack(EquipmentSlot.HEAD)
        val uuid = equippedStack?.let { StoneMaskItem.getMaskUUID(it) }

        if (uuid != null) {
            val clientState = StoneMaskClientStateManager.getOrCreate(uuid)
            val age = player?.age?.toFloat() ?: 0f

            when (clientState.phase) {
                StoneMaskPhase.INACTIVE -> inactiveAnim.apply(0L, 1.0f)
                StoneMaskPhase.AWAKEN   -> awakenAnim.apply(clientState.animationState, age)
                StoneMaskPhase.PIERCED  -> piercedAnim.apply(clientState.animationState, age)
                StoneMaskPhase.RETRACT  -> retractAnim.apply(clientState.animationState, age)
            }
        } else {
            inactiveAnim.apply(0L, 1.0f)
        }
    }

    class Unbaked : SpecialModelRenderer.Unbaked {
        override fun bake(context: SpecialModelRenderer.BakeContext): SpecialModelRenderer<*>? {
            val layer = StoneMaskModel.MODEL_LAYERS.getModelData(EquipmentSlot.HEAD)
            val modelPart = context.entityModelSet().getModelPart(layer)
            val model = StoneMaskModel<BipedEntityRenderState>(modelPart)
            val defs = StoneMaskAnimation()
            val root = model.rootPart
            return StoneMaskItemRenderer(
                model,
                inactiveAnim = StoneMaskKeyframeAnimation.bake(root, defs.inactive),
                awakenAnim   = StoneMaskKeyframeAnimation.bake(root, defs.awaken),
                piercedAnim  = StoneMaskKeyframeAnimation.bake(root, defs.pierced),
                retractAnim  = StoneMaskKeyframeAnimation.bake(root, defs.retract),
            )
        }

        override fun getCodec() = CODEC

        companion object {
            val CODEC: MapCodec<Unbaked> = MapCodec.unit(::Unbaked)
        }
    }
}