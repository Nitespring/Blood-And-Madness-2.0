package com.nitespring.bloodborne.common.entities.projectiles;

import com.nitespring.bloodborne.common.customdamage.CustomDamageType;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.phys.BlockHitResult;

public class FireBulletEntity extends BulletProjectileEntity{

	public FireBulletEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, float damage, int lifeTime,
			BulletItem item, boolean isPerforant, LivingEntity owner, Level p_36834_) {
		super(p_36833_, damage, lifeTime, item, isPerforant, owner, p_36834_);
		
	}
	public FireBulletEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
		
	}

	public FireBulletEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_) {
		super(p_36833_, null);
		
	}
	
	
	@Override
	protected ParticleOptions getTrailParticle() {
		
		return ParticleTypes.FLAME;
	}
	

}
