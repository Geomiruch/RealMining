package com.realmining.realminingmod.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;


public class RockBlock extends DecorativeBlock
{
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    private static final Supplier<VoxelShape> SHAPE = () ->
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D);
    private static final Supplier<Properties> PROPERTIES =
            () -> AbstractBlock.Properties
                    .copy(Blocks.STONE)
                    .noCollission()
                    .noOcclusion()
                    .instabreak();


    public RockBlock()
    {
        super(PROPERTIES.get(), SHAPE.get(), () -> Items.COBBLESTONE);
    }


    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot)
    {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }


    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(RockBlock.WATERLOGGED, FACING);
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
                BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
                if (blockstate.canSurvive(context.getLevel(), context.getClickedPos()))
                    return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            }
        }

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    public BlockState getStateWithRandomDirection(IWorld level)
    {
        return this.getStateWithRandomDirection(level.getRandom());
    }


    public BlockState getStateWithRandomDirection(@Nonnull Random random)
    {
        return this.defaultBlockState().setValue(FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));
    }

    @Override
    public Item asItem()
    {
        return this.getDecorativeItem();
    }
}