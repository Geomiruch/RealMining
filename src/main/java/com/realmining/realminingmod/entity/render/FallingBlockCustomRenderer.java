package com.realmining.realminingmod.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.realmining.realminingmod.entity.custom.FallingBlockCustomEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FallingBlockCustomRenderer extends EntityRenderer<FallingBlockCustomEntity> {
    public FallingBlockCustomRenderer(EntityRendererManager p_i46177_1_) {
        super(p_i46177_1_);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void render(FallingBlockCustomEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        BlockState blockstate = p_225623_1_.getBlockState();
        if (blockstate.getRenderShape() == BlockRenderType.MODEL) {
            World world = p_225623_1_.getLevel();
            if (blockstate != world.getBlockState(p_225623_1_.blockPosition()) && blockstate.getRenderShape() != BlockRenderType.INVISIBLE) {
                p_225623_4_.pushPose();
                BlockPos blockpos = new BlockPos(p_225623_1_.getX(), p_225623_1_.getBoundingBox().maxY, p_225623_1_.getZ());
                p_225623_4_.translate(-0.5D, 0.0D, -0.5D);
                BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
                for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.chunkBufferLayers()) {
                    if (RenderTypeLookup.canRenderInLayer(blockstate, type)) {
                        net.minecraftforge.client.ForgeHooksClient.setRenderLayer(type);
                        blockrendererdispatcher.getModelRenderer().tesselateBlock(world, blockrendererdispatcher.getBlockModel(blockstate), blockstate, blockpos, p_225623_4_, p_225623_5_.getBuffer(type), false, new Random(), blockstate.getSeed(p_225623_1_.getStartPos()), OverlayTexture.NO_OVERLAY);
                    }
                }
                net.minecraftforge.client.ForgeHooksClient.setRenderLayer(null);
                p_225623_4_.popPose();
                super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
            }
        }
    }

    @Override
    public ResourceLocation getTextureLocation(FallingBlockCustomEntity p_110775_1_) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}