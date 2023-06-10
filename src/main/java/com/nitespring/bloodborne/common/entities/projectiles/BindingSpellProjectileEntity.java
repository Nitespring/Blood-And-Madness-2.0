package com.nitespring.bloodborne.common.entities.projectiles;



import java.util.Random;

import org.joml.Vector3f;

import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.EffectInit;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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

public class BindingSpellProjectileEntity extends AbstractHurtingProjectile{
	
	public static ParticleOptions particle = new DustParticleOptions(new Vector3f(0,1,1), 1.0f);
	private Entity target;
	 private int lifeTime;
	 private final float damage;
	 public Vec3 v0= new Vec3(0, 0, 0);

	 public BindingSpellProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
			this(p_36833_, p_36834_,4.0f);
		}
	public BindingSpellProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_, float dmg) {
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

	
	
	@Override
	public void tick() {
		for(int i = 0; i<=5; i++) {
		this.level().addAlwaysVisibleParticle(particle, this.position().x+new Random().nextFloat()*0.4-0.2, this.position().y+new Random().nextFloat()*0.4, this.position().z+new Random().nextFloat()*0.4-0.2, 0.1, 0.1, 0.1);
		}
		lifeTime++;
		
		
		if(lifeTime>=100) {
			
			
			 this.discard();
			 lifeTime=0;
			
		}
		if (this.target != null) {
			
		
		   /* if(lifeTime==9) {
			this.v0=MathHelpers.AimVector(this.position(), new Vec3(this.getTarget().position().x, this.getTarget().position().y +  this.getTarget().getBbHeight()/2, this.getTarget().position().z));
			 //this.setDeltaMovement(v0.scale(0.1f));
			}*/
			if(lifeTime>=10&&lifeTime<=50) {
			Vec3 pos = this.position();
			Vec3 pos1 = this.getTarget().position();
			
			Vec3 aim = MathHelpers.AimVector(pos, new Vec3(pos1.x, pos1.y +  this.getTarget().getBbHeight()/2, pos1.z));
			Vec3 mov = this.getDeltaMovement();
			
			v0 = mov.add((aim.x - mov.x) * 0.05D, (aim.y - mov.y) * 0.05D, (aim.z - mov.z) * 0.05D);
			//v0.normalize().scale(0.5);
			/*
			double alpha=Math.atan2(vec1.z, vec1.x);
			double beta=Math.atan2(v0.z, v0.x);
			double omega=alpha-beta;
			
			Vec3 vec2 = new Vec3(Math.sqrt(vec1.x*vec1.x+vec1.z*vec1.z)*Math.sin(omega)*Math.cos(beta), 0, Math.sqrt(vec1.x*vec1.x+vec1.z*vec1.z)*Math.sin(omega)*Math.sin(beta));
			*/
			//Vec3 vec2 = new Vec3(Math.sqrt(aim.x*aim.x+aim.z*aim.z)*Math.sin(omega)*Math.cos(beta), 0, Math.sqrt(aim.x*aim.x+aim.z*aim.z)*Math.sin(omega)*Math.sin(beta));
			
            //Vec3 vecf = mov.add((mov.x+vec2.x)*0.15,(aim.y - mov.y) * 0.05D,(mov.z+vec2.z)*0.15);
			//Vec3 vecf = mov.add((aim.x-vec2.x- mov.x)*0.05,(aim.y - mov.y) * 0.05D,(aim.z-vec2.z- mov.x)*0.05);
            
           Vec3 vecf = mov.add(v0.x*0.025*(lifeTime-10)/100 + (aim.x - mov.x) * 0.025D, (aim.y - mov.y) * 0.05D, v0.z*0.025*(lifeTime-10)/100 +(aim.z - mov.z) * 0.025D);
			}
         
            this.setDeltaMovement(v0);	
            
            
         
			}
		
		super.tick();
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_) {
	
		this.discard();
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
	
         if(p_37259_.getEntity()!=this.getOwner() && !(p_37259_.getEntity() instanceof BindingSpellProjectileEntity)) {
        	 p_37259_.getEntity().hurt(this.damageSources().source(CustomDamageSource.ARCANE_DAMAGE,this,this.getOwner()), damage);
        	 if(p_37259_.getEntity() instanceof LivingEntity) {
        	 ((LivingEntity) p_37259_.getEntity()).addEffect(new MobEffectInstance(EffectInit.TRAPPED.get(), 85), getOwner());
        	 if(p_37259_.getEntity() instanceof Mob) {
        	 ((Mob)p_37259_.getEntity()).setLastHurtByMob((LivingEntity) getOwner());
        	
        	 }
        	 }
        	this.discard();
         }

	}

	 @Override
	public boolean hurt(DamageSource p_36839_, float p_36840_) {
		return super.hurt(p_36839_, p_36840_);
	}
	
	
	


}
