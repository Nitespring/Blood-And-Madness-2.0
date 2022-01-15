package com.nitespring.bloodborne.common.entities.mobs.beasts;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.common.customdamage.CustomDamageType;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractHuntsmanEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FlameProjectileEntity;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.StrollThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SilverbeastEntity extends AbstractBloodborneEntity implements IAnimatable{

	
	protected AnimationFactory factory = new AnimationFactory(this);
   
    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(1.4f, 1.2f, false);
	
	
	
	public SilverbeastEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		
	
	}

	


	
	@Override
	public void tick() {
		float healthMax = this.getMaxHealth();
		float health = this.getHealth();
		if(this.getCombatState()!=2&&health<=healthMax/2) {
			//this.setBoundingBox(EntityDimensions.fixed(healthMax, health));	
			this.setCombatState(2);
			
		}
		if(health<=healthMax/2) {
		  this.refreshDimensions();
		}
		super.tick();
	}
	
	
	
	
	
	@Override
	public EntityDimensions getDimensions(Pose p_19975_) {
		if(this.getCombatState()==2) {
			  return CRAWLING_BB;
			
		}else{
	      return this.getType().getDimensions();
		}
	      
	   }
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		int animState = this.getAnimationState();	
		int combatState = this.getCombatState();	
		
		
		
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.death", false));	
		}else {
	
			switch(animState) {
			case 11:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.retract", false));
				break;
			case 21:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_1", false));
				break;
			case 22:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_2", false));
				break;
			case 23:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_3", false));
				break;
			case 24:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite", false));
				break;
			case 25:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite2", false));
				break;
			case 26:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite3", false));
				break;
			case 27:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.breath", false));
				break;
			case 28:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.explode", false));
				break;
			case 31:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_1_retracted", false));
				break;
			case 32:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_2_retracted", false));
				break;
			case 33:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_3_retracted", false));
				break;
			case 34:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite_retracted", false));
				break;
			case 35:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite2_retracted", false));
				break;
			case 36:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite3_retracted", false));
				break;
			case 37:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.breath_retracted", false));
				break;
			case 38:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.explode_retracted", false));
				break;
			case 41:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_bite_down", false));
				break;
			case 42:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.atk_down_worm", false));
				break;
			
			default:
				switch(combatState) {
				case 1:
					if(!(event.getLimbSwingAmount() > -0.03F && event.getLimbSwingAmount() < 0.03F)){
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.walk_retracted", true));
					}else {
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.idle_retracted", true)); 
					} 
				break;
				case 2:
					if(!(event.getLimbSwingAmount() > -0.03F && event.getLimbSwingAmount() < 0.03F)){
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.walk_down", true));
					}else {
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.idle_down", true)); 
					} 
				break;
				default:
					if(!(event.getLimbSwingAmount() > -0.03F && event.getLimbSwingAmount() < 0.03F)){
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.walk", true));
					}else {
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.silverbeast.idle", true)); 
					} 
				break;
				}
				break;
		}
	
		  
		  
		  
		  
		  
		  
	  
		}
        return PlayState.CONTINUE;
	}
	
	
	

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "main_controller", 2, this::predicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		
		return this.factory;
	}
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 200.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.3D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }

	@Override
	public boolean isBoss() {
		
		return false;
	}

	@Override
	public boolean isBeastly() {
		
		return true;
	}

	@Override
	public boolean isCorrupted() {
		
		return false;
	}

	@Override
	public boolean isKin() {
		
		return false;
	}

	@Override
	public int physRes() {
		
		return 650;
	}

	@Override
	public int fireRes() {
		
		return -200;
	}

	@Override
	public int boltRes() {
		
		return 450;
	}

	@Override
	public int arcRes() {
		
		return 230;
	}

	@Override
	public int bloodRes() {
		
		return 270;
	}
	
	
	@Override
	protected void registerGoals() {
		
		this.goalSelector.addGoal(1, new SilverbeastEntity.StandingMeleeAttackGoal(this,  1.0F, true));
		this.goalSelector.addGoal(1, new SilverbeastEntity.CrawlingMeleeAttackGoal(this,  1.2F, true));
		
		
		
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
	      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	
	      
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this).setAlertOthers(SilverbeastEntity.class)));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractHuntsmanEntity.class, true));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FatherGascoigneBossEntity.class, true));
     
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
	      
	    
	      
	}
	
	

     
	
	
	
	
	
	
	private class StandingMeleeAttackGoal extends Goal{

		 protected final SilverbeastEntity mob;
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
		   
		   

		   public StandingMeleeAttackGoal(SilverbeastEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
		      this.mob = p_i1636_1_;
		      this.speedModifier = p_i1636_2_;
		      this.followingTargetEvenIfNotSeen = p_i1636_4_;
		      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		   }

		   public boolean canUse() {
			   if(this.mob.getCombatState()!=2) {
		      long i = this.mob.level.getGameTime();
		     
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
			   }else {
				   return false;
			   }
		      
		   }

		   public boolean canContinueToUse() {
			   if(this.mob.getCombatState()!=2) {
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
			   }else {
				   return false;
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
			   this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
				  
			   LivingEntity target = this.mob.getTarget();
			      double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
			      double reach = this.getAttackReachSqr(target);
			      int animState = this.mob.getAnimationState();
			      Vec3 aim = this.mob.getLookAngle();
			      Vec2 aim2d = new Vec2((float)(aim.x/(1-Math.abs(aim.y))), (float)(aim.z/(1-Math.abs(aim.y))));
		   
			    
					
					switch(animState) {
					case 11:
						tickRetractNails();
						break;
					case 21:
						tickHandAttack1();
						break;
					case 22:
						tickHeavyHandAttack();
						break;
					case 23:
						tickHandAttack2();
						break;
					case 24:
						tickBiteAttack1();
						break;
					case 25:
						tickBiteAttack2();
						break;
					case 26:
						tickBiteAttack3();
						break;
					case 27:
						tickBreath();
						break;
					case 28:
						tickExplode();
						break;
					case 31:
						tickHandAttack1();
						break;
					case 32:
						tickHeavyHandAttack();
						break;
					case 33:
						tickHandAttack2();
						break;
					case 34:
						tickBiteAttack1();
						break;
					case 35:
						tickBiteAttack2();
						break;
					case 36:
						tickBiteAttack3();
						break;
					case 37:
						tickBreath();
						break;
					case 38:
						tickExplode();
						break;
					default:
						
						this.ticksUntilNextAttack = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
						this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		      			this.doMovement(target, distance);
						this.checkForCloseRangeAttack(distance, reach);
						this.checkForChangeState();
						break;
					
					}
		   
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
				   
				   if(this.mob.getCombatState()==1) {
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=850) {
						   this.mob.setAnimationState(31);
						    }else if(r<=1000){
						   this.mob.setAnimationState(32);
						    }else  if(r<=1550){
						   this.mob.setAnimationState(34);		
						    }else  if(r<=1850){
						   this.mob.setAnimationState(37);		
						    }else{
						   this.mob.setAnimationState(38);		
						    }
				 
				   }else {
				 
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=850) {
						   this.mob.setAnimationState(21);
						    }else if(r<=1000){
						   this.mob.setAnimationState(22);
						    }else  if(r<=1550){
						   this.mob.setAnimationState(24);		
						    }else  if(r<=1850){
						   this.mob.setAnimationState(27);		
						    }else{
						   this.mob.setAnimationState(28);		
						    }
				 
	            } 
		     }
		   }
		   protected void checkForChangeState(){
			 
				   if(this.mob.getCombatState()!=1) {
				   
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=50) {
						   this.mob.setAnimationState(11);		
						  	
						    }
				   }
	            
					   
		     }
		   
		 
		   
		   protected boolean getRangeCheck() {
			   
		          return 
		        		  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
		        		  	<= 
		        		  1.5*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		   
		   
		   protected void tickRetractNails(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==6) {
				 this.mob.setCombatState(1);
			 }  
			 if(animTime>=10) {
			   animTime=0;
				   this.mob.setAnimationState(0); 
				   this.ticksUntilNextPathRecalculation = 0;
			    
			 }  
			 
		   }
		   
		   
		   
		   protected void tickHandAttack1(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==6) {
			   performAttackHand();
			 }  
			 if(animTime>=8) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   if(this.mob.getCombatState()==1) {
					this.mob.setAnimationState(33);   
				   }else {
				   this.mob.setAnimationState(23);
				   }
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   } 
			 }  
			 
		   }
		   protected void tickHandAttack2(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==4) {
			   performAttackHand();
			 }  
			 if(animTime>=7) {
			   animTime=0;
			  
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			 
		   }
		   protected void tickHeavyHandAttack(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
			   performHeavyAttackHand();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			  
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			 
		   }
		   protected void tickBiteAttack1(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==7) {
			   performAttackBite();
			 }  
			 if(animTime>=10) {
			   animTime=0;
			   if (this.getRangeCheck()) {
					   if(this.mob.getCombatState()==1) {
						this.mob.setAnimationState(35);   
					   }else {
				   this.mob.setAnimationState(25);
					   }
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   } 
			 }  
			 
		   }
		   protected void tickBiteAttack2(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
			   performAttackBite();
			 }  
			 if(animTime>=7) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   if(this.mob.getCombatState()==1) {
						this.mob.setAnimationState(36);   
					   }else {
				   this.mob.setAnimationState(26);
					   }
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   } 
			 }  
			 
		   }
		   protected void tickBiteAttack3(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
			   performAttackBite();
			 }  
			 if(animTime>=9) {
			   animTime=0;
			 
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			 
		   }
		   
		   
		   protected void tickBreath() {
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime>=10&&animTime<=55) {
			   breathFire();
			 }  
			 if(animTime>=66) {
			   animTime=0;
			 
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			   
		   }
		   
		   protected void tickExplode() {
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==6) {
			   explode();
			 }  
			 if(animTime>=10) {
			   animTime=0;
			 
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			   
		   }
		   
		   
		   
		   protected void breathFire() {
			   Vec3 pos = this.mob.position();
				Vec3 aim = this.mob.getLookAngle();
				double a = Math.PI/10;
			
				
				for (int i=-2; i<=2; i++) {
					for (int k=-1; k<=1; k++) {
						
						FlameProjectileEntity flame = new FlameProjectileEntity(ProjectileInit.FLAME.get(), this.mob.level, 4.0f);
						flame.setOwner(this.mob);
						double x = pos.x + aim.x + 0.75*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));
						double z = pos.z + aim.z + 0.75*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));
						double y = pos.y + 2 + 0.075*(aim.y+ 0.5*Math.sin(k*a));
						flame.setPos(x, y, z);
						//flame.setPos(pos);
						flame.xPower=aim.x*0.04 + 0.005*(aim.x*Math.cos(i*a)-aim.z*Math.sin(i*a));
						flame.yPower=aim.y*0.04 - 0.025 + 0.03*(Math.sin(k*a));
						flame.zPower=aim.z*0.04 + 0.005*(aim.z*Math.cos(i*a)+aim.x*Math.sin(i*a));
						this.mob.level.addFreshEntity(flame);
						//System.out.println("added flame to:" + x + y + z );
						
					}
					}
		   }
		   
		   
		   protected void explode() {
			   Vec3 pos = this.mob.position();
			   
				Vec2 knockVec = MathHelpers.OrizontalAimVector(
						MathHelpers.AimVector(new Vec3(-this.mob.position().x, -this.mob.position().y, -this.mob.position().z), 
						new Vec3(-this.mob.getTarget().position().x, -this.mob.getTarget().position().y, -this.mob.getTarget().position().z)
					 ));
			   AABB scanAbove = new AABB(pos.x-8, pos.y - 4, pos.z- 8, pos.x+ 8, pos.y + 8, pos.z + 8);
				List<LivingEntity> entities = new ArrayList<>(this.mob.level.getEntitiesOfClass(LivingEntity.class, scanAbove));
			 
				   if(!entities.isEmpty()) {
					   for(int n = 0; n < entities.size(); n++) {
					
						   LivingEntity target = entities.get(n);
					
						   if(target != this.mob) {
							   //entityIn.doHurtTarget(target);
							   target.hurt(CustomDamageType.BOLT_DAMAGE, 16.0f);
							   target.setLastHurtByMob(this.mob);
							   
							   target.knockback(1.2, knockVec.x, knockVec.y);
							   
						   }
					   }
					}
				   
				   
				   
				   explosionParticles(pos);
		   }
		   
		   protected void explosionParticles(Vec3 pos) {
			   Level worldIn = this.mob.level;
	    	   this.mob.playSound(SoundEvents.LIGHTNING_BOLT_THUNDER, 2.0F, 1.4f);
	    	   ParticleOptions particle = new DustParticleOptions(new Vector3f(0.0f,1,1), 2.0f);
				
				double x0= pos.x;
				double y0= pos.y+1.5;
				double z0= pos.z;
				
				for (int i = 0; i <= 64; ++i) {
					for (int k = 0; k <= 64; ++k) {	
						
						
			    
				double x = x0 + 4 * Math.cos(i*Math.PI/32) * Math.cos(k*Math.PI/32);
				double y = y0  +  4 * Math.sin(i*Math.PI/32); 
				double z = z0 +  4 * Math.cos(i*Math.PI/32) *  Math.sin(k*Math.PI/32);
				
				
				if(worldIn instanceof ServerLevel) {
					
					
					((ServerLevel) worldIn).sendParticles( particle, x, y, z, 1,  0, 0, 0, 0);
					
					
					
					
				
	    	   
	    	   
						}
					}
				}
			   
			   
			   
			   
		   }
		   
		   
		   
		   
		   
		   
		   
		   protected void performAttackHand() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.WOLF_GROWL, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttack(DamageSource.mobAttack(mob),5.0f, 0.1f, mob, pos, 2.5f, -Math.PI/5, Math.PI/5, -1.0f, 3.0f);
	   
	   
	  }
		   protected void performHeavyAttackHand() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.WOLF_GROWL, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttack(DamageSource.mobAttack(mob),5.0f, 0.1f, mob, pos, 2.5f, -Math.PI/5, Math.PI/5, -1.0f, 3.0f);
	   
	   
	  }
		   protected void performAttackBite() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.WOLF_GROWL, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttack(DamageSource.mobAttack(mob),5.0f, 0.1f, mob, pos, 2.5f, -Math.PI/5, Math.PI/5, -1.0f, 3.0f);
	   
	   
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
	
	private class CrawlingMeleeAttackGoal extends Goal{

		 protected final SilverbeastEntity mob;
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
		   
		   

		   public CrawlingMeleeAttackGoal(SilverbeastEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
		      this.mob = p_i1636_1_;
		      this.speedModifier = p_i1636_2_;
		      this.followingTargetEvenIfNotSeen = p_i1636_4_;
		      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		   }

		   public boolean canUse() {
			   if(this.mob.getCombatState()==2) {
		      long i = this.mob.level.getGameTime();
		     
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
			   }else {
				   return false;
			   }
		      
		   }

		   public boolean canContinueToUse() {
			   if(this.mob.getCombatState()==2) {
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
			   }else {
				   return false;
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
			   this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
				  
			   LivingEntity target = this.mob.getTarget();
			      double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
			      double reach = this.getAttackReachSqr(target);
			      int animState = this.mob.getAnimationState();
			      Vec3 aim = this.mob.getLookAngle();
			      Vec2 aim2d = new Vec2((float)(aim.x/(1-Math.abs(aim.y))), (float)(aim.z/(1-Math.abs(aim.y))));
		   
			    
					
					switch(animState) {
					case 41:
						tickBiteAttack();
						break;
					case 42:
						tickWormAttack();
						break;
					default:
						
						this.ticksUntilNextAttack = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
						this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		      			this.doMovement(target, distance);
						this.checkForCloseRangeAttack(distance, reach);
						this.checkForMediumRangeAttack(distance, reach);
					
						break;
					
					}
		   
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
				   
				   this.mob.setAnimationState(41);
				 
			
	            
		     }
		   }
		   
           protected void checkForMediumRangeAttack(double distance, double reach){
			   
			   if (distance <= 4*reach && this.ticksUntilNextAttack <= 0) {
				   
				
				 
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=350) {
						  
						    
						   this.mob.setAnimationState(42);
						    }
				 
	            
		     }
		   }
		  
		   
		 
		   
		   protected boolean getRangeCheck() {
			   
		          return 
		        		  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
		        		  	<= 
		        		  1.5*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		 
		   protected void tickBiteAttack(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
			   performAttackBite();
			 }  
			 if(animTime>=7) {
			   animTime=0;
			 
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			 
		   }
		   protected void tickWormAttack(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==5) {
			   performAttackWorm();
			 }  
			 if(animTime>=18) {
			   animTime=0;
			 
				   this.mob.setAnimationState(0); 
				   this.ticksUntilNextAttack = 50;
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
			 
		   }
		   
		   
		   
		   
		   protected void performAttackBite() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.WOLF_GROWL, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttack(DamageSource.mobAttack(mob),5.0f, 0.1f, mob, pos, 2.5f, -Math.PI/5, Math.PI/5, -1.0f, 3.0f);
	   
	   
	  }
		   protected void performAttackWorm() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.WOLF_GROWL, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LongAttackWithTargetCheck(DamageSource.mobAttack(mob),7.0f, 0.1f, mob, pos, 6.0f, -2.0, 2.0, -1.0f, 2.0f);
	   
	   
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
	
	
	
	

}
