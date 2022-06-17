package com.realmining.realminingmod.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ModItemTiers {
    public static IItemTier FLINT = new ModItemTier().setMaxUses(16).setEfficiency(1.5F)
            .setAttackDamage(1.0F).setHarvestLvl(1).setEnchantability(0).setRepairMats(Items.FLINT);
    public static IItemTier BONE = new ModItemTier().setMaxUses(128).setEfficiency(2.0F)
            .setAttackDamage(2.0F).setHarvestLvl(1).setEnchantability(0).setRepairMats(Items.BONE);
    public static IItemTier COPPER = new ModItemTier().setMaxUses(192).setEfficiency(1.65F)
            .setAttackDamage(1.5F).setHarvestLvl(2).setEnchantability(0)
            .setRepairMat(getTagOrNull("forge:ingots/copper"));
    public static IItemTier BRONZE = new ModItemTier().setMaxUses(442).setEfficiency(2.5F)
            .setAttackDamage(2.5F).setHarvestLvl(2).setEnchantability(0)
            .setRepairMat(getTagOrNull("forge:ingots/bronze"));


    @Nullable
    public static ITag<Item> getTagOrNull(String tagName) {
        ResourceLocation resLoc = new ResourceLocation(tagName);
        if (ItemTags.getAllTags().getAvailableTags().contains(resLoc)) {
            return ItemTags.getAllTags().getTag(resLoc);
        }
        return null;
    }
}
