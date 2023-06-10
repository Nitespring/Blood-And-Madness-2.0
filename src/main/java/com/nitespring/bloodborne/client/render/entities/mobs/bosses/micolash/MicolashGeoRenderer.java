package com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash;

import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MicolashGeoRenderer extends GeoEntityRenderer<MicolashBossEntity>{

	public MicolashGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MicolashModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(MicolashBossEntity entityLivingBaseIn) {
		
		return 0f;
	}

}
