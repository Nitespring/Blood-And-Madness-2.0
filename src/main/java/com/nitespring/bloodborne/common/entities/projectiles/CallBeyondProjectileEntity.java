package com.nitespring.bloodborne.common.entities.projectiles;

import org.joml.Vector3f;

import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.core.helpers.MathHelpers;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CallBeyondProjectileEntity extends AbstractHurtingProjectile{
	
	public static ParticleOptions particle = new DustParticleOptions(new Vector3f(0.75f,1,1), 1.0f);
	private Entity target;
	private LivingEntity owner;
	 private int lifeTime;
	 private final float damage;

	 public CallBeyondProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
			this(p_36833_, p_36834_,4.0f);
		}
	public CallBeyondProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float dmg) {
		super(p_36833_, p_36834_);
		this.damage=dmg;
	}
	
	
	@Override
	protected boolean shouldBurn() {
		
		return false;
	}
	
	@Override
	protected ParticleOptions getTrailParticle() {
		
		return particle;
	}
	
	public Entity getTarget() {
		return this.target;
	}
	public void setTarget(Entity e) {
		this.target = e;
	}
	public LivingEntity getOwner() {
		return this.owner;
	}
	public void setOwner(LivingEntity e) {
		this.owner = e;
	}
	
	@Override
	public void tick() {
		for(int i = 0; i<=5; i++) {
		this.level().addAlwaysVisibleParticle(particle, this.position().x, this.position().y, this.position().z, 0.1, 0.1, 0.1);
		}
		lifeTime++;
		
		
		if(lifeTime>=60) {
			
			
			 this.discard();
			 lifeTime=0;
			
		}
		if (this.target != null && lifeTime>=10) {
			Vec3 pos = this.position();
			Vec3 pos1 = this.getTarget().position();
			
			Vec3 aim = MathHelpers.AimVector(pos, new Vec3(pos1.x, pos1.y +  this.getTarget().getBbHeight()/2, pos1.z));
			Vec3 mov = this.getDeltaMovement();
			
			Vec3 vec = new Vec3(1,2,3);
			
            
            
            
            this.setDeltaMovement(mov.add((aim.x - mov.x) * 0.2D, (aim.y - mov.y) * 0.2D, (aim.z - mov.z) * 0.2D));
            
         }
		
		super.tick();
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
	
		this.discard();
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
	
         if(p_37259_.getEntity()!=this.getOwner() && !(p_37259_.getEntity() instanceof CallBeyondProjectileEntity) && !(p_37259_.getEntity() instanceof CallBeyondAimHelperEntity)) {
        	 p_37259_.getEntity().hurt(p_37259_.getEntity().damageSources().source(CustomDamageSource.ARCANE_DAMAGE,this, owner), damage);
        	 if(p_37259_.getEntity() instanceof Mob) {
        	 ((Mob)p_37259_.getEntity()).setLastHurtByMob(owner);
        	 }
        	this.discard();
         }

	}
	
	
	
	
	


}
