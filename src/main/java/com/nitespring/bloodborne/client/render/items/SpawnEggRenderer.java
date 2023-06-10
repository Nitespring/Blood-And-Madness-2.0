package com.nitespring.bloodborne.client.render.items;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.items.EntityModelSpawnEggItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;



public class SpawnEggRenderer<T extends EntityModelSpawnEggItem> extends BlockEntityWithoutLevelRenderer{
	
	
	
	
	public SpawnEggRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext p_108831_, PoseStack pose,
			MultiBufferSource p_108833_, int p_108834_, int p_108835_) {
		
		EntityModelSpawnEggItem item = (EntityModelSpawnEggItem)stack.getItem();
		Minecraft instance = Minecraft.getInstance();
		EntityType<?> entity = item.getType(stack.getTag());
		Entity e = entity.create(instance.level);
		
		
		pose.pushPose();
	    pose.translate(0.5, 0, 0);
	    pose.scale(0.4f, 0.4f, 0.4f);
	    /*
		Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(20.0F);
        Quaternion quaternion2 = Vector3f.YP.rotationDegrees(20.0F);
        quaternion.mul(quaternion1);
        
        pose.mulPose(quaternion);
        */
		instance.level.addFreshEntity(e);
		EntityRenderDispatcher entityrenderermanager = instance.getEntityRenderDispatcher();
        //quaternion1.conj();
        //entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        MultiBufferSource.BufferSource irendertypebuffer$impl = instance.renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(e, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, pose, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.endBatch();
		pose.popPose();
		e.discard();
		
		
		
		//super.renderByItem(stack, p_108831_, pose, p_108833_, p_108834_, p_108835_);
	}
	
	
	
	

}
