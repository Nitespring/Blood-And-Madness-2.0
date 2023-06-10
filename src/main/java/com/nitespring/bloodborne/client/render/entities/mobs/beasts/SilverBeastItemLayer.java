package com.nitespring.bloodborne.client.render.entities.mobs.beasts;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class SilverBeastItemLayer extends BlockAndItemGeoLayer<SilverbeastEntity>{

	public SilverBeastItemLayer(GeoRenderer<SilverbeastEntity> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, SilverbeastEntity animatable) {
		 if (bone.getName().equals("item_right_hand")) { 
			 	return new ItemStack(WeaponInit.LIT_TORCH.get());
		 }else {
			 return null;
		 }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, SilverbeastEntity animatable) {
		if (bone.getName().equals("item_right_hand")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else
		return null;
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, SilverbeastEntity animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	if (bone.getName().equals("item_right_hand")) { 
		//poseStack.translate(0.37, 0.75, -0.12); 
		poseStack.mulPose(Axis.XP.rotationDegrees(-90)); 
   
      
	  }
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
