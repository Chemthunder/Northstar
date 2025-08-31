package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface NorthItemGroup {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Northstar.id("northstar"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NorthItems.LUMIUM_SPARK))
            .displayName(Text.translatable("itemGroup.northstar").styled(style -> style.withColor(0x354b66)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(NorthItemGroup::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(NorthItems.LUMIUM_SPARK);
        itemGroup.add(NorthItems.STEELBOUND_GLAIVE);
        itemGroup.add(NorthItems.LUMIUM_INGOT);
        itemGroup.add(NorthItems.TERRAFORMAL_DISC);
        itemGroup.add(NorthItems.BVO_DISC);
        itemGroup.add(NorthItems.BERGENTRUCK_DISC);

    }
}
