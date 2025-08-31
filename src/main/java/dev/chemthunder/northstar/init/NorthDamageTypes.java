package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface NorthDamageTypes {
    RegistryKey<DamageType> STEAMED = of("steamed");
    RegistryKey<DamageType> SPARKED = of("sparked");
    RegistryKey<DamageType> THRONGLED = of("throngled");

    static DamageSource steamed(LivingEntity entity) {
        return entity.getDamageSources().create(STEAMED); }

    static DamageSource sparked(LivingEntity entity) {
        return entity.getDamageSources().create(SPARKED); }

    static DamageSource throngled(LivingEntity entity) {
        return entity.getDamageSources().create(THRONGLED); }


    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Northstar.id(name));
    }
}
