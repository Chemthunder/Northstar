package dev.chemthunder.northstar.datagen;

import dev.chemthunder.northstar.Northstar;
import dev.chemthunder.northstar.init.NorthItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class NorthItemTagProvider extends FabricTagProvider<Item> {
    public NorthItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    public static final TagKey<Item> LUMIUM_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Northstar.MOD_ID, "lumium_items"));

    public static final TagKey<Item> GRACE_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Northstar.MOD_ID, "grace_items"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(LUMIUM_ITEMS)
                .add(NorthItems.LUMIUM_INGOT)
                .setReplace(true);

        getOrCreateTagBuilder(GRACE_ITEMS)
                .add(NorthItems.FORBIDDEN_VIAL)
                .setReplace(true);
    }
}
