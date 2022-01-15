package com.nitespring.bloodborne.common.entities.mobs.boss;

import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastNewAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.GascoigneBeastPainGoal;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GascoigneBeastBossEntity extends AbstractBloodborneEntity implements IAnimatable, IAnimationTickable{
	protected AnimationFactory factory = new AnimationFactory(this);
	public GascoigneBeastBossEntity(EntityType<? extends AbstractBloodborneEntity> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		
	}

	

	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		int animState = this.getAnimationState();	
		String tAnim = "animation.gascoigne_beast.transition";
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.death", false));
		}else {
		switch(animState) {
		case 5:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.pain", true));
			break;
		case 6:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.air", true));
			break;
		case 21:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.atk_1", false));
			break;
		case 22:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.atk_2", false));
			break;
		case 23:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.attack", false));
			break;
		case 24:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.kick", false));
			break;
		case 25:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.slam", false));
			break;
		case 26:
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.jump", false));
			break;
		
		default:
			 
		  if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){			
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.walk", true));					
		  }else{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.new", true));
		  }
			break;
		}
	}
		 return PlayState.CONTINUE;
	}
	private <E extends IAnimatable> PlayState clothPredicate(AnimationEvent<E> event) {
	
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne_beast.cloth", true));	
		 return PlayState.CONTINUE;
			}	
		
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "main_controller", 2, this::predicate));
		data.addAnimationController(new AnimationController<>(this, "cloth_controller", 20, this::clothPredicate));
		
	}

	@Override
	public AnimationFactory getFactory() {
		
		return this.factory;
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

	      this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
	      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	
	      

          this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
	      
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	      

	     
	   }

	
	
	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		// TODO Auto-generated method stub
		return SoundEvents.RAVAGER_ATTACK;
	}



	@Override
	public int tickTimer() {
		
		return tickCount;
	}



	



	
	
}
