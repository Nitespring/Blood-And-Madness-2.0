package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.weapons.special.BeastCutterWhip;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class BeastCutterModel extends GeoModel<BeastCutterWhip>
{

	public BeastCutterModel() {

	}

    @Override
    public ResourceLocation getModelResource(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/beastcutter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/item/weapons/beastcutter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BeastCutterWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/beastcutter.animation.json");
      
    }

	
}