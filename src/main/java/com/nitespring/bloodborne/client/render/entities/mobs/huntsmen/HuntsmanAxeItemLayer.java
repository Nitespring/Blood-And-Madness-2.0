package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class HuntsmanAxeItemLayer extends BlockAndItemGeoLayer<HuntsmanAxeEntity>{

	public HuntsmanAxeItemLayer(GeoRenderer<HuntsmanAxeEntity> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, HuntsmanAxeEntity animatable) {
		 if (bone.getName().equals("right_hand_item")) { 
			 	return new ItemStack(WeaponInit.LIT_TORCH.get());
		 }else  if (bone.getName().equals("left_hand_item")) { 
			 	return new ItemStack(WeaponInit.HUNTING_AXE.get());
		 }else {
			 return null;
		 }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, HuntsmanAxeEntity animatable) {
		if (bone.getName().equals("right_hand_item")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("left_hand_item")) { 
			  return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		  }else
		return null;
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, HuntsmanAxeEntity animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	if (bone.getName().equals("right_hand_item")) { 
		poseStack.translate(0, -0.15, 0);  
		poseStack.mulPose(Axis.XP.rotationDegrees(-90)); 
		poseStack.mulPose(Axis.YP.rotationDegrees(0)); 
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
	  }
	if (bone.getName().equals("left_hand_item")) { 
		poseStack.translate(0, 0.3, -0.5); 
		poseStack.mulPose(Axis.XP.rotationDegrees(0)); 
		poseStack.mulPose(Axis.YP.rotationDegrees(0)); 
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));
   
      
	  }
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
