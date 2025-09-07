package dev.chemthunder.northstar;

import dev.chemthunder.northstar.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Northstar implements ModInitializer {
    public static final String MOD_ID = "northstar";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        NorthItemGroup.init();
        NorthItems.init();
        NorthSounds.init();
        NorthEffects.init();
        NorthEnchantments.init();
        NorthDataComponents.init();



        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 4.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.6F))
                        .with(ItemEntry.builder(NorthItems.FORBIDDEN_VIAL));

                tableBuilder.pool(poolBuilder);
            }

        });

        FabricLoader.getInstance().getModContainer(Northstar.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(id("owoify"), container, Text.literal("Owoify"), ResourcePackActivationType.NORMAL);
        });

    }

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }
}