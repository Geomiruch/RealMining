package com.realmining.realminingmod.events;

import com.realmining.realminingmod.RealMiningMod;
import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.blocks.PileBlock;
import com.realmining.realminingmod.items.ModItems;
import com.realmining.realminingmod.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = RealMiningMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEvents {
    public static ArrayList<BlockPos> getDirections(BlockPos mainPos)
    {
        BlockPos leftPos = mainPos.east();
        BlockPos rightPos=mainPos.west();
        BlockPos towardPos=mainPos.north();
        BlockPos backwardPos=mainPos.south();
        BlockPos upPos=mainPos.above();
        BlockPos downPos=mainPos.below();

        ArrayList<BlockPos> blockPos = new ArrayList<>();
        blockPos.add(leftPos);
        blockPos.add(rightPos);
        blockPos.add(towardPos);
        blockPos.add(backwardPos);
        blockPos.add(downPos);
        blockPos.add(upPos);

        return blockPos;
    }

    public static void PillarEffect(World world, BlockPos pos)
    {
        while(world.getBlockState(pos)==Blocks.SAND.defaultBlockState() || world.getBlockState(pos)==Blocks.DIRT.defaultBlockState() || world.getBlockState(pos)==Blocks.GRAVEL.defaultBlockState())
        {
            if(world.getBlockState(pos)==Blocks.SAND.defaultBlockState())
            {
                world.setBlockAndUpdate(pos, ModBlocks.SAND_PILE.get().defaultBlockState().setValue(PileBlock.LAYERS, 8));
            }
            if(world.getBlockState(pos)==Blocks.DIRT.defaultBlockState())
            {
                world.setBlockAndUpdate(pos, ModBlocks.DIRT.get().defaultBlockState());
            }
            if(world.getBlockState(pos)==Blocks.GRAVEL.defaultBlockState())
            {
                world.setBlockAndUpdate(pos, ModBlocks.GRAVEL_PILE.get().defaultBlockState().setValue(PileBlock.LAYERS, 8));
            }
            pos=pos.above();
        }
    }
    public static void DustTransformation(World world, BlockPos mainPos)
    {
        ArrayList<BlockPos> blockPos = new ArrayList<>();
        blockPos.add(mainPos.above());
        blockPos.add(mainPos.north());
        blockPos.add(mainPos.south());
        blockPos.add(mainPos.west());
        blockPos.add(mainPos.east());

        for (BlockPos block: blockPos)
        {
            if(world.getBlockState(block)==Blocks.SAND.defaultBlockState() || world.getBlockState(block)==Blocks.DIRT.defaultBlockState() || world.getBlockState(block)==Blocks.GRAVEL.defaultBlockState())
            {
                PillarEffect(world,block);
            }
        }
    }
    public static void StoneTransformation(World world, BlockPos mainPos, int chance )
    {
        ArrayList<BlockPos> blockPos = getDirections(mainPos);
            for (BlockPos block: blockPos)
            {
                double ch = Math.random()*100;
                if (ch <= chance)
                {
                    if(world.getBlockState(block).getBlock()==Blocks.STONE.getBlock())
                    {
                        world.setBlockAndUpdate(block, ModBlocks.FALLING_COBBLE.get().defaultBlockState());
                        if(chance!=15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_1.get(), SoundCategory.AMBIENT, 0.25F,1);
                        if(chance==15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_2.get(), SoundCategory.AMBIENT, 0.25F,1);
                        StoneTransformation(world, block, 15);
                    }
                    if(world.getBlockState(block).getBlock()==Blocks.ANDESITE.getBlock())
                    {
                        world.setBlockAndUpdate(block, ModBlocks.FALLING_ANDESITE_COBBLE.get().defaultBlockState());
                        if(chance!=15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_1.get(), SoundCategory.AMBIENT, 0.25F,1);
                        if(chance==15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_2.get(), SoundCategory.AMBIENT, 0.25F,1);
                        StoneTransformation(world, block, 15);
                    }
                    if(world.getBlockState(block).getBlock()==Blocks.DIORITE.getBlock())
                    {
                        world.setBlockAndUpdate(block, ModBlocks.FALLING_DIORITE_COBBLE.get().defaultBlockState());
                        if(chance!=15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_1.get(), SoundCategory.AMBIENT, 0.25F,1);
                        if(chance==15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_2.get(), SoundCategory.AMBIENT, 0.25F,1);
                        StoneTransformation(world, block, 15);
                    }
                    if(world.getBlockState(block).getBlock()==Blocks.GRANITE.getBlock())
                    {
                        world.setBlockAndUpdate(block, ModBlocks.FALLING_GRANITE_COBBLE.get().defaultBlockState());
                        if(chance!=15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_1.get(), SoundCategory.AMBIENT, 0.25F,1);
                        if(chance==15)
                            world.playSound(null, mainPos, ModSounds.ROCK_CRUMBLING_2.get(), SoundCategory.AMBIENT, 0.25F,1);
                        StoneTransformation(world, block, 15);
                    }
                }
            }
    }

    @SubscribeEvent
    public static void onBlockExplosion(ExplosionEvent.Detonate event)
    {
        List<BlockPos> blockPos = event.getAffectedBlocks();
        for (BlockPos block: blockPos)
        {
            StoneTransformation(event.getWorld(), block, 50);
        }
    }

    @SubscribeEvent
    public static void onBlockPlacing(BlockEvent.EntityPlaceEvent event)
    {
        World world = (World) event.getWorld();
        BlockPos blockPos = event.getPos();

        if(world.getBlockState(blockPos).getBlock()==Blocks.SAND.getBlock())
        {
            world.setBlockAndUpdate(blockPos, ModBlocks.SAND_PILE.get().defaultBlockState().setValue(PileBlock.LAYERS, 8));
        }
        if(world.getBlockState(blockPos).getBlock()==Blocks.GRAVEL.getBlock())
        {
            world.setBlockAndUpdate(blockPos, ModBlocks.GRAVEL_PILE.get().defaultBlockState().setValue(PileBlock.LAYERS, 8));
        }
        if(world.getBlockState(blockPos).getBlock()==Blocks.DIRT.getBlock())
        {
            world.setBlockAndUpdate(blockPos, ModBlocks.DIRT.get().defaultBlockState());
        }
    }

    @SubscribeEvent
    public static void onBlockBreaking(BlockEvent.BreakEvent event)
    {
        PlayerEntity player = event.getPlayer();

        DustTransformation((World) event.getWorld(), event.getPos());

        if(event.getState().getMaterial() == Material.STONE){
            Item item = player.getItemInHand(Hand.MAIN_HAND).getItem();
            if(item.getToolTypes(player.getItemInHand(Hand.MAIN_HAND).getStack()).contains(ToolType.PICKAXE))
            {
                if (item instanceof TieredItem)
                {
                    TieredItem pickaxe = (TieredItem) item;
                    if (pickaxe.getTier().getLevel() == 0) {
                        StoneTransformation((World) event.getWorld(), event.getPos(), 60);
                    }
                    if (pickaxe.getTier().getLevel() == 1) {
                        StoneTransformation((World) event.getWorld(), event.getPos(), 40);
                    }
                    if (pickaxe.getTier().getLevel() == 2) {
                        StoneTransformation((World) event.getWorld(), event.getPos(), 20);
                    }
                    if (pickaxe.getTier().getLevel() == 3) {
                        StoneTransformation((World) event.getWorld(), event.getPos(), 10);
                    }
                    if (pickaxe.getTier().getLevel() == 4) {
                        StoneTransformation((World) event.getWorld(), event.getPos(), 5);
                    }
                }

            }
        }
    }


    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event)
    {
        PlayerEntity player = event.getPlayer();
        BlockPos blockPos = event.getPos();
        World world = event.getWorld();

        if(player.getItemInHand(Hand.MAIN_HAND).getItem()==ModItems.MORTAR.get() || player.getItemInHand(Hand.MAIN_HAND).getItem()==ModItems.NAILS.get())
        {
            ItemStack stack = player.getItemInHand(Hand.MAIN_HAND).getStack();
            if(world.getBlockState(blockPos).getBlock()==ModBlocks.FALLING_COBBLE.get().getBlock())
            {
                stack.setCount(stack.getCount()-1);
                world.setBlockAndUpdate(blockPos, Blocks.COBBLESTONE.defaultBlockState());
            }
            if(world.getBlockState(blockPos).getBlock()==ModBlocks.FALLING_ANDESITE_COBBLE.get().getBlock())
            {
                stack.setCount(stack.getCount()-1);
                world.setBlockAndUpdate(blockPos, ModBlocks.ANDESITE_COBBLE.get().defaultBlockState());
            }
            if(world.getBlockState(blockPos).getBlock()==ModBlocks.FALLING_DIORITE_COBBLE.get().getBlock())
            {
                stack.setCount(stack.getCount()-1);
                world.setBlockAndUpdate(blockPos, ModBlocks.DIORITE_COBBLE.get().defaultBlockState());
            }
            if(world.getBlockState(blockPos).getBlock()==ModBlocks.FALLING_GRANITE_COBBLE.get().getBlock())
            {
                stack.setCount(stack.getCount()-1);
                world.setBlockAndUpdate(blockPos, ModBlocks.GRANITE_COBBLE.get().defaultBlockState());
            }
        }
    }

    @SubscribeEvent
    public static void onToolInteract(BlockEvent.BlockToolInteractEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockPos blockPos = event.getPos();
        BlockState blockState = event.getState();
        World world = (World) event.getWorld();
        if (blockState == Blocks.SPRUCE_LOG.defaultBlockState())
        {
            Item item = player.getItemInHand(Hand.MAIN_HAND).getItem();
            if(item.getToolTypes(player.getItemInHand(Hand.MAIN_HAND).getStack()).contains(ToolType.AXE)){
                if (item instanceof TieredItem){

                    TieredItem axe=(TieredItem) item;

                    if (axe.getTier().getLevel()==0) {
                        ItemStack stack = new ItemStack(ModItems.WOOD_GLUE.get(), 1);
                        ItemEntity itementity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                        world.addFreshEntity(itementity);
                    }
                    if (axe.getTier().getLevel()==1) {
                        ItemStack stack = new ItemStack(ModItems.WOOD_GLUE.get(), 2);
                        ItemEntity itementity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                        world.addFreshEntity(itementity);
                    }
                    if (axe.getTier().getLevel()==2) {
                        ItemStack stack = new ItemStack(ModItems.WOOD_GLUE.get(), 3);
                        ItemEntity itementity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                        world.addFreshEntity(itementity);
                    }
                    if (axe.getTier().getLevel()==3) {
                        ItemStack stack = new ItemStack(ModItems.WOOD_GLUE.get(), 4);
                        ItemEntity itementity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                        world.addFreshEntity(itementity);
                    }
                    if (axe.getTier().getLevel()==4) {
                        ItemStack stack = new ItemStack(ModItems.WOOD_GLUE.get(), 5);
                        ItemEntity itementity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                        world.addFreshEntity(itementity);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBreakingWithoutTools(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        BlockState blockState = event.getState();
        if(!(player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof AxeItem) && (blockState.getMaterial()==Material.WOOD || blockState.getMaterial()==Material.NETHER_WOOD))
        {
            event.setCanceled(true);
        }
        if(!(player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof PickaxeItem) && blockState.getMaterial()==Material.STONE)
        {
            event.setCanceled(true);
        }
    }
}
