package com.nitespring.bloodborne.client.render.entities.mobs.beasts;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
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

public class SilverbeastGeoRenderer extends GeoEntityRenderer<SilverbeastEntity> {
	
	

	
	
	//ExampleGeoRenderer
	
	public SilverbeastGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SilverbeastModel());
        //this.addLayer(new HuntsmanHairLayerRenderer(this));
        this.shadowRadius = 0.5F;
        
       
    }

	

	 @Override
	    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	       ItemStack weaponRenderer = new ItemStack(WeaponInit.LIT_TORCH.get());
	      
	       
		 
	       if (bone.getName().equals("item_right_hand")) { 
			   
	            stack.pushPose();
	          
	            
	            stack.translate(0.37, 0.75, -0.12); 
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
protected float getDeathMaxRotation(SilverbeastEntity entityLivingBaseIn) {
	
	return 0f;
}



   
   
	
}
