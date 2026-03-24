package dev.sterner.mixin;

import dev.sterner.stone_mask.StoneMaskHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow @Final public PlayerEntity player;

    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void nycto_stone_mask$onSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        if (!StoneMaskHelper.INSTANCE.isHelmetSlot(slot)) return;

        var current = player.getInventory().getStack(slot);
        if (StoneMaskHelper.INSTANCE.isStackLocked(current)) {
            ci.cancel();
        }
    }
}