package com.nitespring.bloodborne.common.effects;


import org.joml.Vector3f;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class TrappedEffect extends MobEffect{

	public TrappedEffect(MobEffectCategory p_i50391_1_, int p_i50391_2_) {
		super(p_i50391_1_, p_i50391_2_);
	
	}
	
	    @Override
	   public void applyEffectTick(LivingEntity entityIn, int id) {
		   
		   //entityIn.setDeltaMovement(0, -10.0f, 0);
		   
		   entityIn.setSpeed(0);
		   
		   
		   for(int i = 0; i<=36; i++) {
			   ParticleOptions particle = new DustParticleOptions(new Vector3f(0, 1, 1), 1);;
			   
			   
			  entityIn.level().addParticle(particle, entityIn.position().x + 1 * Math.sin(i) * 0.01,  entityIn.position().y + 1.2f,  entityIn.position().z + 1 * Math.cos(i) * 0.01, 1, 1, 1);
			
		   }
		  
		 
		   
		   
		   
		  
		   
	
	   }
	
	
	
	
	
	
	
	
	
	
	

}
