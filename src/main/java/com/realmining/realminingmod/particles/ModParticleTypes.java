package com.realmining.realminingmod.particles;

import com.mojang.serialization.Codec;
import com.realmining.realminingmod.RealMiningMod;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticleTypes {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RealMiningMod.MOD_ID);

    public static RegistryObject<ParticleType<BlockParticleData>> FALLING_DUST_CUSTOM =
            PARTICLE_TYPES.register("falling_dust_custom", () -> new ParticleType<BlockParticleData>(false, BlockParticleData.DESERIALIZER) {
                public Codec<BlockParticleData> codec() {
                    return BlockParticleData.codec(this);
                }
            });

    private static RegistryObject<BasicParticleType> register(String key, boolean alwaysShow) {
        return PARTICLE_TYPES.register(key, () -> new BasicParticleType(alwaysShow));
    }
}
