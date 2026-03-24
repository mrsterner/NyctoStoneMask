package dev.sterner.mixin;

import dev.sterner.NyctoStoneMask;
import dev.sterner.stone_mask.client.StoneMaskItemRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpecialModelTypes.class)
public class MixinSpecialModelTypes {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void registerCustomTypes(CallbackInfo ci) {
        SpecialModelTypes.ID_MAPPER.put(
                NyctoStoneMask.INSTANCE.id("stone_mask"),
                StoneMaskItemRenderer.Unbaked.Companion.getCODEC()
        );
    }
}
