package com.realmining.realminingmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.World;

import java.util.Random;

public class MudBlock extends Block {
    public MudBlock(Properties p_i48388_1_) {
        super(p_i48388_1_);
    }
    public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos pos, Entity entity) {
        entity.makeStuckInBlock(p_196262_1_, new Vector3d(0.25D, (double)0.05F, 0.25D));
        if (entity instanceof LivingEntity && entity.isAlive() && entity.getEyeY() < (double)(pos.getY() + 1)) {
            entity.hurt(DamageSource.IN_WALL, 1.0F);
        }
    }

    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidState) {
        return true;
    }
}
