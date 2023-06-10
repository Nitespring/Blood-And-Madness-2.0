package com.nitespring.bloodborne.client.render.entities.mobs.beasts;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.BeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.CloakedBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractHuntsmanEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;


public class CloakedBeastPatientEyesLayer<T extends CloakedBeastPatient> extends GeoRenderLayer<CloakedBeastPatient>{

	private static final ResourceLocation LAYER = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/beast/beast_patient_eyes.png");
	 private static final ResourceLocation MODEL = new ResourceLocation(BloodborneMod.MOD_ID, "geo/cloaked_beast_patient.geo.json");
	
	public CloakedBeastPatientEyesLayer(GeoRenderer<CloakedBeastPatient> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	

	@Override
	public void render(PoseStack matrixStackIn, CloakedBeastPatient entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType,
			MultiBufferSource bufferSource, VertexConsumer bufferIn, float partialTicks,
			int packedLight, int packedOverlay) {
		
		
		if(entityLivingBaseIn.getCombatState()==1) {
		RenderType cameo = RenderType.eyes(LAYER);
		
		
		
		 
	
		matrixStackIn.pushPose();
	        matrixStackIn.translate(0.0d, 0.0d, 0.0d);
		      super.render(matrixStackIn, entityLivingBaseIn, bakedModel, cameo, 
		    		  bufferSource, bufferIn, partialTicks, packedLight, packedOverlay);
		
	        
		matrixStackIn.popPose();
		}
	}

	
	
	

	

	

}
