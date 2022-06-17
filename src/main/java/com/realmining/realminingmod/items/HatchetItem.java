package com.realmining.realminingmod.items;

import com.realmining.realminingmod.util.groups.ModItemGroups;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class HatchetItem extends AxeItem {
    public HatchetItem() {
        super(ModItemTiers.FLINT, 1.8F, 0F,
                new Item.Properties().addToolType(ToolType.AXE, 0)
                        .tab(ModItemGroups.REALMINING_TOOLS).stacksTo(1));
    }
}