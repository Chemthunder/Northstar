package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class EnsculkedLumiumItem extends Item {
    public EnsculkedLumiumItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.STONECUTTER)) {
            ItemStack stack = user.getMainHandStack();
            ItemStack offStack = user.getOffHandStack();

            if (stack.isOf(NorthItems.ENSCULKED_LUMIUM)) {
                offStack.decrement(1);
                stack.decrement(1);
                user.giveItemStack(NorthItems.LUMIUM_INGOT.getDefaultStack());
                user.playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 0.8F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

}
