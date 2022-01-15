package com.nitespring.bloodborne.client.render.entities.mobs.beasts;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SilverbeastModel extends AnimatedGeoModel<SilverbeastEntity>
{

	public SilverbeastModel() {

	}

    @Override
    public ResourceLocation getModelLocation(SilverbeastEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/silverbeast.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SilverbeastEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/beast/silverbeast.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SilverbeastEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/silverbeast.animation.json");
      
    }

    @Override
	public void setLivingAnimations(SilverbeastEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
    	super.setLivingAnimations(entity, uniqueID, customPredicate);
    	IBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        
		
	}
  
	
}