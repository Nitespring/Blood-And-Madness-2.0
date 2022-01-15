package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.ClientListener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class FlameProjectileRenderer extends EntityRenderer<Entity>{
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/projectiles/flame_projectile.png");
	  
	  FlameModel<Entity> bulletModel;
	public FlameProjectileRenderer(Context p_174008_) {
		super(p_174008_);
		
		this.bulletModel = new FlameModel<Entity>(Minecraft.getInstance().getEntityModels().bakeLayer(ClientListener.FLAME_LAYER));
	}
//ShulkerBulletRenderer

	@Override
	public ResourceLocation getTextureLocation(Entity p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	
	
	@Override
	public void render(Entity p_115862_, float p_115863_, float p_115864_, PoseStack p_115865_, MultiBufferSource p_115866_, int p_115867_) {
	      p_115865_.pushPose(); 
	    
	      float f = Mth.rotlerp(p_115862_.yRotO, p_115862_.getYRot(), p_115864_);
	      float f1 = Mth.lerp(p_115864_, p_115862_.xRotO, p_115862_.getXRot());
	      float f2 = (float)p_115862_.tickCount + p_115864_;
	      p_115865_.translate(0.0D, (double)0.15F, 0.0D);
	      p_115865_.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(f2 * 0.1F) * 180.0F));
	      p_115865_.mulPose(Vector3f.XP.rotationDegrees(Mth.cos(f2 * 0.1F) * 180.0F));
	      p_115865_.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(f2 * 0.15F) * 360.0F));
	      p_115865_.scale(0.2F, 0.2F, 0.2F);
	      this.bulletModel.setupAnim(p_115862_, 0.0F, 0.0F, 0.0F, f, f1);
	      VertexConsumer vertexconsumer = p_115866_.getBuffer(this.bulletModel.renderType(TEXTURE_LOCATION));
	      this.bulletModel.renderToBuffer(p_115865_, vertexconsumer, p_115867_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	      p_115865_.scale(1.25F, 1.25F, 1.25F);
	      VertexConsumer vertexconsumer1 = p_115866_.getBuffer(RenderType.entityTranslucent(TEXTURE_LOCATION));
	      this.bulletModel.renderToBuffer(p_115865_, vertexconsumer1, p_115867_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.15F);
	      p_115865_.popPose();
	      super.render(p_115862_, p_115863_, p_115864_, p_115865_, p_115866_, p_115867_);
	   }
	
	
	
	
	
	public static class FlameModel<T extends Entity> extends EntityModel<T> {
		//ShulkerBulletRenderer
		//private final ModelPart root;
		private final ModelPart main;

		public FlameModel(ModelPart root) {
			super(RenderType::entityTranslucent);
			//this.root = r;
			this.main = root.getChild("bullet");
		}
	      public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();
        /*
			partdefinition.addOrReplaceChild("bullet", CubeListBuilder.create()
					.texOffs(0, 1).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
					.texOffs(1, 12).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(-0.05F))
					.texOffs(8, 2).addBox(-0.5F, -2.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(-0.15F))
					.texOffs(0, 8).addBox(-1.5F, -2.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), 
					PartPose.offset(0.0F, 24.0F, 0.0F));
              */
			
			partdefinition.addOrReplaceChild("bullet", CubeListBuilder.create()
					.texOffs(0, 0).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 2.0F)
					.texOffs(0, 10).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 8.0F, 8.0F)
					.texOffs(20, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F), 
					PartPose.ZERO);

			return LayerDefinition.create(meshdefinition, 8, 8);
		}

	      public void setupAnim(T p_103716_, float p_103717_, float p_103718_, float p_103719_, float p_103720_, float p_103721_) {
	          this.main.yRot = p_103720_ * ((float)Math.PI / 180F);
	          this.main.xRot = p_103721_ * ((float)Math.PI / 180F);
	       }

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			main.render(poseStack, buffer, packedLight, packedOverlay);
		}
		
		


	}
	
}
