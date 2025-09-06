package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class AmethystineItem extends Item {
    public AmethystineItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.SCULK_CATALYST)) {
            ItemStack stack = user.getMainHandStack();
            ItemStack offStack = user.getOffHandStack();

            if (stack.isOf(NorthItems.AMETHYSTINE_LUMIUM)) {
                offStack.decrement(1);
                stack.decrement(1);
                user.giveItemStack(NorthItems.ENSCULKED_LUMIUM.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, 0.8F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }

}
