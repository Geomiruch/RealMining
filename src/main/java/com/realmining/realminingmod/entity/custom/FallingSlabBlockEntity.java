package com.realmining.realminingmod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FallingSlabBlockEntity extends FallingBlockCustomEntity {
    public FallingSlabBlockEntity(World pLevel, double pX, double pY, double pZ, BlockState pState) {
        super(pLevel, pX, pY, pZ, pState);
    }

    @Override
    public void tick() {
        BlockPos blockpos = this.blockPosition();
        BlockState belowState = this.level.getBlockState(blockpos.below());
        if(this.getY() - blockpos.getY() <= 0.8f && belowState.getBlock() instanceof SlabBlock && belowState.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) {
            BlockState blockState = getBlockState();
            if(belowState.getBlock() == blockState.getBlock()) {
                this.level.setBlockAndUpdate(blockpos.below(), belowState.setValue(SlabBlock.TYPE, SlabType.DOUBLE));
                if(blockState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE) {
                    this.setBlockState(blockState.setValue(SlabBlock.TYPE, SlabType.BOTTOM));
                } else {
                    this.remove();
                    return;
                }
            }
        }
        super.tick();
    }
}