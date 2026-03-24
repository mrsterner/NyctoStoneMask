package dev.sterner.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Animation.class)
public interface AnimationAccessor {
    @Invoker("of")
    static Animation of(ModelPart root, AnimationDefinition definition) {
        throw new AssertionError();
    }
}