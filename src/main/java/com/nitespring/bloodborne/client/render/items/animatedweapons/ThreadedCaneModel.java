package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class ThreadedCaneModel extends GeoModel<ThreadedCaneWhip>
{

	public ThreadedCaneModel() {

	}

    @Override
    public ResourceLocation getModelResource(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/threaded_cane_trick.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/item/weapons/threaded_cane.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/threaded_cane_trick.animation.json");
      
    }

	
}