package com.realmining.realminingmod.world.features.ores;

import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.world.features.ModOreFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class LapisOre {
    public static Feature<OreFeatureConfig> LAPIS_ORE_FEATURE = new ModOreFeature(OreFeatureConfig.CODEC, 900, ModBlocks.LAPIS_ORE_SAMPLE.get().defaultBlockState(), 6);
    public static final ConfiguredFeature<?, ?> Lapis_Ore_Feature = LAPIS_ORE_FEATURE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.defaultBlockState(), 40)).range(20).count(1);
    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND)) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Lapis_Ore_Feature);
        }
    }
}
