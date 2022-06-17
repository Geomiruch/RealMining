package com.realmining.realminingmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class MudBlock extends Block {
    public MudBlock(Properties p_i48388_1_) {
        super(p_i48388_1_);
    }
    public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
        p_196262_4_.makeStuckInBlock(p_196262_1_, new Vector3d(0.25D, (double)0.05F, 0.25D));
    }
}
