package dev.sterner.mixin;

import dev.sterner.stone_mask.StoneMaskHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Inject(method = "onSlotClick", at = @At("HEAD"), cancellable = true)
    private void nycto_stone_mask$onSlotClicked(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (!StoneMaskHelper.INSTANCE.isHelmetScreenSlot(slotIndex)) return;
        if (StoneMaskHelper.INSTANCE.isMaskLocked(player)) {
            ci.cancel();
        }
    }
}