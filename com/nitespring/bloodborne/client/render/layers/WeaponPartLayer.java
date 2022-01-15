package com.nitespring.bloodborne.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WeaponPartLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	
  
	
	
	

		   public WeaponPartLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> p_117346_) {
		super(p_117346_);
		
	}

		

		@Override
		public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_,
				AbstractClientPlayer playerIn, float p_117353_, float p_117354_, float p_117355_, float p_117356_,
				float p_117357_, float p_117358_) {
			ItemStack mainHand = playerIn.getMainHandItem();
			
			if(mainHand.getItem() instanceof BloodborneWeapon) {
			
			ItemStack itemstack = ((BloodborneWeapon)mainHand.getItem()).getWeaponPart().getDefaultInstance();
		      
			if(itemstack.getItem() != ItemStack.EMPTY.getItem()) {
		    	  poseStack.pushPose();
		    	 if(playerIn.isCrouching()) {
		    	poseStack.mulPose(Vector3f.XP.rotationDegrees(30));
		    	poseStack.translate(0, 0.2, -0.1);
		    	 }
		         Minecraft.getInstance().getItemInHandRenderer().renderItem(playerIn, itemstack, TransformType.THIRD_PERSON_RIGHT_HAND, true, poseStack, p_117350_, p_117351_);
		        
		         poseStack.popPose();
			}
			}
			
			
		}
		
		
		
		

}
