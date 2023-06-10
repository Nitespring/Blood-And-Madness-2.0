package com.nitespring.bloodborne.common.entities.ai.teams;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class GetTargetByTeamGoal<T extends LivingEntity> extends TargetGoal {
	   protected final int randomInterval;
	   @Nullable
	   protected LivingEntity target;
	   protected TargetingConditions targetConditions;

	   public GetTargetByTeamGoal(Mob e) {
		   this(e, false, false);
	   }
	   
	   
	   public GetTargetByTeamGoal(Mob e, boolean mustSee, boolean mustReach) {
	      super(e, mustSee, mustReach);
	      this.randomInterval = reducedTickDelay(10);
	      this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	      this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance());
	   }
       @Override
	   public boolean canUse() {
	      if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0 && this.mob.serializeNBT().contains("BnMTeam")) {
	         return false;
	      } else {
	         this.findTarget();
	         return this.target != null;
	      }
	   }
	   protected AABB getTargetSearchArea(double p_26069_) {
	      return this.mob.getBoundingBox().inflate(p_26069_, 4.0D, p_26069_);
	   }

	   
	   
	   protected void findTarget() {
		   
		   int team = this.mob.serializeNBT().getInt("BnMTeam"); 
		   
		   Player playerTarget = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
		   
		   
		   switch(team) {
		   case 0:
			   this.target = playerTarget;
			   break;
		   case 1:   //HUNTSMAN
			   if(playerTarget==null) {
				   this.target = this.findTargetByPredicate(
						   (t) -> {
							   CompoundTag nbt = t.serializeNBT();
						       return (nbt.contains("BnMTeam") && (nbt.getInt("BnMTeam") == 2 || nbt.getInt("BnMTeam") == 5)) 
						    		   || t instanceof Enemy && !(t instanceof Creeper);
						      }
						   
						   );
				   break;
			   }else {
				   this.target = playerTarget;
			   }
		   case 2:   //BEAST
			   if(playerTarget==null) {
				   this.target = this.findTargetByPredicate(
						   (t) -> {
							   CompoundTag nbt = t.serializeNBT();
						       return (nbt.contains("BnMTeam") && (nbt.getInt("BnMTeam") == 1 || nbt.getInt("BnMTeam") == 3 || nbt.getInt("BnMTeam") == 5)) 
						    		   || t instanceof Player || t instanceof Villager || t instanceof IronGolem || t instanceof AbstractIllager;
						      }
						   
						   );
				   break;
			   }else {
				   this.target = playerTarget;
			   }
			   break;
		   case 3:   //CHURCH
			   if(playerTarget==null) {
				   this.target = this.findTargetByPredicate(
						   (t) -> {
							   CompoundTag nbt = t.serializeNBT();
						       return (nbt.contains("BnMTeam") && (nbt.getInt("BnMTeam") == 2 || nbt.getInt("BnMTeam") == 5)) 
						    		   || t instanceof Player ||t instanceof Enemy && !(t instanceof Creeper);
						      }
						   
						   );
				   break;
			   }else {
				   this.target = playerTarget;
			   }
			   break;
		   case 5: //GASCOIGNE
			   if(playerTarget==null) {
				   this.target = this.findTargetByPredicate(
						   (t) -> {
							   CompoundTag nbt = t.serializeNBT();
						       return (nbt.contains("BnMTeam") && (nbt.getInt("BnMTeam") == 2 || nbt.getInt("BnMTeam") == 1 || nbt.getInt("BnMTeam") == 3) || nbt.getInt("BnMTeam") == 5 || nbt.getInt("BnMTeam") == 6 || nbt.getInt("BnMTeam") == 7) 
						    		   || t instanceof Villager || t instanceof IronGolem || (t instanceof Enemy && !(t instanceof Creeper));
						      }
						   
						   );
				   break;
			   }else {
				   this.target = playerTarget;
			   }
			   break;
		   case 6: //MICOLASH
			   this.target = playerTarget;
			   break; 
		   case 7: //HYPER-AGGRESSIVE
			   if(playerTarget==null) {
				   this.target = this.findTargetByPredicate(
						   (t) -> {
						       return true;
						      }
						   
						   );
				   break;
			   }else {
				   this.target = playerTarget;
			   }
			   break;
		   }
		  

	   }
	   
	   
	   protected LivingEntity findTargetByPredicate(Predicate<LivingEntity> predicate){
		   
		   return this.mob.level().getNearestEntity(
				   this.mob.level().getEntitiesOfClass(LivingEntity.class, this.getTargetSearchArea(this.getFollowDistance()), 
						   (p_148152_) -> {
							   return true;
						   }), 
				   this.targetConditions.selector(predicate), 
				   this.mob, 
				   this.mob.getX(), 
				   this.mob.getEyeY(), 
				   this.mob.getZ());
	   }
	   
	   
	   
	   @Override
	   public void start() {
	      this.mob.setTarget(this.target);
	      super.start();
	   }
	   
	   public void setTarget(@Nullable LivingEntity p_26071_) {
	      this.target = p_26071_;
	   }
	}
