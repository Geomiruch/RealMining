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

public class CoalOre {
    public static Feature<OreFeatureConfig> COAL_ORE_FEATURE = new ModOreFeature(OreFeatureConfig.CODEC, 400, ModBlocks.COAL_ORE_SAMPLE.get().defaultBlockState(), 1);
    public static final ConfiguredFeature<?, ?> Coal_Ore_Feature = COAL_ORE_FEATURE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.defaultBlockState(), 100)).range(20).count(3);

    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND)) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Coal_Ore_Feature);
        }
    }
}
