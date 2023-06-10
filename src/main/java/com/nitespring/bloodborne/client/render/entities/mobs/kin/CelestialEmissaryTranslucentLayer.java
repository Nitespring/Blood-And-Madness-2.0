package com.nitespring.bloodborne.client.render.entities.mobs.kin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.kin.CelestialEmissary;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class CelestialEmissaryTranslucentLayer<T extends CelestialEmissary> extends GeoRenderLayer<CelestialEmissary>{

	private static final ResourceLocation LAYER = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/kin/celestial_emissary_translucent.png");
	 //private static final ResourceLocation MODEL = new ResourceLocation(BloodborneMod.MOD_ID, "geo/celestial_emissary.geo.json");
	
	public CelestialEmissaryTranslucentLayer(GeoRenderer<CelestialEmissary> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	

	@Override
	public void render(PoseStack poseStack, CelestialEmissary animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {
		RenderType cameo = RenderType.entityTranslucent(LAYER);

		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		
	}

	
	
	
	@Override
	protected ResourceLocation getTextureResource(CelestialEmissary animatable) {
	
		return LAYER;
	}
	


	

	

}
