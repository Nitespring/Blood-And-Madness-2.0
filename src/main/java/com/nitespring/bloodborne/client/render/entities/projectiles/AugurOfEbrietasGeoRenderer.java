package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AugurOfEbrietasGeoRenderer extends GeoEntityRenderer<AugurOfEbrietasEntity>{

	public AugurOfEbrietasGeoRenderer(Context renderManager) {
		super(renderManager, new AugurOfEbrietasModel());
		
	}
	
	
	@Override
	public void render( AugurOfEbrietasEntity animatable, float entityYaw, float partialTicks,
			PoseStack matrixStackIn, MultiBufferSource renderTypeBuffer,
			int packedLightIn) {

		matrixStackIn.pushPose();
		matrixStackIn.scale(0.75f, 0.75f, 0.75f);
		
		
		super.render( animatable, entityYaw, partialTicks,  matrixStackIn, renderTypeBuffer,  packedLightIn);
		matrixStackIn.popPose();
	}

}
