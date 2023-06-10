package com.nitespring.bloodborne.common.entities.mobs.boss;

import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastNewAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastPainGoal;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.config.CommonConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GascoigneBeastBossEntity extends AbstractBloodborneEntity implements GeoEntity{
	
	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	public GascoigneBeastBossEntity(EntityType<? extends AbstractBloodborneEntity> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.xpReward= 40;
	}

	

	private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		String tAnim = "animation.gascoigne_beast.transition";
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.death"));
		}else {
		switch(animState) {
		case 5:
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne_beast.pain"));
			break;
		case 6:
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne_beast.air"));
			break;
		case 21:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.atk_1"));
			break;
		case 22:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.atk_2"));
			break;
		case 23:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.attack"));
			break;
		case 24:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.kick"));
			break;
		case 25:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.slam"));
			break;
		case 26:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne_beast.jump"));
			break;
		
		default:
			 
		  if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){			
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne_beast.walk"));					
		  }else{
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne_beast.new"));
		  }
			break;
		}
	}
		 return PlayState.CONTINUE;
	}
	private <E extends GeoAnimatable> PlayState clothPredicate(AnimationState<E> event) {
	
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne_beast.cloth"));	
		 return PlayState.CONTINUE;
			}	
		
	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 2, this::predicate));
		data.add(new AnimationController<>(this, "cloth_controller", 20, this::clothPredicate));
		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	 @Override
	 protected int getBnMDefaultTeam() {
		return 7;
	 }
	
	
	@Override
	public boolean isBoss() {
		return true;
	}

	@Override
	public boolean isBeastly() {
		
		return true;
	}

	@Override
	public boolean isKin() {
		
		return false;
	}
	@Override
	public boolean isCorrupted() {
		return false;
	}
	@Override
	public int physRes() {
		return 670;
	}
	@Override
	public int fireRes() {
		return 120;
	}
	@Override
	public int boltRes() {
		return 550;
	}
	@Override
	public int arcRes() {
		return 375;
	}
	@Override
	public int bloodRes() {
		return 240;
	}

	  public static AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 1000.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.7D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }
	  
	  @Override
	  protected void registerGoals() {
		  
		  this.goalSelector.addGoal(1, new GascoigneBeastPainGoal(this));
	      this.goalSelector.addGoal(2, new GascoigneBeastNewAttackGoal(this, 0.8D, true));

	      super.registerGoals();
	      

	     
	   }

	
	
	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		// TODO Auto-generated method stub
		return SoundEvents.RAVAGER_ATTACK;
	}



@Override
public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {

	return false;
}
	
@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
			MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
		  
		  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.father_gascoigne_hp.get());;
		  
		  
		
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}


	
	
}
