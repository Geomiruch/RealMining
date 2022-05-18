package com.realmining.realminingmod.util;

import com.realmining.realminingmod.realminingMod;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModTags
{
    public static class Blocks
    {
        public static final ITag.INamedTag<Block> CAN_LANDSLIDE = BlockTags.createOptional(new ResourceLocation(realminingMod.MOD_ID, "blocks/can_landslide"));
        public static final ITag.INamedTag<Block> CAN_TRANSFORM = BlockTags.createOptional(new ResourceLocation(realminingMod.MOD_ID, "blocks/can_transform"));
    }
}
