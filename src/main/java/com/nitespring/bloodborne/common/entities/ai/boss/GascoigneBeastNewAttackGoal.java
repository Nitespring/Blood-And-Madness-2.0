package com.nitespring.bloodborne.common.entities.ai.boss;

import java.util.EnumSet;
import java.util.Random;

import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.ProjectileInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;


public class GascoigneBeastNewAttackGoal extends Goal {
	   protected final GascoigneBeastBossEntity mob;
	   private final double speedModifier;
	   private final boolean followingTargetEvenIfNotSeen;
	   private Path path;
	   private double pathedTargetX;
	   private double pathedTargetY;
	   private double pathedTargetZ;
	   private int ticksUntilNextPathRecalculation;
	   private int ticksUntilNextAttack;
	   private long lastCanUseCheck;
	   private int failedPathFindingPenalty = 0;
	   private boolean canPenalize = false;
	   private int animTime = 0;
	   private Vec2 saveAim;

	   

	   public GascoigneBeastNewAttackGoal(GascoigneBeastBossEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
	      this.mob = p_i1636_1_;
	      this.speedModifier = p_i1636_2_;
	      this.followingTargetEvenIfNotSeen = p_i1636_4_;
	      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	   }

	   public boolean canUse() {
	      long i = this.mob.level().getGameTime();
	     
	      if (i - this.lastCanUseCheck < 20L) {
	         return false;
	      } else {
	         this.lastCanUseCheck = i;
	         LivingEntity livingentity = this.mob.getTarget();
	         if (livingentity == null) {
	            return false;
	         } else if (!livingentity.isAlive()) {
	            return false;
	         } else {
	           if (canPenalize) {
	               if (--this.ticksUntilNextPathRecalculation <= 0) {
	                  this.path = this.mob.getNavigation().createPath(livingentity, 0);
	                  this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
	                  return this.path != null;
	               } else {
	                  return true;
	               }
	            }
	            this.path = this.mob.getNavigation().createPath(livingentity, 0);
	            if (this.path != null) {
	               return true;
	            } else {
	               return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
	            }
	         }
	      }
	      
	   }

