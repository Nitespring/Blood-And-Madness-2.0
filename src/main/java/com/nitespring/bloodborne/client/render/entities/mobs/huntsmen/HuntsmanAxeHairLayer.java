package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractHuntsmanEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class HuntsmanAxeHairLayer<T extends HuntsmanAxeEntity> extends GeoRenderLayer<HuntsmanAxeEntity>{

	private static final ResourceLocation LAYER = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_black.png");
	 private static final ResourceLocation MODEL = new ResourceLocation(BloodborneMod.MOD_ID, "geo/huntsman_axe.geo.json");
	
	public HuntsmanAxeHairLayer(GeoRenderer<HuntsmanAxeEntity> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	

	@Override
	public void render(PoseStack poseStack, HuntsmanAxeEntity animatable, BakedGeoModel bakedModel, RenderType renderType,
			MultiBufferSource bufferSource, VertexConsumer bufferIn, float partialTick,
			int packedLight, int packedOverlay) {
		
		
		
		RenderType cameo = RenderType.armorCutoutNoCull(HuntsmanResourceLocations.getHuntsmanHairColourLocation(animatable));
		
		
		
		 
	
		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		
		
	        
	}

	

	

}
