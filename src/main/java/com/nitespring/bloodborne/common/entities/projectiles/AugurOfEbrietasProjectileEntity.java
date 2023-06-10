package com.nitespring.bloodborne.common.entities.projectiles;

import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;

public class AugurOfEbrietasProjectileEntity extends AbstractHurtingProjectile{
	
	
	
	
	 private int lifeTime;
	 private static ParticleOptions particle = ParticleTypes.LANDING_OBSIDIAN_TEAR;
	 public float damage = 4.0f;
	 public int maxLifeTime = 15;
	 
	 public AugurOfEbrietasProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float damage, int lifetime) {
			super(p_36833_, p_36834_);
			this.damage=damage;
			this.maxLifeTime=lifetime;
		}
	
	 public AugurOfEbrietasProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float damage) {
			super(p_36833_, p_36834_);
			this.damage=damage;
		}
	 
	 
	public AugurOfEbrietasProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		
		return particle;
	}
	
	
	@Override
	public boolean isOnFire() {
		
		return false;
	}
	
	
	
	@Override
	public void tick() {
		 
		lifeTime++;
		
		
		if(lifeTime>=maxLifeTime) {
			lifeTime=0;
			
			 this.discard();
			
		}
		super.tick();
		}
	
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
	
         if(p_37259_.getEntity()!=this.getOwner() && !(p_37259_.getEntity() instanceof AugurOfEbrietasProjectileEntity)) {
        	 p_37259_.getEntity().hurt(p_37259_.getEntity().damageSources().source(CustomDamageSource.ARCANE_DAMAGE, this, this.getOwner()), damage);
        	 if(p_37259_.getEntity() instanceof Mob) {
        	 ((Mob)p_37259_.getEntity()).setLastHurtByMob((LivingEntity) this.getOwner());
        	 }
        	
         }

	}
	
	
	
	
	


}
