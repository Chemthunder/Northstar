package dev.chemthunder.northstar.effect;

import dev.chemthunder.northstar.init.NorthDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import static dev.chemthunder.northstar.init.NorthDamageTypes.steamed;

public class EnsteamedEffect extends StatusEffect {
    public EnsteamedEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x94ffff);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        World world = entity.getWorld();

        entity.damage(world.getDamageSources().create(NorthDamageTypes.STEAMED), 3f);

        return super.applyUpdateEffect(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20;
        return duration % i == 0;
    }
}
