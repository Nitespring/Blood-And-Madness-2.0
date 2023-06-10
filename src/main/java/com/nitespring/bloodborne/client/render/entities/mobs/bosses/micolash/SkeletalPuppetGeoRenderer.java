package com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash;



import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.common.entities.mobs.SkeletalPuppet;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LightLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkeletalPuppetGeoRenderer extends GeoEntityRenderer<SkeletalPuppet>{

	public SkeletalPuppetGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SkeletalPuppetModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(SkeletalPuppet entityLivingBaseIn) {
		
		return 0f;
	}
	
	
	@Override
	public RenderType getRenderType(SkeletalPuppet animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {

		return RenderType.entityTranslucent(texture);
	}
	
	
	@Override
	protected int getBlockLightLevel(SkeletalPuppet p_114496_, BlockPos p_114497_) {
		int l = 7;
		if(p_114496_.level().getBrightness(LightLayer.BLOCK, p_114497_)>=7) {
			return super.getBlockLightLevel(p_114496_, p_114497_);
		}else {
			return l;
		}
	}
	
	

}
