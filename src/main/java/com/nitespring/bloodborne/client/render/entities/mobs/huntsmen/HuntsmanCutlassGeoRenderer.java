package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;




import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuntsmanCutlassGeoRenderer extends GeoEntityRenderer<HuntsmanCutlassEntity> {
	
	

	
	
	//ExampleGeoRenderer
	
	public HuntsmanCutlassGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HuntsmanCutlassModel());
        this.addRenderLayer(new HuntsmanCutlassHairLayer<>(this));
        this.addRenderLayer(new HuntsmanCutlassItemLayer(this));
        this.shadowRadius = 0.5F;
        
       
    }

	

	
	

	
@Override
protected float getDeathMaxRotation(HuntsmanCutlassEntity entityLivingBaseIn) {
	
	return 0f;
}
















   
   
	
}
