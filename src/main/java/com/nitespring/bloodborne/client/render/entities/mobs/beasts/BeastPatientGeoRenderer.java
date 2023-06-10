package com.nitespring.bloodborne.client.render.entities.mobs.beasts;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.BeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BeastPatientGeoRenderer extends GeoEntityRenderer<BeastPatient> {
	
	

	
	
	//ExampleGeoRenderer
	
	public BeastPatientGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BeastPatientModel());
        this.addRenderLayer(new BeastPatientEyesLayer<BeastPatient>(this));
        this.shadowRadius = 0.5F;
        
       
    }

	


	@Override
	public RenderType getRenderType(BeastPatient animatable, ResourceLocation texture,
			MultiBufferSource bufferSource, float partialTick) {
		
		return RenderType.entityCutoutNoCull(texture);
	}
	 
	

	
@Override
protected float getDeathMaxRotation(BeastPatient entityLivingBaseIn) {
	
	return 0f;
}



   
   
	
}
