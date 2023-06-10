package com.nitespring.bloodborne.client.render.entities.mobs.beasts;


import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SilverbeastGeoRenderer extends GeoEntityRenderer<SilverbeastEntity> {
	
	

	public SilverbeastGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SilverbeastModel());
        this.addRenderLayer(new SilverBeastItemLayer(this));
        this.shadowRadius = 0.5F;
        
       
    }

	

	
@Override
protected float getDeathMaxRotation(SilverbeastEntity entityLivingBaseIn) {
	
	return 0f;
}



   
   
	
}
