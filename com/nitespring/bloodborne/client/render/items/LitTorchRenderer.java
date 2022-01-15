package com.nitespring.bloodborne.client.render.items;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;


import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Tick;

public class LitTorchRenderer<T extends BloodborneWeapon> extends BlockEntityWithoutLevelRenderer{ 
	
	
	
	public LitTorchRenderer() {

		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
		
	}


@Override
public void renderByItem(ItemStack stack, TransformType transformType, PoseStack pose,
		MultiBufferSource buffer, int p_108834_, int p_108835_) {
	 Minecraft instance = Minecraft.getInstance();
	 //LocalPlayer playerIn = instance.player;
	 //Vec3 pos = playerIn.position();
	ItemStack renderStack = new ItemStack(WeaponInit.TORCH.get());
	ItemStack flameStack = new ItemStack(ItemInit.FIRE.get());
	//SimpleParticleType particle = ParticleTypes.FLAME;
	
	int tick = instance.player.tickCount;
	
	
	  pose.pushPose();
	  pose.translate(0.5, 0.5, 0);
	  instance.getItemRenderer().renderStatic(renderStack, TransformType.THIRD_PERSON_RIGHT_HAND, p_108834_, p_108835_, pose, buffer, 1);
	  
	  pose.popPose();
	  
	  pose.pushPose();
	  
	  pose.translate(0.5, 1.22, 0.1);
	  pose.scale(0.25f, 0.85f, 0.25f);
	  pose.mulPose(Vector3f.YP.rotationDegrees( tick/16));
	  instance.getItemRenderer().renderStatic(flameStack, TransformType.THIRD_PERSON_RIGHT_HAND, 255, p_108835_, pose, buffer, 1);
	  
	  pose.popPose();
	  
	  
	  for(int i = -4; i<=4; i++) {
	  float f = (float) (Math.sin(tick*i/4)*0.01);
		  pose.pushPose();
		  pose.translate(0.5, 1.24 + f, 0.1);
		  pose.scale(0.3f - f, 0.7f + f, 0.3f + f);
		  pose.mulPose(Vector3f.YP.rotationDegrees(46*i + i*tick/2));
		  instance.getItemRenderer().renderStatic(flameStack, TransformType.THIRD_PERSON_RIGHT_HAND, 255, p_108835_, pose, buffer, 1);
		  pose.popPose();
		  
		 
	  }
		  
		  /*
	  
	  pose.pushPose();
	  pose.translate(0.5, 1.2, 0);
	  pose.scale(0.44f, 0.7f, 0.44f);
	  instance.getItemRenderer().renderStatic(flameStack, TransformType.THIRD_PERSON_RIGHT_HAND, 255, p_108835_, pose, buffer, 1);
	  pose.popPose();
	 
	  
	  pose.pushPose();
	  
	  pose.translate(0.5, 1.2, 0.05);
	  pose.scale(0.4f, 0.7f, 0.4f);
	  pose.mulPose(Vector3f.YP.rotationDegrees(45));
	 
	  instance.getItemRenderer().renderStatic(flameStack, TransformType.THIRD_PERSON_RIGHT_HAND, 255, p_108835_, pose, buffer, 1);
	  pose.popPose();
	  
	  
      pose.pushPose();
	  
	  pose.translate(0.5, 1.2, 0);
	  pose.scale(0.4f, 0.7f, 0.4f);
	  pose.mulPose(Vector3f.YP.rotationDegrees(135));
	 
	  instance.getItemRenderer().renderStatic(flameStack, TransformType.THIRD_PERSON_RIGHT_HAND, 255, p_108835_, pose, buffer, 1);
	  pose.popPose();
	  */
}
	
}
