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

public class RedstoneOre {
    public static Feature<OreFeatureConfig> REDSTONE_ORE_FEATURE = new ModOreFeature(OreFeatureConfig.CODEC, 675, ModBlocks.REDSTONE_ORE_SAMPLE.get().defaultBlockState(), 7);
    public static final ConfiguredFeature<?, ?> Redstone_Ore_Feature = REDSTONE_ORE_FEATURE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.defaultBlockState(), 50)).range(15).count(1);
    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND)) {
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Redstone_Ore_Feature);
        }
    }
}
