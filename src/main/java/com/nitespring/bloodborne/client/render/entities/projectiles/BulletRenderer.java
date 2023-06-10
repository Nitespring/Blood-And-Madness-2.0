package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraft.world.entity.Entity;


public class BulletRenderer extends EntityRenderer<Entity>{
	private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/projectiles/bullet.png");
	  
	  BulletModel<Entity> bulletModel;
	public BulletRenderer(Context p_174008_) {
		super(p_174008_);
		
		this.bulletModel = new BulletModel<Entity>(Minecraft.getInstance().getEntityModels().bakeLayer(ClientListener.BULLET_LAYER));
	}
//ShulkerBulletRenderer

	@Override
	public ResourceLocation getTextureLocation(Entity p_114482_) {
		
		return TEXTURE_LOCATION;
	}
	
	
	
	@Override
	public void render(Entity p_115862_, float p_115863_, float p_115864_, PoseStack p_115865_, MultiBufferSource p_115866_, int p_115867_) {


	      p_115865_.pushPose(); 

	      p_115865_.scale(0.5f, 0.5f, 0.5f);
	      p_115865_.translate(0, -1.25, 0);
	      this.bulletModel.renderToBuffer(p_115865_, p_115866_.getBuffer(RenderType.entityCutout(TEXTURE_LOCATION)), p_115867_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.15F);
	      p_115865_.popPose();
	      super.render(p_115862_, p_115863_, p_115864_, p_115865_, p_115866_, p_115867_);
	   }
	
	
	
	
	
	public class BulletModel<T extends Entity> extends EntityModel<T> {
		
		private final ModelPart main;

		public BulletModel(ModelPart root) {
			super(RenderType::entityCutoutNoCull);
			this.main = root.getChild("bullet");
		}
	      public static LayerDefinition createBodyLayer() {
	    	  
	    	  MeshDefinition meshdefinition = new MeshDefinition();
	  		PartDefinition partdefinition = meshdefinition.getRoot();

	    	PartDefinition bone = partdefinition.addOrReplaceChild("bullet", CubeListBuilder.create().texOffs(3, 5).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F))
	    				.texOffs(3, 2).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
	    				.texOffs(4, 4).addBox(-1.0F, 0.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F))
	    				.texOffs(4, 4).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 23.0F, 0.0F));

	    			bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(3, 2).addBox(-1.0F, -1.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F))
	    				.texOffs(4, 4).addBox(-1.0F, 0.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

	    			bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, 0.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 1.5708F));

	    			bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(4, 4).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.25F, 1.5708F, 0.0F, 1.5708F));

	    			bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(3, 5).addBox(-1.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

	    			bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(3, 5).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

	    	return LayerDefinition.create(meshdefinition, 16, 16);

		}

		@Override
		public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			main.render(poseStack, buffer, packedLight, packedOverlay);
		}
		
		


	}
	
}
