package com.realmining.realminingmod.entity.custom;

import com.realmining.realminingmod.blocks.PileBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;


public class FallingPileBlockEntity extends FallingBlockCustomEntity {
    public FallingPileBlockEntity(World pLevel, double pX, double pY, double pZ, BlockState pState) {
        super(pLevel, pX, pY, pZ, pState);
    }

    @Override
    public void tick() {
        BlockPos blockpos = this.blockPosition();
        BlockState belowState = this.level.getBlockState(blockpos.below());
        int belowLayers;
        if(this.getY() - blockpos.getY() <= 0.8f && belowState.getBlock() instanceof PileBlock && (belowLayers = belowState.getValue(PileBlock.LAYERS)) < 8) {
            BlockState blockState = getBlockState();
            if(belowState.getBlock() == blockState.getBlock()) {
                int currentLayers = blockState.getValue(PileBlock.LAYERS);
                if(currentLayers + belowLayers > 8) {
                    this.level.setBlockAndUpdate(blockpos.below(), belowState.setValue(PileBlock.LAYERS, 8));
                    this.setBlockState(blockState.setValue(PileBlock.LAYERS, currentLayers + belowLayers - 8));
                } else {
                    this.level.setBlockAndUpdate(blockpos.below(), belowState.setValue(PileBlock.LAYERS, belowLayers + currentLayers));
                    this.remove();
                    return;
                }
            }
        }
        super.tick();
    }
}
