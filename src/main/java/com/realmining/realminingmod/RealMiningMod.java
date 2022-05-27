package com.realmining.realminingmod;

import com.realmining.realminingmod.blocks.ModBlocks;
import com.realmining.realminingmod.entity.ModEntityTypes;
import com.realmining.realminingmod.entity.render.FallingBlockCustomRenderer;
import com.realmining.realminingmod.events.ModEvents;
import com.realmining.realminingmod.items.ModItems;
import com.realmining.realminingmod.particles.ModParticleTypes;
import com.realmining.realminingmod.particles.custom.FallingDustCustomParticle;
import com.realmining.realminingmod.sounds.ModSounds;
import com.realmining.realminingmod.world.features.StonesGen;
import com.realmining.realminingmod.world.features.ores.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RealMiningMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RealMiningMod
{
    private static RealMiningMod instance;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "realmining";

    public RealMiningMod() {
        instance=this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSounds.SOUNDS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);
        ModParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, ModEvents::biomeLoadingIntercept);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, StonesGen::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, CoalOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, IronOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, GoldOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, DiamondOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, RedstoneOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, LapisOre::generateOre);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, EmeraldOre::generateOre);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FALLING_BLOCK_CUSTOM.get(), FallingBlockCustomRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticleTypes.FALLING_DUST_CUSTOM.get(), FallingDustCustomParticle.Factory::new);
    }

    public static RealMiningMod getInstance() {
        return instance;
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
