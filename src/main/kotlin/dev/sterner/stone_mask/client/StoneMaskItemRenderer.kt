package dev.sterner.stone_mask.client

import com.mojang.serialization.MapCodec
import dev.sterner.NyctoStoneMask
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.model.special.SimpleSpecialModelRenderer
import net.minecraft.client.render.item.model.special.SpecialModelRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemDisplayContext
import org.joml.Vector3fc
import java.util.function.Consumer

class StoneMaskItemRenderer(
    private val model: StoneMaskModel<BipedEntityRenderState>,
    private val inactiveAnim: StoneMaskKeyframeAnimation,
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

        inactiveAnim.apply(0L, 1.0f)

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
        inactiveAnim.apply(0L, 1.0f)
        model.getRootPart().collectVertices(lv, consumer)
    }

    class Unbaked : SpecialModelRenderer.Unbaked {
        override fun bake(context: SpecialModelRenderer.BakeContext): SpecialModelRenderer<*>? {
            val layer = StoneMaskModel.MODEL_LAYERS.getModelData(EquipmentSlot.HEAD)
            val modelPart = context.entityModelSet().getModelPart(layer)
            val model = StoneMaskModel<BipedEntityRenderState>(modelPart)
            val defs = StoneMaskAnimation()
            val inactiveAnim = StoneMaskKeyframeAnimation.bake(model.rootPart, defs.inactive)
            return StoneMaskItemRenderer(model, inactiveAnim)
        }

        override fun getCodec() = CODEC

        companion object {
            val CODEC: MapCodec<Unbaked> = MapCodec.unit(::Unbaked)
        }
    }
}