package com.nitespring.bloodborne.common.items.bullets;

import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EffectBulletItem extends BulletItem{

	private MobEffect effect;
	
	
	public EffectBulletItem(String typeTag, float dmg, int lifeTime, MobEffect mobEffect, Properties p_41383_) {
		super(typeTag, dmg, lifeTime, p_41383_);
		this.effect = mobEffect;
	}
	@Override
	public void onHitTarget(float dmg, BulletProjectileEntity entity, LivingEntity owner, Entity target) {
		
		if(target instanceof LivingEntity) {
			((LivingEntity)target).addEffect(new MobEffectInstance(this.effect, 240));
		}
		
		super.onHitTarget(dmg, entity, owner, target);
	}
}
