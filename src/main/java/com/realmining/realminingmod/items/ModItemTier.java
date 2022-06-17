package com.realmining.realminingmod.items;

import com.realmining.realminingmod.RealMiningMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModItemTier implements IItemTier {
    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int harvestLvl;
    private int enchantability;
    private Ingredient repairMat;

    public ModItemTier(int maxUses, float eff, float dmg, int harv, int ench,
                           Ingredient repairMat) {
        this.maxUses = maxUses;
        this.efficiency = eff;
        this.attackDamage = dmg;
        this.harvestLvl = harv;
        this.enchantability = ench;
        this.repairMat = repairMat;
    }

    public ModItemTier(int maxUses, float eff, float dmg, int harv, int ench,
                           ITag<Item> repairMatTag) {
        this.maxUses = maxUses;
        this.efficiency = eff;
        this.attackDamage = dmg;
        this.harvestLvl = harv;
        this.enchantability = ench;
        this.repairMat = Ingredient.of(repairMatTag);
    }

    public ModItemTier() {
        this.maxUses = 0;
        this.efficiency = 0;
        this.attackDamage = 0;
        this.harvestLvl = 0;
        this.enchantability = 0;
        this.repairMat = Ingredient.of(Items.BEDROCK);
    }

    public ModItemTier setMaxUses(int maxUses) {
        this.maxUses = maxUses;
        return this;
    }

    public ModItemTier setEfficiency(float eff) {
        this.efficiency = eff;
        return this;
    }

    public ModItemTier setAttackDamage(float dmg) {
        this.attackDamage = dmg;
        return this;
    }

    public ModItemTier setHarvestLvl(int lvl) {
        this.harvestLvl = lvl;
        return this;
    }

    public ModItemTier setEnchantability(int ench) {
        this.enchantability = ench;
        return this;
    }

    public ModItemTier setRepairMat(Ingredient mat) {
        this.repairMat = mat;
        return this;
    }

    public ModItemTier setRepairMat(@Nullable ITag<Item> tag) {
        if (tag == null || tag.getValues().isEmpty()) {
            this.repairMat = Ingredient.of(Items.BEDROCK);
            RealMiningMod.getInstance().LOGGER.warn(
                    "Dynamic saw repair material {} could not be found. Defaulting to Bedrock",
                    tag == null ? "" : tag.toString());
        } else {
            this.repairMat = Ingredient.of(tag);
        }
        return this;
    }

    public ModItemTier setRepairMats(Item... items) {
        this.repairMat = Ingredient.of(items);
        return this;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLvl;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    @Nonnull
    public Ingredient getRepairIngredient() {
        return this.repairMat;
    }
}
