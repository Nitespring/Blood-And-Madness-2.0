package com.nitespring.bloodborne.common.entities.mobs;

import java.util.EnumSet;
import java.util.UUID;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SkeletalPuppet extends AbstractBloodborneEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	
	
	public SkeletalPuppet(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.xpReward= 1;
	}
	
	

	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeletal_puppet.death"));
		}else {
		switch(animState) {
		
		case 21:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeletal_puppet.atk1"));
			break;
		case 22:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeletal_puppet.atk2"));
			break;
		case 23:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.skeletal_puppet.atk3"));
			break;
		default:
			 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
				 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeletal_puppet.walk"));
			 }else {
				 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.skeletal_puppet.idle"));
			 }
			break;
		
		}
		}
        return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 5, this::predicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	 @Override
	 protected int getBnMDefaultTeam() {
		return 6;
	 }
	@Override
	public boolean isBoss() {return false;}
	@Override
	public boolean isBeastly() {return false;}
	@Override
	public boolean isCorrupted() {return false;}
	@Override
	public boolean isKin() {return false;}
	@Override
	public int physRes() {return 200;}
	@Override
	public int fireRes() {return 100;}
	@Override
	public int boltRes() {return 50;}
	@Override
	public int arcRes() {return 300;}
	@Override
	public int bloodRes() {return 200;}
	
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 60.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.2D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
					.add(Attributes.FOLLOW_RANGE, 15);
		  }

	 	
	 
	 @Override
		protected void registerGoals() {
			
			this.goalSelector.addGoal(1, new SkeletalPuppet.AttackGoal(this));
			
			
			 this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
		      super.registerGoals();
		      
		}
	 
	
	 
	
	 
	 
	 //Skeleton
	 protected SoundEvent getAmbientSound() {
	      return SoundEvents.SKELETON_AMBIENT;
	   }

	   protected SoundEvent getHurtSound(DamageSource p_33579_) {
	      return SoundEvents.SKELETON_HURT;
	   }

	   protected SoundEvent getDeathSound() {
	      return SoundEvents.SKELETON_DEATH;
	   }

	   protected SoundEvent getStepSound() {
	      return SoundEvents.SKELETON_STEP;
	   }
	 
	   @Override
		public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
				MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
			  
			  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.puppet_hp.get());;
			  
			  
			
			return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		}
	 
	 
	 public class AttackGoal extends Goal{

			
		   private final double speedModifier = 1.0f;
		   private final boolean followingTargetEvenIfNotSeen = true;
		   protected final SkeletalPuppet mob;
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

		
		
		  public AttackGoal(SkeletalPuppet entityIn) {
		      this.mob = entityIn;
		      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		   }
		
		
		@Override
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
		
		
		@Override
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
		   @Override
		   public void start() {
		      this.mob.getNavigation().moveTo(this.path, this.speedModifier);
		      this.mob.setAggressive(true);
		      this.ticksUntilNextPathRecalculation = 0;
		      this.ticksUntilNextAttack = 0;
		      this.animTime = 0;
		      this.mob.setAnimationState(0);
		   }
		   @Override
		   public void stop() {
		      LivingEntity livingentity = this.mob.getTarget();
		      if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
		         this.mob.setTarget((LivingEntity)null);
		      }
		      
		      this.mob.setAnimationState(0);
		      
		      
		
		      this.mob.setAggressive(false);
		      this.mob.getNavigation().stop();
		   }
		
		   
			
		   
		@Override
		public void tick() {
			 LivingEntity target = this.mob.getTarget();
		      double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
		      double reach = this.getAttackReachSqr(target);
		      int animState = this.mob.getAnimationState();

			
			switch(animState) {
			
			case 21, 22, 23:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
			      if(animTime==10) {
			    	this.performLightAttack();  
			    	  
			      } 
			      
		          if(animTime>=16) {
			    	  this.mob.setAnimationState(0);
			    	  this.resetAttackCooldown();
			    	  this.animTime = 0;
			      } 
				break;
		
			default:
				   
				     
				   
				   this.doMovement(target, reach);
				   this.checkForCloseRangeAttack(distance, reach);
				   //this.checkForPreciseAttack();
				break;
					
			}
			  this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0); 
			
		}
		
		
		@SuppressWarnings("unused")
		private void checkForPreciseAttack() {
			 if (this.ticksUntilNextAttack <= 0) {
				 
				 this.mob.setAnimationState(72);
			 }
			
		}
		
		
		protected void doMovement(LivingEntity livingentity, Double d0) {
			this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
			this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
			   if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
			         this.pathedTargetX = livingentity.getX();
			         this.pathedTargetY = livingentity.getY();
			         this.pathedTargetZ = livingentity.getZ();
			         this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
			         if (this.canPenalize) {
			            this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
			            if (this.mob.getNavigation().getPath() != null) {
			               net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
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
					    }else if (r<=1200) {
					    	
					    	 this.mob.setAnimationState(22);
					    }else if (r<=1800) {
					    	
					    	 this.mob.setAnimationState(23);
					    }
				    } 
			   }
		   

	    
	    protected void performLightAttack() {

			Vec3 pos = mob.position();

	   	    
	   	    this.mob.playSound(SoundEvents.SKELETON_STEP, 1.0f, 1.2f);
	   	    
	   	    
	   	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob), 8.0f, 0.6f, mob, pos, 3.0f, -Math.PI/3, Math.PI/3, -1.0f, 2.0f);
	   	    
	   	    
	   	    
	   	    
	   }
	  
	  
	 


		
		
			protected void resetAttackCooldown() {
			    this.ticksUntilNextAttack = 20;
			 }
				
		
		  protected double getAttackReachSqr(LivingEntity p_179512_1_) {
		      return (double)(this.mob.getBbWidth() * 3.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
		   }

	}

}
