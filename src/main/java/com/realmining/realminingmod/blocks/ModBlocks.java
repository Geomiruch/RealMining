package com.realmining.realminingmod.blocks;

import com.realmining.realminingmod.RealMiningMod;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RealMiningMod.MOD_ID);

    public static final RegistryObject<Block> FALLING_COBBLE = BLOCKS.register("falling_cobble", () -> new SlidingBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> FALLING_GRANITE_COBBLE = BLOCKS.register("falling_granite_cobble", () -> new SlidingBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> FALLING_DIORITE_COBBLE = BLOCKS.register("falling_diorite_cobble", () -> new SlidingBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> FALLING_ANDESITE_COBBLE = BLOCKS.register("falling_andesite_cobble", () -> new SlidingBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRANITE_COBBLE = BLOCKS.register("granite_cobble", () -> new Block(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> DIORITE_COBBLE = BLOCKS.register("diorite_cobble", () -> new Block(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_COBBLE = BLOCKS.register("andesite_cobble", () -> new Block(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRANITE_COBBLE_STAIRS = BLOCKS.register("granite_cobble_stairs", () -> new StairsBlock(() -> GRANITE_COBBLE.get().defaultBlockState(), Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRANITE_COBBLE_SLAB = BLOCKS.register("granite_cobble_slab", () -> new SlabBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GRANITE_COBBLE_WALL = BLOCKS.register("granite_cobble_wall", () -> new WallBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_COBBLE_STAIRS = BLOCKS.register("andesite_cobble_stairs", () -> new StairsBlock(() -> ANDESITE_COBBLE.get().defaultBlockState(), Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_COBBLE_SLAB = BLOCKS.register("andesite_cobble_slab", () -> new SlabBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_COBBLE_WALL = BLOCKS.register("andesite_cobble_wall", () -> new WallBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> DIORITE_COBBLE_STAIRS = BLOCKS.register("diorite_cobble_stairs", () -> new StairsBlock(() -> DIORITE_COBBLE.get().defaultBlockState(), Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> DIORITE_COBBLE_SLAB = BLOCKS.register("diorite_cobble_slab", () -> new SlabBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> DIORITE_COBBLE_WALL = BLOCKS.register("diorite_cobble_wall", () -> new WallBlock(Block.Properties.of(Material.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> GRAVEL_PILE = BLOCKS.register("gravel_pile", () -> new PileBlock(Block.Properties.of(Material.DIRT).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL).strength(0.5F)));
    public static final RegistryObject<Block> SAND_PILE = BLOCKS.register("sand_pile", () -> new PileBlock(Block.Properties.of(Material.SAND).harvestTool(ToolType.SHOVEL).sound(SoundType.SAND).strength(0.6F)));
    public static final RegistryObject<Block> DIRT = BLOCKS.register("dirt", () -> new SlidingBlock(Block.Properties.of(Material.DIRT).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL).strength(0.6F)));

    public static final RegistryObject<Block> ACACIA_WORKING_STUB = BLOCKS.register("acacia_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BIRCH_WORKING_STUB = BLOCKS.register("birch_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DARK_OAK_WORKING_STUB = BLOCKS.register("dark_oak_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> OAK_WORKING_STUB = BLOCKS.register("oak_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SPRUCE_WORKING_STUB = BLOCKS.register("spruce_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JUNGLE_WORKING_STUB = BLOCKS.register("jungle_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CRIMSON_WORKING_STUB = BLOCKS.register("crimson_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WARPED_WORKING_STUB = BLOCKS.register("warped_working_stub", () -> new CraftingTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MUD = BLOCKS.register("mud", () -> new MudBlock(Block.Properties.of(Material.DIRT).noCollission().strength(0.6F).harvestTool(ToolType.SHOVEL).sound(SoundType.GRAVEL)));

}