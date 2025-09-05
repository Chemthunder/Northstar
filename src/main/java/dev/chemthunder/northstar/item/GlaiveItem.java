package dev.chemthunder.northstar.item;

import dev.chemthunder.northstar.init.NorthDamageTypes;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GlaiveItem extends SwordItem implements CustomHitParticleItem, CustomHitSoundItem, CustomKillSourceItem {
    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x1c6a91, 0x17465e), new SweepParticleEffect(0xb5e6ff, 0x83cdf2)};

    public GlaiveItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack offHand = user.getOffHandStack();



        return super.use(world, user, hand);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.BLOCK_CHAIN_PLACE);
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return NorthDamageTypes.glaive_kill(livingEntity);
    }
}
