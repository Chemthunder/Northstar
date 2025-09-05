package dev.chemthunder.northstar;

import dev.chemthunder.northstar.init.NorthEffects;
import dev.chemthunder.northstar.init.NorthItemGroup;
import dev.chemthunder.northstar.init.NorthItems;
import dev.chemthunder.northstar.init.NorthSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Northstar implements ModInitializer {
	public static final String MOD_ID = "northstar";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        NorthItemGroup.init();
        NorthItems.initialize();
        NorthSounds.init();
        NorthEffects.init();


        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 4.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.6F))
                        .with(ItemEntry.builder(NorthItems.MACHINE_OIL_BOTTLE));

                tableBuilder.pool(poolBuilder);
            }

        });

	}

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }
}