package com.realmining.realminingmod.items;


import com.realmining.realminingmod.RealMiningMod;
import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.blocks.RockBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealMiningMod.MOD_ID);

    public static final RegistryObject<Item> WOOD_GLUE = ITEMS.register("wood_glue", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> GRANITE_COBBLE = ITEMS.register("granite_cobble", () -> new BlockItem(ModBlocks.GRANITE_COBBLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE = ITEMS.register("diorite_cobble", () -> new BlockItem(ModBlocks.DIORITE_COBBLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE = ITEMS.register("andesite_cobble", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_STAIRS = ITEMS.register("granite_cobble_stairs", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_SLAB = ITEMS.register("granite_cobble_slab", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_SLAB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_WALL = ITEMS.register("granite_cobble_wall", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_WALL.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_STAIRS = ITEMS.register("diorite_cobble_stairs", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_SLAB = ITEMS.register("diorite_cobble_slab", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_SLAB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_WALL = ITEMS.register("diorite_cobble_wall", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_WALL.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_STAIRS = ITEMS.register("andesite_cobble_stairs", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_SLAB = ITEMS.register("andesite_cobble_slab", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_SLAB.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_WALL = ITEMS.register("andesite_cobble_wall", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_WALL.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> GRAVEL_PILE = ITEMS.register("gravel_pile", () -> new BlockItem(ModBlocks.GRAVEL_PILE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SAND_PILE = ITEMS.register("sand_pile", () -> new BlockItem(ModBlocks.SAND_PILE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIRT_PILE = ITEMS.register("dirt_pile", () -> new BlockItem(ModBlocks.DIRT_PILE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> COAL_ORE_SAMPLE = ITEMS.register("coal_ore_sample", () -> new BlockItem(ModBlocks.COAL_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> IRON_ORE_SAMPLE = ITEMS.register("iron_ore_sample", () -> new BlockItem(ModBlocks.IRON_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIAMOND_ORE_SAMPLE = ITEMS.register("diamond_ore_sample", () -> new BlockItem(ModBlocks.DIAMOND_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GOLD_ORE_SAMPLE = ITEMS.register("gold_ore_sample", () -> new BlockItem(ModBlocks.GOLD_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> EMERALD_ORE_SAMPLE = ITEMS.register("emerald_ore_sample", () -> new BlockItem(ModBlocks.EMERALD_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> REDSTONE_ORE_SAMPLE = ITEMS.register("redstone_ore_sample", () -> new BlockItem(ModBlocks.REDSTONE_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> LAPIS_ORE_SAMPLE = ITEMS.register("lapis_ore_sample", () -> new BlockItem(ModBlocks.LAPIS_ORE_SAMPLE.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> SIMPLE_GRAVEL = ITEMS.register("simple_gravel", () -> new BlockItem(ModBlocks.SIMPLE_GRAVEL.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
}