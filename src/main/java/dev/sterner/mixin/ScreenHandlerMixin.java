package dev.sterner.mixin;

import dev.sterner.item.StoneMaskItem;
import dev.sterner.stone_mask.StoneMaskPhase;
import dev.sterner.stone_mask.StoneMaskStateManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Inject(method = "onSlotClick", at = @At("HEAD"), cancellable = true)
    private void nycto_stone_mask$onSlotClicked(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci){
        if (slotIndex != 5) {
            return;
        }

        if (player instanceof ServerPlayerEntity serverPlayer) {

            var equipped = serverPlayer.getEquippedStack(net.minecraft.entity.EquipmentSlot.HEAD);
            if (!(equipped.getItem() instanceof StoneMaskItem)) {
                return;
            }
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
