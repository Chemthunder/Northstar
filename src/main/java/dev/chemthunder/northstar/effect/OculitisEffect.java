package dev.chemthunder.northstar.effect;

import dev.chemthunder.northstar.init.NorthSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OculitisEffect extends StatusEffect {
    public OculitisEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x94ffff);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();

        if  (world instanceof ServerWorld sw) {
            sw.spawnParticles(ParticleTypes.CHERRY_LEAVES, entity.getX(), entity.getY() + 1, entity.getZ(), 3, 0.5, 0.5, 0.5, 0.5);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (source instanceof ServerPlayerEntity player) {
            player.playSoundToPlayer(SoundEvents.BLOCK_AZALEA_LEAVES_FALL, SoundCategory.MASTER, 1, 1);
        }
        super.applyInstantEffect(source, attacker, target, amplifier, proximity);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.SCULK.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20;
        return duration % i == 0;
    }
}
