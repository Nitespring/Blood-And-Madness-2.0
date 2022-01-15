package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HuntsmanAxeGeoRenderer extends GeoEntityRenderer<HuntsmanAxeEntity> {
	
	

	
	
	//ExampleGeoRenderer
	
	public HuntsmanAxeGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HuntsmanAxeModel());
        //this.addLayer(new HuntsmanHairLayerRenderer(this));
        this.shadowRadius = 0.5F;
        
       
    }

	

	 @Override
	    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	       //float scaleFactor = 1.4f;
	       ItemStack weaponRenderer = new ItemStack(WeaponInit.HUNTING_AXE.get());
	      
	       ItemStack torchStack = new ItemStack(WeaponInit.LIT_TORCH.get());
		 
	       if (bone.getName().equals("right_hand_item")) { 
			   
	            stack.pushPose();
	          
	            
	            stack.translate(0.4, 0.75, 0); 
	            stack.mulPose(Vector3f.XP.rotationDegrees(-90)); 
	            stack.mulPose(Vector3f.YP.rotationDegrees(0)); 
	            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
	          
	          
	          
	           
	            Minecraft.getInstance().getItemRenderer().renderStatic(torchStack, TransformType.THIRD_PERSON_LEFT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
	            stack.popPose();
	            bufferIn = rtb.getBuffer(RenderType.entityCutoutNoCull(whTexture));
	        
	        }
	       
	       
	       
		 if (bone.getName().equals("left_hand_item")) { 
			   
	            stack.pushPose();
	            stack.translate(-0.4, 0.6f, 0f); 
	            stack.mulPose(Vector3f.XP.rotationDegrees(-90)); 
	            stack.mulPose(Vector3f.YP.rotationDegrees(0)); 
	            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
	          
	           
	          
	          
	           
	            Minecraft.getInstance().getItemRenderer().renderStatic(weaponRenderer, TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
	            stack.popPose();
	            bufferIn = rtb.getBuffer(RenderType.entityCutoutNoCull(whTexture));
	           
	        }
		
		 
	
	        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	    }
	 
	 @Override
	public void renderLate(HuntsmanAxeEntity animatable, PoseStack stackIn, float ticks,
			MultiBufferSource renderTypeBuffer, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float partialTicks) {
		
		super.renderLate(animatable, stackIn, ticks, renderTypeBuffer, bufferIn, packedLightIn, packedOverlayIn, red, green,
				blue, partialTicks);
	}
	

	
@Override
protected float getDeathMaxRotation(HuntsmanAxeEntity entityLivingBaseIn) {
	
	return 0f;
}
   
   
	
}
