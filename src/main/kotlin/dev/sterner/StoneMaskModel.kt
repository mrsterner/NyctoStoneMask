package dev.sterner

import moriyashiine.nycto.common.Nycto
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.KeyframeAnimations
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.client.renderer.entity.ArmorModelSet
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.world.entity.AnimationState


class StoneMaskModel<T : HumanoidRenderState>(root: ModelPart) : HumanoidModel<T>(root) {

    private val armorHead: ModelPart = root.getChild("armorHead")
    private val lPlate: ModelPart = armorHead.getChild("lPlate")
    private val blood: ModelPart = lPlate.getChild("blood")
    private val rPlate: ModelPart = armorHead.getChild("rPlate")

    private val lClaw02: ModelPart = armorHead.getChild("lClaw02")
    private val rClaw02: ModelPart = armorHead.getChild("rClaw02")
    private val lClaw01: ModelPart = armorHead.getChild("lClaw01")
    private val rClaw01: ModelPart = armorHead.getChild("rClaw01")
    private val lClaw03: ModelPart = armorHead.getChild("lClaw03")
    private val rClaw03: ModelPart = armorHead.getChild("rClaw03")
    private val lClaw04: ModelPart = armorHead.getChild("lClaw04")
    private val rClaw04: ModelPart = armorHead.getChild("rClaw04")
    private val mClaw: ModelPart = armorHead.getChild("mClaw")
    private val decorations: ModelPart = armorHead.getChild("decorations")

    private val armorBody: ModelPart = root.getChild("armorBody")
    private val armorRightArm: ModelPart = root.getChild("armorRightArm")
    private val armorLeftArm: ModelPart = root.getChild("armorLeftArm")
    private val armorLeftLeg: ModelPart = root.getChild("armorLeftLeg")
    private val armorLeftBoot: ModelPart = root.getChild("armorLeftBoot")
    private val armorRightLeg: ModelPart = root.getChild("armorRightLeg")
    private val armorRightBoot: ModelPart = root.getChild("armorRightBoot")



    companion object {

        val MODEL_LAYERS: ArmorModelSet<ModelLayerLocation> = ArmorModelSet(
            "helmet",
            "chestplate",
            "leggings",
            "boots"
        ).map({ s -> ModelLayerLocation(Nycto.id("stone_mask"), s) })


        fun createBodyLayer(): LayerDefinition {
            val meshDefinition = createMesh(CubeDeformation.NONE, 0.0f)
            val partDefinition = meshDefinition.root

            val armorHead = partDefinition.addOrReplaceChild(
                "armorHead",
                CubeListBuilder.create(),
                PartPose.offset(0f, 0f, 0f)
            )

            val lPlate = armorHead.addOrReplaceChild(
                "lPlate",
                CubeListBuilder.create()
                    .texOffs(96, 6)
                    .mirror()
                    .addBox(-0.95f, -8.25f, -5.0f, 5f, 6f, 2f, CubeDeformation(-0.1f))
                    .mirror(false),
                PartPose.offsetAndRotation(0.25f, 0.25f, -0.25f, -0.0436f, -0.1309f, 0f)
            )

            lPlate.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create()
                    .texOffs(96, 16)
                    .mirror()
                    .addBox(-1.2f, -1.5f, -1.25f, 4f, 3f, 2f, CubeDeformation(0.1f))
                    .mirror(false),
                PartPose.offsetAndRotation(0.45f, -1.1539f, -3.1819f, 0.2618f, 0f, 0f)
            )

            val blood = lPlate.addOrReplaceChild(
                "blood",
                CubeListBuilder.create()
                    .texOffs(112, 14)
                    .mirror()
                    .addBox(-0.95f, -8.25f, -5.0f, 5f, 6f, 2f, CubeDeformation(-0.05f))
                    .mirror(false),
                PartPose.offset(0f, 0f, 0f)
            )

            blood.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create()
                    .texOffs(116, 24)
                    .mirror()
                    .addBox(-1.2f, -1.5f, -1.5f, 4f, 3f, 2f, CubeDeformation(-0.05f))
                    .mirror(false),
                PartPose.offsetAndRotation(0.25f, -1.2747f, -3.1741f, 0.2618f, 0f, 0f)
            )

