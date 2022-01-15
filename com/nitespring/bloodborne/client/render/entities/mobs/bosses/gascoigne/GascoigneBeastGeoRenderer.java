package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;







import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;



public class GascoigneBeastGeoRenderer extends GeoEntityRenderer<GascoigneBeastBossEntity> {
	public GascoigneBeastGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager,new GascoigneBeastModel());
        
        this.shadowRadius = 1.0F; //change 0.7 to the desired shadow size.
       
    }
//BikeGeoRenderer


	@Override
	protected float getDeathMaxRotation(GascoigneBeastBossEntity entityLivingBaseIn) {
		// TODO Auto-generated method stub
		return 0;
	}

    
    
    
    
    
   
	
}
