package com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FatherGascoigneGeoRenderer extends GeoEntityRenderer<FatherGascoigneBossEntity> {
	
	
	public ItemStack lightningStack;
	
	
	//ExampleGeoRenderer
	
	public FatherGascoigneGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FatherGascoigneModel());
        this.addRenderLayer(new FatherGascoigneItemLayer(this));
        this.shadowRadius = 0.5F;
     
       
    }
	


	

	
	 
	

	
@Override
protected float getDeathMaxRotation(FatherGascoigneBossEntity entityLivingBaseIn) {
	
	return 0f;
}
   
   
	
}
