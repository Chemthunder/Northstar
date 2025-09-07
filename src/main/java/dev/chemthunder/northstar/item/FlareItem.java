package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthEnchantments;
import dev.chemthunder.northstar.init.NorthItems;
import dev.chemthunder.northstar.init.NorthSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
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
        if (!EnchantmentHelper.hasAnyEnchantmentsWith(user.getStackInHand(hand), NorthEnchantments.GUST)) {
            if (!user.isSneaking()) {
                launchFireball(user, user.getMainHandStack());
            } else if (user.isSneaking()) {
                launchBeegFireball(user, user.getMainHandStack());
            }

        } else if (EnchantmentHelper.hasAnyEnchantmentsWith(user.getStackInHand(hand), NorthEnchantments.GUST)) {
            if (!user.isSneaking()) {
                if (!world.isClient) {
                    WindChargeEntity windCharge = new WindChargeEntity(user, world, user.getX(), user.getY() + 1.5f, user.getZ());

                    windCharge.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 3f, 1.0f); // power, divergence

                    world.spawnEntity(windCharge);

                    user.getItemCooldownManager().set(NorthItems.LUMIUM_SPARK, 10);
                }

            } else if (user.isSneaking()) {
                user.setVelocity(user.getVelocity().x, 4, user.getVelocity().z);
                user.velocityModified = true;
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
            player.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 1f, 1);
            SmallFireballEntity fireball = new SmallFireballEntity(world, player, player.getRotationVec(0));
            Vec3d pos = player.getPos();
            fireball.updatePosition(pos.x, pos.y + 1.5f, pos.z);
            world.spawnEntity(fireball);
            player.getItemCooldownManager().set(NorthItems.LUMIUM_SPARK, 4);
        }

        public static void launchBeegFireball(PlayerEntity player, ItemStack stack) {
            World world = player.getWorld();
            player.playSound(NorthSounds.SPARK_LOAD, 0.5f, 1);
            FireballEntity fireball = new FireballEntity(world, player, player.getRotationVec(0), 1);
            Vec3d pos = player.getPos();
            fireball.updatePosition(pos.x, pos.y + 1.5f, pos.z);
            fireball.setVelocity(0, 0,0);
            world.spawnEntity(fireball);
            player.getItemCooldownManager().set(NorthItems.LUMIUM_SPARK, 40);
        }

        public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(this.getDescription().withColor(0x212a38));
        }

        public MutableText getDescription() {
            return Text.translatable(this.getTranslationKey() + ".desc");
        }
    }
