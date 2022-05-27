package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import com.realmining.realminingmod.entity.custom.FallingPileBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SimpleGravelBlock extends FallingCustomBlock {
    public SimpleGravelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getDustColor(BlockState blockState, IBlockReader level, BlockPos pos) {
        return -8356741;
    }

    @Override
    public void onFalling(FallingBlockCustomEntity fallingBlockEntity) {
        BlockPos pos = fallingBlockEntity.blockPosition();
        World level = fallingBlockEntity.getLevel();
        if (level.getBlockState(pos).is(this)) {
            fallingBlockEntity.time++;
            level.removeBlock(pos, false);
        }
    }

    @Override
    public FallingBlockCustomEntity createFallingEntity(World level, BlockPos pos, BlockState blockState) {
        return new FallingPileBlockEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, ModBlocks.GRAVEL_PILE.get().defaultBlockState().setValue(PileBlock.LAYERS, 8));
    }
}
