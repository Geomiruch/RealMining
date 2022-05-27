package com.realmining.realminingmod.world.features;

import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.blocks.PileBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.function.Supplier;

public class StonesGen {
    public static final ConfiguredFeature<?, ?> ANDESITE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.ANDESITE.defaultBlockState(), 33)).range(80).squared().count(10);
    public static final ConfiguredFeature<?, ?> DIORITE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIORITE.defaultBlockState(), 33)).range(80).squared().count(10);
    public static final ConfiguredFeature<?, ?> GRANITE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRANITE.defaultBlockState(), 33)).range(80).squared().count(10);
    public static final ConfiguredFeature<?, ?> DIRT = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.defaultBlockState(), 33)).range(256).squared().count(10);
    public static final ConfiguredFeature<?, ?> SIMPLE_GRAVEL = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SIMPLE_GRAVEL.get().defaultBlockState(), 33)).range(256).squared().count(8);

    @SubscribeEvent
    public static void generateOre(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if (!event.getCategory().equals(Biome.Category.THEEND) & !event.getCategory().equals(Biome.Category.NETHER)) { //OPTIONAL IF STATEMENT allows you to generate ores in specific biome types for example icy biomes
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ANDESITE);
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DIORITE);
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GRANITE);
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DIRT);
            generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SIMPLE_GRAVEL);
        }
    }
}
