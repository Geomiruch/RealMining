package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class FallingCustomBlock extends Block implements IFallingBlock {
    public FallingCustomBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        FallingBlockPhys.scheduleTick(this, p_196271_4_, p_196271_5_);
        return super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
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
    public void onFalling(FallingBlockCustomEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(true);
    }

}
