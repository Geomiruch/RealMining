package com.realmining.realminingmod.util.groups;

import com.realmining.realminingmod.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
    public static final ItemGroup REALMINING_BUILDING_BLOCKS = new ModItemGroup("realmining_building_blocks", () -> new ItemStack( ModItems.GRANITE_COBBLE.get()));
    public static final ItemGroup REALMINING_ITEMS = new ModItemGroup("realmining_items", () -> new ItemStack( ModItems.MORTAR.get()));
    public static final ItemGroup REALMINING_TOOLS = new ModItemGroup("realmining_tools", () -> new ItemStack( ModItems.FLINT_AXE.get()));

}
