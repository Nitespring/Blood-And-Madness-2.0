package com.nitespring.bloodborne.client.render.entities.mobs.church;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorPistol;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChurchDoctorPistolGeoRenderer extends GeoEntityRenderer<ChurchDoctorPistol>{

	public ChurchDoctorPistolGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new ChurchDoctorPistolModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	
	@Override
	public void render(ChurchDoctorPistol entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		float scaleFactor = 1.1f;
		poseStack.pushPose();
		poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		poseStack.translate(0, 0, 0);

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		
		poseStack.popPose();
	}
	
	
	@Override
	protected float getDeathMaxRotation(ChurchDoctorPistol entityLivingBaseIn) {
		
		return 0f;
	}

}
