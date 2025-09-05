package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthDamageTypes;
import dev.chemthunder.northstar.init.NorthSounds;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class GraceItem extends SwordItem implements CustomHitParticleItem, CustomHitSoundItem, CustomKillSourceItem {
    public GraceItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x1c6a91, 0x17465e), new SweepParticleEffect(0xb5e6ff, 0x83cdf2)};


    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }



    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME);
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return NorthDamageTypes.grace_kill(livingEntity);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            // Set weather: clearDuration, rainDuration, raining, thundering
            serverWorld.setWeather(0, 6000, true, true);

            //feedback to player
            user.sendMessage(Text.translatable("text.grace.change_weather").withColor(0x3b5169).formatted(Formatting.ITALIC), true);
            serverWorld.setThunderGradient(60);

        } else {
            //client-side feedback
            user.sendMessage(Text.translatable("text.grace.change_weather_fail").withColor(0x3b5169).formatted(Formatting.ITALIC), true);
        }

        // else
        world.playSound(user, user.getBlockPos(), NorthSounds.STORM_RING, SoundCategory.MASTER);

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(this.getDescription().withColor(0x3d414f));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }
}
