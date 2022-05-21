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

public class IronOre {
    public static Feature<OreFeatureConfig> IRON_ORE_FEATURE = new ModOreFeature(OreFeatureConfig.CODEC, 200, ModBlocks.IRON_ORE_SAMPLE.get().defaultBlockState(), 5);
    public static final ConfiguredFeature<?, ?> Iron_Ore_Feature = IRON_ORE_FEATURE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 70)).range(100).count(1);
    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND)) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Iron_Ore_Feature);
        }
    }
}
