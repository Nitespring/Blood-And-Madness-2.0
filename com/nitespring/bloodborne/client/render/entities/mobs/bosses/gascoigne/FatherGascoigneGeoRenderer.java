package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
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

public class FatherGascoigneGeoRenderer extends GeoEntityRenderer<FatherGascoigneBossEntity> {
	
	
	public ItemStack lightningStack;
	
	
	//ExampleGeoRenderer
	
	public FatherGascoigneGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FatherGascoigneModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
@Override
public void render(GeoModel model, FatherGascoigneBossEntity animatable, float partialTicks, RenderType type,
		PoseStack matrixStackIn, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder,
		int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	float scaleFactor = 1.0f;
    matrixStackIn.pushPose();
    matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);

   matrixStackIn.translate(0, 0, 0);
    
   super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn,
			packedOverlayIn, red, green, blue, alpha);
	 matrixStackIn.popPose();
	super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn,
			packedOverlayIn, red, green, blue, alpha);
}

	

	 @Override
	    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	       //float scaleFactor = 1.4f;
	       ItemStack weaponRenderer = new ItemStack(WeaponInit.HUNTER_AXE.get());
	       ItemStack extendedWeaponRenderer = new ItemStack(WeaponInit.HUNTER_AXE_EXTENDED.get());
	       ItemStack gunRenderer = new ItemStack(WeaponInit.HUNTER_PISTOL.get());
		 
		 if (bone.getName().equals("item_mainhand")) { 
			   
	            stack.pushPose();
	          
	            stack.mulPose(Vector3f.XP.rotationDegrees(-80)); 
	            stack.mulPose(Vector3f.YP.rotationDegrees(0)); 
	            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
	          
	            stack.translate(0.4D, 0.15f, 0.9f); 
	          
	            //stack.scale(0.9f, 0.9f, 0.9f); 
	           
	            Minecraft.getInstance().getItemRenderer().renderStatic(weaponRenderer, TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
	            stack.popPose();
	            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
	           
	        }
		 if (bone.getName().equals("item_mainhand_extended")) { 
			   
	            stack.pushPose();
	          
	            stack.mulPose(Vector3f.XP.rotationDegrees(-80)); 
	            stack.mulPose(Vector3f.YP.rotationDegrees(0)); 
	            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
	          
	            stack.translate(0.4D, 0.25f, 0.9f); 
	          
	            //stack.scale(0.9f, 0.9f, 0.9f); 
	           
	            Minecraft.getInstance().getItemRenderer().renderStatic(extendedWeaponRenderer, TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
	            stack.popPose();
	            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
	           
	        }
		 if (bone.getName().equals("item_offhand")) { 
			   
	            stack.pushPose();
	          
	            stack.mulPose(Vector3f.XP.rotationDegrees(-35)); 
	            stack.mulPose(Vector3f.YP.rotationDegrees(0)); 
	            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
	          
	            stack.translate(-0.55, 0.8D, 0.5); 
	          
	            //stack.scale(scaleFactor, scaleFactor, scaleFactor); 
	           
	            Minecraft.getInstance().getItemRenderer().renderStatic(gunRenderer, TransformType.THIRD_PERSON_LEFT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
	            stack.popPose();
	            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
	           
	        }
		
	        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	    }
	 
	

	
@Override
protected float getDeathMaxRotation(FatherGascoigneBossEntity entityLivingBaseIn) {
	
	return 0f;
}
   
   
	
}
