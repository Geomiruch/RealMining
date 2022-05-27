package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;

public class FallingDamagingBlock extends FallingCustomBlock {

    public FallingDamagingBlock(Properties p_i48401_1_) {
        super(p_i48401_1_);
    }

    @Override
    public void onFalling(FallingBlockCustomEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(true);
    }
}
