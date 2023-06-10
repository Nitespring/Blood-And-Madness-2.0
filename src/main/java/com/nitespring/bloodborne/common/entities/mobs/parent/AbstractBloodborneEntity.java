package com.nitespring.bloodborne.common.entities.mobs.parent;


import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.common.entities.ai.teams.GetTargetByTeamGoal;
import com.nitespring.bloodborne.common.entities.ai.teams.HurtByTargetAlertTeamGoal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractBloodborneEntity extends PathfinderMob{
	
	
	
	public abstract boolean isBoss();
	public abstract boolean isBeastly();
	public abstract boolean isCorrupted();
	public abstract boolean isKin();
	public abstract int physRes();
	public abstract int fireRes();
	public abstract int boltRes();
	public abstract int arcRes();
	public abstract int bloodRes();
	
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;

	
	private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> COMBAT_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ENTITY_STATE = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> BNMTEAM = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));
	
	public AbstractBloodborneEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.noCulling = true;
	}
	
	
	
	 public int getAnimationState() {
		 
		 return this.entityData.get(ANIMATION_STATE);
		 }
	 
	 public void setAnimationState(int anim) {
		 
		 this.entityData.set(ANIMATION_STATE, anim);
		 }
	 
	 public int getCombatState() {
		 
		 return this.entityData.get(COMBAT_STATE);
		 }
	 
	 public void setCombatState(int anim) {
		 
		 this.entityData.set(COMBAT_STATE, anim);
		 }
	 
	 public int getEntityState() {
		 
		 return this.entityData.get(ENTITY_STATE);
		 }
	 
	 public void setEntityState(int anim) {
		 
		 this.entityData.set(ENTITY_STATE, anim);
		 }
	 
	 
	 public int getBnMTeam() {
		 
		 return this.entityData.get(BNMTEAM);
		 }
	 
	 public void setBnMTeam(int anim) {
		 
		 this.entityData.set(BNMTEAM, anim);
		 }
	 
	 @Override
	 protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(ANIMATION_STATE, 0);
	      this.entityData.define(COMBAT_STATE, 0);   
	      this.entityData.define(ENTITY_STATE, 0);
	      this.entityData.define(BNMTEAM, getBnMDefaultTeam());
	      
	   }
	
	protected abstract int getBnMDefaultTeam();
	 
	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setAnimationState(tag.getInt("AnimationId"));
		this.setCombatState(tag.getInt("CombatStateId"));
		this.setEntityState(tag.getInt("EntityStateId"));
		this.setBnMTeam(tag.getInt("BnMTeam"));
		if (tag.hasUUID("Owner")) {
	         this.ownerUUID = tag.getUUID("Owner");
	      }
		}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("AnimationId", this.getAnimationState());
		tag.putInt("CombatStateId", this.getCombatState());
		tag.putInt("EntityStateId", this.getEntityState());
		tag.putInt("BnMTeam", this.getBnMTeam());
		if (this.ownerUUID != null) {
	    	  tag.putUUID("Owner", this.ownerUUID);
	      }	
		}
	
	 @Nullable
	   public LivingEntity getOwner() {
	      return this.owner;
	   }
	
	 
	 public void setOwner(LivingEntity p_33995_) {
	      this.owner = p_33995_;
	      this.ownerUUID= p_33995_.getUUID();
	   }
	 
	 @Override
	protected boolean shouldDespawnInPeaceful() {

		return true;
	}

	
	 
	 @Override
		public void startSeenByPlayer(ServerPlayer p_184178_1_) {
		      super.startSeenByPlayer(p_184178_1_);
		      
		      if (this.isBoss()) {
		      this.bossEvent.addPlayer(p_184178_1_);
		      }
		   }
	 
     @Override
	 public void stopSeenByPlayer(ServerPlayer p_184203_1_) {
    	
		      super.stopSeenByPlayer(p_184203_1_);
		      this.bossEvent.removePlayer(p_184203_1_);
		   } 
		
     @Override
     public void tick() {
	          float h = this.getHealth(); 
	          this.bossEvent.setProgress(h / this.getMaxHealth());
	 
	 
	          super.tick(); 
     		}


     /*
      *     TEAMS:
      *           >0: HOSTILE TO PLAYER
      *           >1: HUNTSMAN
      *           >2: BEAST
      *           >3: CHURCH
      *           >4: NEUTRAL
      *           >5: AGGRESSIVE
      *           >6: MENSIS
      * 
      * 
      */
     
     //protected abstract double getMaxHP();
     
     
     @Override
     public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
       
    	
    
    	 
    	 if(!this.serializeNBT().contains("BnMTeam")) {
    	   this.setBnMTeam(this.getBnMDefaultTeam());
       }
       if(this.owner!=null) {
    	   if(this.owner.serializeNBT().contains("BnMTeam")) {
    		   this.setBnMTeam(this.owner.serializeNBT().getInt("BnMTeam"));
    	   }else if(this.owner instanceof Player) {
        	   this.setBnMTeam(4);
    	   }
       }
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
     }

     @Override
    public boolean isAlliedTo(Entity e) {
    	if(this.getOwner()!=null) {
    		return this.getOwner().isAlliedTo(e);
    	}else {
	    	if(this.serializeNBT().contains("BnMTeam")) {
	    		int teamOwner = this.serializeNBT().getInt("BnMTeam");
			    	if(e.serializeNBT().contains("BnMTeam")) {	
				    	 int teamTarget = e.serializeNBT().getInt("BnMTeam");
				    	 switch(teamOwner) {
					    	 case 0:
					    		return super.isAlliedTo(e);
					    	 case 1:
					    		 switch(teamTarget) {
					    		 case 1, 3:
					    			 return true;
					    		 default:
					    			 return super.isAlliedTo(e);
					    		 }
					    	 case 2:
					    		 switch(teamTarget) {
					    		 case 2:
					    			 return true;
					    		 default:
					    			 return super.isAlliedTo(e);
					    		 }
					    	 case 3:
					    		 switch(teamTarget) {
					    		 case 1, 3:
					    			 return true;
					    		 default:
					    			 return super.isAlliedTo(e);
					    		 }
					    	 case 6:
					    		 switch(teamTarget) {
					    		 case 6:
					    			 return true;
					    		 default:
					    			 return super.isAlliedTo(e);
					    		 }
					    	default:
					    		return super.isAlliedTo(e);
				    	 }
			    	}else {
			    		switch(teamOwner) {
				    		case 1, 3:
				    			if(e instanceof Villager || (e instanceof IronGolem && !((IronGolem)e).isPlayerCreated())) {
				    				return true;
				    			}
				    		default:
				    			return super.isAlliedTo(e);
				    		}
			        	}
	    	}else {
	    		return super.isAlliedTo(e);
	    	}
    	}
    }
     
  
     @Override
		protected void registerGoals() {
			
			this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
		      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		
		      //this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		      this.targetSelector.addGoal(1, (new HurtByTargetAlertTeamGoal(this)));
		      this.targetSelector.addGoal(2, new AbstractBloodborneEntity.CopyOwnerTargetGoal(this));
		      this.targetSelector.addGoal(2, new GetTargetByTeamGoal<>(this));
		      //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	     
		      this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		      
		}
     
     
     
     public class CopyOwnerTargetGoal extends TargetGoal {
	      private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

	      public CopyOwnerTargetGoal(PathfinderMob p_34056_) {
	         super(p_34056_, false);
	      }

	      public boolean canUse() {
	         return AbstractBloodborneEntity.this.getOwner() != null && AbstractBloodborneEntity.this.getOwner() instanceof Mob && ((Mob)AbstractBloodborneEntity.this.getOwner()).getTarget() != null && this.canAttack(((Mob)AbstractBloodborneEntity.this.getOwner()).getTarget(), this.copyOwnerTargeting);
	      }

	      public void start() {
	    	  AbstractBloodborneEntity.this.setTarget(((Mob)AbstractBloodborneEntity.this.getOwner()).getTarget());
	         super.start();
	      }
	   } 
     
     public static boolean checkHuntsmanSpawnRules(EntityType<? extends AbstractBloodborneEntity> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
         return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkBnMMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
      }
     
     
     public static boolean checkBnMMonsterSpawnRules(EntityType<? extends AbstractBloodborneEntity> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
         return p_219015_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkBnMMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
      }

      public static boolean checkBnMAnyLightMonsterSpawnRules(EntityType<? extends AbstractBloodborneEntity> p_219020_, LevelAccessor p_219021_, MobSpawnType p_219022_, BlockPos p_219023_, RandomSource p_219024_) {
         return p_219021_.getDifficulty() != Difficulty.PEACEFUL && checkBnMMobSpawnRules(p_219020_, p_219021_, p_219022_, p_219023_, p_219024_);
      }

      public static boolean checkBnMMobSpawnRules(EntityType<? extends AbstractBloodborneEntity> p_217058_, LevelAccessor p_217059_, MobSpawnType p_217060_, BlockPos p_217061_, RandomSource p_217062_) {
          BlockPos blockpos = p_217061_.below();
          return p_217060_ == MobSpawnType.SPAWNER || p_217059_.getBlockState(blockpos).isValidSpawn(p_217059_, blockpos, p_217058_);
       }
      
      
      public static boolean isDarkEnoughToSpawn(ServerLevelAccessor p_33009_, BlockPos p_33010_, RandomSource p_33011_) {
          if (p_33009_.getBrightness(LightLayer.SKY, p_33010_) > p_33011_.nextInt(32)) {
             return false;
          } else if (p_33009_.getBrightness(LightLayer.BLOCK, p_33010_) > 0) {
             return false;
          } else {
             int i = p_33009_.getLevel().isThundering() ? p_33009_.getMaxLocalRawBrightness(p_33010_, 10) : p_33009_.getMaxLocalRawBrightness(p_33010_);
             return i <= p_33011_.nextInt(8);
          }
       }

	
    //Monster
	
}
