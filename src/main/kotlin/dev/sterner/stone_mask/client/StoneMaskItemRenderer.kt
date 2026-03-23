package dev.sterner.mask.client

import com.mojang.serialization.MapCodec
import dev.sterner.NyctoStoneMask
import dev.sterner.stone_mask.client.StoneMaskAnimation
import dev.sterner.stone_mask.client.StoneMaskKeyframeAnimation
import dev.sterner.stone_mask.client.StoneMaskModel
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.command.OrderedRenderCommandQueue
import net.minecraft.client.render.entity.state.BipedEntityRenderState
import net.minecraft.client.render.item.model.special.SimpleSpecialModelRenderer
import net.minecraft.client.render.item.model.special.SpecialModelRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.component.ComponentMap
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemDisplayContext
import net.minecraft.item.ItemStack
import org.jetbrains.annotations.Nullable
import org.joml.Vector3fc
import java.util.function.Consumer

class StoneMaskItemRenderer(
    private val model: StoneMaskModel<BipedEntityRenderState>,
    private val inactiveAnim: StoneMaskKeyframeAnimation
) : SpecialModelRenderer<ItemStack> {

    private val TEXTURE = NyctoStoneMask.id("textures/armor/stone_mask.png")

    override fun render(
        stack: ItemStack?,
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

        inactiveAnim.applyStatic()

        val dummyState = BipedEntityRenderState()
        model.setAngles(dummyState)

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
        lv.translate(0.5f, 0.0f, 0.5f)
        lv.scale(-1.0f, -1.0f, 1.0f)
        this.model.getRootPart().collectVertices(lv, consumer);
    }

    override fun getData(stack: ItemStack?): ItemStack? {
        return stack
    }

    class Unbaked : SpecialModelRenderer.Unbaked {
        override fun bake(context: SpecialModelRenderer.BakeContext): SpecialModelRenderer<*>? {
            val modelPart = context.entityModelSet()
                .getModelPart(
                    StoneMaskModel.MODEL_LAYERS.getModelData(EquipmentSlot.HEAD)
                )
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