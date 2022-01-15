package com.nitespring.bloodborne.common.items.magic;

import java.util.ArrayList;
import java.util.List;

import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondAimHelperEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondProjectileEntity;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

public class CallBeyondItem extends Item{

	public CallBeyondItem(Properties p_41383_) {
		super(p_41383_);
		
	}

	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		
		this.shoot(playerIn, worldIn);
	
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
	}
	
	
	public void shoot(Player playerIn, Level worldIn){
		
		
		double ang = Math.PI/5;
		Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();
		
		CallBeyondAimHelperEntity aimHelper = new CallBeyondAimHelperEntity(ProjectileInit.CALL_BEYOND_AIM_HELPER.get(), worldIn);
		double ax = pos.x + 1.5*aim.x;
		double az = pos.z + 1.5*aim.z;
		double ay = pos.y + 1.0 + 1.5*aim.y;
		aimHelper.setPos(ax,ay,az);
		aimHelper.xPower = aim.x*0.25;
		aimHelper.yPower = aim.y*0.25;
		aimHelper.zPower = aim.z*0.25;
		
		List<CallBeyondProjectileEntity> children = new ArrayList<CallBeyondProjectileEntity>();
		
		for(int i=0; i<=5; i++) {
		
			for(int k=0; k<=5; k++) {
			
				
				double x = pos.x + 1.25*Math.cos(ang*i)*Math.cos(ang*k);
				double z = pos.z + 1.25*Math.cos(ang*i)*Math.sin(ang*k);
				double y = pos.y + 2.5 + 1.25*Math.sin(ang*6/5*i - Math.PI/6);
				
				CallBeyondProjectileEntity bullet = new CallBeyondProjectileEntity(ProjectileInit.CALL_BEYOND.get(), worldIn);
				bullet.setPos(x, y, z);
				bullet.setTarget(aimHelper);
				Vec3 aim1 = MathHelpers.AimVector(new Vec3(pos.x, pos.y+2.5, pos.z), bullet.position());
				bullet.xPower = aim1.x*0.1;
				bullet.yPower = aim1.y*0.1;
				bullet.zPower = aim1.z*0.1;
			    worldIn.addFreshEntity(bullet);
				
			    children.add(bullet);
			    aimHelper.addChild(bullet);
				
			}
			
		}
		aimHelper.setChildren(children);
		worldIn.addFreshEntity(aimHelper);
		
		
		
		
	}
	
	
	
	
	
	
	
}
