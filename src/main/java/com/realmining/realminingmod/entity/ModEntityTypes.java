package com.realmining.realminingmod.entity;

import com.realmining.realminingmod.RealMiningMod;
import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, RealMiningMod.MOD_ID);

    public static final RegistryObject<EntityType<FallingBlockCustomEntity>> FALLING_BLOCK_CUSTOM =
            ENTITY_TYPES.register("falling_block_custom",
                    () -> EntityType.Builder.<FallingBlockCustomEntity>of(FallingBlockCustomEntity::new,
                                    EntityClassification.MISC).sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(20)
                            .build(new ResourceLocation(RealMiningMod.MOD_ID, "falling_block_custom").toString()));
}
