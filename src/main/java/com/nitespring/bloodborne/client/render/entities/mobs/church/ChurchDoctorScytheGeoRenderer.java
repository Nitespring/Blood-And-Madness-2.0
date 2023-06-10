package com.nitespring.bloodborne.client.render.entities.mobs.church;


import com.mojang.blaze3d.vertex.PoseStack;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChurchDoctorScytheGeoRenderer extends GeoEntityRenderer<ChurchDoctorScythe> {
	
	

	
	
	
	public ChurchDoctorScytheGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new ChurchDoctorScytheModel());
        this.shadowRadius = 0.5F;
        this.addRenderLayer(new ChurchDoctorScytheItemLayer(this));
        
       
    }

	@Override
	public void render(ChurchDoctorScythe entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		float scaleFactor = 1.1f;
		poseStack.pushPose();
		poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		poseStack.translate(0, 0, 0);

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		
		poseStack.popPose();
	}
	
	

	
	@Override
	protected float getDeathMaxRotation(ChurchDoctorScythe entityLivingBaseIn) {	
		return 0f;
	}


	
}
