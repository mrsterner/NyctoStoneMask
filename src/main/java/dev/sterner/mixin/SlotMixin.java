package dev.sterner.mixin;

import dev.sterner.stone_mask.StoneMaskHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin {

    @Shadow @Final public net.minecraft.inventory.Inventory inventory;

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    private void nycto_stone_mask$canTakeItems(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        if (!(inventory instanceof PlayerInventory playerInventory)) return;

        ItemStack helmetStack = playerInventory.getStack(39);
        if (StoneMaskHelper.INSTANCE.isStackLocked(helmetStack)) {
            cir.setReturnValue(false);
        }
    }
}