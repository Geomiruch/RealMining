package com.realmining.realminingmod.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;

public class ModOreFeature extends OreFeature {

    public int chuncks;
    public BlockState sample;
    public int chance;

    public ModOreFeature(Codec<OreFeatureConfig> p_i231976_1_, int chuncks, BlockState sample, int chance) {
        super(p_i231976_1_);
        this.chuncks=chuncks;
        this.sample=sample;
        this.chance=chance;
    }

    @Override
    public boolean place(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, OreFeatureConfig p_241855_5_) {
        int ch = (int) (Math.random()*chuncks);
        if(ch!=chance)
        {
            return false;
        }
        else{
            int x = p_241855_4_.getX();
            int z = p_241855_4_.getZ();
            int count = p_241855_3_.nextInt(5) + 2;
            int[] numsx = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
            int[] numsz = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

            for(int i = 0; i < count; ++i) {
                int indexX = p_241855_3_.nextInt(10);
                int indexZ = p_241855_3_.nextInt(10);

                for(int y = 256; y > 0; --y) {
                    BlockPos position = new BlockPos(x + numsx[indexX], y, z + numsz[indexZ]);
                    if (p_241855_1_.getBlockState(position).getMaterial() == Material.DIRT || p_241855_1_.getBlockState(position).getMaterial() == Material.STONE || p_241855_1_.getBlockState(position).getMaterial() == Material.SAND || p_241855_1_.getBlockState(position).getMaterial() == Material.GRASS) {
                        position = new BlockPos(x + numsx[indexX], y + 1, z + numsz[indexZ]);
                        p_241855_1_.setBlock(position, sample, 2);
                        break;
                    }
                }
            }

            return super.place(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_, p_241855_5_);
        }
    }
}
