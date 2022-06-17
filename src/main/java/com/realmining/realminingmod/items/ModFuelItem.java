package com.realmining.realminingmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModFuelItem extends Item {
    private int burnTime;

    public ModFuelItem(Properties properties, int burnTime) {
        super(properties);
        this.burnTime=burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.burnTime;
    }
}
