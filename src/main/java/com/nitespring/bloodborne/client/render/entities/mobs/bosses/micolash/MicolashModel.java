package com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MicolashModel extends GeoModel<MicolashBossEntity>{

	@Override
	public ResourceLocation getAnimationResource(MicolashBossEntity animatable) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "animations/micolash.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MicolashBossEntity object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "geo/micolash.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MicolashBossEntity object) {
		
		return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/boss/micolash.png");
	}
	
	
	@Override
	public void setCustomAnimations(MicolashBossEntity entity, long uniqueID, AnimationState<MicolashBossEntity> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_root");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

}
