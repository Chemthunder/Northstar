package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface NorthEnchantments {
    Map<ComponentType<?>, Identifier> ENCHANTMENT_EFFECTS = new LinkedHashMap<>();

    ComponentType<Unit> GUST = create("gust", builder -> builder.codec(Unit.CODEC));

    private static <T> ComponentType<T> create(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> componentType = builderOperator.apply(ComponentType.builder()).build();
        ENCHANTMENT_EFFECTS.put(componentType, Northstar.id(id));
        return componentType;
    }

    static void init() {
        ENCHANTMENT_EFFECTS.keySet().forEach(effect -> {
            Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, ENCHANTMENT_EFFECTS.get(effect), effect);
        });
    }
}
