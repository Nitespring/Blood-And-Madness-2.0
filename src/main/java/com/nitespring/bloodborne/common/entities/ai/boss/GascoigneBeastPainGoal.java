package com.nitespring.bloodborne.common.entities.ai.boss;

import java.util.EnumSet;

import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;



public class GascoigneBeastPainGoal extends Goal{
    GascoigneBeastBossEntity mob;
    int animationTick;
    
    
    public GascoigneBeastPainGoal(GascoigneBeastBossEntity gascoigne) {
    	this.mob=gascoigne;
    	 this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    	 
    }
    
    @Override
    public void setFlags(EnumSet<Flag> p_220684_1_) {
    	
    	EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK);
    }
	@Override
	public boolean canUse() {
		if(mob.getAnimationState()==5) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void start() {
		this.animationTick=0;
	 
	}

	@Override
	public void stop() {
		animationTick=0;
		
		this.mob.setAnimationState(0);
	}
	
	
	@Override
	public void tick() {
		animationTick++;	
		 this.mob.playSound( SoundEvents.RAVAGER_ROAR, 0.1F, 0.4F / (this.mob.getRandom().nextFloat() * 0.4F + 2.4F));
		 this.mob.getNavigation().stop();
		 this.mob.getLookControl().setLookAt(0, 0, 0);
		if(animationTick>=100) {	
			this.stop();
			
		}
	}
	
	
	
	
	
}
