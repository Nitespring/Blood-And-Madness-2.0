package com.nitespring.bloodborne.common.items.bullets;

import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class ExplosiveBulletItem extends BulletItem{

	public ExplosiveBulletItem(String typeTag, float dmg, int lifeTime, Properties p_41383_) {
		super(typeTag, dmg, lifeTime, p_41383_);
		
	}
	
	@Override
	public void onHitTarget(float dmg, BulletProjectileEntity entity, LivingEntity owner, Entity target) {
		
		//entity.level.explode(entity, entity.position().x, entity.position().y, entity.position().z, 1f,  Explosion.BlockInteraction.DESTROY);
		
		entity.level().explode(entity, entity.position().x, entity.position().y, entity.position().z, 1f,false, ExplosionInteraction.TNT);
		super.onHitTarget(dmg, entity, owner, target);
		
	}

}
