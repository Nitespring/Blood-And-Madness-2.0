package com.nitespring.bloodborne.common.entities.mobs.kin;

import java.util.EnumSet;
import java.util.Random;

import org.joml.Vector3f;

import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.projectiles.BindingSpellProjectileEntity;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.helpers.AttackHitboxHelpers;
import com.nitespring.bloodborne.core.init.EffectInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cow;
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

public class Brainsucker extends AbstractBloodborneEntity implements GeoEntity{

	
	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	private static final EntityDataAccessor<Integer> CHARGE_LEVEL = SynchedEntityData.defineId(Brainsucker.class, EntityDataSerializers.INT);
	
	public Brainsucker(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.xpReward= 8;
	}
	
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();
		int entityState = this.getEntityState();	
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.death"));
		}else {
			switch(animState) {
			case 11:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.transform"));
				break;
			case 12:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.grab_continue"));
				break;
			default:
				switch(entityState) {
				case 1:
					switch(animState) {
					case 21:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.attack_suck"));
						break;
					case 22:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.shoot_suck"));
						break;
					case 23:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.charge_suck"));
						break;
					case 24:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.grab_suck"));
						break;
					default:
						 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
							 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.brainsucker.walk_suck"));	 
							 }else {
							 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.brainsucker.idle_suck"));
						 }
						break;
					}
					break;
				default:
					switch(animState) {
					case 21:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.attack"));
						break;
					case 22:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.shoot"));
						break;
					case 23:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.charge"));
						break;
					case 24:
						event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.brainsucker.grab"));
						break;
					default:
						 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
							 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.brainsucker.walk"));	 
							 }else {
							 event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.brainsucker.idle"));
						 }
						break;
					}
					break;
				}
				break;
			}
		}
		
        return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 1, this::predicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}
	
    public int getChargeLevel() {
		 
		 return this.entityData.get(CHARGE_LEVEL);
		 }
	 
	 public void setChargeLevel(int anim) {
		 
		 this.entityData.set(CHARGE_LEVEL, anim);
		 }
	 
	 @Override
	 protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(CHARGE_LEVEL, 0);      
	   }
	 
	 @Override
	 public void readAdditionalSaveData(CompoundTag tag) {
		 	super.readAdditionalSaveData(tag);
		 	this.setAnimationState(tag.getInt("ChargeLevel"));				
		}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
			super.addAdditionalSaveData(tag);
			tag.putInt("ChargeLevel", this.getAnimationState());		
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
		return true;
	}

	@Override
	public int physRes() {
		return 150;
	}

	@Override
	public int fireRes() {
		return 100;
	}

	@Override
	public int boltRes() {
		return -500;
	}

	@Override
	public int arcRes() {
		return 450;
	}

	@Override
	public int bloodRes() {
		return 100;
	}

	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 80.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.28D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
					.add(Attributes.FOLLOW_RANGE, 30);
		  }

	 
	 
	 @Override
		protected void registerGoals() {
			
			this.goalSelector.addGoal(1, new Brainsucker.AttackGoal(this));
			
		
		      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cow.class, true));

		      super.registerGoals();
		      
		}
	 
	
	 
	 @Override
	 public boolean isAlliedTo(Entity p_32665_) {
	      if (p_32665_ == null) {
	         return false;
	      } else if (p_32665_ == this) {
	         return true;
	      } else if (super.isAlliedTo(p_32665_)) {
	         return true;
	      } else if (p_32665_ instanceof Brainsucker || p_32665_ instanceof CelestialEmissary) {
		         return true;
	      } else {
	         return false;
	      }
	   }
	 
	 
	 //Skeleton
	 protected SoundEvent getAmbientSound() {
	      return SoundEvents.GENERIC_DRINK;
	   }

	   protected SoundEvent getHurtSound(DamageSource p_33579_) {
	      return SoundEvents.PLAYER_BURP;
	   }

	   protected SoundEvent getDeathSound() {
	      return SoundEvents.DROWNED_DEATH_WATER;
	   }

	   protected SoundEvent getStepSound() {
	      return SoundEvents.SLIME_SQUISH_SMALL;
	   }
	   
	   
	   public static boolean checkBrainsuckerSpawnRules(EntityType<? extends AbstractBloodborneEntity> e, ServerLevelAccessor access, MobSpawnType type, BlockPos pos, RandomSource r) 
	     {  	 
	    	return checkBnMAnyLightMonsterSpawnRules(e, access, type, pos, r) && 
	    			CommonConfig.should_brainsucker_spawn.get();
	     }
	   
	   
	 
	   @Override
		public void positionRider(Entity p_184232_1_, Entity.MoveFunction p_19958_) {
	
			super.positionRider(p_184232_1_);
			 float f3 = (float) Math.sin(this.yBodyRot * ((float)Math.PI / 180F));
	         float f = (float) Math.cos(this.yBodyRot * ((float)Math.PI / 180F));
			 p_184232_1_.setPos(this.getX() - (double)(1 * f3), this.getY() - 0.4f, this.getZ() + (double)(1 * f));
		}
	 
	   @Override
		public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
				MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
			  
			  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.brainsucker_hp.get());;
			  
			  
			
			return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		}
	
	 public class AttackGoal extends Goal{

			
		   private final double speedModifier = 1.0f;
		   private final boolean followingTargetEvenIfNotSeen = true;
		   protected final Brainsucker mob;
		   private Path path;
		   private double pathedTargetX;
		   private double pathedTargetY;
		   private double pathedTargetZ;
		   private int ticksUntilNextPathRecalculation;
		   private int ticksUntilNextAttack;
		   private long lastCanUseCheck;
		   private int failedPathFindingPenalty = 0;
		   private boolean canPenalize = false;
		   
		   private int ticksUntilNextSpell;
		   private int ticksUntilNextCharge;
		   private int ticksUntilTransform;
		   
		   private int animTime = 0;

		
		
		  public AttackGoal(Brainsucker entityIn) {
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
		      if(this.mob.getEntityState()!=1) {
		      this.ticksUntilTransform = 400;
		      }
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
			case 11:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
			      
			      
		          if(animTime>=16) {
			    	  this.mob.setAnimationState(0);
			    	  this.mob.setEntityState(1);
			    	  this.animTime = 0;
			      } 
				break;
			case 12:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);
			      switch(animTime) {
					case 12, 24, 36, 48, 64:
			    	  target.hurt(mob.damageSources().mobAttack(mob), 4.0f);
					this.mob.playSound(SoundEvents.GENERIC_DRINK, 1.0f, 0.8f);
					
			      }
			      if(animTime<=72) {
			    	 
			    	  if(target.isPassenger()){
			    		  if(target.getVehicle()!=this.mob) {
			    			  target.stopRiding();
		        		      target.startRiding(mob);
			    		  }  
			    	  }else {
			    		  target.startRiding(mob);
			    	  }
			    	  
                     target.skipAttackInteraction(this.mob);
                     target.addEffect(new MobEffectInstance(EffectInit.TRAPPED.get()));
			    	  
			      } 
		          if(animTime==72) {
			    	  target.stopRiding();
			      } 
		          if(animTime>=84) {
			    	  this.mob.setAnimationState(0);
			    	  this.resetAttackCooldown();
			    	  this.animTime = 0;
			      } 
				break;
			case 21:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
			      if(animTime==6) {
			    	this.performAttack();  
			    	  
			      } 
			      
		          if(animTime>=10) {
			    	  this.mob.setAnimationState(0);
			    	  this.resetAttackCooldown();
			    	  this.animTime = 0;
			      } 
				break;
			case 22:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
			      if(animTime==4) {
			    	this.performSpellAttack();  
			    	this.mob.setChargeLevel(this.mob.getChargeLevel()-1);  
			      } 
			      
		          if(animTime>=5) {
			    	  this.mob.setAnimationState(0);
			    	  this.ticksUntilNextSpell = 35 - this.mob.getChargeLevel()*8;
			    	  this.animTime = 0;
			      } 
				break;
			case 23:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
			      if(animTime==10) {
			    	this.createParticles();
			    	this.mob.setChargeLevel(this.mob.getChargeLevel()+1);
			    	this.mob.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 1.0f, 0.2f);
			    	  
			      } 
			      
		          if(animTime>=14) {
			    	  this.mob.setAnimationState(0);
			    	  this.ticksUntilNextCharge = 30;
			    	  this.animTime = 0;
			      } 
				break;
			case 24:
				this.animTime++;
			      this.mob.getNavigation().setSpeedModifier(0.0f);;
		          if(animTime>=12) {
		        	  if(distance<=reach) {
		        		  this.mob.setAnimationState(12);
		        		  this.mob.setEntityState(1);
		        		  if(target.isPassenger()) {target.stopRiding();}
		        		  target.startRiding(mob);
		        		  target.hurt(mob.damageSources().mobAttack(mob), 2.0f);
		        		  
		        	  }else {
		        		  this.mob.setAnimationState(0);
		        	  }
			    	  this.animTime = 0;
			      } 
				break;
		
			default:
				   
				     
				   
				   this.doMovement(target, reach);
				   this.checkForAttack(distance, reach);
				   
				   if(this.mob.getEntityState()!=1) {
					      this.ticksUntilTransform = Math.max(this.ticksUntilTransform - 1, 0);;
					      if(this.ticksUntilTransform<=0) {
					    	  int r = this.mob.getRandom().nextInt(2048);
							     if (r<=40) {
							    	
							    	 this.mob.setAnimationState(11);
							    }
					      }
					      }
				   this.ticksUntilNextSpell = Math.max(this.ticksUntilNextSpell - 1, 0); 
					this.ticksUntilNextCharge = Math.max(this.ticksUntilNextCharge - 1, 0); 
				  
				   
				   
				   
				   //this.checkForPreciseAttack();
				break;
					
			}
			  this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0); 
			 
				
			
		}
		
		
		@SuppressWarnings("unused")
		private void checkForPreciseAttack() {
			 if (this.ticksUntilNextAttack <= 0) {
				 
				 this.mob.setAnimationState(21);
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
		
		
		
		
		
		
		
		   protected void checkForAttack(double distance, double reach){
			   if (distance <= reach && this.ticksUntilNextAttack <= 0) {
				   if(this.mob.getTarget().hasEffect(EffectInit.TRAPPED.get())) {
					   this.mob.setAnimationState(24);
				   }else {
				    int r = this.mob.getRandom().nextInt(2048);
					     if (r<=1000) {
					    	
					    	 this.mob.setAnimationState(21);
					    }else{
					    	
					    	 this.mob.setAnimationState(24);
					    }
				    } 
			   }else { 
				   
				   if(this.ticksUntilNextSpell<=0&&this.mob.getChargeLevel()>=1&&this.mob.getSensing().hasLineOfSight(this.mob.getTarget())&&!this.mob.getTarget().hasEffect(EffectInit.TRAPPED.get())) {
				   int r = this.mob.getRandom().nextInt(2048);
				     if (r<=200) {
				    	
				    	 this.mob.setAnimationState(22);
				    }

			    }
				   if(this.ticksUntilNextCharge<=0&&this.mob.getChargeLevel()<3) {
					   int r = this.mob.getRandom().nextInt(2048);
					     if (r<=500) {
					    	
					    	 this.mob.setAnimationState(23);
					    }

				    } 
				   
			   }
			   }
		   

	    
	    protected void performAttack() {

			Vec3 pos = mob.position();

	   	    
	   	    this.mob.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0f, 0.2f);
	   	    
	   	    
	   	    AttackHitboxHelpers.LargeAttack(mob.damageSources().mobAttack(mob), 6.0f, 0.6f, mob, pos, 2.5f, -Math.PI/4, Math.PI/4, -1.0f, 3.0f);
	   	    
	   	    
	   	    
	   	    
	   }
	    
	    protected void performSpellAttack() {

			Vec3 pos = mob.position();
			Vec3 aim = mob.getLookAngle();

	   	    
	   	    this.mob.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 1.0f, 0.2f);
	   	    
	   	    
	   	    BindingSpellProjectileEntity spell = new BindingSpellProjectileEntity(ProjectileInit.BINDING_SPELL.get(), level(), 1.0f);
	   	    spell.setPos(pos.add(0,1.25f,0).add(aim));
	   	    spell.setOwner(this.mob);
	   	    spell.setTarget(this.mob.getTarget());
	   	 level().addFreshEntity(spell);
	   	    
	   	    
	   	    
	   }
	    
	    private void createParticles() {
 Level worldIn = this.mob.level();
		
		ParticleOptions lightning = new DustParticleOptions(new Vector3f(0, 1, 1), 1);;
		Vec3 pos = this.mob.position();
		
		double x0= pos.x;
		double y0= pos.y;
		double z0= pos.z;
		
		
			for (int k = 0; k <= 64; ++k) {	
				
			
				double x = x0 + 1.0  * Math.cos(k*Math.PI/8);
				double y = y0 + 0.25f; 
				double z = z0 +  1.0 *  Math.sin(k*Math.PI/8);
		
		if(worldIn instanceof ServerLevel) {
			
			
			((ServerLevel) worldIn).sendParticles( lightning, x, y, z, 1,  0, 0, 0, 1);
			
			
		}
		
		
			}
	  
	 }
		
			protected void resetAttackCooldown() {
			    this.ticksUntilNextAttack = 20;
			 }
				
		
		  protected double getAttackReachSqr(LivingEntity p_179512_1_) {
		      return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + p_179512_1_.getBbWidth());
		   }

	}


	@Override
	protected int getBnMDefaultTeam() {
		return 0;
	}

}
	

