package com.nitespring.bloodborne.common.entities.projectiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class CallBeyondAimHelperEntity extends AbstractHurtingProjectile{

	List<CallBeyondProjectileEntity> children = new ArrayList<CallBeyondProjectileEntity>();
	 private int lifeTime;

	
	public CallBeyondAimHelperEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
		super(p_36833_, p_36834_);
		
	}
	
	public void addChild(CallBeyondProjectileEntity e) {
		this.children.add(e);
		
		
	}
	
	public void setChildren(List<CallBeyondProjectileEntity> e) {
		this.children = e;
		
		
	}
	
	@Override
	protected boolean shouldBurn() {
		
		return false;
	}

	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) {
		
		this.setChildrenTarget(p_37259_.getEntity());
		this.discard();
		
		
	}
	
	
    void setChildrenTarget(Entity e){
    	for(int i=1; i<= children.size(); i++) {
			if(i<children.size()) {
			CallBeyondProjectileEntity bullet = children.get(i);
			bullet.setTarget(e);
			}
		}
		
		
	}
	
	
	
	
	
	
}
