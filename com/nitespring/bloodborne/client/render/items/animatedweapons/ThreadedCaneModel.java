package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ThreadedCaneModel extends AnimatedGeoModel<ThreadedCaneWhip>
{

	public ThreadedCaneModel() {

	}

    @Override
    public ResourceLocation getModelLocation(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/threaded_cane_trick.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/item/weapons/threaded_cane.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ThreadedCaneWhip object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/threaded_cane_trick.animation.json");
      
    }

	
}