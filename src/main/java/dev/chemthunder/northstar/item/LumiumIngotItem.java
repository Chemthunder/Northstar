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
import net.minecraft.util.math.BlockPos;

public class LumiumIngotItem extends Item {
    public LumiumIngotItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.WATER_CAULDRON)) {
            ItemStack stack = user.getMainHandStack();
            ItemStack offStack = user.getOffHandStack();
            BlockPos blockPos = user.getBlockPos();

            if (stack.isOf(Items.CROSSBOW) && offStack.isOf(NorthItems.LUMIUM_INGOT)) {
                offStack.decrement(1);
                stack.decrement(1);
                user.giveItemStack(NorthItems.LUMIUM_SPARK.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, 0.8F, 1.0F);
            }

            if (stack.isOf(Items.NETHERITE_SWORD) && offStack.isOf(NorthItems.LUMIUM_INGOT)) {
                offStack.decrement(1);
                stack.decrement(1);
                user.giveItemStack(NorthItems.STEELBOUND_GLAIVE.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, 0.8F, 1.0F);
            }

            return ActionResult.SUCCESS;
        }


        return super.useOnBlock(context);
    }



}
