package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;


import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GascoigneBeastModel extends AnimatedGeoModel<GascoigneBeastBossEntity>
{
	
	public GascoigneBeastModel() {

	}
    @Override
    public ResourceLocation getModelLocation(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/beast_gascoigne.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/hunters/gascoigne_beast.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GascoigneBeastBossEntity object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/gascoigne_beast.animation.json");
      
    }

	
    
    @Override
  	public void setLivingAnimations(GascoigneBeastBossEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
      	super.setLivingAnimations(entity, uniqueID, customPredicate);
      	IBone head = this.getAnimationProcessor().getBone("head");
          assert customPredicate != null;
          EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
          head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
          head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        
  		
  	}

	
	
}