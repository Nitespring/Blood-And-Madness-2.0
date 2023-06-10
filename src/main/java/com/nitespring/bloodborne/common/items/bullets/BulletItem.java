package com.nitespring.bloodborne.common.items.bullets;



import java.util.List;


import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FireBulletEntity;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

public class BulletItem extends Item{
	
	
	
	public final float baseDamage;
	public final float lifeTime;
	public final String typeTag;

	public BulletItem(String typeTag, float dmg, int lifeTime, Properties p_41383_) {
		super(p_41383_);
		this.baseDamage = dmg;
		this.lifeTime = lifeTime;
		this.typeTag = typeTag;
	}
	
	public String getTypeTag() {
		return this.typeTag;
	}

	public void onHitTarget(float dmg, BulletProjectileEntity entity, LivingEntity owner, Entity target) {
		target.hurt(entity.damageSources().mobProjectile(entity, owner), dmg + baseDamage);
		if(target instanceof LivingEntity) {
		((LivingEntity) target).setLastHurtByMob(owner);
		}
	}
	
	
	
	
	public static BulletItem getTypeByTag(String s){
		switch(s) {
		case "Quicksilver":
			return ItemInit.QUICKSILVER_BULLET.get();
		case "Copper":
			return ItemInit.COPPER_BULLET.get();
		case "Iron":
			return ItemInit.IRON_BULLET.get();
		case "Gold":
			return ItemInit.GOLDEN_BULLET.get();
		case "Poisonous":
			return ItemInit.POISONOUS_BULLET.get();
		case "Explosive":
			return ItemInit.EXPLOSIVE_BULLET.get();
		case "Electric":
			return ItemInit.ELECTRIC_BULLET.get();
		case "Netherite":
			return ItemInit.NETHERITE_BULLET.get();
		case "Fire":
			return ItemInit.FIRE_BULLET.get();
		case "Withering":
			return ItemInit.WITHERING_BULLET.get();
		case "Glowing":
			return ItemInit.GLOWING_BULLET.get();
		case "Sniper":
			return ItemInit.SNIPER_BULLET.get();
		default:
			return null;
		}
	}
	
	
	public static EntityType<? extends AbstractHurtingProjectile> getEntityByTag(String s){
		switch(s) {
		case "Fire":
			return ProjectileInit.FIRE_BULLET_ENTITY.get();
		case "Explosive":
			return ProjectileInit.EXPLOSIVE_BULLET_ENTITY.get();
		default:
			return ProjectileInit.BULLET_ENTITY.get();
		}
	}
	 @Override
		public Rarity getRarity(ItemStack p_41461_) {
			
			return Rarity.create("WORKSHOP", ChatFormatting.RED);
		}
		   
		 
	  @Override
	  @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
		
			String dmg = "\u00A77" 
			        	 + Math.round((baseDamage)  * 100.0 )/ 100.0 
			        	 + " Damage";
			tooltip.add(Component.literal(dmg));
					 
		}
			
		
		 
	
	
	


}
