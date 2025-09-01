package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthItems;
import dev.chemthunder.northstar.init.NorthSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class FlareItem extends Item {
    public FlareItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
   ItemStack mainHand = user.getMainHandStack();
   ItemStack offHand = user.getOffHandStack();


    if (user.isSneaking() == false && mainHand.isOf(NorthItems.LUMIUM_SPARK)) {
        launchFireball(user, user.getMainHandStack());
    } else if (user.isSneaking() && mainHand.isOf(NorthItems.LUMIUM_SPARK)) {
        launchBeegFireball(user, user.getMainHandStack());
    }

    if (offHand.isOf(NorthItems.LUMIUM_SPARK) && user.isSneaking()) {
        if (!world.isClient) {
            WindChargeEntity windCharge = new WindChargeEntity(user, world, user.getX(), user.getY() + 1.5f,user.getZ());

            windCharge.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 3f, 1.0f); // power, divergence

            world.spawnEntity(windCharge);

            user.getItemCooldownManager().set(offHand.getItem(), 4);
        }
    }

        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(5f);
        return super.postHit(stack, target, attacker);
    }

    public static void launchFireball(PlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
        player.playSound(NorthSounds.SPARK_LOAD, 0.5f, 1);
        FireballEntity fireball = new FireballEntity(world, player, player.getRotationVec(0), 0);
        Vec3d pos = player.getPos();
        fireball.updatePosition(pos.x, pos.y + 1.5f, pos.z);
        world.spawnEntity(fireball);
     //   fireball.setVelocity(0, 0,0);
        player.getItemCooldownManager().set(stack.getItem(), 10);
    }

public static void launchBeegFireball(PlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
    player.playSound(NorthSounds.SPARK_LOAD, 0.5f, -1);
    FireballEntity fireball = new FireballEntity(world, player, player.getRotationVec(0), 2);
    Vec3d pos = player.getPos();
    fireball.updatePosition(pos.x, pos.y + 1.5f, pos.z);
    world.spawnEntity(fireball);
    player.getItemCooldownManager().set(stack.getItem(), 40);
}

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(this.getDescription().formatted(Formatting.AQUA));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }
}
