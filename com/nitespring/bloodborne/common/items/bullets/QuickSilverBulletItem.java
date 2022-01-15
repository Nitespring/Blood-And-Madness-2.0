package com.nitespring.bloodborne.common.items.bullets;

import com.nitespring.bloodborne.common.customdamage.CustomDamageType;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class QuickSilverBulletItem extends BulletItem{

	public QuickSilverBulletItem(String typeTag, float dmg, int lifeTime, Properties p_41383_) {
		super(typeTag, dmg, lifeTime, p_41383_);
	}
	@Override
	public void onHitTarget(float dmg, BulletProjectileEntity entity, LivingEntity owner, Entity target) {
		target.hurt(CustomDamageType.BLOOD_DAMAGE, dmg + baseDamage);
		if(target instanceof LivingEntity) {
		((LivingEntity) target).setLastHurtByMob(owner);
		}
	}
}
