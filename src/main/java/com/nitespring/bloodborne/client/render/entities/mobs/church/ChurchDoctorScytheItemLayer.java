package com.nitespring.bloodborne.client.render.entities.mobs.church;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class ChurchDoctorScytheItemLayer extends BlockAndItemGeoLayer<ChurchDoctorScythe>{

	public ChurchDoctorScytheItemLayer(GeoRenderer<ChurchDoctorScythe> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, ChurchDoctorScythe animatable) {
		 if (bone.getName().equals("item")) { 
		 		return new ItemStack(WeaponInit.CHURCH_SCYTHE.get());
		 	}else {
			 return null;
		 }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, ChurchDoctorScythe animatable) {
		if (bone.getName().equals("item")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else
		return null;
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, ChurchDoctorScythe animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	
	if (bone.getName().equals("item")) { 

		  poseStack.translate(0, -0.15, 0);
		  poseStack.scale(1.2f, 1.2f, 1.2f); 
		  poseStack.mulPose(Axis.XP.rotationDegrees(-90)); 
  
		  }
	
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
