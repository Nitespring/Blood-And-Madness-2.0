package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;


public class ThreadedCaneGeoRenderer extends GeoItemRenderer<ThreadedCaneWhip> {

	public ThreadedCaneGeoRenderer() {
		super(new ThreadedCaneModel());
	}
	

}
