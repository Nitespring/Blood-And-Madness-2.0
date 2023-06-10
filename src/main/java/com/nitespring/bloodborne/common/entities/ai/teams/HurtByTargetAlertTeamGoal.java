package com.nitespring.bloodborne.common.entities.ai.teams;


import java.util.Iterator;
import java.util.List;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.phys.AABB;

public class HurtByTargetAlertTeamGoal extends HurtByTargetGoal{

	public HurtByTargetAlertTeamGoal(PathfinderMob p_26039_, Class<?>... p_26040_) {
		super(p_26039_, p_26040_);
		this.setAlertOthers();
		
	}
	
	
	
	
	@Override
	protected void alertOthers() {
		 double d0 = this.getFollowDistance();
	      AABB aabb = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, 10.0D, d0);
	      List<Mob> list = this.mob.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS);

	      for(int i = 0; i<list.size(); i++) {

	    	  if(this.mob.serializeNBT().contains("BnMTeam") && list.get(i).serializeNBT().contains("BnMTeam")) {
	    		  int ownerTeam = this.mob.serializeNBT().getInt("BnMTeam");
	    		  int targetTeam = list.get(i).serializeNBT().getInt("BnMTeam");
	    	  
	    		  
	    		  switch(ownerTeam) {
		    		  case 1:
		    			  switch(targetTeam) {
			    			  case 1,3:
			    				  this.alertOther(list.get(i), this.mob.getLastHurtByMob());
		    			  }
		    		  case 2:
		    			  switch(targetTeam) {
			    			  case 2:
			    				  this.alertOther(list.get(i), this.mob.getLastHurtByMob());
		    			  }
		    		  case 3:
		    			  switch(targetTeam) {
			    			  case 3:
			    				  this.alertOther(list.get(i), this.mob.getLastHurtByMob());
		    			  }
		    		  case 6:
		    			  switch(targetTeam) {
			    			  case 6:
			    				  this.alertOther(list.get(i), this.mob.getLastHurtByMob());
		    			  }
	    		  
	    		  }

	    		 /* if(ownerTeam==targetTeam) {
	    	  
	    			  this.alertOther(list.get(i), this.mob.getLastHurtByMob());
	    		  } */
	    	  }
	      }
		
		
	}
	
	
	

}
