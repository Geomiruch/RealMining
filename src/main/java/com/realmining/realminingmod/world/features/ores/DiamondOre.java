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

public class DiamondOre {
    public static Feature<OreFeatureConfig> DIAMOND_ORE_FEATURE = new ModOreFeature(OreFeatureConfig.CODEC, 1500, ModBlocks.DIAMOND_ORE_SAMPLE.get().defaultBlockState(), 2);
    public static final ConfiguredFeature<?, ?> Diamond_Ore_Feature = DIAMOND_ORE_FEATURE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.defaultBlockState(), 20)).range(10).count(1);
    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND)) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Diamond_Ore_Feature);
        }
    }
}
