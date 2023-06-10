package com.nitespring.bloodborne.client.render.items;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.MusicBoxItem;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class MusicBoxModel extends GeoModel<MusicBoxItem>
{

	public MusicBoxModel() {

	}

    @Override
    public ResourceLocation getModelResource(MusicBoxItem object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "geo/music_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MusicBoxItem object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "textures/item/music_box.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MusicBoxItem object)
    {
        return new ResourceLocation(BloodborneMod.MOD_ID, "animations/music_box.animation.json");
      
    }

	
}