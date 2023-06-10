package com.nitespring.bloodborne.client.render.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;

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
		this.main.yRot = netHeadYaw * ((float)Math.PI / 180F);
	     this.main.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		poseStack.translate(0.0D, -1.45, 0.0D);
		main.render(poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();
	}
	
	


}
