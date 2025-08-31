package dev.chemthunder.northstar.init;

import dev.chemthunder.northstar.Northstar;
import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public interface NorthBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

 //   Block LUMIUM_BLOCK = createWithItem("lumium_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)
   //         ));

    private static Block create(String name, Block block) {
        BLOCKS.put(block, Northstar.id(name));
        return block;
    }

    private static Block createWithItem(String name, Block block) {
        Block block2 = create(name, block);
        NorthItems.ITEMS.put(new TranslationBlockItem(block2, new Item.Settings()), Northstar.id(name));
        return block2;
    }

    static void init() {
        BLOCKS.keySet().forEach(block -> {
            Registry.register(Registries.BLOCK, BLOCKS.get(block), block);
        });
    }

    static void clientInit() {
        /*
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                new Block[]{}
        );
         */
    }
}
