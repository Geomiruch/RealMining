package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import com.realmining.realminingmod.entity.custom.FallingSlabBlockEntity;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class FallingSlabBlock extends SlabBlock implements IFallingBlock {
    public FallingSlabBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        FallingBlockPhys.scheduleTick(this, p_196271_4_, p_196271_5_);
        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockState blockState = super.getStateForPlacement(p_196258_1_);
        if(blockState.is(this) && blockState.getValue(TYPE) == SlabType.TOP) {
            blockState = blockState.setValue(TYPE, SlabType.BOTTOM);
        }
        return blockState;
    }

    @Override
    public void neighborChanged(BlockState blockState, World level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
        BlockState aboveState = level.getBlockState(pos.above());
        if(aboveState.is(this) && blockState.is(this) && blockState.getValue(TYPE) != SlabType.DOUBLE) {
            if(aboveState.getValue(TYPE) == SlabType.DOUBLE) {
                aboveState = aboveState.setValue(TYPE, SlabType.BOTTOM);
            } else {
                aboveState = Blocks.AIR.defaultBlockState();
            }
            blockState = blockState.setValue(TYPE, SlabType.DOUBLE);
            level.setBlockAndUpdate(pos, blockState);
            level.setBlockAndUpdate(pos.above(), aboveState);
        }
    }

    @Override
    public void onPlace(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        super.onPlace(p_220082_1_, p_220082_2_, p_220082_3_, p_220082_4_, p_220082_5_);
        FallingBlockPhys.scheduleTick(this, p_220082_2_, p_220082_3_);
    }

    @Override
    public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
        super.tick(p_225534_1_, p_225534_2_, p_225534_3_, p_225534_4_);
        FallingBlockPhys.tick(this, p_225534_2_, p_225534_3_);
    }

    @Override
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
        super.animateTick(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);
        FallingBlockPhys.animateTick(this, p_180655_2_, p_180655_3_, p_180655_1_, p_180655_4_);
    }

    @Override
    public FallingBlockCustomEntity createFallingEntity(World level, BlockPos pos, BlockState blockState) {
        return new FallingSlabBlockEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, blockState);
    }

    @Override
    public void onFalling(FallingBlockCustomEntity fallingBlockEntity) {
        if(fallingBlockEntity instanceof FallingSlabBlockEntity) {
            FallingSlabBlockEntity slabEntity = (FallingSlabBlockEntity) fallingBlockEntity;
            BlockState blockState = slabEntity.getBlockState();
            if(blockState.is(this)) {
                if(blockState.getValue(TYPE) == SlabType.TOP) {
                    slabEntity.setBlockState(blockState.setValue(TYPE, SlabType.BOTTOM));
                }
            }
        }
    }
}
