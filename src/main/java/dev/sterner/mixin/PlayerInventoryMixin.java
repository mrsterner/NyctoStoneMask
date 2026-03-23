package dev.sterner.mixin;

import dev.sterner.item.StoneMaskItem;
import dev.sterner.stone_mask.StoneMaskPhase;
import dev.sterner.stone_mask.StoneMaskStateManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow @Final public PlayerEntity player;

    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void nycto_stone_mask$onSetStack(int slot, ItemStack stack, CallbackInfo ci){

        if (slot != 39) return;
        if (player instanceof ServerPlayerEntity serverPlayer) {
            var equipped = serverPlayer.getInventory().getStack(39);
            if (!(equipped.getItem() instanceof StoneMaskItem)) {
                var maskUuid = StoneMaskItem.Companion.getMaskUUID(equipped);

                if (maskUuid == null) {
                    return;
                }
                var state = StoneMaskStateManager.INSTANCE.getStates().get(maskUuid);
                if (state == null) {
                    return;
                }

                if (state.getPhase() == StoneMaskPhase.AWAKEN || state.getPhase() == StoneMaskPhase.PIERCED) {
                    ci.cancel();
                }
            }
        }
    }
}
