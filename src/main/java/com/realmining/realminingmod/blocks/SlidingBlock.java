package com.realmining.realminingmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidingBlock extends FallingCustomBlock {
    public SlidingBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void tick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        super.tick(pState, pLevel, pPos, pRand);
        pLevel.getBlockTicks().scheduleTick(pPos, this, this.getUpdateRate());
        updateTickSliding(pState, pLevel, pPos, pRand);
    }

    public static boolean canFallHere(ServerWorld pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).canBeReplaced(new DirectionalPlaceContext(pLevel, pPos, Direction.DOWN, ItemStack.EMPTY, Direction.UP));
    }

    public static boolean canFallBelow(ServerWorld pLevel, BlockPos pPos) {
        return canFallHere(pLevel, pPos.below());
    }

    public static void updateTickSliding(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        if (!canFallBelow(pLevel, pPos)) {
            List<Direction> availableDirections = new ArrayList<>();

            for(Direction dir : Direction.Plane.HORIZONTAL) {
                if(canFallHere(pLevel, pPos.relative(dir)) && canFallBelow(pLevel, pPos.relative(dir))) {
                    availableDirections.add(dir);
                }
            }

            if (availableDirections.size() > 1) {
                Direction fallDirection = availableDirections.get(pRandom.nextInt(availableDirections.size()));

                pLevel.removeBlock(pPos, false);

                pLevel.setBlockAndUpdate(pPos.relative(fallDirection), pState);
            }

        }
    }
}
