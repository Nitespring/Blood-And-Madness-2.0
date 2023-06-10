package com.nitespring.bloodborne.client.render.entities.projectiles;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AugurOfEbrietasModel extends GeoModel<AugurOfEbrietasEntity>{

	@Override
	public ResourceLocation getAnimationResource(AugurOfEbrietasEntity animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/augur_of_ebrietas.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AugurOfEbrietasEntity object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/augur_of_ebrietas.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AugurOfEbrietasEntity object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/projectiles/augur_of_ebrietas.png");
	}
	
	
	  @Override
	public void setCustomAnimations(AugurOfEbrietasEntity animatable, long instanceId,
			AnimationState<AugurOfEbrietasEntity> animationState) {
		
		super.setCustomAnimations(animatable, instanceId, animationState);
		
		
		super.setCustomAnimations(animatable, instanceId, animationState);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("root");
        assert animationState != null;
        head.setRotX(animatable.getXRot());
        head.setRotY(animatable.getYRot());
	}

}
