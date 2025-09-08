package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import dev.chemthunder.northstar.effect.EnsteamedEffect;
import dev.chemthunder.northstar.effect.RallyEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public interface NorthEffects {
    RegistryEntry<StatusEffect> ENSTEAMED = create("ensteamed", new EnsteamedEffect());
    RegistryEntry<StatusEffect> RALLY = create("schizophrenia", new RallyEffect());


    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Northstar.id(name), statusEffect);
    }

    static void init() {
    }
}
