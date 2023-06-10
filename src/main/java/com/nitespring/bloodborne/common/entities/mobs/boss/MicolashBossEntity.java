package com.nitespring.bloodborne.common.entities.mobs.boss;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.common.entities.mobs.SkeletalPuppet;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondProjectileEntity;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.helpers.MathHelpers;
import com.nitespring.bloodborne.core.init.EntityInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MicolashBossEntity extends AbstractBloodborneEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	@Nullable
	protected AugurOfEbrietasEntity tentacleEntity;
	
	
	
	
	public MicolashBossEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.xpReward= 50;
	}

	//Animation Stuff
	
	
	private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.micolash.death"));
		}else {
		switch(animState) {
		case 51:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.micolash.a_call_beyond"));
			break;
		case 52:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.micolash.augur_of_ebrietas"));
			break;
		case 53:
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.micolash.summon"));
			break;
		case 54:
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.micolash.summon"));
			break;
		
		default:
			 
		  if(!(event.getLimbSwingAmount() > -0.025F && event.getLimbSwingAmount() < 0.025F)){			
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.micolash.walk"));					
		  }else{
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.micolash.idle"));
		  }
			break;
		}
	}
		 return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 2, this::predicate));
		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	//Properties Stuff
	
	@Override
	public boolean isBoss() {	
		return true;
	}

	 @Override
	 protected int getBnMDefaultTeam() {
		return 6;
	 }
	
	@Override
	public boolean isBeastly() {return false;}
	@Override
	public boolean isCorrupted() {return false;}
	@Override
	public boolean isKin() {return false;}

	@Override
	public int physRes() {return 150;}
	@Override
	public int fireRes() {return 350;}
	@Override
	public int boltRes() {return 250;}
	@Override
	public int arcRes() {return 600;}
	@Override
	public int bloodRes() {return 100;}
	
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 1500.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.25D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.25D)
					.add(Attributes.FOLLOW_RANGE, 50);
	
		  }
	 
	 //AI

	 @Override
	  protected void registerGoals() {
		  
		 this.goalSelector.addGoal(2, new MicolashAttackGoal(this));
		 this.goalSelector.addGoal(2, new MicolashFleeGoal(this));
		 
	      
	
	      this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      super.registerGoals();
	      

	     
	   }
	 
	 
	 @Override
	public boolean hurt(DamageSource source, float amount) {
		 if(this.isAlive()&&this.getHealth()>0&&!(this.isDeadOrDying())&&!(amount>=this.getHealth())&&this.getCombatState()!=1) {
			 
		     this.teleport1();
		   
		    	 
		 }
		return super.hurt(source, amount);
	}
	 
	 
	 public boolean teleport() {
	      if (!this.level().isClientSide() && this.isAlive()) {
	         double d0 = this.getX() + (this.random.nextInt(8) - 4) * 4.0D;
	         double d1 = this.getY() + 20;
	         double d2 = this.getZ() + (this.random.nextInt(8) - 4) * 4.0D;
	         return this.teleport(d0, d1, d2);
	      } else {
	         return false;
	      }
	   }
	 
	 private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
	      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

	      while(blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
	         blockpos$mutableblockpos.move(Direction.DOWN);
	      }

	      BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
	      boolean flag = blockstate.blocksMotion();
	      boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
	      if (flag && !flag1) {
	         
	    	  net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
	         if (event.isCanceled()) return false;
	         boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
	         if (flag2 && !this.isSilent()) {
	            this.level().playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.WOOL_BREAK, this.getSoundSource(), 1.0F, 1.0F);
	            this.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
	         }
          
	         return flag2;
	    	  
	      } else {
	         return false;
	      }
	   }
	 
	 
	 private boolean teleport1() {
		 if (!this.level().isClientSide() && this.isAlive()) {
	         
			 for(int i = 0; i < 10; ++i) {
		            double d0 = this.random.nextGaussian() * 0.02D;
		            double d1 = this.random.nextGaussian() * 0.02D;
		            double d2 = this.random.nextGaussian() * 0.02D;
		            this.level().addParticle(ParticleTypes.POOF, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
		         }  
			 
			 
			 
	         
			 double d0 = this.getX() + (this.random.nextInt(8)-4)*8;
	         double d1 = this.position().y()*2+10;
	         double d2 = this.getZ() + (this.random.nextInt(8)-4)*8;
	
			  
			  Vec3 dVec = new Vec3(d0-this.getX(), d1-this.getY(), d2-this.getZ()).normalize();
			  
			  Vec3 pathedPos = this.position().add(dVec.scale(-20.0));
			  

		            	Path path = this.getNavigation().createPath(pathedPos.x(), pathedPos.y(), pathedPos.z(), 0);
		            	
		            	if(path==null||!path.canReach()) {
		            	
		            	int i = 0;
		            	while((path==null||!path.canReach()) && i<=12) {
		            		i++;
		            		
		            		while((path==null||!path.canReach()) && d1>= this.getY()-10) {
			            		d1--;
			            		path = this.getNavigation().createPath(pathedPos.x(), d1, pathedPos.z(), 0);
			            	}
		            		
		            		 d0 = this.getX() + (this.random.nextInt(8)-4)*8;
		            		 d1 = this.getY() + this.position().y();
		        	         d2 = this.getZ() + (this.random.nextInt(8)-4)*8;
		        	
		        			  
		        			  dVec = new Vec3(d0-this.getX(), d1-this.getY(), d2-this.getZ()).normalize();
		            		pathedPos = this.position().add(dVec.scale(-20.0)); 
		   		        
		            		
		            		path = this.getNavigation().createPath(pathedPos.x(), pathedPos.y(), pathedPos.z(), 0);
		            	}
		            	}
		            	if(path!=null&&path.canReach()) {
		            		this.teleport1(pathedPos.x(), pathedPos.y(), pathedPos.z());
		            		return true;
		            		
		            	}else {
		            		return false;
		            	}
		            
		            
			    
		
		 } else {
	         return false;
	      }
		 
	 }
	 
	 
	 private boolean teleport1(double p_32544_, double p_32545_, double p_32546_) {
		 
		 
		 
		 
		 
	      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);
	      
	      

	      while(blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
	         blockpos$mutableblockpos.move(Direction.DOWN);
	      }

	      BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
	      boolean flag = blockstate.blocksMotion();
	      boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
	      if (flag && !flag1) {
	         
	    	  net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
	         if (event.isCanceled()) return false;
	         boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
	         if (flag2 && !this.isSilent()) {
	            this.level().playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.WOOL_BREAK, this.getSoundSource(), 1.0F, 1.0F);
	            this.playSound(SoundEvents.WOOL_BREAK, 1.0F, 1.0F);
	         }
         
	         return flag2;
	    	  
	      } else {
	         return false;
	      }  
	   }
	 

	 
	 
	 @Override
	public void tick() {
		 
		 if(this.getHealth()<= this.getMaxHealth()*2/3&&this.getEntityState()!=1) {
			 this.teleport1();
			 this.setEntityState(1);
			 this.setCombatState(0);
			
		 }
		 
		 
		if(this.isDeadOrDying()&&this.tentacleEntity!=null) {
			this.tentacleEntity.remove(RemovalReason.DISCARDED);
	    		
	    }
		super.tick();
	}
	 
	  @Override
		public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
				MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
			  
			  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.micolash_hp.get());;
			  
			  
			
			return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		}
	 
	 
	 
	public class MicolashAttackGoal extends Goal{

		private final MicolashBossEntity mob;
		
		private final double speedModifier = 1.0f;
		@Nullable
		private LivingEntity target;
		private int attackCooldown;
		private int animationTick;
		private int goalTime;
		
	    private final float attackRadiusSqr = 144;
		private int seeTime;
		private boolean strafingClockwise;
		private boolean strafingBackwards;
		private int strafingTime = -1;
		
		public MicolashAttackGoal(MicolashBossEntity entity) {
			this.mob = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK)); 
			 
		}
		 
	    @Override
		public boolean canUse() {
			LivingEntity livingentity = this.mob.getTarget();
			    if (livingentity != null && livingentity.isAlive()&&this.mob.getCombatState()==1) {
			      this.target = livingentity;
			      return true;
			    } else {
			      return false;
			}
		}
		
	    @Override
	    public boolean canContinueToUse() {
	    	if (this.mob.getTarget() != null && this.mob.getTarget().isAlive()&&this.mob.getCombatState()==1) {
	    	return true;
	    	}else {
	        return false;
	    	}
	    }
	    
	    @Override
	    public void stop() {
	    	this.mob.setAnimationState(0);
	    	if(this.mob.tentacleEntity!=null) {
	    		this.mob.tentacleEntity.remove(RemovalReason.DISCARDED);
	    		
	    	}
	    	super.stop();
	    }
	    
	    
	    @Override
	    public void tick() {
	    	
	    	if(this.mob.getHealth()>=350) {
	    	this.goalTime++;
	    	if(this.goalTime>=1000) {
	    		this.mob.teleport1();
	    		this.mob.setCombatState(0);
	    		this.stop();
	    	}
	    	}
	    	
	    	int animState = this.mob.getAnimationState();

	    	
	    	switch(animState) {
	    	
	    	case 51:
	    		this.tickACallBeyond();
	    		break;
	    	case 52:
	    		this.tickAugurOfEbrietas();
	    		break;
	    	case 53:
	    		this.tickSummonSkeletons();
	    		break;
	    	case 54:
	    		this.tickSummonTentaclesBelowTarget();
	    		break;
	    	default:
	    		
	    		this.tickStrafing();
	    		
	    		if(this.attackCooldown>=0) {
	    			
	    			this.attackCooldown--;
	    			
	    		}else {
	    			
	    			this.selectAttack();
	    			
	    		}
	   
	    		break;
	    	}
	    	
	    	
	    }
	 
	    
	 //Movement 
	    
	  private void tickStrafing() {
		
		   LivingEntity livingentity = this.mob.getTarget();
		      if (livingentity != null) {
		         double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
		         boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
		         boolean flag1 = this.seeTime > 0;
		         if (flag != flag1) {
		            this.seeTime = 0;
		         }

		         if (flag) {
		            ++this.seeTime;
		         } else {
		            --this.seeTime;
		         }

		         if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
		            this.mob.getNavigation().stop();
		            ++this.strafingTime;
		         } else {
		            this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
		            this.strafingTime = -1;
		         }

		         if (this.strafingTime >= 20) {
		            if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
		               this.strafingClockwise = !this.strafingClockwise;
		            }

		            if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
		               this.strafingBackwards = !this.strafingBackwards;
		            }

		            this.strafingTime = 0;
		         }

		         if (this.strafingTime > -1) {
		            if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
		               this.strafingBackwards = false;
		            } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
		               this.strafingBackwards = true;
		            }

		            this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
		            this.mob.lookAt(livingentity, 30.0F, 30.0F);
		         } else {
		            this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
		         }
		      }
	  }
	
	  
	//Attacks
		      
	private void selectAttack() {
				  
		if(this.mob.getEntityState()==1) {
	
			int rMax = 100;
			int r = Math.round(new Random().nextFloat()*rMax);
			if(r<=rMax*1/3) {
				if(this.mob.distanceToSqr(this.mob.getTarget())<=65){this.mob.setAnimationState(52);}
			}else if(r<=rMax*8/12){
				this.mob.setAnimationState(54);
			}else if(r<=rMax*11/12){
				this.mob.setAnimationState(51);
			}else{
				this.mob.setAnimationState(53);
			}
		}else {
			int rMax = 100;
			int r = Math.round(new Random().nextFloat()*rMax);
			if(r<=rMax*1/3) {
				if(this.mob.distanceToSqr(this.mob.getTarget())<=65){this.mob.setAnimationState(52);}
			}else if(r<=rMax*8/12){
				this.mob.setAnimationState(54);
			}else{
				this.mob.setAnimationState(53);
			}
		}
			  }
			  

	  private void tickACallBeyond() {
		  this.animationTick++;
		  if(animationTick==6) {
			  this.aCallBeyond();
		  }

		  if(animationTick>=15) {
			  this.resetAnimationTick();
			  this.setAttackCooldown(50,30);
			  this.mob.setAnimationState(0);
		  }
	  }
	  
	  
	  private void tickAugurOfEbrietas() {
		  
		  this.animationTick++;
		  Vec3 pos = this.mob.position();
		  Vec3 aim = this.mob.getLookAngle();
		  this.mob.lookAt(this.mob.getTarget(), 30.0F, 30.0F);
		  if(animationTick==6) {
			  
			AugurOfEbrietasEntity tentacle = new AugurOfEbrietasEntity(ProjectileInit.AUGUR_OF_EBRIETAS.get(), this.mob.level(), 7.0f);
		
			tentacle.setPos(pos.x+0.75*aim.x, pos.y+1.25+0.75*aim.y, pos.z+0.75*aim.z);
			tentacle.setXRot(this.mob.getViewXRot(1.0f)+90);
			tentacle.setYRot(180-this.mob.getViewYRot(1.0f));
			tentacle.setAim(aim);
			tentacle.setOwner(this.mob);
			
			this.mob.level().addFreshEntity(tentacle);
			this.mob.tentacleEntity=tentacle;
	
		  }
		  if(animationTick>6) {
		  
			  this.mob.tentacleEntity.setPos(pos.x+0.75*aim.x, pos.y+1.25+0.75*aim.y, pos.z+0.75*aim.z);
			  this.mob.tentacleEntity.setXRot(this.mob.getViewXRot(1.0f)+90);
			  this.mob.tentacleEntity.setYRot(180-this.mob.getViewYRot(1.0f));
			  this.mob.tentacleEntity.setAim(aim);
		  
		  }
		  
		  if(animationTick>=35) {
			  
			this.mob.tentacleEntity.remove(RemovalReason.DISCARDED);  
			this.resetAnimationTick();
			this.setAttackCooldown(25,10);
			this.mob.setAnimationState(0); 
			
			
		  }
		  
	  }
	  
	  
	  
	  
	  private void tickSummonSkeletons() {
		  this.animationTick++;
		  if(animationTick==15) {
			  ServerLevel serverlevel = (ServerLevel)this.mob.level();

		         for(int i = 0; i < 2; ++i) {
		            BlockPos blockpos = this.mob.blockPosition().offset(-2 + this.mob.random.nextInt(5), 1, -2 + this.mob.random.nextInt(5));
		            SkeletalPuppet skeleton = EntityInit.SKELETAL_PUPPET.get().create(this.mob.level());
		            skeleton.moveTo(blockpos, 0.0F, 0.0F);
		            skeleton.setOwner(mob);
		            serverlevel.addFreshEntityWithPassengers(skeleton);
		            skeleton.setTarget(this.mob.getTarget());
		         }
		  }

		  if(animationTick>=20) {
			  this.resetAnimationTick();
			  this.setAttackCooldown(10,6);
			  this.mob.setAnimationState(0);
		  }
	  }
		
	    
	  private void tickSummonTentaclesBelowTarget() {
		  this.animationTick++;
		  if(animationTick==20) {
		  LivingEntity target = this.mob.getTarget();
	         double d0 = Math.min(target.getY(), this.mob.getY());
	         double d1 = Math.max(target.getY(), this.mob.getY()) + 1.0D;
	         float f = (float)Mth.atan2(target.getZ() - this.mob.getZ(), target.getX() - this.mob.getX());
		  this.createTentacleFixed(this.mob.getTarget().getX() , this.mob.getTarget().getZ(), d0, d1, 8.0f);
     	 
     	 for(int i = 0; i < 8; ++i) {
	               float f1 = f + (float)i * (float)Math.PI * 2.0F / 8.0F;
	               this.createTentacleFixed(this.mob.getTarget().getX() + (double)Mth.cos(f1) * 2.0D, target.getZ() + (double)Mth.sin(f1) * 2.0D, d0, d1,4.0f);
	            }
	 
		  }
		  
		  if(animationTick>=25) {
			  this.resetAnimationTick();
			  this.setAttackCooldown(12,8);
			  this.mob.setAnimationState(0);
		  }
     
	  }
	  
	  private void createTentacleFixed(double x, double z, double yMin, double yMax, float dmg) {
	         BlockPos blockpos = new BlockPos((int)x, (int)yMax, (int)z);
	         boolean flag = false;
	         double d0 = 0.0D;

	         do {
	            BlockPos blockpos1 = blockpos.below();
	            BlockState blockstate = this.mob.level().getBlockState(blockpos1);
	            if (blockstate.isFaceSturdy(this.mob.level(), blockpos1, Direction.UP)) {
	               if (!this.mob.level().isEmptyBlock(blockpos)) {
	                  BlockState blockstate1 = this.mob.level().getBlockState(blockpos);
	                  VoxelShape voxelshape = blockstate1.getCollisionShape(this.mob.level(), blockpos);
	                  if (!voxelshape.isEmpty()) {
	                     d0 = voxelshape.max(Direction.Axis.Y);
	                  }
	               }

	               flag = true;
	               break;
	            }

	            blockpos = blockpos.below();
	         } while(blockpos.getY() >= Mth.floor(yMin) - 1);

	         if (flag) {
	        	 
	        	 AugurOfEbrietasEntity tentacle = new AugurOfEbrietasEntity(ProjectileInit.AUGUR_OF_EBRIETAS.get(), level(), dmg, 50);
	        	 tentacle.setOwner(mob);
	        	 tentacle.setPos(blockpos.getX(),blockpos.getY(),blockpos.getZ());
	        	 this.mob.level().addFreshEntity(tentacle);
	        	 
	        	 
	         }

	      }
	  
	  
	  
	  private void aCallBeyond(){

		  MicolashBossEntity playerIn = this.mob;
		  Level worldIn = playerIn.level();
			double ang = Math.PI/5;
			Vec3 pos = playerIn.position();
			
			
			for(int i=0; i<=5; i++) {
			
				for(int k=0; k<=5; k++) {
				
					
					double x = pos.x + 1.25*Math.cos(ang*i)*Math.cos(ang*k);
					double z = pos.z + 1.25*Math.cos(ang*i)*Math.sin(ang*k);
					double y = pos.y + 2.5 + 1.25*Math.sin(ang*6/5*i - Math.PI/6);
					
					CallBeyondProjectileEntity bullet = new CallBeyondProjectileEntity(ProjectileInit.CALL_BEYOND.get(), worldIn,7.0f);
					bullet.setPos(x, y, z);
					bullet.setTarget(playerIn.getTarget());
					bullet.setOwner(playerIn);
					Vec3 aim1 = MathHelpers.AimVector(new Vec3(pos.x, pos.y+2.5, pos.z), bullet.position());
					bullet.xPower = aim1.x*0.1;
					bullet.yPower = aim1.y*0.1;
					bullet.zPower = aim1.z*0.1;
				    worldIn.addFreshEntity(bullet);
				    
					
				}
				
			}
	
			
			
			
		}
	  
	  
	 

	  private void resetAnimationTick() {
		  this.animationTick=0;
		  } 
	  
	  
	  private void setAttackCooldown(int cooldown) {
		  this.setAttackCooldown(cooldown, 0);
		  } 
	    
	  private void setAttackCooldown(int mediumCooldown, int randomizationRange) {
		  int k = 2;
		  if(this.mob.getEntityState()==1) {
			  k=1;
		  }
		  this.attackCooldown = (mediumCooldown + (int)(new Random().nextFloat()*randomizationRange - randomizationRange/2))*k/2;
	      }
	
		
	}
	
	
	public class MicolashFleeGoal extends Goal{

		private final MicolashBossEntity mob;
		
		private final double speedModifier = 1.5f;
		@Nullable
		private LivingEntity target;
		private int attackCooldown;
		private int animationTick;
		private int goalTime;
		
		private Path path;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private int ticksUntilNextPathRecalculation;
		
	    private final float attackRadiusSqr = 144;
		private int seeTime;
		
		public MicolashFleeGoal(MicolashBossEntity entity) {
			this.mob = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK)); 
			 
		}
		 
	    @Override
		public boolean canUse() {
			LivingEntity livingentity = this.mob.getTarget();
			    if (livingentity != null && livingentity.isAlive()&&this.mob.getCombatState()!=1) {
			      this.target = livingentity;
			      return true;
			    } else {
			      return false;
			}
		}
		
	    @Override
	    public boolean canContinueToUse() {
	    	if (this.mob.getTarget() != null && this.mob.getTarget().isAlive()&&this.mob.getCombatState()!=1) {
	    		
	    			return true;
	    		
	    	}else {
	        return false;
	    	}
	    }
	    
	    @Override
	    public void start() {
	    	 LivingEntity livingentity = this.mob.getTarget();
	    	 Vec3 dVec = new Vec3(livingentity.getX()-this.mob.getX(), livingentity.getY()-this.mob.getY(), livingentity.getZ()-this.mob.getZ());
			 Vec3 pathedPos = this.mob.position().add(dVec.normalize().scale(-30.0));
	    	 this.path = this.mob.getNavigation().createPath(pathedPos.x(), pathedPos.y(), pathedPos.z(), 0);
	    	 this.mob.getNavigation().moveTo(this.path, this.speedModifier);
	         this.mob.setAggressive(true);
	         this.ticksUntilNextPathRecalculation = 0;
	         //this.goalTime=0;
	    }
	    
	    @Override
	    public void stop() {
	    	this.mob.setAnimationState(0);
	    	if(this.mob.tentacleEntity!=null) {
	    		this.mob.tentacleEntity.remove(RemovalReason.DISCARDED);
	    		
	    	}
	    	super.stop();
	    }
	    
	    
	    @Override
	    public void tick() {
	    	this.goalTime++;
	    	if(this.goalTime>=500) {
	    		this.mob.teleport1();
	    		this.mob.setCombatState(1);
	    		this.goalTime=0;
	    		this.stop();
	    	}
	    	
	    	
	    	int animState = this.mob.getAnimationState();

	    	
	    	switch(animState) {
	    	case 53:
	    		this.tickSummonSkeletons();
	    		break;
	    	case 54:
	    		this.tickSummonTentaclesBelowTarget();
	    		break;
	    	default:
	    		
	    		this.tickFleeing();
	    		
	    		if(this.attackCooldown>=0) {
	    			
	    			this.attackCooldown--;
	    			
	    		}else {
	    			LivingEntity livingentity = this.mob.getTarget();
	    			double d0 = this.mob.distanceTo(livingentity);
	    			if(d0>=5&&this.attackCooldown<=0) {
	    				this.selectAttack();
	    			}
	    		}
	   
	    		break;
	    	}
	    	
	    	
	    }
	 
	    
	 //Movement 
	 //AvoidEntityGoal
	 //MeleeAttackGoal
	    
	  private void tickFleeing() {
		  LivingEntity livingentity = this.mob.getTarget();
		  
		  
		  if (livingentity != null) {
			  double d0 = Math.sqrt(this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ()));
			  if(d0<=5) {
			  Vec3 dVec = new Vec3(livingentity.getX()-this.mob.getX(), livingentity.getY()-this.mob.getY(), livingentity.getZ()-this.mob.getZ()).normalize();
			  
			  Vec3 pathedPos = this.mob.position().add(dVec.scale(-30.0+d0));
		 
		         this.mob.getLookControl().setLookAt(pathedPos);
		         
		         this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
		            this.pathedTargetX = pathedPos.x();
		            this.pathedTargetY = pathedPos.y();
		            this.pathedTargetZ = pathedPos.z();
		            //this.ticksUntilNextPathRecalculation--;
		        
		            
		            
		            if(ticksUntilNextPathRecalculation<=0) {
		            	this.path = this.mob.getNavigation().createPath(pathedPos.x(), pathedPos.y(), pathedPos.z(), 0);
		            	if(path==null||!path.canReach()) {
		            	int i = 0;
		            	while((path==null||!path.canReach()) && i<=12) {
		            		i++;
		            		Vec3 dVec1 = dVec.multiply(Math.cos(Math.PI*i/6), 1, Math.sin(Math.PI*i/6)).normalize();
		            		pathedPos = this.target.position().add(dVec1.scale(-30.0+d0)); 
		            		if(pathedPos.distanceTo(this.mob.getTarget().position())>=this.mob.position().distanceTo(this.mob.getTarget().position())) {
		   		            this.mob.getLookControl().setLookAt(pathedPos);
		            		this.pathedTargetX = pathedPos.x();
				            this.pathedTargetY = pathedPos.y();
				            this.pathedTargetZ = pathedPos.z();
		            		this.path = this.mob.getNavigation().createPath(pathedPos.x(), pathedPos.y(), pathedPos.z(), 0);
		            		}
		            	}
		            	}
		            	if(path!=null&&path.canReach()) {
		            	this.mob.getNavigation().moveTo(this.path, this.speedModifier);
		            	this.ticksUntilNextPathRecalculation=3;
		            	}else {
		            	this.ticksUntilNextPathRecalculation=0;	
		            	}
		            }
		            
			  }   
		  }
		  
	  }
	
	  
	//Attacks
		      
	private void selectAttack() {
				  
		//this.mob.setAnimationState(54);
		
		if(this.mob.getEntityState()!=1) {
		
			this.mob.setAnimationState(53);
			
		}else {
		
		int rMax = 100;
		int r = Math.round(new Random().nextFloat()*rMax);
		if(r<=rMax*8/12){
			this.mob.setAnimationState(54);
		}else{
			this.mob.setAnimationState(53);
		}
		}
				  
			  }
			  

	  

	  
	  private void tickSummonSkeletons() {
		  this.animationTick++;
		  if(animationTick==7) {
			  ServerLevel serverlevel = (ServerLevel)this.mob.level();

		         for(int i = 0; i < 2; ++i) {
		            //BlockPos blockpos = this.mob.blockPosition().offset(-2 + this.mob.random.nextInt(5), 1, -2 + this.mob.random.nextInt(5));
		            SkeletalPuppet skeleton = new SkeletalPuppet(EntityInit.SKELETAL_PUPPET.get(), this.mob.level());
		            skeleton.setPos(this.mob.position());
		            skeleton.setOwner(mob);
		            skeleton.setBnMTeam(this.mob.getBnMTeam());
		            serverlevel.addFreshEntity(skeleton);
		            skeleton.setTarget(this.mob.getTarget());
		         }
		  }

		  if(animationTick>=12) {
			  this.resetAnimationTick();
			  this.setAttackCooldown(56,6);
			  this.mob.setAnimationState(0);
			  if(this.mob.distanceTo(this.mob.getTarget())<=7) {
				  this.mob.teleport1();
			  }
		  }
	  }
		
	    
	  private void tickSummonTentaclesBelowTarget() {
		  this.animationTick++;
		  if(animationTick==6) {
		  LivingEntity target = this.mob.getTarget();
	         double d0 = Math.min(target.getY(), this.mob.getY());
	         double d1 = Math.max(target.getY(), this.mob.getY()) + 1.0D;
	         float f = (float)Mth.atan2(target.getZ() - this.mob.getZ(), target.getX() - this.mob.getX());
		  this.createTentacleFixed(this.mob.getTarget().getX() , this.mob.getTarget().getZ(), d0, d1, 8.0f);
     	 
     	
	               
	               //this.createTentacleFixed(target.getX(), target.getZ(), d0, d1,4.0f);
	            
	 
		  }
		  
		  if(animationTick>=10) {
			  this.resetAnimationTick();
			  this.setAttackCooldown(15,8);
			  this.mob.setAnimationState(0);
			  if(this.mob.distanceTo(this.mob.getTarget())<=7) {
				  this.mob.teleport1();
			  }
		  }
     
	  }
	  
	  private void createTentacleFixed(double x, double z, double yMin, double yMax, float dmg) {
	         BlockPos blockpos = new BlockPos((int)x, (int)yMax, (int)z);
	         boolean flag = false;
	         double d0 = 0.0D;

	         do {
	            BlockPos blockpos1 = blockpos.below();
	            BlockState blockstate = this.mob.level().getBlockState(blockpos1);
	            if (blockstate.isFaceSturdy(this.mob.level(), blockpos1, Direction.UP)) {
	               if (!this.mob.level().isEmptyBlock(blockpos)) {
	                  BlockState blockstate1 = this.mob.level().getBlockState(blockpos);
	                  VoxelShape voxelshape = blockstate1.getCollisionShape(this.mob.level(), blockpos);
	                  if (!voxelshape.isEmpty()) {
	                     d0 = voxelshape.max(Direction.Axis.Y);
	                  }
	               }

	               flag = true;
	               break;
	            }

	            blockpos = blockpos.below();
	         } while(blockpos.getY() >= Mth.floor(yMin));

	         if (flag) {
	        	 
	        	 AugurOfEbrietasEntity tentacle = new AugurOfEbrietasEntity(ProjectileInit.AUGUR_OF_EBRIETAS.get(), level(), dmg, 50);
	        	 tentacle.setOwner(this.mob);
	        	 tentacle.setPos(blockpos.getX(),blockpos.getY() - 0.5,blockpos.getZ());
	        	 this.mob.level().addFreshEntity(tentacle);
	        	 
	        	 
	         }

	      }
	  
	  
	  
	  
	  
	  
	 

	  private void resetAnimationTick() {
		  this.animationTick=0;
		  } 
	  
	  
	  private void setAttackCooldown(int cooldown) {
		  this.setAttackCooldown(cooldown, 0);
		  } 
	    
	  private void setAttackCooldown(int mediumCooldown, int randomizationRange) {
		  int k = 2;
		  if(this.mob.getEntityState()==1) {
			  k=1;
		  }
		  
		  this.attackCooldown = (mediumCooldown + (int)(new Random().nextFloat()*randomizationRange - randomizationRange/2))*k/2;
		  this.ticksUntilNextPathRecalculation = 0;
	      }
	
		
	}
	
	

}
