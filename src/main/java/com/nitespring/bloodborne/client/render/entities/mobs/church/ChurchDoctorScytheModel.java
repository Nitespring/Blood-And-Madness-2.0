package com.nitespring.bloodborne.client.render.entities.mobs.church;

import javax.annotation.Nullable;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ChurchDoctorScytheModel extends GeoModel<ChurchDoctorScythe>
{

	public ChurchDoctorScytheModel() {

	}

    @Override
    public ResourceLocation getModelResource(ChurchDoctorScythe object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/church_doctor_scythe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChurchDoctorScythe object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/church/church_doctor_scythe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChurchDoctorScythe object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/church_doctor_scythe.animation.json");
      
    }

    @Override
	public void setCustomAnimations(ChurchDoctorScythe entity, long uniqueID, AnimationState<ChurchDoctorScythe> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}
  
	
}