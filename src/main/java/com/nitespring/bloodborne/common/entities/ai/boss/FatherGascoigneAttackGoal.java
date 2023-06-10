package com.nitespring.bloodborne.common.entities.ai.boss;

import java.util.EnumSet;
import java.util.Random;


import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.ProjectileInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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



public class FatherGascoigneAttackGoal extends Goal {
	   protected final FatherGascoigneBossEntity mob;
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
	   private int ticksUntilNextTransform;
	   

	   public FatherGascoigneAttackGoal(FatherGascoigneBossEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
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
	      this.ticksUntilNextTransform = 200;
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
		      //double pi = Math.PI;
		      
		      
		    
		      switch(animState) {
		      //evasive actions
		      		case 5:
		      			break;
		      		case 6:
		      			tickTransformTo2H();
	      				break;
		      		case 7:
		      			tickTransformTo1H();
	      				break;
		      		case 11:
		      			tickRoll(aim2d);
		      			break;
		      		case 12:
		      			tickSidestepR(aim2d);
		      			break;
		      		case 13:
		      			tickSidestepL(aim2d);
		      			break;
		      		case 14:
		      			tickSidestepR(aim2d);
		      			break;
		      		case 15:
		      			tickSidestepL(aim2d);
		      			break;
		      //melee attacks
		      		case 21:
		      			tick1hLightAttack(); 
		      			break;
		      		case 22:
		      			tick1hLightAttack2(); 
		      			break;
		      		case 23:
		      			tick1hLightAttack3(); 
		      			break;
		      		case 24:
		      			tick1hRollAttack(); 
		      			break;
		      		case 25:
		      			tick1hJumpAttack(aim2d); 
		      			break;
		      		case 26:
		      			tickShootGun(aim2d); 
		      			break;
		      		case 31:
		      			tick2hLightAttack(); 
		      			break;
		      		case 32:
		      			tick2hLightAttack2(); 
		      			break;
		      		case 33:
		      			tick2hLightAttack3(); 
		      			break;
		      		case 34:
		      			tick2hRollAttack(); 
		      			break;
		      		case 35:
		      			tick2hJumpAttack(aim2d); 
		      			break;
		      		case 36:
		      			tickShootGun(aim2d); 
		      			break;
		      		case 37:
		      			tick2hLightAttack4(); 
		      			break;
		      		case 38:
		      			tick2hLightAttackThrust(); 
		      			break;
		      		case 39:
		      			tick2hRunAttack(); 
		      			break;
		      		case 40:
		      			tick2hChargedAttack(); 
		      			break;
		      		default:
		      
		      			this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
		      			ticksUntilNextTransform--;
		      			
		      			
		      			this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		      			this.doMovement(target, distance);
		      			this.checkForCloseRangeAttack(distance, reach);
		      			this.checkForMediumRangeAction(distance, reach);
		      			this.checkForTransform();
		     
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
				   
				   if(this.mob.getEntityState()==1) {
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=600) {
						   this.mob.setAnimationState(31);
						    }else if(r<=800){
						   this.mob.setAnimationState(38);
						    }else  if(r<=1400){
						   this.mob.setAnimationState(39);		
						    }else {
						   this.mob.setAnimationState(40);		
						    }
				   
				   } else {
					   
					   this.mob.setAnimationState(21); 
					    
				   }
			  
	   } 
		   }
		   
		   protected void checkForTransform(){
			   
			   if (this.ticksUntilNextTransform <= 0) {
				   
				    int r = this.mob.getRandom().nextInt(2048);
				    
				    if(r<=400) {
				    if(this.mob.getEntityState()==1) {
				      this.mob.setAnimationState(7);  	
				    }else
			    	  this.mob.setAnimationState(6);
				    }
			  
	   } 
		   }
		   
		   protected void checkForMediumRangeAction(double distance, double reach){
			   if (distance <= 6*reach && this.ticksUntilNextAttack <= 0) {
				   if(this.mob.getEntityState()==1) {
					   int r = this.mob.getRandom().nextInt(4096);
					    
					    if(r<=600) {
					    
				    	  this.mob.setAnimationState(11);
					    } else if(r<=1000) {
					      this.mob.setAnimationState(35);
					    }
					    else if(r<=1200) {
						  this.mob.setAnimationState(36);
						}
					    else if(r<=1700) {
					    	this.mob.setAnimationState(14);
					    }
					    else if(r<=2200) {
					    	this.mob.setAnimationState(15);
					    }  
					   
					   
					   
					   
				   }else {
				    int r = this.mob.getRandom().nextInt(4096);
				    
				    if(r<=600) {
				    
			    	  this.mob.setAnimationState(11);
				    } else if(r<=1000) {
				      this.mob.setAnimationState(25);
				    }
				    else if(r<=1200) {
					  this.mob.setAnimationState(26);
					}
				    else if(r<=1700) {
				    	this.mob.setAnimationState(12);
				    }
				    else if(r<=2200) {
				    	this.mob.setAnimationState(13);
				    }
				   }
	   } 
		   }
		   
		   
		   protected boolean getRangeCheck() {
			   
	          return 
	        		  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
	        		  	<= 
	        		  1.5*this.getAttackReachSqr(this.mob.getTarget());
			      
		   }
		   protected boolean getRangeCheck2() {
			   
			   return 
					  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
					  	<= 
					  3.0*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		   protected boolean getRangeCheckRanged() {
			   
			   return 
					  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
					  	<= 
					  7.0*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		   
		   //transform Ticks
		   protected void tickTransformTo2H(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime>=20) {
			   animTime=0;
			       this.mob.setEntityState(1);  
				   this.mob.setAnimationState(0); 
				   this.resetTransformCooldown();
			   
			 }  
			 
		   }
		   
		   protected void tickTransformTo1H(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime>=20) {
			   animTime=0;
			       this.mob.setEntityState(0);  
				   this.mob.setAnimationState(0); 
				   this.resetTransformCooldown();
			   
			 }  
			 
		   }
		   
		   
		   
		   
		   //evasive actions ticks
		   protected void tickRoll(Vec2 aim2d){
			   
			   animTime++;
			 if(animTime==2) {
				 this.mob.getNavigation().stop();
			   this.mob.setDeltaMovement(aim2d.x*0.7, 0.1, aim2d.y*0.7);
			 }  
			 if(animTime>=12) {
			   animTime=0;
			   if (this.getRangeCheck2()) {
				   if(this.mob.getEntityState()==1) {
				   this.mob.setAnimationState(34);   
				   }else {
				   this.mob.setAnimationState(24);
				   }
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   
		   protected void tickSidestepR(Vec2 aim2d){
			   this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()));  
			   animTime++;
			 if(animTime==5) {
				 
				  double startX=this.mob.getX();
		    	  
		    	  double startZ=this.mob.getZ();
		    	  double destX=startX + 4*(aim2d.x*Math.cos(Math.PI/4) - aim2d.y*Math.sin(Math.PI/4));
		    	  
		    	  double destZ=startZ + 4*(aim2d.y*Math.cos(Math.PI/4) + aim2d.x*Math.sin(Math.PI/4) );
		    	 
		    	  
			   this.mob.setDeltaMovement((destX-startX)*0.7/2.5, 0.15, (destZ-startZ)*0.7/2.5);
			 }  
			 if(animTime>=20) {
				
			   animTime=0;
			   if (this.getRangeCheckRanged()) {
				   this.mob.getNavigation().stop();
				   if(this.mob.getEntityState()==1) {
				   this.mob.setAnimationState(36);
				   }else {
					this.mob.setAnimationState(26);
				   }
				   
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   
		   protected void tickSidestepL(Vec2 aim2d){
			   this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ())); 
			   animTime++;
			 if(animTime==5) {
				 
				  double startX=this.mob.getX();
		    	  
		    	  double startZ=this.mob.getZ();
		    	  double destX=startX + 4*(aim2d.x*Math.cos(Math.PI/4) + aim2d.y*Math.sin(Math.PI/4));
		    	  
		    	  double destZ=startZ + 4*(aim2d.y*Math.cos(Math.PI/4) - aim2d.x*Math.sin(Math.PI/4) );
		    	 
		    	  
			   this.mob.setDeltaMovement((destX-startX)*0.7/2.5, 0.15, (destZ-startZ)*0.7/2.5);
			 }  
			 if(animTime>=20) {
				
			   animTime=0;
			   if (this.getRangeCheckRanged()) {
				   this.mob.getNavigation().stop();
				   if(this.mob.getEntityState()==1) {
					   this.mob.setAnimationState(36);
					   }else {
						this.mob.setAnimationState(26);
					   }
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   
		   
		   

		 //attack ticks
		   
		   
		   protected void tick1hLightAttack(){
			   animTime++;
			 if(animTime==5) {
			   performLightAttack();
			 }  
			 if(animTime>=10) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(22);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   protected void tick1hLightAttack2(){
			   animTime++;
			 if(animTime==6) {
			   performLightAttack();
			 }  
			 if(animTime>=12) {
			   animTime=0;
			 if (this.getRangeCheck()) {
			   this.mob.setAnimationState(23);
			 }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
		   }
		   
		   protected void tick1hLightAttack3(){
			   animTime++;
			 if(animTime==5) {
			   performLightAttack();
			 }  
			 if(animTime>=10) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		   protected void tick1hRollAttack(){
			   animTime++;
			   if(animTime<=3) {
					 this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()));
				 }
			 if(animTime==4) {
			   performLightAttack();
			 }  
			 if(animTime>=12) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		   protected void tick1hJumpAttack(Vec2 aim2d){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
				this.mob.setDeltaMovement(aim2d.x*0.5, 0.3, aim2d.y*0.5);
			 }  
			 if(animTime==17) {
				performLightAttack(); 
			 }  
			 if(animTime>=20) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		   protected void tickShootGun(Vec2 aim2d){
			   animTime++;
			 if(animTime<=2) {
				 this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()));
			 }
			 if(animTime==3) {
				 this.mob.getNavigation().stop();
				 performRangeAttack(aim2d); 
			 }  
			 
			 if(animTime>=27) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		   
		   protected void tick2hLightAttack(){
			   animTime++;
			 if(animTime==8) {
			   performLightAttack();
			 }  
			 if(animTime>=11) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(32);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   protected void tick2hLightAttack2(){
			   animTime++;
			 if(animTime==10) {
			   performLightAttack();
			 }  
			 if(animTime>=11) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(33);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   protected void tick2hLightAttack3(){
			   animTime++;
			 if(animTime==3) {
			   performLightAttack();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(37);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   } 
			 }  
			 
		   }
		   
		   protected void tick2hLightAttack4(){
			   animTime++;
			 if(animTime==15) {
			   performLightAttack();
			 }  
			 if(animTime>=18) {
			   animTime=0;
			  
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   
			 }  
			 
		   }
		   
		   
		   protected void tick2hLightAttackThrust(){
			   animTime++;
			 if(animTime==7) {
			   performLightAttack();
			 }  
			 if(animTime>=14) {
			   animTime=0;
			  
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
			   
			 }  
			 
		   }
		   
		   protected void tick2hRollAttack(){
			   animTime++;
			   if(animTime<=3) {
					 this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()));
				 }
			 if(animTime==6) {
			   performLightAttack();
			 }  
			 if(animTime>=13) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   
		   protected void tick2hJumpAttack(Vec2 aim2d){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==4) {
				this.mob.setDeltaMovement(aim2d.x*0.5, 0.3, aim2d.y*0.5);
			 }  
			 if(animTime==13) {
				performLightAttack(); 
			 }  
			 if(animTime>=17) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   protected void tick2hRunAttack(){
			   animTime++;
			   this.mob.getNavigation().stop();
			   if(animTime<=5) {
					 this.doMovement(this.mob.getTarget(), this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()));
				 }
			 if(animTime==5) {
				performLightAttack(); 
			 }  
			 if(animTime>=9) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		   protected void tick2hChargedAttack(){
			   
			   animTime++;
			   this.mob.getNavigation().stop();
			   if(animTime==15) {
				   performLargeSweepAttack(); 
				 }
			   if(animTime==17) {
				   Vec2 vec = MathHelpers.OrizontalAimVector(MathHelpers.AimVector(this.mob.position(), this.mob.getTarget().position()));
					performSweepAttackBehind();
					this.mob.setDeltaMovement(vec.x*0.5, 0.1, vec.y*0.5);
				 }   
			 if(animTime==20) {
				 performLargeSweepAttack(); 
			 }  
			 if(animTime>=33) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			 }  
		   }
		  
		   
		   //attack actions
		   
		  protected void performLightAttack() {
			    
		
					Vec3 pos = mob.position();
		    	    
		    	    
		    	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 2.0f, 0.2f);
		    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
		    	    
		    	    
		    	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob),6.0f, 0.4f, mob, pos, 3.5f, -Math.PI/4, Math.PI/4, 1.0f, 3.0f);
		   
		   
		  }
		  protected void performLargeSweepAttack() {
			    
				
				Vec3 pos = mob.position();
	    	    //Vector2f vec = MathHelpers.OrizontalAimVector(MathHelpers.AimVector(this.mob.position(), this.mob.getTarget().position()));
	    	    
	    	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob),10.0f,1.6f, mob, pos, 4.5f, -Math.PI/2, Math.PI/2, 1.0f, 3.0f);
	    	    
	    	    //this.mob.getTarget().knockback(2.5f, vec.x, vec.y);
		  
	   
	  }
		 
		  
		  protected void performSweepAttackBehind() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob),9.0f,2.0f, mob, pos, 3.5f, Math.PI/4, -Math.PI/4, 1.0f, 3.0f);
	   
	   
	  }
		   
		   
		  protected void performRangeAttack(Vec2 aim) {
			   
			   FatherGascoigneBossEntity playerIn = this.mob;
			   Level worldIn = this.mob.level();
			   
			   Vec3 pos = playerIn.position();
	          
	  		  RandomSource rng = playerIn.getRandom();
	  		  int sizeVar = rng.nextInt(5) -2;
	  		  
	  		  int nBullets = 10 + sizeVar;
	 		    for(int i = -nBullets/2; i < nBullets/2; i++) {
	 		    	RandomSource rng1 = playerIn.getRandom();
	 		    	RandomSource rng2 = playerIn.getRandom();
	 		    	int dmgVar = rng1.nextInt(10) -4;
	 		    	double rngPos = rng2.nextInt(1)*0.1f - 0.4f;
	 		    	BulletProjectileEntity bullet = new BulletProjectileEntity(ProjectileInit.BULLET_ENTITY.get(), 7.0f, 60, worldIn, this.mob);
	 		    	bullet.setOwner(this.mob);
	 		   
	 		   worldIn.playSound((Player)null, pos.x, pos.y, pos.z, SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 0.5F, 0.4F );
	 		    	bullet.setPos(playerIn.getX() + aim.x * 0.5D, playerIn.getY() + 0.7, playerIn.getZ() + aim.y * 0.5D);
	 		   
	 				    bullet.xPower = aim.x * 0.2 + Math.sin(i)* 0.15 * rngPos;
	 				    bullet.yPower = Math.tan(i)* 0.05 * rngPos;
	 				    bullet.zPower = aim.y * 0.2 + Math.cos(i)* 0.15 * rngPos;
	 		
	 				    
	 				    worldIn.addFreshEntity(bullet);	
	 		    }
			
		}
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
	   protected void resetTransformCooldown() {
		  this.ticksUntilNextTransform = 400;
	   }	   
		   
		   
	   protected void resetAttackCooldown() {
	      this.ticksUntilNextAttack = 20;
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
	      return (double)(this.mob.getBbWidth() * 2.5F * this.mob.getBbWidth() * 2.5F + p_179512_1_.getBbWidth());
	   }
	}
