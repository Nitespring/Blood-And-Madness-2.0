package com.nitespring.bloodborne.client.render.entities.mobs.kin;


import com.nitespring.bloodborne.common.entities.mobs.kin.CelestialEmissary;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class CelestialEmissaryGeoRenderer extends GeoEntityRenderer<CelestialEmissary>{

	public CelestialEmissaryGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CelestialEmissaryModel());
        
        this.shadowRadius = 0.5F;
        this.addRenderLayer(new CelestialEmissaryEmissiveLayer<>(this));
        this.addRenderLayer(new CelestialEmissaryTranslucentLayer<>(this));
       
    }
	
	@Override
	protected float getDeathMaxRotation(CelestialEmissary entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	protected int getBlockLightLevel(CelestialEmissary e, BlockPos b) {

		if(e.level().getBrightness(LightLayer.BLOCK, b)>=5) {
			return 15;
		}
		else   {
			return e.level().getBrightness(LightLayer.BLOCK, b) +10;
		}
	
	

	}

}