	   public boolean canContinueToUse() {
		   
	      LivingEntity livingentity = this.mob.getTarget();
	     
	      if (livingentity == null) {
	         return false;
	      } else if (!livingentity.isAlive()) {
	         return false;
	      } else if (!this.followingTargetEvenIfNotSeen) {
	         return !this.mob.getNavigation().isDone();
	      } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
	         return false;
	      } else {
	         return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
	      }
	      
	   }

	   public void start() {
	      this.mob.getNavigation().moveTo(this.path, this.speedModifier);
	      this.mob.setAggressive(true);
	      this.ticksUntilNextPathRecalculation = 0;
	      this.ticksUntilNextAttack = 0;
	    
	      this.animTime = 0;
	      this.mob.setAnimationState(0);
	      
	   }

	   public void stop() {
	      LivingEntity livingentity = this.mob.getTarget();
	      if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
	         this.mob.setTarget((LivingEntity)null);
	      }
	      
	      this.mob.setAnimationState(0);

	      this.mob.setAggressive(false);
	      this.mob.getNavigation().stop();
	   }

	   public void tick() {
		   
		  
		   LivingEntity target = this.mob.getTarget();
		      double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
		      double reach = this.getAttackReachSqr(target);
		      int animState = this.mob.getAnimationState();
		      Vec3 aim = this.mob.getLookAngle();
		      Vec2 aim2d = new Vec2((float)(aim.x/(1-Math.abs(aim.y))), (float)(aim.z/(1-Math.abs(aim.y))));
		      double pi = Math.PI;
		      
		      
		    
		      switch(animState) {
		      //evasive actions
		      		case 5:
		      			break;
		      		case 6:
		      			tickInAir();
	      				break;
		      //melee attacks
		      		case 21:
		      			tickLightAttack(aim2d); 
		      			break;
		      		case 22:
		      			tickLightAttack2(aim2d); 
		      			break;
		      		case 23:
		      			tickPunchAttack(); 
		      			break;
		      		case 24:
		      			tickKickAttack(); 
		      			break;
		      		case 25:
		      			tickSlamAttack(); 
		      			break;
		      		case 26:
		      			tickJumpAttack(aim2d); 
		      			break;
		      		default:
		      
		      			this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
		      		
		      			
		      			this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		      			this.doMovement(target, distance);
		      			this.checkForCloseRangeAttack(distance, reach);
		      			this.checkForMediumRangeAction(distance, reach);
		      			
		     
		      			break;
		      
		      }	 
		      this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0); 
		      
		      
		   }
		   
		
		   protected void doMovement(LivingEntity livingentity, Double d0) {
			   if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
			         this.pathedTargetX = livingentity.getX();
			         this.pathedTargetY = livingentity.getY();
			         this.pathedTargetZ = livingentity.getZ();
			         this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
			         if (this.canPenalize) {
			            this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
			            if (this.mob.getNavigation().getPath() != null) {
			               Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
			               if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
			                  failedPathFindingPenalty = 0;
			               else
			                  failedPathFindingPenalty += 10;
			            } else {
			               failedPathFindingPenalty += 10;
			            }
			         }
			         if (d0 > 1024.0D) {
			            this.ticksUntilNextPathRecalculation += 10;
			         } else if (d0 > 256.0D) {
			            this.ticksUntilNextPathRecalculation += 5;
			         }

			         if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
			            this.ticksUntilNextPathRecalculation += 15;
			         }
			      }
				  
			  }
		   protected void checkForCloseRangeAttack(double distance, double reach){
			   if (distance <= reach && this.ticksUntilNextAttack <= 0) {
				   
				  
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=600) {
						   this.mob.setAnimationState(21);
						    }else if(r<=800){
						   this.mob.setAnimationState(23);
						    }else  if(r<=1400){
						   this.mob.setAnimationState(24);		
						    }else {
						   this.mob.setAnimationState(25);		
						    }
				   
				
			  
	   } 
		   }
		   
		 
		   
		   protected void checkForMediumRangeAction(double distance, double reach){
			   if (distance <= 6.5*reach && this.ticksUntilNextAttack <= 0) {
				 
					   int r = this.mob.getRandom().nextInt(4096);
					    
					    if(r<=350) {
					    
				    	  this.mob.setAnimationState(26);
					    } 
					   
					   
					   
					   
				 
	   } 
		   }
		   
		   
		  
		   protected boolean getRangeCheck() {
			   
			   return 
					  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
					  	<= 
					  2.5*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		  
		   
		 
		   
		   
		   
		   
		   //evasive actions ticks
		  
		   protected void tickInAir(){
			   animTime++;
			   this.mob.setDeltaMovement(saveAim.x*1.25, -1.25, saveAim.y*1.25);
				performLargeSweepAttack(); 
			 if(this.mob.onGround()||animTime>=500) {
			   animTime=0;
			       performLightAttack();
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   
			 }  
			 
		   }
		
		   
		   

		 //attack ticks
		   
		   
		   protected void tickLightAttack(Vec2 aim2d){
			   animTime++;
			 if(animTime==3) {
			   performLightAttack();
			   this.mob.setDeltaMovement(aim2d.x*1.25, 0.1, aim2d.y*1.25);
			 }  
			 if(animTime>=6) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(22);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   protected void tickLightAttack2(Vec2 aim2d){
			   animTime++;
			 if(animTime==3) {
			   performLightAttack();
			   this.mob.setDeltaMovement(aim2d.x*1.25, 0.1, aim2d.y*1.25);
			 }  
			 if(animTime>=6) {
			   animTime=0;
			 if (this.getRangeCheck()) {
			   this.mob.setAnimationState(21);
			 }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
		   }
		   
		   protected void tickPunchAttack(){
			   animTime++;
			 if(animTime==6) {
			   performLightAttack();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   protected void tickKickAttack(){
			   animTime++;
			 if(animTime==6) {
			   performLightAttack();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   protected void tickSlamAttack(){
			   animTime++;
			 if(animTime==6) {
			   performLightAttack();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		  
		   
		   protected void tickJumpAttack(Vec2 aim2d){
			   animTime++;
			   this.mob.getNavigation().stop();
			  
			 if(animTime>=6&&animTime<=10) {
				this.mob.setDeltaMovement(0, 1.5, 0);
			 }  
			 if(animTime>9&&animTime<=12) {
			
				 this.mob.setDeltaMovement(0, 0, 0);
				 
			 }
			 if(animTime==12) {
				 saveAim=aim2d;
			 }
			 if(animTime>12) {
					this.mob.setDeltaMovement(saveAim.x*1.25, -1.25, saveAim.y*1.25);
					performLargeSweepAttack(); 
				 }  
				 
			
			 if(animTime>=18) {
			   animTime=0;
			   this.mob.setAnimationState(6);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		  
		  
		   
		   //attack actions
		   
		  protected void performLightAttack() {
			    
		
					Vec3 pos = mob.position();
		    	    
		    	    
		    	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.5f, 0.1f);
		    	    this.mob.playSound(SoundEvents.RAVAGER_ATTACK, 1.5f, 0.1f);
		    	    
		    	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob),8.0f, 0.6f, mob, pos, 5.0f, -Math.PI/4, Math.PI/4, 1.0f, 3.0f, true);
		   
		   
		  }
		  protected void performLargeSweepAttack() {
			    
				
				Vec3 pos = mob.position();
	    	    //Vector2f vec = MathHelpers.OrizontalAimVector(MathHelpers.AimVector(this.mob.position(), this.mob.getTarget().position()));
	    	    
	    	   
	    	    
	    	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob),10.0f,1.6f, mob, pos, 3.0f, -3/2*Math.PI, 3/2*Math.PI, 1.0f, 3.0f, true);
	    	    
	    	    //this.mob.getTarget().knockback(2.5f, vec.x, vec.y);
		  
	   
	  }
		 
	
	   protected void resetAttackCooldown() {
	      this.ticksUntilNextAttack = 6;
	   }

	   protected boolean isTimeToAttack() {
	      return this.ticksUntilNextAttack <= 0;
	   }

	   protected int getTicksUntilNextAttack() {
	      return this.ticksUntilNextAttack;
	   }

	   protected int getAttackInterval() {
	      return 20;
	   }

	   protected double getAttackReachSqr(LivingEntity p_179512_1_) {
	      return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
	   }
	}
