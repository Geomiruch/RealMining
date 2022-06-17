package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import com.realmining.realminingmod.entity.custom.FallingPileBlockEntity;
import com.realmining.realminingmod.util.DirectionUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class PileBlock extends FallingCustomBlock implements IWaterLoggable {
    public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape[] SHAPES = new VoxelShape[] { VoxelShapes.empty(), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

    public PileBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(LAYERS, 1).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean isPathfindable(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType) {
        if (pType == PathType.LAND) {
            return pState.getValue(LAYERS) < 5;
        }
        return false;
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return SHAPES[pState.getValue(LAYERS)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public boolean placeLiquid(IWorld level, BlockPos pos, BlockState blockState, FluidState fluidState) {
        if(blockState.is(this)) {
            if(blockState.getValue(LAYERS) >= 7) {
                return false;
            }
        }
        return IWaterLoggable.super.placeLiquid(level, pos, blockState, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader level, BlockPos pos, BlockState blockState, Fluid fluid) {
        if(blockState.is(this)) {
            if(blockState.getValue(LAYERS) >= 7) {
                return false;
            }
        }
        return IWaterLoggable.super.canPlaceLiquid(level, pos, blockState, fluid);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED))
            pLevel.getLiquidTicks().scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        if(belowState.getBlock() instanceof PileBlock && !belowState.getBlock().is(this)) {
            return false;
        }
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LAYERS, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(LAYERS) < 7 && blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockItemUseContext context) {
        int i = blockState.getValue(LAYERS);
        if (context.getItemInHand().getItem() == this.asItem() && i < 8) {
            if (context.replacingClickedOnBlock()) {
                return context.getClickedFace() == Direction.UP;
            } else {
                return true;
            }
        } else {
            return i == 1;
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            int i = blockstate.getValue(LAYERS);
            blockstate = blockstate.setValue(LAYERS, Integer.valueOf(Math.min(8, i + 1)));
        } else {
            blockstate = super.getStateForPlacement(context);
        }
        return getBlockStateForFluidEnvironment(blockstate, context.getLevel(), context.getClickedPos());
    }

    @Override
    public void tick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        super.tick(pState, pLevel, pPos, pRand);
        FallingBlockPhys.scheduleTick(this, pLevel, pPos);
        updateTickSlidingPile(pLevel, pPos, pRand);
        slideToNeighbours(pLevel, pPos);
    }

    @Override
    public FallingBlockCustomEntity createFallingEntity(World level, BlockPos pos, BlockState blockState) {
        return new FallingPileBlockEntity(level, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, level.getBlockState(pos));
    }

    public void fallBelow(ServerWorld pLevel, BlockPos pPos) {
        BlockState curState = pLevel.getBlockState(pPos);
        BlockState belowState = pLevel.getBlockState(pPos.below());
        if(belowState.is(this) && curState.is(this)) {
            int belowLayers = belowState.getValue(LAYERS);
            int currentLayers = curState.getValue(LAYERS);
            if(currentLayers > 0 && belowLayers < 8) {
                if(currentLayers + belowLayers <= 8) {
                    pLevel.setBlockAndUpdate(pPos.below(), getBlockStateForFluidEnvironment(belowState.setValue(LAYERS, belowLayers + currentLayers), pLevel, pPos));
                    pLevel.removeBlock(pPos, false);
                } else {
                    pLevel.setBlockAndUpdate(pPos.below(), getBlockStateForFluidEnvironment(belowState.setValue(LAYERS, 8), pLevel, pPos));
                    pLevel.setBlockAndUpdate(pPos, getBlockStateForFluidEnvironment(curState.setValue(LAYERS, currentLayers + belowLayers - 8), pLevel, pPos));
                }
            }
        }
    }

    public void updateTickSlidingPile(ServerWorld pLevel, BlockPos pPos, Random pRandom) {
        BlockState pState = pLevel.getBlockState(pPos);
        if(!pState.is(this)) {
            return;
        }
        fallBelow(pLevel, pPos);
    }

    public void slideToNeighbours(World level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        if(!blockState.is(this)) {
            return;
        }
        int layers = blockState.getValue(LAYERS);
        final int startLayers = layers;
        if(layers > 1) {
            int[] neighbors = new int[4];
            for(int i = 0; i < DirectionUtils.HORIZONTAL.length; ++i) {
                Direction dir = DirectionUtils.HORIZONTAL[i];
                BlockState neighbor = level.getBlockState(pos.relative(dir));
                neighbors[i] = getBlockLayers(neighbor, level, pos.relative(dir));
            }

            while(layers > 1) {
                int minIndex = 0;
                for(int i = 1; i < neighbors.length; ++i) {
                    if(neighbors[minIndex] < 0 || (neighbors[i] < neighbors[minIndex] && neighbors[i] >= 0)) {
                        minIndex = i;
                    }
                }
                int minLayers = neighbors[minIndex];
                if(minLayers >= 0 && minLayers < 8 && layers - minLayers > 1) {
                    --layers;
                    ++neighbors[minIndex];
                } else break;
            }

            if(startLayers != layers) {
                level.setBlock(pos, getBlockStateForFluidEnvironment(blockState.setValue(LAYERS, layers), level, pos), 2);
                for(int i = 0; i < DirectionUtils.HORIZONTAL.length; ++i) {
                    if(neighbors[i] > 0) {
                        Direction dir = DirectionUtils.HORIZONTAL[i];
                        BlockState state;
                        if(!(state = level.getBlockState(pos.relative(dir))).is(this)) {
                            state = getBlockStateForFluidEnvironment(defaultBlockState(), level, pos.relative(dir));
                        }
                        level.setBlock(pos.relative(dir), state.setValue(LAYERS, neighbors[i]), 2);
                        level.updateNeighborsAtExceptFromFacing(pos.relative(dir), Blocks.AIR, dir.getOpposite());
                    }
                }
            }
        }
    }

    private int getBlockLayers(BlockState blockState, World level, BlockPos pos) {
        int res = -1;
        if(blockState.is(this)) {
            res = blockState.getValue(LAYERS);
        } else if(blockState.canBeReplaced(new DirectionalPlaceContext(level, pos, Direction.DOWN, ItemStack.EMPTY, Direction.UP))
                && canSurvive(blockState, level, pos)) {
            res = 0;
        }
        return res;
    }

    private BlockState getBlockStateForFluidEnvironment(BlockState blockState, IWorld level, BlockPos pos) {
        FluidState fluidstate = level.getFluidState(pos);
        return blockState.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
