package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class FatherGascoigneModel extends AnimatedGeoModel<FatherGascoigneBossEntity>
{

	public FatherGascoigneModel() {

	}

    @Override
    public ResourceLocation getModelLocation(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/gascoigne.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/hunters/father_gascoigne.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FatherGascoigneBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/gascoigne.animation.json");
      
    }

    @Override
	public void setLivingAnimations(FatherGascoigneBossEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
    	super.setLivingAnimations(entity, uniqueID, customPredicate);
    	IBone head = this.getAnimationProcessor().getBone("head");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
       
		
	}
    
   

	
  

	
	
}