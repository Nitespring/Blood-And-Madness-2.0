package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;

import software.bernie.geckolib.renderer.GeoItemRenderer;


public class ThreadedCaneGeoRenderer extends GeoItemRenderer<ThreadedCaneWhip> {

	public ThreadedCaneGeoRenderer() {
		super(new ThreadedCaneModel());
	}
	

}
