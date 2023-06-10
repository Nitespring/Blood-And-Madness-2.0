package com.nitespring.bloodborne.core.helpers;


import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;
import com.nitespring.bloodborne.config.CommonConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AttackDamageCalculators {

   @SuppressWarnings("deprecation")
public static void calculateAttackDamage(ItemStack stack, Player playerIn, Entity entityIn, 
		   float physIn, float fireIn, float boltIn, float arcaneIn, float bloodIn, float knockBackIn) {
	   Vec2 knockVec = MathHelpers.OrizontalAimVector(
				MathHelpers.AimVector(new Vec3(-playerIn.position().x, -playerIn.position().y, -playerIn.position().z), 
				new Vec3(-entityIn.position().x, -entityIn.position().y, -entityIn.position().z)
			 ));
	   float damageModifier = ultimateAttackDamageModifierCalculations(stack, playerIn, entityIn);
	   //Skeleton
	   entityIn.hurt(playerIn.damageSources().mobAttack(playerIn), (float) (physIn*damageModifier));
	   entityIn.invulnerableTime = 0; 
	   entityIn.hurt(entityIn.damageSources().source(CustomDamageSource.FIRE_DAMAGE, playerIn), (float) (fireIn*damageModifier));
	   entityIn.invulnerableTime = 0;
	   entityIn.hurt(entityIn.damageSources().source(CustomDamageSource.BOLT_DAMAGE, playerIn), (float) (boltIn*damageModifier));
	   entityIn.invulnerableTime = 0;
	   entityIn.hurt(entityIn.damageSources().source(CustomDamageSource.ARCANE_DAMAGE, playerIn), (float) (arcaneIn*damageModifier));
	   entityIn.invulnerableTime = 0;
	   entityIn.hurt(entityIn.damageSources().source(CustomDamageSource.BLOOD_DAMAGE, playerIn), (float) (bloodIn*damageModifier));
	   
	   
	   
       if(entityIn instanceof LivingEntity) {
    	   ((LivingEntity)entityIn).setLastHurtByMob(playerIn);
    	   ((LivingEntity) entityIn).knockback(
    			   knockBackIn + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stack), 
    			   knockVec.x, knockVec.y);
       }
    }
	
	public static float ultimateAttackDamageModifierCalculations(ItemStack stack, Player playerIn, Entity entityIn){
		
		float swingProgressModifier = playerIn.getAttackStrengthScale(0);
		float effectModifier = 1;
		float enchantmentsModifier = 1;
		float mobModifier = 1;
		float levelModifier = 1;
		 if (stack.hasTag() && stack.getTag().contains("BBLevel"))
			{
	    		          int level =	stack.getTag().getInt("BBLevel");
	    		 levelModifier = (float) (levelModifier + level*CommonConfig.upgrade_level_scaling.get());         
	    		          
		
			}
		
		
		
		if(playerIn.hasEffect(MobEffects.DAMAGE_BOOST)){
		effectModifier = effectModifier + (1 + playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier())*0.2f;
		}
		if(playerIn.hasEffect(MobEffects.WEAKNESS)){
		effectModifier = effectModifier - (1 + playerIn.getEffect(MobEffects.WEAKNESS).getAmplifier())*0.2f;
		}
	
		if(stack.isEnchanted()) {
			
			if(entityIn instanceof LivingEntity) {
				MobType type = ((LivingEntity) entityIn).getMobType();
				enchantmentsModifier = enchantmentsModifier + 0.1f*EnchantmentHelper.getDamageBonus(stack, type);
				
			}
			
				enchantmentsModifier = enchantmentsModifier + 0.1f*EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);	
		
		}
		
		
		if(entityIn instanceof AbstractBloodborneEntity && stack.getItem() instanceof BloodborneWeapon) {
			Item item = stack.getItem();
			if(((AbstractBloodborneEntity) entityIn).isBeastly() && ((BloodborneWeapon)item).isSerrated) {
				mobModifier = mobModifier + 0.2f;
			}
			if(((AbstractBloodborneEntity) entityIn).isCorrupted() && ((BloodborneWeapon)item).isRighteous) {
				mobModifier = mobModifier + 0.2f;
			}
			
		}
		float f=effectModifier * enchantmentsModifier * mobModifier * levelModifier *swingProgressModifier;
		if(CommonConfig.apply_dawncraft_patch.get()) {
			f=f/2;
		}
	
		return f;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static float calculateDisplayDamageValues(ItemStack stack) {
		
		float enchantmentsModifier = 1;
		float levelModifier = 1;
		 if (stack.hasTag() && stack.getTag().contains("BBLevel"))
			{
	    		          int level =	stack.getTag().getInt("BBLevel");
	    		 levelModifier = (float) (levelModifier + level* CommonConfig.upgrade_level_scaling.get());         
	    		          
		
			}
		
		 enchantmentsModifier = enchantmentsModifier + 0.1f*EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);	
		
		
		float f = enchantmentsModifier * levelModifier;
		
		return f;
	}
	
}
