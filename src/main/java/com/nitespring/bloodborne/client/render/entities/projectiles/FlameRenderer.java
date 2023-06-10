package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.nitespring.bloodborne.common.entities.projectiles.FlameProjectileEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class FlameRenderer extends ThrownItemRenderer<FlameProjectileEntity>{

	public FlameRenderer(Context p_174416_) {
		super(p_174416_, 0.4f, true);
		
	}

}
