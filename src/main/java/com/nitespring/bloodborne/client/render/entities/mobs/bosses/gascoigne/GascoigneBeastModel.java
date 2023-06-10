package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;


import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GascoigneBeastModel extends GeoModel<GascoigneBeastBossEntity>
{
	
	public GascoigneBeastModel() {

	}
    @Override
    public ResourceLocation getModelResource(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/beast_gascoigne.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/hunters/gascoigne_beast.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/gascoigne_beast.animation.json");
      
    }

	
    
    @Override
	public void setCustomAnimations(GascoigneBeastBossEntity entity, long uniqueID, AnimationState<GascoigneBeastBossEntity> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        
		
	}

	
	
}