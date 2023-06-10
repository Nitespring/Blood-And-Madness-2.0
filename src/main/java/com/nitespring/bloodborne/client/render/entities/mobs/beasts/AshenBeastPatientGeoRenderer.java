package com.nitespring.bloodborne.client.render.entities.mobs.beasts;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.BeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.CloakedBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AshenBeastPatientGeoRenderer extends GeoEntityRenderer<AshenBeastPatient> {
	
	

	
	
	//ExampleGeoRenderer
	
	public AshenBeastPatientGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new AshenBeastPatientModel());
        this.addRenderLayer(new AshenBeastPatientEyesLayer<AshenBeastPatient>(this));
        this.shadowRadius = 0.5F;
        
        
       
    }

	@Override
	public RenderType getRenderType(AshenBeastPatient animatable, ResourceLocation texture,
			MultiBufferSource bufferSource, float partialTick) {
		
		return RenderType.entityCutoutNoCull(texture);
	}

	 
	 @Override
	public void render(AshenBeastPatient animatable, float entityYaw, float partialTick, PoseStack matrixStackIn,
    		MultiBufferSource bufferSource, int packedLight) {
		
		 	float scaleFactor = 1.5f;
		 	matrixStackIn.pushPose();
		 	matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);

		 	matrixStackIn.translate(0, 0, 0);
		     
		    super.render( animatable, entityYaw, partialTick, matrixStackIn, bufferSource, packedLight);
		    matrixStackIn.popPose();
		 
		
	}

	
@Override
protected float getDeathMaxRotation(AshenBeastPatient entityLivingBaseIn) {
	
	return 0f;
}



   
   
	
}
