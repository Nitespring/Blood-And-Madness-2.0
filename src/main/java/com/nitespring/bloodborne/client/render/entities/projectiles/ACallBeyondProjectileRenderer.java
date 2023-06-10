package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.ClientListener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.phys.Vec3;


public class ACallBeyondProjectileRenderer extends EntityRenderer<Entity>{
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/projectiles/call_beyond_projectile.png");
	  
	  BulletModel<Entity> model;
	public ACallBeyondProjectileRenderer(Context p_174008_) {
		super(p_174008_);
		
		this.model = new  BulletModel<Entity>(Minecraft.getInstance().getEntityModels().bakeLayer(ClientListener.GENERIC_BULLET_LAYER));
	}
//ShulkerBulletRenderer

	@Override
	public ResourceLocation getTextureLocation(Entity p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	
	@Override
	public void render(Entity p_115862_, float p_115863_, float p_115864_, PoseStack p_115865_, MultiBufferSource p_115866_, int p_115867_) {
	      p_115865_.pushPose();
	     
	      p_115865_.scale(0.3f, 0.3f, 0.3f);
	      float f = Mth.rotLerp(p_115862_.yRotO, p_115862_.getYRot(), p_115864_);
	      float f1 = Mth.lerp(p_115864_, p_115862_.xRotO, p_115862_.getXRot());
	      float f2 = (float)p_115862_.tickCount + p_115864_;
	      p_115865_.translate(0.0D, (double)0.3F, 0.0D);
	      p_115865_.mulPose(Axis.YP.rotationDegrees(Mth.sin(f2 * 0.1F) * 180.0F));
	      p_115865_.mulPose(Axis.XP.rotationDegrees(Mth.cos(f2 * 0.1F) * 180.0F));
	      p_115865_.mulPose(Axis.ZP.rotationDegrees(Mth.sin(f2 * 0.15F) * 360.0F));
	      this.model.setupAnim(p_115862_, 0.0F, 0.0F, 0.0F, f, f1);
	      VertexConsumer vertexconsumer = p_115866_.getBuffer(RenderType.entityCutout(TEXTURE_LOCATION));
	      this.model.renderToBuffer(p_115865_, vertexconsumer, p_115867_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	      p_115865_.scale(1.5F, 1.5F, 1.5F);
	      VertexConsumer vertexconsumer1 = p_115866_.getBuffer(RenderType.eyes(TEXTURE_LOCATION));
	      this.model.renderToBuffer(p_115865_, vertexconsumer1, p_115867_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.15F);
	      
	      p_115865_.popPose();
	      super.render(p_115862_, p_115863_, p_115864_, p_115865_, p_115866_, p_115867_);
	   }
	
	
	
	
	
	
	
}
