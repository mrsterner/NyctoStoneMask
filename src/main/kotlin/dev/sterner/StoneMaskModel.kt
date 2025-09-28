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

    private var animationState = StoneMaskAnimationState()
    private var currentAnimationTick = 0f

    init {
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

    fun playAnimation(type: StoneMaskAnimationType) {
        animationState.startAnimation(type)
    }

    override fun setupAnim(state: HumanoidRenderState) {
        super.setupAnim(state)

        armorHead.xRot = state.xRot * (Math.PI.toFloat() / 180f)
        armorHead.yRot = state.yRot * (Math.PI.toFloat() / 180f)

        currentAnimationTick = state.ageInTicks

        animationState.animate(this)
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