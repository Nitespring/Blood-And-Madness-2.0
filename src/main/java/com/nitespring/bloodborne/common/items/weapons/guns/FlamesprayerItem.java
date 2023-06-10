package com.nitespring.bloodborne.common.items.weapons.guns;

import com.nitespring.bloodborne.common.entities.projectiles.FlameProjectileEntity;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlamesprayerItem extends Item{

	public float damage;
	
	
	public FlamesprayerItem(float basedamage, Properties p_41383_) {
		super(p_41383_);
		this.damage=basedamage;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		sprayFire(worldIn, playerIn, handIn);
		playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), 7);
		return super.use(worldIn, playerIn, handIn);
	}
	
	
	
	public void sprayFire(Level worldIn, Player playerIn, InteractionHand handIn) {
		Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();
		double a = Math.PI/10;
	
		
		for (int i=-2; i<=2; i++) {
			for (int k=-1; k<=1; k++) {
				
				FlameProjectileEntity flame = new FlameProjectileEntity(ProjectileInit.FLAME.get(), worldIn, damage);
				flame.setOwner(playerIn);
				double x = pos.x + 0.75*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));
				double z = pos.z + 0.75*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));
				double y = pos.y + 1 + 0.075*(aim.y+Math.sin(k*a));
				flame.setPos(x, y, z);
				//flame.setPos(pos);
				flame.xPower=aim.x*0.025 + 0.005*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));
				flame.yPower=aim.y*0.025 + 0.025*(aim.y+Math.sin(k*a));;
				flame.zPower=aim.z*0.025 + 0.005*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));
				worldIn.addFreshEntity(flame);
				//System.out.println("added flame to:" + x + y + z );
				
			}
			}
		}
		
		public void sprayFire2(Level worldIn, Player playerIn, InteractionHand handIn) {
			Vec3 pos = playerIn.position();
			Vec3 aim = playerIn.getLookAngle();
			double a = Math.PI/8;
		
			
			for (int i=-1; i<=1; i++) {
				for (int k=-1; k<=1; k++) {
					
				
					
					FlameProjectileEntity flame = new FlameProjectileEntity(ProjectileInit.FLAME.get(), worldIn, damage);
					flame.setOwner(playerIn);
					double x = pos.x + 0.75*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));
					double z = pos.z + 0.75*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));
					double y = pos.y + 1 + 0.75*(aim.y+Math.sin(k*a));
					flame.setPos(x, y, z);
					
					flame.xPower=aim.x*0.025 + 0.005*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));;
					flame.yPower=aim.y*0.025; 
					flame.zPower=aim.z*0.025 + 0.005*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));;
					worldIn.addFreshEntity(flame);
				
					
					
				}
			}
		
	}
	
	@Override
	public int getMaxStackSize(ItemStack stack) {
		return 1;
	}
	
	
	
	

}
