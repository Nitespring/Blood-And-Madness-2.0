package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class HuntsmanAxeModel extends GeoModel<HuntsmanAxeEntity>
{

	public HuntsmanAxeModel() {

	}

    @Override
    public ResourceLocation getModelResource(HuntsmanAxeEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/huntsman_axe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HuntsmanAxeEntity object)
    {
    	return HuntsmanResourceLocations.getHuntsmanCoatColourLocation(object);
    	
    	
    }

    @Override
    public ResourceLocation getAnimationResource(HuntsmanAxeEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/huntsman.animation.json");
      
    }

    @Override
	public void setCustomAnimations(HuntsmanAxeEntity entity, long uniqueID, AnimationState<HuntsmanAxeEntity> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}
    
    
  
	
}