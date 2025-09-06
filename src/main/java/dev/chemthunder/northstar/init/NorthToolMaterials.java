package dev.chemthunder.northstar.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public interface NorthToolMaterials {

    ToolMaterial DIVINE = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 999999, 5, 0.5f, 0, ItemTags.BEACON_PAYMENT_ITEMS);

    ToolMaterial LUMIUM = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4050, 4, 0.5f, 5, NorthItemTagProvider.LUMIUM_ITEMS);
    private static ToolMaterial create(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {

        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return miningSpeed;
            }

            @Override
            public float getAttackDamage() {
                return attackDamageBonus;
            }

            @Override
            public TagKey<Block> getInverseTag() {
                return incorrectBlocksForDrops;
            }

            @Override
            public int getEnchantability() {
                return enchantmentValue;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.fromTag(repairItems);
            }
        };
    }
}
