package dev.sterner

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations

object StoneMaskAnimations {
    val INACTIVE: AnimationDefinition = AnimationDefinition.Builder.withLength(0.0417f)
        .addAnimation("lClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("mClaw", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("blood", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))
        .build()

    val AWAKEN: AnimationDefinition = AnimationDefinition.Builder.withLength(1.5833f)
        .addAnimation("lClaw02", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.8333f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.1667f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.2917f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7083f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.8333f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.2917f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw02", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.8333f, KeyframeAnimations.degreeVec(15f, -72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.1667f, KeyframeAnimations.degreeVec(15f, -72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.2917f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7083f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.8333f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.2917f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw01", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.9167f, KeyframeAnimations.degreeVec(0.0f, 50.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(0.0f, 50.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7083f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7917f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw01", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.9167f, KeyframeAnimations.degreeVec(0.0f, -50.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(0.0f, -50.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7083f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.7917f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw03", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(1.0833f, KeyframeAnimations.degreeVec(22.5f, 52.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(22.5f, 52.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.875f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9583f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0833f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw03", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(1.0833f, KeyframeAnimations.degreeVec(22.5f, -52.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(22.5f, -52.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.875f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9583f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0833f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw04", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(1.0833f, KeyframeAnimations.degreeVec(43.18528f, 9.18229f, 8.51814f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(43.18528f, 9.18229f, 8.51814f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.875f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9583f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0833f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw04", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(1.0833f, KeyframeAnimations.degreeVec(43.18528f, -9.1823f, -8.5181f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(43.18528f, -9.1823f, -8.5181f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.875f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9583f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0833f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("mClaw", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(1.0833f, KeyframeAnimations.degreeVec(57.5f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.25f, KeyframeAnimations.degreeVec(57.5f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("mClaw", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.875f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.9583f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0833f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.375f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("blood", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("armorHead", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.0833f, KeyframeAnimations.degreeVec(0.0f, -7.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(-0.21865f, -4.99523f, 7.50954f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.25f, KeyframeAnimations.degreeVec(-0.6574f, 7.4713f, -5.04292f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.3333f, KeyframeAnimations.degreeVec(0.43688f, 4.98093f, 0.019f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(-5f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(-0.21865f, -4.99523f, 7.50954f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5833f, KeyframeAnimations.degreeVec(0.0f, 2.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.6667f, KeyframeAnimations.degreeVec(-0.2197f, 7.49521f, -7.51916f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .build()

    val RETRACT: AnimationDefinition = AnimationDefinition.Builder.withLength(1f)
        .addAnimation("lClaw02", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("lClaw03", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("lClaw01", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("lClaw04", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("lClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))




        .addAnimation("rClaw02", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw02", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("rClaw03", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw03", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("rClaw01", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw01", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("rClaw04", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("rClaw04", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .addAnimation("mClaw", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.1667f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.2917f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.degreeVec(15f, 72.5f, 0.0f), AnimationChannel.Interpolations.LINEAR)))
        .addAnimation("mClaw", AnimationChannel(AnimationChannel.Targets.SCALE,
            Keyframe(0.0f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.4167f, KeyframeAnimations.scaleVec(1.0, 1.0, 1.0), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.625f, KeyframeAnimations.scaleVec(0.2, 0.2, 0.2), AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.scaleVec(0.0, 0.0, 0.0), AnimationChannel.Interpolations.LINEAR)))

        .build()
}