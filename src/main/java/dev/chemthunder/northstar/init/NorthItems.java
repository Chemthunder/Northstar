package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import dev.chemthunder.northstar.item.FlareItem;
import dev.chemthunder.northstar.item.GlaiveItem;
import dev.chemthunder.northstar.item.OilItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface NorthItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();


    Item LUMIUM_INGOT = create("lumium_ingot", new Item(new Item.Settings()
            .fireproof()
    ));

    Item STEELBOUND_GLAIVE = create("steelbound_glaive", new GlaiveItem(ToolMaterials.NETHERITE,  new Item.Settings()
            .maxCount(1)
            .fireproof()
            .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 3, -2.3f))
    ));

    Item TERRAFORMAL_DISC = create("terraformal_disc", new Item( new Item.Settings()
            .maxCount(1)
            .jukeboxPlayable(NorthJukeboxSongs.TERRAFORMAL)
            .rarity(Rarity.EPIC)
    ));

    Item BERGENTRUCK_DISC = create("bergentruck_disc", new Item( new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.RARE)
            .jukeboxPlayable(NorthJukeboxSongs.BERGENTRUCK)
    ));

    Item BVO_DISC = create("bvo_disc", new Item( new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.EPIC)
            .jukeboxPlayable(NorthJukeboxSongs.BUTCHERVANITYOVERCOOKED)
    ));


    Item LUMIUM_SPARK = create("lumium_spark", new FlareItem( new Item.Settings()
            .maxCount(1)
    ));

    Item MACHINE_OIL_BOTTLE = create("machine_oil_bottle", new OilItem(new Item.Settings()
            .maxCount(16)
    ));

    static void initialize() {
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

        modifyItemNameColor(STEELBOUND_GLAIVE, 0x9999999);
        modifyItemNameColor(LUMIUM_SPARK, 0x639cff);
    }

    private static Item create(String name, Item item) {
        ITEMS.put(item, Northstar.id(name));
        return item;
    }
}
