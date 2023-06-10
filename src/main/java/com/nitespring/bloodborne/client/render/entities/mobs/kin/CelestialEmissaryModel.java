package com.nitespring.bloodborne.client.render.entities.mobs.kin;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;
import com.nitespring.bloodborne.common.entities.mobs.kin.CelestialEmissary;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class CelestialEmissaryModel extends GeoModel<CelestialEmissary>{

	@Override
	public ResourceLocation getAnimationResource(CelestialEmissary animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/celestial_emissary.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CelestialEmissary object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/celestial_emissary.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CelestialEmissary object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/kin/celestial_emissary.png");
	}
	
	
	@Override
	public void setCustomAnimations(CelestialEmissary entity, long uniqueID, AnimationState<CelestialEmissary> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
