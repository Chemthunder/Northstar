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
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.Nullable;

public class RallyEffect extends StatusEffect {
    public RallyEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x94ffff);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setGlowing(true);
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (source instanceof ServerPlayerEntity player) {
            player.playSoundToPlayer(NorthSounds.HARBINGER_SUMMON, SoundCategory.MASTER, 1, 1);
        }

        source.isGlowing();
        super.applyInstantEffect(source, attacker, target, amplifier, proximity);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20;
        return duration % i == 0;
    }
}
