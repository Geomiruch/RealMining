package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public interface IFallingBlock {
    default void onLand(World level, BlockPos pos, BlockState curBlockState, BlockState fallBlockState, FallingBlockCustomEntity fallingBlockEntity) {
    }
    default void onBroken(World level, BlockPos pos, FallingBlockCustomEntity fallingBlockEntity) {
    }
    default void onFalling(FallingBlockCustomEntity fallingBlockEntity) {
    }
    default FallingBlockCustomEntity createFallingEntity(World level, BlockPos pos, BlockState blockState) {
        return new FallingBlockCustomEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, blockState);
    }
    default int getUpdateRate() {
        return 2;
    }
    default int getDustColor(BlockState blockState, IBlockReader level, BlockPos pos) {
        return -16777216;
    }
}
