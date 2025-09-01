package dev.chemthunder.northstar;

import dev.chemthunder.northstar.init.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Northstar implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "northstar";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RegistryKey<DamageType> SOUL_BOX_DAMAGE_TYPE =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("northstar", "soul_box"));

	@Override
	public void onInitialize() {
        NorthItemGroup.init();
        NorthItems.initialize();
        NorthBlocks.init();
        NorthSounds.init();
        NorthEffects.init();


        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 2.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.4F))
                        .with(ItemEntry.builder(NorthItems.MACHINE_OIL_BOTTLE));

                tableBuilder.pool(poolBuilder);
            }

        });

	}

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
        NorthBlocks.clientInit();
    }
}