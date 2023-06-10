package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;



import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;


import net.minecraft.client.renderer.entity.EntityRendererProvider;

import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuntsmanAxeGeoRenderer extends GeoEntityRenderer<HuntsmanAxeEntity> {
	
	

	
	
	//ExampleGeoRenderer
	
	public HuntsmanAxeGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HuntsmanAxeModel());
        this.addRenderLayer(new HuntsmanAxeHairLayer<>(this));
        this.addRenderLayer(new HuntsmanAxeItemLayer(this));
        this.shadowRadius = 0.5F;
        
       
    }

	

	 
	

	
@Override
protected float getDeathMaxRotation(HuntsmanAxeEntity entityLivingBaseIn) {
	
	return 0f;
}





 
   
	
}
