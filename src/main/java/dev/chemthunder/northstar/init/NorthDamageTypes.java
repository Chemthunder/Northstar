package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface NorthDamageTypes {
    RegistryKey<DamageType> STEAMED = of("steamed");
    RegistryKey<DamageType> GRACE_KILL = of("grace_kill");
    RegistryKey<DamageType> GLAIVE_KILL = of("glaive_kill");
    RegistryKey<DamageType> ENRAPTURED = of("enraptured");

    static DamageSource steamed(LivingEntity entity) {
        return entity.getDamageSources().create(STEAMED); }

    static DamageSource grace_kill(LivingEntity entity) {
        return entity.getDamageSources().create(GRACE_KILL); }

    static DamageSource glaive_kill(LivingEntity entity) {
        return entity.getDamageSources().create(GLAIVE_KILL); }

    static DamageSource enraptured(LivingEntity entity) {
        return entity.getDamageSources().create(ENRAPTURED); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Northstar.id(name));
    }
}
