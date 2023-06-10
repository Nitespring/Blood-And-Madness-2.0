package com.nitespring.bloodborne.client.render.entities.mobs.kin;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class BrainsuckerModel extends GeoModel<Brainsucker>{

	@Override
	public ResourceLocation getAnimationResource(Brainsucker animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/brainsucker.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Brainsucker object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/brainsucker.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Brainsucker object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/kin/brainsucker.png");
	}
	
	
	@Override
	public void setCustomAnimations(Brainsucker entity, long uniqueID, AnimationState<Brainsucker> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
