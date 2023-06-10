package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class FatherGascoigneItemLayer extends BlockAndItemGeoLayer<FatherGascoigneBossEntity>{

	public FatherGascoigneItemLayer(GeoRenderer<FatherGascoigneBossEntity> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, FatherGascoigneBossEntity animatable) {
		 if (bone.getName().equals("item_mainhand")) { 
			 	return new ItemStack(WeaponInit.HUNTER_AXE.get());
		 }else if (bone.getName().equals("item_mainhand_extended")) { 
			 	return new ItemStack(WeaponInit.HUNTER_AXE_EXTENDED.get());
		 }else if (bone.getName().equals("item_offhand")) { 
			 	return new ItemStack(WeaponInit.HUNTER_PISTOL.get());
		 }else{
			 return null;
		 }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, FatherGascoigneBossEntity animatable) {
		if (bone.getName().equals("item_mainhand")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("item_mainhand_extended")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("item_offhand")) { 
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else
		return null;
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, FatherGascoigneBossEntity animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	if (bone.getName().equals("item_mainhand")) { 
		poseStack.mulPose(Axis.XP.rotationDegrees(-80)); 
		//poseStack.translate(0.4D, 0.15f, 0.9f); 
	  }
	if (bone.getName().equals("item_mainhand_extended")) {
		//poseStack.translate(0.4, 1.0f, 0.2f);
		poseStack.mulPose(Axis.XP.rotationDegrees(-70)); 
		poseStack.mulPose(Axis.YP.rotationDegrees(-20)); 
		poseStack.mulPose(Axis.ZP.rotationDegrees(7));
	  }
	if (bone.getName().equals("item_offhand")) { 
		poseStack.mulPose(Axis.XP.rotationDegrees(-35)); 
		//poseStack.translate(-0.3, 0.9D, 0.4);  
		poseStack.translate(0.0, -0.1, 0.0);
   
      
	  }
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
