package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import com.realmining.realminingmod.particles.ModParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class FallingBlockPhys {
    public static <T extends Block & IFallingBlock> void scheduleTick(T fallingBlock, IWorld level, BlockPos pos) {
        if(!level.getBlockTicks().hasScheduledTick(pos, fallingBlock)) {
            level.getBlockTicks().scheduleTick(pos, fallingBlock, fallingBlock.getUpdateRate());
        }
    }

    public static <T extends Block & IFallingBlock> void tick(T fallingBlock, ServerWorld level, BlockPos pos) {
        if (level.isEmptyBlock(pos.below()) || FallingBlock.isFree(level.getBlockState(pos.below())) && pos.getY() >= 0) {
            FallingBlockCustomEntity fallingBlockEntity = fallingBlock.createFallingEntity(level, pos, level.getBlockState(pos));
            fallingBlock.onFalling(fallingBlockEntity);
            level.addFreshEntity(fallingBlockEntity);
        }
    }

    public static <T extends Block & IFallingBlock> void animateTick(T fallingBlock, World level, BlockPos pos, BlockState blockState, Random random) {
        if (random.nextInt(16) == 0) {
            BlockPos blockpos = pos.below();
            if (level.isEmptyBlock(blockpos) || FallingBlock.isFree(level.getBlockState(blockpos))) {
                double d0 = (double)pos.getX() + random.nextDouble();
                double d1 = (double)pos.getY() - 0.05D;
                double d2 = (double)pos.getZ() + random.nextDouble();
                level.addParticle(new BlockParticleData(ModParticleTypes.FALLING_DUST_CUSTOM.get(), blockState), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
