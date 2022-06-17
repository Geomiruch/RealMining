package com.realmining.realminingmod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class RockEntity extends SnowballEntity {
    public RockEntity(World p_i1774_1_, LivingEntity p_i1774_2_) {
        super(p_i1774_1_, p_i1774_2_);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult p_213868_1_)
    {
        super.onHitEntity(p_213868_1_);
        Entity entity = p_213868_1_.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)1);
    }
}