            val rPlate = armorHead.addOrReplaceChild(
                "rPlate",
                CubeListBuilder.create()
                    .texOffs(96, 6)
                    .addBox(-4.05f, -8.25f, -5.0f, 5f, 6f, 2f, CubeDeformation(-0.1f)),
                PartPose.offsetAndRotation(-0.25f, 0.25f, -0.25f, -0.0436f, 0.1309f, 0f)
            )

            val cube_r3: PartDefinition? = rPlate.addOrReplaceChild(
                "cube_r3",
                CubeListBuilder.create().texOffs(96, 16)
                    .addBox(-2.8f, -1.5f, -1.25f, 4.0f, 3.0f, 2.0f, CubeDeformation(0.1f)),
                PartPose.offsetAndRotation(-0.45f, -1.1539f, -3.1819f, 0.2618f, 0.0f, 0.0f)
            )

            val lClaw02 = armorHead.addOrReplaceChild(
                "lClaw02",
                CubeListBuilder.create().texOffs(96, 41)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -3.5f, -2.75f)
            )

            val rClaw02 = armorHead.addOrReplaceChild(
                "rClaw02",
                CubeListBuilder.create().texOffs(96, 41).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -3.5f, -2.75f)
            )

            val lClaw01 = armorHead.addOrReplaceChild(
                "lClaw01",
                CubeListBuilder.create().texOffs(95, 27)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -1.0f, -3.75f)
            )

            val rClaw01 = armorHead.addOrReplaceChild(
                "rClaw01",
                CubeListBuilder.create().texOffs(95, 27).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 8.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -1.0f, -3.75f)
            )

            val lClaw03 = armorHead.addOrReplaceChild(
                "lClaw03",
                CubeListBuilder.create().texOffs(96, 41)
                    .addBox(-3.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(4.0f, -6.0f, -2.75f)
            )

            val rClaw03 = armorHead.addOrReplaceChild(
                "rClaw03",
                CubeListBuilder.create().texOffs(96, 41).mirror()
                    .addBox(-0.5f, -1.0f, 0.0f, 4.0f, 1.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-4.0f, -6.0f, -2.75f)
            )

            val lClaw04 = armorHead.addOrReplaceChild(
                "lClaw04",
                CubeListBuilder.create().texOffs(99, 53)
                    .addBox(-1.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(2.5f, -8.0f, -2.75f)
            )

            val rClaw04 = armorHead.addOrReplaceChild(
                "rClaw04",
                CubeListBuilder.create().texOffs(99, 53).mirror()
                    .addBox(0.0f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)).mirror(false),
                PartPose.offset(-2.5f, -8.0f, -2.75f)
            )

            val mClaw = armorHead.addOrReplaceChild(
                "mClaw",
                CubeListBuilder.create().texOffs(99, 53)
                    .addBox(-0.5f, -0.5f, -0.01f, 1.0f, 4.0f, 7.0f, CubeDeformation(-0.1f)),
                PartPose.offset(0.0f, -8.0f, -2.75f)
            )

            val decorations =
                armorHead.addOrReplaceChild("decorations", CubeListBuilder.create(), PartPose.offset(0.0f, 0.0f, 0.0f))

            val cube_r4 = decorations.addOrReplaceChild(
                "cube_r4",
                CubeListBuilder.create().texOffs(112, 0)
                    .addBox(-1.0f, -6.0f, -1.25f, 2.0f, 7.0f, 3.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, -4.0f, -5.0f, -0.3054f, 0.0f, 0.0f)
            )

            val cube_r5 = decorations.addOrReplaceChild(
                "cube_r5",
                CubeListBuilder.create().texOffs(82, 25)
                    .addBox(-4.25f, -7.5f, -7.25f, 4.0f, 4.0f, 1.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, -0.3054f, 0.1309f, 0.0f)
            )
            return LayerDefinition.create(meshDefinition, 128, 128)
        }
    }



}