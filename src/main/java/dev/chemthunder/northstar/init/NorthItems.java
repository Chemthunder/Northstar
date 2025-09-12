package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import dev.chemthunder.northstar.item.*;
import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface NorthItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();


    Item LUMIUM_INGOT = create("lumium_ingot", new LumiumIngotItem(new Item.Settings()
            .fireproof()
    ));

    Item STEELBOUND_GLAIVE = create("steelbound_glaive", new GlaiveItem(NorthToolMaterials.LUMIUM,  new Item.Settings()
            .maxCount(1)
            .fireproof()
            .attributeModifiers(SwordItem.createAttributeModifiers(NorthToolMaterials.LUMIUM, 6, -2.3f))
    ));

    Item TERRAFORMAL_DISC = create("terraformal_disc", new Item( new Item.Settings()
            .maxCount(1)
            .jukeboxPlayable(NorthJukeboxSongs.TERRAFORMAL)
            .rarity(Rarity.EPIC)
    ));

    Item BVO_DISC = create("bvo_disc", new Item( new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.EPIC)
            .jukeboxPlayable(NorthJukeboxSongs.BUTCHERVANITYOVERCOOKED)
    ));


    Item LUMIUM_SPARK = create("lumium_spark", new FlareItem(new AcornItemSettings()
            .followsCam()
            .maxCount(1)
            .jukeboxPlayable(NorthJukeboxSongs.BIGSHOT)
    ));

    Item ENSCULKED_LUMIUM = create("ensculked_lumium", new EnsculkedLumiumItem(new AcornItemSettings()
            .maxCount(1)
            .fireproof()
    ));

    Item AMETHYSTINE_LUMIUM = create("amethystine_lumium", new AmethystineItem(new AcornItemSettings()
            .fireproof()
    ));

    Item FORBIDDEN_VIAL = create("forbidden_vial", new OilItem(new Item.Settings()
            .maxCount(16)
    ));

    Item GRACE = create("grace", new GraceItem(NorthToolMaterials.DIVINE, new AcornItemSettings()
            .twoHanded()
            .undroppable()
            .maxCount(1)
            .attributeModifiers(SwordItem.createAttributeModifiers(NorthToolMaterials.DIVINE, 8, -2.8f))
    ));

    Item HARBINGER_DISC = create("harbinger_disc", new Item(new AcornItemSettings()
            .maxCount(1)
            .jukeboxPlayable(NorthJukeboxSongs.HARBINGER)
    ));

    Item NORTHSTAR_SIMULACRUM = create("northstar_simulacrum", new SimulacrumItem(new AcornItemSettings()
            .maxCount(1)
    ));

    static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

        modifyItemNameColor(STEELBOUND_GLAIVE, 0x9999999);
        modifyItemNameColor(LUMIUM_SPARK, 0x639cff);
        modifyItemNameColor(FORBIDDEN_VIAL, 0x1a1b26);
        modifyItemNameColor(GRACE, 0xdbe9ff);
        modifyItemNameColor(AMETHYSTINE_LUMIUM, 0xd987ff);
        modifyItemNameColor(ENSCULKED_LUMIUM, 0x00ffd9);
        modifyItemNameColor(LUMIUM_INGOT, 0x598ac9);
        modifyItemNameColor(HARBINGER_DISC, 0x598ac9);
        modifyItemNameColor(NORTHSTAR_SIMULACRUM, 0x5861a1);
    }

    private static Item create(String name, Item item) {
        ITEMS.put(item, Northstar.id(name));
        return item;
    }
}
