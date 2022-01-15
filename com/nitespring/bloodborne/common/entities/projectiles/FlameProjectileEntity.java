package com.nitespring.bloodborne.common.entities.projectiles;


import java.util.Random;

import com.nitespring.bloodborne.common.customdamage.CustomDamageType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FlameProjectileEntity extends AbstractHurtingProjectile{

	private LivingEntity owner;
	 private int lifeTime;
	 private static ParticleOptions particle = ParticleTypes.FLAME;
	 public float damage = 4.0f;
	 public int maxLifeTime = 15;
	 
	 public FlameProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float damage, int lifetime) {
			super(p_36833_, p_36834_);
			this.damage=damage;
			this.maxLifeTime=lifetime;
		}
	
	 public FlameProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float damage) {
			super(p_36833_, p_36834_);
			this.damage=damage;
		}
	 
	 
	public FlameProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
	}

	@Override
	protected ParticleOptions getTrailParticle() {
		
		return particle;
	}
	public LivingEntity getOwner() {
		return this.owner;
	}
	public void setOwner(LivingEntity e) {
		this.owner = e;
	}
	
	@Override
	public void tick() {
		for(int i = 0; i<=2; i++) {
			Random r = random;
		this.level.addAlwaysVisibleParticle(particle, this.position().x, this.position().y, this.position().z, 0.01*(r.nextInt(10)-5), 0.01*(r.nextInt(10)-5), 0.01*(r.nextInt(10)-5));
		}
		lifeTime++;
		
		
		if(lifeTime>=maxLifeTime) {
			lifeTime=0;
			
			 this.discard();
			
		}
		super.tick();
		}
	
	@Override
	protected void onHitBlock(BlockHitResult result) {
	    this.level.blockUpdated(result.getBlockPos().above(), Blocks.FIRE);
		this.discard();
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
	
         if(p_37259_.getEntity()!=this.getOwner() && !(p_37259_.getEntity() instanceof FlameProjectileEntity)) {
        	 p_37259_.getEntity().hurt(CustomDamageType.FIRE_DAMAGE, damage);
        	 p_37259_.getEntity().setSecondsOnFire(3);
        	 if(p_37259_.getEntity() instanceof Mob) {
        	 ((Mob)p_37259_.getEntity()).setLastHurtByMob(owner);
        	 }
        	
         }

	}
	
	
	
}
