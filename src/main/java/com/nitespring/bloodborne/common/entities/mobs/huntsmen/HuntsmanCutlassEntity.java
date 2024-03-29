package com.nitespring.bloodborne.common.entities.mobs.huntsmen;

import java.util.EnumSet;
import java.util.Random;

import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractHuntsmanEntity;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec2;
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


public class HuntsmanCutlassEntity extends AbstractHuntsmanEntity implements GeoEntity{

	
	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

	public HuntsmanCutlassEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.xpReward= 6;
	}

	
	
	
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		int combatState = this.getCombatState();	
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman_axe.death"));	
		}else {
		switch(animState) {
		
		case 21:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman_cutlass.atk_r1"));
			break;
		case 22:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman_cutlass.atk_r2"));
			break;
		case 23:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman_cutlass.atk_h1"));
			break;
		case 24:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman_cutlass.atk_h2"));
			break;
		default:
			//switch(combatState) {
			//case 0:
			if(this.isAggressive()) {
				if(!(event.getLimbSwingAmount() > -0.03F && event.getLimbSwingAmount() < 0.03F)){
				 	event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman_cutlass.ag_walk"));
			 	}else {
				 	event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman_cutlass.ag_idle")); 
			 	}
			 	//break;
			}else {
			 //default:
				if(!(event.getLimbSwingAmount() > -0.03F && event.getLimbSwingAmount() < 0.03F)){
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman_cutlass.walk"));
				 }else {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman_cutlass.idle")); 
				 } 
				 //break;
			//}
			}
			break;
		
		}
		}
        return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState clothPredicate(AnimationState<E> event) {
		 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman_axe.cloth")); 
		 return PlayState.CONTINUE;
	}
	

	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 1, this::predicate));
		data.add(new AnimationController<>(this, "cloth_controller", 10, this::clothPredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 28.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.25D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	 
	 @Override
	 protected int getBnMDefaultTeam() {
		return 1;
	 }

	@Override
	public boolean isBoss() {
		
		return false;
	}

	@Override
	public boolean isBeastly() {
		
		return false;
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
		
		return 350;
	}

	@Override
	public int fireRes() {
		
		return 100;
	}

	@Override
	public int boltRes() {
		
		return 150;
	}

	@Override
	public int arcRes() {
		
		return 120;
	}

	@Override
	public int bloodRes() {
		
		return 180;
	}
	
	
	@Override
	protected void registerGoals() {
		
		this.goalSelector.addGoal(1, new HuntsmanCutlassEntity.MeleeAttackGoal(this,  1.35F, true));
		//SkeletonEntity
		
		
		
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.4D));
		
	      this.getHuntsmanGoals();
	      super.registerGoals();
	      
	    
	      
	}
	
	 public static boolean checkHuntsmanCutlassSpawnRules(EntityType<? extends AbstractBloodborneEntity> e, ServerLevelAccessor access, MobSpawnType type, BlockPos pos, RandomSource r) 
     {  	 
    	return checkBnMMonsterSpawnRules(e, access, type, pos, r) && 
    			CommonConfig.should_huntsman_cutlass_spawn.get();
     }
	
	  @Override
		public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
				MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
			  
			  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.huntsman_cutlass_hp.get());;
			  
			  
			
			return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		}
	private class MeleeAttackGoal extends Goal{

		 protected final HuntsmanCutlassEntity mob;
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
		   
		   

		   public MeleeAttackGoal(HuntsmanCutlassEntity p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
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
		   
			    
					
					switch(animState) {
					case 21:
						tickLightAttack();
						break;
					case 22:
						tickLightAttack2();
						break;
					case 23:
						tickHeavyAttack();
						break;
					case 24:
						tickHeavyThrustAttack();
						break;
					default:
						this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
						this.ticksUntilNextAttack = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
						this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
		      			this.doMovement(target, distance);
						this.checkForCloseRangeAttack(distance, reach);
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
				   
				   
					   int r = this.mob.getRandom().nextInt(2048);
					   if(r<=850) {
						   this.mob.setAnimationState(21);
						    }else if(r<=1000){
						   this.mob.setAnimationState(22);
						    }else  if(r<=1550){
						   this.mob.setAnimationState(23);		
						    }else {
						  this.mob.setAnimationState(24);		
						    }
				 
	            } 
		     }
		   
		 
		   
		   protected boolean getRangeCheck() {
			   
		          return 
		        		  this.mob.distanceToSqr(this.mob.getTarget().getX(), this.mob.getTarget().getY(), this.mob.getTarget().getZ()) 
		        		  	<= 
		        		  1.5*this.getAttackReachSqr(this.mob.getTarget());
				      
			   }
		   
		   
		   protected void tickLightAttack(){
			   animTime++;
			   this.mob.getNavigation().stop();
			 if(animTime==4) {
			   performLightAttack();
			 }  
			 if(animTime>=8) {
			   animTime=0;
			   if (this.getRangeCheck()) {
				   this.mob.setAnimationState(22);
			   }else {
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   } 
			 }  
			 
		   }
		   
		   protected void tickLightAttack2(){
			   animTime++;
			  
			 if(animTime==4) {
			   performLightAttack();
			 }  
			 if(animTime>=7) {
			   animTime=0;
			
				   this.mob.setAnimationState(0); 
				   this.resetAttackCooldown();
				   this.ticksUntilNextPathRecalculation = 0;
			   
			 }  
		   }
		
		   protected void tickHeavyAttack(){
			   animTime++;
			 if(animTime==8) {
			   performHeavyAttack();
			 }  
			 if(animTime>=14) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			   this.ticksUntilNextPathRecalculation = 0;
			 }  
		   }
		   
		   protected void tickHeavyThrustAttack(){
			   animTime++;
			 if(animTime==7) {
			   performHeavyAttack();
			 }  
			 if(animTime>=12) {
			   animTime=0;
			   this.mob.setAnimationState(0);
			   this.resetAttackCooldown();
			   this.ticksUntilNextPathRecalculation = 0;
			 }  
		   }
		   
		   
		   protected void performLightAttack() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttackWithTargetCheck(mob.damageSources().mobAttack(mob),5.0f, 0.1f, mob, pos, 2.5f, -Math.PI/5, Math.PI/5, -1.0f, 3.0f);
	   
	   
	  }
		   
		   
		   protected void performHeavyAttack() {
			    
				
				Vec3 pos = mob.position();
	    	    
	    	    
	    	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 2.0f, 0.2f);
	    	    this.mob.playSound(SoundEvents.VINDICATOR_HURT, 1.0f, 0.2f);
	    	    
	    	    
	    	    AttackHitboxHelpers.LargeAttackWithTargetCheck(mob.damageSources().mobAttack(mob),7.0f, 0.3f, mob, pos, 3.0f, -Math.PI/4, Math.PI/4, -1.0f, 3.5f);
	   
	   
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
