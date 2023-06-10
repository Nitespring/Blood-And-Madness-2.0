package com.nitespring.bloodborne.client.render.entities.mobs.church;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctor;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorFlamesprayer;
import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class ChurchDoctorFlamesprayerModel extends GeoModel<ChurchDoctorFlamesprayer>{

	@Override
	public ResourceLocation getAnimationResource(ChurchDoctorFlamesprayer animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/church_doctor_flamesprayer.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ChurchDoctorFlamesprayer object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/church_doctor_flamesprayer.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChurchDoctorFlamesprayer object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/church/church_doctor.png");
	}
	
	
	@Override
	public void setCustomAnimations(ChurchDoctorFlamesprayer entity, long uniqueID, AnimationState<ChurchDoctorFlamesprayer> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
