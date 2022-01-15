package com.nitespring.bloodborne.common.entities.mobs.parent;


import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class AbstractBloodborneEntity extends PathfinderMob{
	
	
	
	public abstract boolean isBoss();
	public abstract boolean isBeastly();
	public abstract boolean isCorrupted();
	public abstract boolean isKin();
	public abstract int physRes();
	public abstract int fireRes();
	public abstract int boltRes();
	public abstract int arcRes();
	public abstract int bloodRes();

	
	private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> COMBAT_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ENTITY_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));
	
	public AbstractBloodborneEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.noCulling = true;
	}
	
	
	
	 public int getAnimationState() {
		 
		 return this.entityData.get(ANIMATION_STATE);
		 }
	 
	 public void setAnimationState(int anim) {
		 
		 this.entityData.set(ANIMATION_STATE, anim);
		 }
	 
	 public int getCombatState() {
		 
		 return this.entityData.get(COMBAT_STATE);
		 }
	 
	 public void setCombatState(int anim) {
		 
		 this.entityData.set(COMBAT_STATE, anim);
		 }
	 
	 public int getEntityState() {
		 
		 return this.entityData.get(ENTITY_STATE);
		 }
	 
	 public void setEntityState(int anim) {
		 
		 this.entityData.set(ENTITY_STATE, anim);
		 }
	 
	 @Override
	 protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(ANIMATION_STATE, 0);
	      this.entityData.define(COMBAT_STATE, 0);   
	      this.entityData.define(ENTITY_STATE, 0);      
	   }
	 
	 @Override
		public void startSeenByPlayer(ServerPlayer p_184178_1_) {
		      super.startSeenByPlayer(p_184178_1_);
		      
		      if (this.isBoss()) {
		      this.bossEvent.addPlayer(p_184178_1_);
		      }
		   }
	 
     @Override
	 public void stopSeenByPlayer(ServerPlayer p_184203_1_) {
    	
		      super.stopSeenByPlayer(p_184203_1_);
		      this.bossEvent.removePlayer(p_184203_1_);
		   } 
		
     @Override
     public void tick() {
	          float h = this.getHealth(); 
	          this.bossEvent.setProgress(h / this.getMaxHealth());
	 
	 
	          super.tick(); 
     		}
	 
	
	
	
}
