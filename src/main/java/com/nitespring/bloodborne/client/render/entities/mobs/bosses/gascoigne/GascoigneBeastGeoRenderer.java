package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;

import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;



public class GascoigneBeastGeoRenderer extends GeoEntityRenderer<GascoigneBeastBossEntity> {
	public GascoigneBeastGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager,new GascoigneBeastModel());
        
        this.shadowRadius = 1.0F; 
       
    }


	@Override
	protected float getDeathMaxRotation(GascoigneBeastBossEntity entityLivingBaseIn) {
		return 0;
	}

    

    
    @Override
    public RenderType getRenderType(GascoigneBeastBossEntity animatable, ResourceLocation texture,
    		MultiBufferSource bufferSource, float partialTick) {
    	return RenderType.entityCutoutNoCull(texture);
    }
    
   
	
}
