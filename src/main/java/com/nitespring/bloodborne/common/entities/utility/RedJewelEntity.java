package com.nitespring.bloodborne.common.entities.utility;

import java.util.Random;

import org.joml.Vector3f;

import com.nitespring.bloodborne.core.init.EntityInit;

import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class RedJewelEntity extends Entity{

	
	public RedJewelEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
		
	}

	@Override
	protected void defineSynchedData() {
		
		
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {
		

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {
		
		
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		
		return new ClientboundAddEntityPacket(this);
	}
	
	@Override
	public void tick() {
		
		this.playSound(SoundEvents.WATER_AMBIENT, 0.2F, 0.3f);
		this.playSound(SoundEvents.PORTAL_TRIGGER, 0.25f, 0.1f);
		this.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 0.15f, 0.75f);
		
		double x0= position().x;
		double y0= position().y;
		double z0= position().z;
		
		if(tickCount>=60) {
			FatherGascoigneBossEntity entity = new FatherGascoigneBossEntity(EntityInit.FATHER_GASCOIGNE.get(), this.level());
            entity.setPos(x0, y0+0.5, z0);
			this.level().addFreshEntity(entity);
		}
		
		
		if(!level().isClientSide()) {
			
			
			
			for (int i = 0; i <= 7; ++i) {
		
					
		    
			double x = x0 + (new Random().nextFloat() - 0.5)*4;
			double y = y0; 
			double z = z0 +  (new Random().nextFloat() - 0.5)*4;
		
				
			((ServerLevel) level()).sendParticles( new DustParticleOptions(new Vector3f(1, 0, 0), 1), x, y, z, 1,  0, 0, 0, 1);
			}
			
			((ServerLevel) level()).sendParticles( ParticleTypes.CRIMSON_SPORE, position().x, position().y, position().z, 1,  0, 0, 0, 1);
		
			
			if(this.tickCount>=60) {
				this.discard();
			}
		
		}
		
		super.tick();
	}
	
	 @Override
		public boolean fireImmune() {
			
			return true;
		}

}
