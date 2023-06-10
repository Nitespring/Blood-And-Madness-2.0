package com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.SkeletalPuppet;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SkeletalPuppetModel extends GeoModel<SkeletalPuppet>{

	@Override
	public ResourceLocation getAnimationResource(SkeletalPuppet animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/skeletal_puppet.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SkeletalPuppet object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/skeletal_puppet.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SkeletalPuppet object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/skeletal_puppet.png");
	}
	
	
	@Override
	public void setCustomAnimations(SkeletalPuppet entity, long uniqueID, AnimationState<SkeletalPuppet> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
