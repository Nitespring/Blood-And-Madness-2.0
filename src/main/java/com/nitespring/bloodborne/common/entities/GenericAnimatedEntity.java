package com.nitespring.bloodborne.common.entities;



import java.util.UUID;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public abstract class GenericAnimatedEntity extends Entity {

	
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	protected int livingTick;
	private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	
	public GenericAnimatedEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
		
	}
	
	public int getAnimationState() {
		 
		 return this.entityData.get(ANIMATION_STATE);
		 
		}
	 
	public void setAnimationState(int anim) {
		 
		 this.entityData.set(ANIMATION_STATE, anim);
		 
	 	}
	 
	@Override
	protected void defineSynchedData() {
	     this.entityData.define(ANIMATION_STATE, 0);
		
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		this.setAnimationState(tag.getInt("AnimationId"));
		this.livingTick = tag.getInt("LivingTick");
		if (tag.hasUUID("Owner")) {
	         this.ownerUUID = tag.getUUID("Owner");
	      }
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("AnimationId", this.getAnimationState());
		tag.putInt("LivingTick", this.livingTick);
		if (this.ownerUUID != null) {
	    	  tag.putUUID("Owner", this.ownerUUID);
	      }
	}

	
	
	
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		
		return new ClientboundAddEntityPacket(this);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.livingTick++;
		
		
	}
	
	 @Override
		public boolean fireImmune() {
			
			return true;
		}
	 
	 public void setOwner(@Nullable LivingEntity p_36939_) {
		    this.owner = p_36939_;
		    this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
		}

		@Nullable
		public LivingEntity getOwner() {
		   if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
		      Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
		      if (entity instanceof LivingEntity) {
		         this.owner = (LivingEntity)entity;
		      }
		   }

		   return this.owner;
		}
		
		
		
	
	
	

}
