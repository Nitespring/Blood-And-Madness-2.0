package com.nitespring.bloodborne.common.entities.projectiles;
import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class BulletProjectileEntity extends AbstractHurtingProjectile{

	public int livingTick = 0;
	
	
	public BulletItem item = null;
	public LivingEntity owner = null;
	public int lifeTime = 50;
	public float damage = 4.0f;
	public boolean isPerforant = false;

	
	public BulletProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, float damage, int lifeTime, BulletItem item, boolean isPerforant, LivingEntity owner, Level p_36834_) {
		super(p_36833_, p_36834_);
		this.damage = damage;
		this.lifeTime = lifeTime;
		this.item = item;
		this.owner = owner;
		this.isPerforant = isPerforant;
		this.noCulling = true;
	}
	
	public BulletProjectileEntity( EntityType<? extends AbstractHurtingProjectile> p_36833_, float damage, int lifeTime, Level p_36834_,  LivingEntity owner) {
		super(p_36833_, p_36834_);
		this.damage = damage;
		this.lifeTime = lifeTime;
		this.owner = owner;
		
	}
	
	
	public BulletProjectileEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
		
	}

	public BulletProjectileEntity( EntityType<? extends AbstractHurtingProjectile> p_36833_) {
		super(p_36833_, null);
		
		
	}
	
	@Override
	public boolean isOnFire() {
		
		return false;
	}

	
	@Override
	public void onHitEntity(EntityHitResult e) {
		if(e.getEntity() instanceof LivingEntity&&!(e.getEntity() instanceof ArmorStand)) {
			if(e.getEntity()!=this.owner) {
		if(item == null) {
			e.getEntity().hurt(this.damageSources().mobProjectile(this, owner), damage);
			((LivingEntity) e.getEntity()).setLastHurtByMob(owner);
		}else {
		
			item.onHitTarget(damage, this, owner, e.getEntity());    
		}
		}
			
		}
		if(!this.isPerforant) {
			if(e.getEntity()!=this.owner && !(e.getEntity() instanceof BulletProjectileEntity)) {
			this.discard();
			}
		}
		
		
		super.onHitEntity(e);
	}
	
	@Override
	public void onHitBlock(BlockHitResult e) {
		if(this.getType()==ProjectileInit.EXPLOSIVE_BULLET_ENTITY.get()) {
		
	this.level().explode(this,(double)this.position().x, (double)this.position().y, (double)this.position().z, 1f,false, ExplosionInteraction.MOB);
	
		}
      if(this.getType()==ProjectileInit.FIRE_BULLET_ENTITY.get()) {
			
	//this.level.explode( this.owner,CustomDamageType.FIRE_DAMAGE, null, e.getBlockPos().getX(), e.getBlockPos().getY(), e.getBlockPos().getZ(), 1,true, BlockInteraction.NONE);
	
		}
		
		
		
		
		
		this.discard(); 
		super.onHitBlock(e);
		
	}
	
	
	@Override
	public void tick() {
		this.livingTick++;
		
		if(livingTick>=lifeTime) {
			this.discard();
		}
		
		super.tick();
	}

	
	
	
	
	
	
	
}
