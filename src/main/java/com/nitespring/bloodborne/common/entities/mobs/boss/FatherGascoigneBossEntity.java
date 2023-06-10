package com.nitespring.bloodborne.common.entities.mobs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoigneAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoigneNewAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoignePainGoal;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.init.EntityInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
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


public class FatherGascoigneBossEntity extends AbstractBloodborneEntity implements GeoEntity{

	
	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	public int timesStunned;
	
	public FatherGascoigneBossEntity(EntityType<? extends AbstractBloodborneEntity> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.timesStunned=0;
		this.xpReward= 40;
		//Skeleton
	}
	
	
	private <E extends GeoEntity> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		String tAnim = "animation.gascoigne.transition";
		String tAnim2 = "animation.gascoigne.transition2";
		switch(animState) {
			case 5:
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.pain"));
				break;
			case 6:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_to_2h"));
				break;
			case 7:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_to_1h"));
				break;
			case 11:
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.roll_fix"));
				break;
			case 12:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_r_sidestep"));
				break;
			case 13:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_l_sidestep"));
				break;
			case 14:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_r_sidestep"));
				break;
			case 15:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_l_sidestep"));
				break;
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_atk_1"));
				break;
			case 22:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_atk_2"));
				break;
			case 23:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_atk_3"));
				break;
			case 24:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_atk_roll"));
				break;
			case 25:
            	event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_jump"));
				break;
			case 26:
            	event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.1h_shoot_new"));
				break;
			case 31:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_1"));
				break;
			case 32:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_2"));
				break;
			case 33:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_3"));
				break;
			case 34:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_roll"));
				break;
			case 35:
            	event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_jump"));
				break;
			case 36:
            	event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_shoot_new"));
				break;	
			case 37:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_4"));
				break;
			case 38:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_5"));
				break;
			case 39:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_run"));
				break;
			case 40:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.gascoigne.2h_atk_charged_fix"));
				break;
		
		
			default:
			 if(this.getEntityState()==1) {
				 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
						if(this.isAggressive()) {
							event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.2h_run"));	
						}else {
							event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.2h_walk"));
						}			
					}else{
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.2h_idle"));
					}
			 }else{
				 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
						if(this.isAggressive()) {
							event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.1h_run"));	
						}else {
							event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.1h_walk"));
						}			
					}else{
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.1h_idle"));
					}
				
			 }
			break;
	
	}
		 return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState clothPredicate(AnimationState<E> event) {
		
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.cloth"));
		
		 return PlayState.CONTINUE;
	}
	
private <E extends GeoAnimatable> PlayState rotationPredicate(AnimationState<E> event) {
	int animState = this.getAnimationState();	
	switch(animState) {
	case 11:
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.body_rotation_roll"));
	break;
	case 40:
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.body_rotation_charged"));
	break;
	default:
	event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.gascoigne.body_rotation_default"));	
	
	break;
	}
		
		
		 return PlayState.CONTINUE;
	}


	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 2, this::predicate));
		data.add(new AnimationController<>(this, "cloth_controller", 20, this::clothPredicate));
		data.add(new AnimationController<>(this, "rotation_controller", 0, this::rotationPredicate));
		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	 @Override
	 protected int getBnMDefaultTeam() {
		return 5;
	 }
	
	
	@Override
	public boolean isBoss() {
		
		return true;
	}

	@Override
	public boolean isBeastly() {
		
		return false;
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
		
		return 560;
	}
	@Override
	public int fireRes() {
		
		return 400;
	}
	@Override
	public int boltRes() {
		
		return 440;
	}
	@Override
	public int arcRes() {
		
		return 400;
	}
	@Override
	public int bloodRes() {
		
		return 420;
	}
	
	 public static  AttributeSupplier.Builder setCustomAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 1000.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.6D)
					.add(Attributes.ATTACK_DAMAGE, 12)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 0.7D)
					.add(Attributes.FOLLOW_RANGE, 30);
	
		  }

	
	  @Override
	  protected void registerGoals() {
		  
		  
		  this.goalSelector.addGoal(1, new FatherGascoignePainGoal(this));
		  this.goalSelector.addGoal(2, new FatherGascoigneNewAttackGoal(this, 0.7D, true));
		  
		  this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      super.registerGoals();
	      
	     
	   }

	
	
	@Override
	public void tick() {
		if(this.getHealth()<=this.getMaxHealth()/3) {
			this.setAnimationState(5);
		}
		
		super.tick();
	}
	
	public void transform() {
		 float h = this.getHealth();
		 Level worldIn = this.level();
		 Vec3 pos = this.position();
		 this.explosion(worldIn);
		 GascoigneBeastBossEntity beast = new GascoigneBeastBossEntity(EntityInit.GASCOIGNE_BEAST.get(), worldIn);
		 beast.setPos(pos.x, pos.y, pos.z);
		 beast.setHealth(h);
		
		 worldIn.addFreshEntity(beast);
	     beast.setTarget(this.getTarget());
		 this.playSound(SoundEvents.RAVAGER_ROAR, 2.0f, 1.6f);
		 
		 
		 this.discard();

	}
	 protected void explosion(Level worldIn) {
		   ParticleOptions particle = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.MYCELIUM));;
	   Vec3 pos = this.position();
	    double x = pos.x;
	    double y = pos.y;
	    double z = pos.z;
	    float height = this.getBbHeight();
	    float width = this.getBbWidth();
	  
        for (int i = 0; i < 1000; ++i) {
      	  RandomSource rng = this.getRandom();
				Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
							   rng.nextDouble() * width - width / 2);
				if(worldIn instanceof ServerLevel) {
				((ServerLevel) worldIn).sendParticles( particle, x, y, z, 5,  off.x, off.y + 0.05D, off.z, 1.0D);
				
				}
				}
		
  	
		    AABB scanAbove = new AABB(x - 6.5f, y-3.5, z - 6.5f, x + 6.5f, y + 4.5f, z + 6.5f);
		    List<LivingEntity> entities = new ArrayList<>(worldIn.getEntitiesOfClass(LivingEntity.class, scanAbove));
	           if(!entities.isEmpty()) {

	        	for(int i = 0; i < entities.size(); i++) {
	             LivingEntity target = entities.get(i);
	            if(!(target==this)) {

	           target.knockback(2.5f,  this.getX() - target.getX() ,  this.getZ() - target.getZ());
     
   }}} 
	   }
	 @Override
		protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
			// TODO Auto-generated method stub
			return SoundEvents.VINDICATOR_HURT;
		}
	
	 
	  @Override
		public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_,
				MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) {
			  
			  this.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(CommonConfig.father_gascoigne_hp.get());;
			  
			  
			
			return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
		}
	 
}
