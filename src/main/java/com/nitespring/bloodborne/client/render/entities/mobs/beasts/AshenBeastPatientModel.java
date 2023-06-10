package com.nitespring.bloodborne.client.render.entities.mobs.beasts;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class AshenBeastPatientModel extends GeoModel<AshenBeastPatient>
{

	public AshenBeastPatientModel() {

	}

    @Override
    public ResourceLocation getModelResource(AshenBeastPatient object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/cloaked_beast_patient.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AshenBeastPatient object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/beast/beast_patient.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AshenBeastPatient object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/cloaked_beast_patient.animation.json");
      
    }

    @Override
	public void setCustomAnimations(AshenBeastPatient entity, long uniqueID, AnimationState<AshenBeastPatient> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}
  
	
}