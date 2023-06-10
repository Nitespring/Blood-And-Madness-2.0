package com.nitespring.bloodborne.client.render.items.animatedweapons;


import com.nitespring.bloodborne.common.items.weapons.special.BeastCutterWhip;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;

import software.bernie.geckolib.renderer.GeoItemRenderer;


public class BeastCutterGeoRenderer extends GeoItemRenderer<BeastCutterWhip> {

	public BeastCutterGeoRenderer() {
		super(new BeastCutterModel());
	}
	

}
