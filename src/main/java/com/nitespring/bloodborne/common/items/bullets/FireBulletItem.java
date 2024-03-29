package com.nitespring.bloodborne.common.items.bullets;

import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;


public class FireBulletItem extends BulletItem{

	public FireBulletItem(String typeTag, float dmg, int lifeTime, Properties p_41383_) {
		super(typeTag, dmg, lifeTime, p_41383_);
		
	}

	@Override
	public void onHitTarget(float dmg, BulletProjectileEntity entity, LivingEntity owner, Entity target) {
		target.hurt(target.level().damageSources().source(CustomDamageSource.FIRE_DAMAGE, entity, owner), dmg+ baseDamage);
		if(target instanceof LivingEntity) {
		((LivingEntity) target).setLastHurtByMob(owner);
		}
		target.setSecondsOnFire(5);
		
	}
	
	
	
	
	
}
