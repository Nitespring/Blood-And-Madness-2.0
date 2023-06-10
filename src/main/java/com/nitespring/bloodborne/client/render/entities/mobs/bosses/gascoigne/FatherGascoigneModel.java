package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FatherGascoigneModel extends GeoModel<FatherGascoigneBossEntity>
{

	public FatherGascoigneModel() {

	}

    @Override
    public ResourceLocation getModelResource(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/gascoigne.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/hunters/father_gascoigne.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/gascoigne.animation.json");
      
    }

    @Override
	public void setCustomAnimations(FatherGascoigneBossEntity entity, long uniqueID, AnimationState<FatherGascoigneBossEntity> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}
    
   

	
  

	
	
}