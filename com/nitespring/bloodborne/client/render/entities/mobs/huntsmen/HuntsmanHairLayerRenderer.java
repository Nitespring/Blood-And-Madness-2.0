package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.b3d.B3DModel.Texture;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class HuntsmanHairLayerRenderer extends GeoLayerRenderer<HuntsmanAxeEntity>{

	private static final ResourceLocation LAYER = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_black.png");
	 private static final ResourceLocation MODEL = new ResourceLocation(BloodborneMod.MOD_ID, "geo/huntsman_axe.geo.json");
	
	public HuntsmanHairLayerRenderer(IGeoRenderer<HuntsmanAxeEntity> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	

	//@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn,HuntsmanAxeEntity entityLivingBaseIn, 
			float limbSwing, float limbSwingAmount, float partialTicks,float ageInTicks, float netHeadYaw, float headPitch) {
		 RenderType cameo =  RenderType.armorCutoutNoCull(LAYER);
	
		matrixStackIn.pushPose();
		  matrixStackIn.scale(1.001f, 1.001f, 1.001f);
	        matrixStackIn.translate(0.0d, 0.0d, 0.0d);
	      this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, 
	    		 matrixStackIn, bufferIn, bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
		
	        
		matrixStackIn.popPose();
	}

	

	

	

}
