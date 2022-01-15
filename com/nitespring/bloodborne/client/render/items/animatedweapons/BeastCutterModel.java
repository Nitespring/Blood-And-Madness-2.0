package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.weapons.special.BeastCutterWhip;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class BeastCutterModel extends AnimatedGeoModel<BeastCutterWhip>
{

	public BeastCutterModel() {

	}

    @Override
    public ResourceLocation getModelLocation(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/beastcutter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/item/weapons/beastcutter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/beastcutter.animation.json");
      
    }

	
}