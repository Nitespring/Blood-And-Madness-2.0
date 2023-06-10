package com.nitespring.bloodborne.client.render.entities.mobs.kin;

import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BrainsuckerGeoRenderer extends GeoEntityRenderer<Brainsucker>{

	public BrainsuckerGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BrainsuckerModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(Brainsucker entityLivingBaseIn) {
		
		return 0f;
	}

	
	 @Override
	public RenderType getRenderType(Brainsucker animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		// TODO Auto-generated method stub
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 
}
