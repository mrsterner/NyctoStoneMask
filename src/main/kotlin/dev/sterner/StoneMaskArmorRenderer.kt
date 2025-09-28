package dev.sterner



import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.renderer.entity.state.PlayerRenderState
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

class StoneMaskArmorRenderer : ArmorRenderer {
    companion object {
        private val TEXTURE = ResourceLocation.fromNamespaceAndPath(NyctoStoneMask.MODID, "textures/armor/stone_mask.png")
        private val BLOOD_TEXTURE = ResourceLocation.fromNamespaceAndPath(NyctoStoneMask.MODID, "textures/armor/stone_mask_blood.png")
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
        renderState: HumanoidRenderState,
        slot: EquipmentSlot,
        light: Int,
        contextModel: HumanoidModel<HumanoidRenderState>
    ) {
        if (slot != EquipmentSlot.HEAD || stack.item !is StoneMaskItem) {
            return
        }

        val maskModel = getModel()

        // Copy properties from context model to our mask model
        maskModel.copyFrom(contextModel)



        // Set up the model animation with the render state
        maskModel.setupAnim(renderState)

        // Render the main mask
        val vertexConsumer = ItemRenderer.getArmorFoilBuffer(
            vertexConsumers,
            RenderType.armorCutoutNoCull(TEXTURE),
            false
        )

        maskModel.renderMask(
            matrices,
            vertexConsumer,
            light,
            OverlayTexture.NO_OVERLAY,
            0xFFFFFFFF.toInt() // White color
        )
    }
}