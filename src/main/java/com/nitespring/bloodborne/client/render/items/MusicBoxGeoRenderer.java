package com.nitespring.bloodborne.client.render.items;


import com.nitespring.bloodborne.common.items.MusicBoxItem;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;

import software.bernie.geckolib.renderer.GeoItemRenderer;


public class MusicBoxGeoRenderer extends GeoItemRenderer<MusicBoxItem> {

	public MusicBoxGeoRenderer() {
		super(new MusicBoxModel());
	}
	

}
