package dev.sterner.stone_mask.client

import net.minecraft.client.render.entity.animation.AnimationDefinition
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.render.entity.animation.Keyframe
import net.minecraft.client.render.entity.animation.Transformation


class StoneMaskAnimation {

    val inactive: AnimationDefinition = AnimationDefinition.Builder.create(0.0417f)
        .addBoneAnimation(
            "lClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "mClaw", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "blood", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .build()


    val awaken: AnimationDefinition = AnimationDefinition.Builder.create(1.5833f)
        .addBoneAnimation(
            "lClaw02", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(15.0f, 72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(15.0f, 72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.625f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7083f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.8333f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.1667f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw02", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.8333f,
                    AnimationHelper.createRotationalVector(15.0f, -72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.1667f,
                    AnimationHelper.createRotationalVector(15.0f, -72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "rClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.625f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7083f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.8333f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.1667f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw01", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.9167f,
                    AnimationHelper.createRotationalVector(0.0f, 50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, 50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7083f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7917f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.9167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw01", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    0.9167f,
                    AnimationHelper.createRotationalVector(0.0f, -50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(0.0f, -50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7083f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.7917f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.9167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw03", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    1.0833f,
                    AnimationHelper.createRotationalVector(22.5f, 52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(22.5f, 52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.9583f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(1.0833f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw03", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    1.0833f,
                    AnimationHelper.createRotationalVector(22.5f, -52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(22.5f, -52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.9583f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(1.0833f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw04", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    1.0833f,
                    AnimationHelper.createRotationalVector(43.1853f, 9.1823f, 8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(43.1853f, 9.1823f, 8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.9583f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(1.0833f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw04", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    1.0833f,
                    AnimationHelper.createRotationalVector(43.1853f, -9.1823f, -8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(43.1853f, -9.1823f, -8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.9583f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(1.0833f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "mClaw", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(
                    1.0833f,
                    AnimationHelper.createRotationalVector(57.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    1.25f,
                    AnimationHelper.createRotationalVector(57.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(1.375f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "mClaw", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.9583f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(1.0833f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.25f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(1.375f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "blood", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "armorHead", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.0833f,
                    AnimationHelper.createRotationalVector(0.0f, -7.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(-0.2187f, -4.9952f, 7.5095f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.25f,
                    AnimationHelper.createRotationalVector(-0.6574f, 7.4713f, -5.0429f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.3333f,
                    AnimationHelper.createRotationalVector(0.4369f, 4.9809f, 0.019f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(-5.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5f,
                    AnimationHelper.createRotationalVector(-0.2187f, -4.9952f, 7.5095f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5833f,
                    AnimationHelper.createRotationalVector(0.0f, 2.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.6667f,
                    AnimationHelper.createRotationalVector(-0.2197f, 7.4952f, -7.5192f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(0.75f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR)
            )
        )
        .build()


    val pierced: AnimationDefinition = AnimationDefinition.Builder.create(0.0f)

        .build()


    val retract: AnimationDefinition = AnimationDefinition.Builder.create(1.0f)
        .addBoneAnimation(
            "lClaw02", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(15.0f, 72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(15.0f, 72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.1667f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.625f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw02", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(15.0f, -72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(15.0f, -72.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "rClaw02", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.1667f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.625f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw01", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(0.0f, 50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, 50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw01", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(0.0f, -50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(0.0f, -50.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "rClaw01", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw03", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(22.5f, 52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(22.5f, 52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw03", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(22.5f, -52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(22.5f, -52.5f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "rClaw03", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "lClaw04", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(43.1853f, 9.1823f, 8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(43.1853f, 9.1823f, 8.5181f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "lClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "rClaw04", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(43.1853f, -9.1823f, -8.5181f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.5417f,
                    AnimationHelper.createRotationalVector(43.1853f, -9.1823f, -8.5181f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "rClaw04", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.5417f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.875f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .addBoneAnimation(
            "mClaw", Transformation(
                Transformation.Targets.ROTATE,
                Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), Transformation.Interpolations.LINEAR),
                Keyframe(
                    0.1667f,
                    AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.2917f,
                    AnimationHelper.createRotationalVector(57.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                ),
                Keyframe(
                    0.4167f,
                    AnimationHelper.createRotationalVector(57.5f, 0.0f, 0.0f),
                    Transformation.Interpolations.LINEAR
                )
            )
        )
        .addBoneAnimation(
            "mClaw", Transformation(
                Transformation.Targets.SCALE,
                Keyframe(0.0f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.1667f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.2917f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.4167f, AnimationHelper.createScalingVector(1.0, 1.0, 1.0), Transformation.Interpolations.LINEAR),
                Keyframe(0.625f, AnimationHelper.createScalingVector(0.2, 0.2, 0.2), Transformation.Interpolations.LINEAR),
                Keyframe(0.75f, AnimationHelper.createScalingVector(0.0, 0.0, 0.0), Transformation.Interpolations.LINEAR)
            )
        )
        .build()
}