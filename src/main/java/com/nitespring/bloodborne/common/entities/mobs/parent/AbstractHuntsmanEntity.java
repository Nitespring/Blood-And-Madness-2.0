package com.nitespring.bloodborne.common.entities.mobs.parent;




import java.util.Random;

import com.nitespring.bloodborne.client.render.entities.mobs.huntsmen.HuntsmanResourceLocations;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.StrollThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractHuntsmanEntity extends AbstractBloodborneEntity{

	private static final EntityDataAccessor<Integer> HAIR_COLOUR = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> COAT_COLOUR = SynchedEntityData.defineId(AbstractBloodborneEntity.class, EntityDataSerializers.INT);
	
	private AbstractHuntsmanEntity leader;
	   private int huntSize = 1;
	
	
	
	public AbstractHuntsmanEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
	
	}
	
	//AbstractSchoolingFish
	
	
	protected void getHuntsmanGoals(){
		  this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      this.goalSelector.addGoal(1, new StrollThroughVillageGoal(this, 400));
	      //this.goalSelector.addGoal(3, new FollowHuntLeaderGoal(this));
	      
	  
		
		
	}
	
	
	
 public int getHairColour() {
		 
		 return this.entityData.get(HAIR_COLOUR);
		 }
	 
	 public void setHairColour(int anim) {
		 
		 this.entityData.set(HAIR_COLOUR, anim);
		 }
	 
	 public int getCoatColour() {
		 
		 return this.entityData.get(COAT_COLOUR);
		 }
	 
	 public void setCoatColour(int anim) {
		 
		 this.entityData.set(COAT_COLOUR, anim);
		 }
	 
	 
	 @Override
	 protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(HAIR_COLOUR, 0);
	      this.entityData.define(COAT_COLOUR, 0);   
	   }
	 
	 @Override
	 public void readAdditionalSaveData(CompoundTag tag) {
		 super.readAdditionalSaveData(tag);
		 this.setHairColour(tag.getInt("HairColour"));
		 this.setCoatColour(tag.getInt("CoatColour"));
			
				
			}

	 @Override
	 public void addAdditionalSaveData(CompoundTag tag) {
		 super.addAdditionalSaveData(tag);
		 tag.putInt("HairColour", this.getHairColour());
		 tag.putInt("CoatColour", this.getCoatColour());
			
				
			}
	 
	 @Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
			MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
		 
		HuntsmanResourceLocations.randomizeHuntsmanColours(this);
		 
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
	/*
	public class FollowHuntLeaderGoal extends Goal {
		   private static final int INTERVAL_TICKS = 200;
		   private final AbstractHuntsmanEntity mob;
		   private int timeToRecalcPath;
		   private int nextStartTick;

		   public FollowHuntLeaderGoal(AbstractHuntsmanEntity p_25249_) {
		      this.mob = p_25249_;
		      this.nextStartTick = this.nextStartTick(p_25249_);
		   }

		   protected int nextStartTick(AbstractHuntsmanEntity p_25249_) {
		      return reducedTickDelay(200 + p_25249_.getRandom().nextInt(200) % 20);
		   }

		   public boolean canUse() {
		      if (this.mob.hasFollowers()) {
		         return false;
		      } else if (this.mob.isFollower()) {
		         return true;
		      } else if (this.nextStartTick > 0) {
		         --this.nextStartTick;
		         return false;
		      } else {
		         this.nextStartTick = this.nextStartTick(this.mob);
		         Predicate<AbstractSchoolingFish> predicate = (p_25258_) -> {
		            return p_25258_.canBeFollowed() || !p_25258_.isFollower();
		         };
		         List<? extends AbstractSchoolingFish> list = this.mob.level.getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
		         AbstractSchoolingFish abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(AbstractSchoolingFish::canBeFollowed).findAny(), this.mob);
		         abstractschoolingfish.addFollowers(list.stream().filter((p_25255_) -> {
		            return !p_25255_.isFollower();
		         }));
		         return this.mob.isFollower();
		      }
		   }

		   public boolean canContinueToUse() {
		      return this.mob.isFollower() && this.mob.inRangeOfLeader();
		   }

		   public void start() {
		      this.timeToRecalcPath = 0;
		   }

		   public void stop() {
		      this.mob.stopFollowing();
		   }

		   public void tick() {
		      if (--this.timeToRecalcPath <= 0) {
		         this.timeToRecalcPath = this.adjustedTickDelay(10);
		         this.mob.pathToLeader();
		      }
		   }
		}
	*/

}
