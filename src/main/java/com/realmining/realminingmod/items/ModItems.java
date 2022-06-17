package com.realmining.realminingmod.items;


import com.realmining.realminingmod.RealMiningMod;
import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.util.groups.ModItemGroups;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealMiningMod.MOD_ID);

    public static final RegistryObject<Item> WOOD_GLUE = ITEMS.register("wood_glue", () -> new Item(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar", () -> new Item(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> NAILS = ITEMS.register("nails", () -> new Item(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS)));


    public static final RegistryObject<Item> GRANITE_COBBLE = ITEMS.register("granite_cobble", () -> new BlockItem(ModBlocks.GRANITE_COBBLE.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE = ITEMS.register("diorite_cobble", () -> new BlockItem(ModBlocks.DIORITE_COBBLE.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE = ITEMS.register("andesite_cobble", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_STAIRS = ITEMS.register("granite_cobble_stairs", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_SLAB = ITEMS.register("granite_cobble_slab", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_SLAB.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_COBBLE_WALL = ITEMS.register("granite_cobble_wall", () -> new BlockItem(ModBlocks.GRANITE_COBBLE_WALL.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_STAIRS = ITEMS.register("diorite_cobble_stairs", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_SLAB = ITEMS.register("diorite_cobble_slab", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_SLAB.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_COBBLE_WALL = ITEMS.register("diorite_cobble_wall", () -> new BlockItem(ModBlocks.DIORITE_COBBLE_WALL.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_STAIRS = ITEMS.register("andesite_cobble_stairs", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_STAIRS.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_SLAB = ITEMS.register("andesite_cobble_slab", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_SLAB.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> ANDESITE_COBBLE_WALL = ITEMS.register("andesite_cobble_wall", () -> new BlockItem(ModBlocks.ANDESITE_COBBLE_WALL.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> STONE_PEBBLE = ITEMS.register("stone_pebble", () -> new RockItem(new Item.Properties().stacksTo(16).tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> GRANITE_PEBBLE = ITEMS.register("granite_pebble", () -> new RockItem(new Item.Properties().stacksTo(16).tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> ANDESITE_PEBBLE = ITEMS.register("andesite_pebble", () -> new RockItem(new Item.Properties().stacksTo(16).tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> DIORITE_PEBBLE = ITEMS.register("diorite_pebble", () -> new RockItem(new Item.Properties().stacksTo(16).tab(ModItemGroups.REALMINING_ITEMS)));

    public static final RegistryObject<Item> OAK_PLANK = ITEMS.register("oak_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS), 200));
    public static final RegistryObject<Item> BIRCH_PLANK = ITEMS.register("birch_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS), 200));
    public static final RegistryObject<Item> SPUCE_PLANK = ITEMS.register("spruce_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));
    public static final RegistryObject<Item> JUNGLE_PLANK = ITEMS.register("jungle_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));
    public static final RegistryObject<Item> DARK_OAK_PLANK = ITEMS.register("dark_oak_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));
    public static final RegistryObject<Item> ACACIA_PLANK = ITEMS.register("acacia_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));
    public static final RegistryObject<Item> WARPED_PLANK = ITEMS.register("warped_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));
    public static final RegistryObject<Item> CRIMSON_PLANK = ITEMS.register("crimson_plank", () -> new ModFuelItem(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS),200));

    public static final RegistryObject<Item> GRAVEL_PILE = ITEMS.register("gravel_pile", () -> new BlockItem(ModBlocks.GRAVEL_PILE.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SAND_PILE = ITEMS.register("sand_pile", () -> new BlockItem(ModBlocks.SAND_PILE.get(), new Item.Properties().tab(ModItemGroups.REALMINING_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> FLINT_PICKAXE = ITEMS.register("flint_pickaxe", () -> new PickaxeItem(ModItemTiers.FLINT, 1, -2.8F, (new Item.Properties()).tab(ModItemGroups.REALMINING_TOOLS)));
    public static final RegistryObject<Item> FLINT_AXE = ITEMS.register("flint_axe", () -> new HatchetItem());

    public static final RegistryObject<Item> FLINT_AXE_HEAD = ITEMS.register("flint_axe_head", () -> new Item(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS)));
    public static final RegistryObject<Item> FLINT_PICKAXE_HEAD = ITEMS.register("flint_pickaxe_head", () -> new Item(new Item.Properties().tab(ModItemGroups.REALMINING_ITEMS)));


}