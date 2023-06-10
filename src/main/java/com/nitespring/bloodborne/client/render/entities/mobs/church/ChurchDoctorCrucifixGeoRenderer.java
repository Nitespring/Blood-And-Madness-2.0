package com.nitespring.bloodborne.client.render.entities.mobs.church;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorCrucifix;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChurchDoctorCrucifixGeoRenderer extends GeoEntityRenderer<ChurchDoctorCrucifix>{

	public ChurchDoctorCrucifixGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new ChurchDoctorCrucifixModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	public void render(ChurchDoctorCrucifix entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		float scaleFactor = 1.1f;
		poseStack.pushPose();
		poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		poseStack.translate(0, 0, 0);

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		
		poseStack.popPose();
	}
	
	@Override
	protected float getDeathMaxRotation(ChurchDoctorCrucifix entityLivingBaseIn) {
		
		return 0f;
	}

}
