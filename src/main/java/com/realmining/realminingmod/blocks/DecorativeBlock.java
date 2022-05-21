package com.realmining.realminingmod.blocks;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class DecorativeBlock extends FallingBlock implements IWaterLoggable
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final VoxelShape shape;
    private final Lazy<Item> decorativeItem;

    public DecorativeBlock(Properties properties, VoxelShape shape, Lazy<Item> decorativeItem)
    {
        super(properties);
        this.shape = shape;
        this.decorativeItem = decorativeItem;
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false));
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext context)
    {
        Vector3d offset = blockState.getOffset(level, pos);
        return this.shape.move(offset.x(), 0, offset.z());
    }


    @Override
    @SuppressWarnings("deprecation")
    public PushReaction getPistonPushReaction(BlockState blockState)
    {
        return PushReaction.DESTROY;
    }


    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(@Nullable BlockState blockState, IWorldReader level, BlockPos blockPos)
    {
        BlockState blockStateBelow = level.getBlockState(blockPos.below());
        return (level.getBlockState(blockPos).getBlock() instanceof AirBlock
                || level.getBlockState(blockPos).getFluidState().equals(Fluids.WATER.getSource(false)))
                && blockStateBelow.canOcclude();
    }


    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        for (Direction direction : context.getNearestLookingDirections())
        {
            if (direction.getAxis() == Direction.Axis.Y)
            {
                BlockState blockstate = this.defaultBlockState();
                if (blockstate.canSurvive(context.getLevel(), context.getClickedPos()))
                    return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            }
        }

        return null;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, World level, BlockPos blockPos, Random random)
    {
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(WATERLOGGED);
    }


    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState blockState, World level, BlockPos blockPos, @Nullable PlayerEntity player, Hand hand, @Nullable BlockRayTraceResult blockRayTraceResult)
    {
        level.setBlockAndUpdate(blockPos, this.getFluidState(blockState).equals(Fluids.EMPTY.defaultFluidState()) ? Blocks.AIR.defaultBlockState() : this.getFluidState(blockState).createLegacyBlock());
        Block.popResource(level, blockPos, new ItemStack(this.getDecorativeItem(), 1));
        return ActionResultType.SUCCESS;
    }


    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld level, BlockPos currentPos, BlockPos facingPos)
    {
        if (stateIn.getValue(WATERLOGGED))
            level.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }


    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    public Item getDecorativeItem()
    {
        return this.decorativeItem.get();
    }


    @Override
    public OffsetType getOffsetType()
    {
        return OffsetType.XZ;
    }
}