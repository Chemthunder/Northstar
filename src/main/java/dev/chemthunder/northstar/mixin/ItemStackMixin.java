package dev.chemthunder.northstar.mixin;

import dev.chemthunder.northstar.init.NorthItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isOf(Item item);

    @Inject(method = "getDamage", at = @At("HEAD"), cancellable = true)
    private void getDamage(CallbackInfoReturnable<Integer> cir) {
        if (this.isOf(NorthItems.GRACE)) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "isDamageable", at = @At("HEAD"), cancellable = true)
    private void isDamageable(CallbackInfoReturnable<Boolean> cir) {
        if (this.isOf(NorthItems.GRACE)) {
            cir.setReturnValue(false);
        }
    }
}