package com.nitespring.bloodborne.client.render.entities.mobs.church;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctor;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class ChurchDoctorModel extends GeoModel<ChurchDoctor>{

	@Override
	public ResourceLocation getAnimationResource(ChurchDoctor animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/church_doctor.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ChurchDoctor object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/church_doctor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChurchDoctor object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/church/church_doctor.png");
	}
	
	
	@Override
	public void setCustomAnimations(ChurchDoctor entity, long uniqueID, AnimationState<ChurchDoctor> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
