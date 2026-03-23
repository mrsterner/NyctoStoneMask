package dev.sterner.stone_mask.client

import moriyashiine.nycto.common.Nycto
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EquipmentModelData
import net.minecraft.client.render.entity.state.BipedEntityRenderState


class StoneMaskModel<T : BipedEntityRenderState>(root: ModelPart) : BipedEntityModel<T>(root) {

    private val armorHead: ModelPart = root.getChild("armorHead")

    var animationApplier: (() -> Unit)? = null

    override fun setAngles(bipedEntityRenderState: T) {
        super.setAngles(bipedEntityRenderState)
        armorHead.pitch = Math.toRadians(bipedEntityRenderState.pitch.toDouble()).toFloat()
        armorHead.yaw = Math.toRadians(bipedEntityRenderState.relativeHeadYaw.toDouble()).toFloat()
        animationApplier?.invoke()
    }

    companion object {

        val MODEL_LAYERS: EquipmentModelData<EntityModelLayer> = EquipmentModelData(
            "helmet",
            "chestplate",
            "leggings",
            "boots"
        ).map({ s -> EntityModelLayer(Nycto.id("stone_mask"), s) })

        fun createBodyLayer(): TexturedModelData {
            val meshDefinition = getModelData(Dilation.NONE, 0.0f)
            val partDefinition = meshDefinition.root

            val armorHead = partDefinition.addChild(
                "armorHead",
                ModelPartBuilder.create(),
                ModelTransform.origin(0f, 0f, 0f)
            )

            partDefinition.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE)
            partDefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE)
            partDefinition.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE)
            partDefinition.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE)
            partDefinition.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE)
            partDefinition.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE)

            val lPlate = armorHead.addChild(
                "lPlate",
                ModelPartBuilder.create()
                    .uv(96, 6)
                    .mirrored()
                    .cuboid(-0.95f, -8.25f, -5.0f, 5f, 6f, 2f, Dilation(-0.1f))
                    .mirrored(false),
                ModelTransform.of(0.25f, 0.25f, -0.25f, -0.0436f, -0.1309f, 0f)
            )

            lPlate.addChild(
                "cube_r1",
                ModelPartBuilder.create()
                    .uv(96, 16)
                    .mirrored()
                    .cuboid(-1.2f, -1.5f, -1.25f, 4f, 3f, 2f, Dilation(0.1f))
                    .mirrored(false),
                ModelTransform.of(0.45f, -1.1539f, -3.1819f, 0.2618f, 0f, 0f)
            )

            val blood = lPlate.addChild(
                "blood",
                ModelPartBuilder.create()
                    .uv(112, 14)
                    .mirrored()
                    .cuboid(-0.95f, -8.25f, -5.0f, 5f, 6f, 2f, Dilation(-0.05f))
                    .mirrored(false),
                ModelTransform.origin(0f, 0f, 0f)
            )

            blood.addChild(
                "cube_r2",
                ModelPartBuilder.create()
                    .uv(116, 24)
                    .mirrored()
                    .cuboid(-1.2f, -1.5f, -1.5f, 4f, 3f, 2f, Dilation(-0.05f))
                    .mirrored(false),
                ModelTransform.of(0.25f, -1.2747f, -3.1741f, 0.2618f, 0f, 0f)
            )

            val rPlate = armorHead.addChild(
                "rPlate",
                ModelPartBuilder.create()
                    .uv(96, 6)
                    .cuboid(-4.05f, -8.25f, -5.0f, 5f, 6f, 2f, Dilation(-0.1f)),
                ModelTransform.of(-0.25f, 0.25f, -0.25f, -0.0436f, 0.1309f, 0f)
            )

            rPlate.addChild(
                "cube_r3",
                ModelPartBuilder.create().uv(96, 16)
                    .cuboid(-2.8f, -1.5f, -1.25f, 4.0f, 3.0f, 2.0f, Dilation(0.1f)),
                ModelTransform.of(-0.45f, -1.1539f, -3.1819f, 0.2618f, 0.0f, 0.0f)
            )

            armorHead.addChild(
                "lClaw02",
                ModelPartBuilder.create().uv(96, 41)
                    .cuboid(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, Dilation(-0.1f)),
                ModelTransform.origin(4.0f, -3.5f, -2.75f)
            )

            armorHead.addChild(
                "rClaw02",
                ModelPartBuilder.create().uv(96, 41).mirrored()
                    .cuboid(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, Dilation(-0.1f)).mirrored(false),
                ModelTransform.origin(-4.0f, -3.5f, -2.75f)
            )

            armorHead.addChild(
                "lClaw01",
                ModelPartBuilder.create().uv(95, 27)
                    .cuboid(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, Dilation(-0.1f)),
                ModelTransform.origin(4.0f, -1.0f, -3.75f)
            )

            armorHead.addChild(
                "rClaw01",
                ModelPartBuilder.create().uv(95, 27).mirrored()
                    .cuboid(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, Dilation(-0.1f)).mirrored(false),
                ModelTransform.origin(-4.0f, -1.0f, -3.75f)
            )

            armorHead.addChild(
                "lClaw03",
                ModelPartBuilder.create().uv(96, 41)
                    .cuboid(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, Dilation(-0.1f)),
                ModelTransform.origin(4.0f, -6.0f, -2.75f)
            )

            armorHead.addChild(
                "rClaw03",
                ModelPartBuilder.create().uv(96, 41).mirrored()
                    .cuboid(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, Dilation(-0.1f)).mirrored(false),
                ModelTransform.origin(-4.0f, -6.0f, -2.75f)
            )

            armorHead.addChild(
                "lClaw04",
                ModelPartBuilder.create().uv(99, 53)
                    .cuboid(-1.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, Dilation(-0.1f)),
                ModelTransform.origin(2.5f, -8.0f, -2.75f)
            )

            armorHead.addChild(
                "rClaw04",
                ModelPartBuilder.create().uv(99, 53).mirrored()
                    .cuboid(0.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, Dilation(-0.1f)).mirrored(false),
                ModelTransform.origin(-2.5f, -8.0f, -2.75f)
            )

            armorHead.addChild(
                "mClaw",
                ModelPartBuilder.create().uv(99, 53)
                    .cuboid(-0.5f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, Dilation(-0.1f)),
                ModelTransform.origin(0.0f, -8.0f, -2.75f)
            )

            val decorations =
                armorHead.addChild("decorations", ModelPartBuilder.create(), ModelTransform.origin(0.0f, 0.0f, 0.0f))

            decorations.addChild(
                "cube_r4",
                ModelPartBuilder.create().uv(112, 0)
                    .cuboid(-1.0f, -6.0f, -1.25f, 2.0f, 7.0f, 3.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, -4.0f, -5.0f, -0.3054f, 0.0f, 0.0f)
            )

            decorations.addChild(
                "cube_r5",
                ModelPartBuilder.create().uv(82, 25)
                    .cuboid(-4.25f, -7.5f, -7.25f, 4.0f, 4.0f, 1.0f, Dilation(0.0f)),
                ModelTransform.of(0.0f, 0.0f, 0.0f, -0.3054f, 0.1309f, 0.0f)
            )

            return TexturedModelData.of(meshDefinition, 128, 128)
        }
    }
}