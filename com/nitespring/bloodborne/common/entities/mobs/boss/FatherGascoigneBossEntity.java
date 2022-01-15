package com.nitespring.bloodborne.common.entities.mobs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoigneAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoigneNewAttackGoal;
import com.nitespring.bloodborne.common.entities.ai.boss.FatherGascoignePainGoal;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.core.init.EntityInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FatherGascoigneBossEntity extends AbstractBloodborneEntity implements IAnimatable, IAnimationTickable{

	
	protected AnimationFactory factory = new AnimationFactory(this);
	public int timesStunned;
	
	public FatherGascoigneBossEntity(EntityType<? extends AbstractBloodborneEntity> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
		this.timesStunned=0;
	}
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		int animState = this.getAnimationState();	
		String tAnim = "animation.gascoigne.transition";
		String tAnim2 = "animation.gascoigne.transition2";
		switch(animState) {
			case 5:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.pain", true));
				break;
			case 6:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_to_2h", false));
				break;
			case 7:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_to_1h", false));
				break;
			case 11:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.roll_fix", true));
				break;
			case 12:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_r_sidestep", false));
				break;
			case 13:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_l_sidestep", false));
				break;
			case 14:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_r_sidestep", false));
				break;
			case 15:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_l_sidestep", false));
				break;
			case 21:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_atk_1", false));
				break;
			case 22:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_atk_2", false));
				break;
			case 23:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_atk_3", false));
				break;
			case 24:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_atk_roll", false));
				break;
			case 25:
            	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_jump", false));
				break;
			case 26:
            	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_shoot_new", false));
				break;
			case 31:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_1", false));
				break;
			case 32:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_2", false));
				break;
			case 33:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_3", false));
				break;
			case 34:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_roll", false));
				break;
			case 35:
            	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_jump", false));
				break;
			case 36:
            	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_shoot_new", false));
				break;	
			case 37:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_4", false));
				break;
			case 38:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_5", false));
				break;
			case 39:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_run", false));
				break;
			case 40:
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_atk_charged_fix", false));
				break;
		
		
			default:
			 if(this.getEntityState()==1) {
				 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
						if(this.isAggressive()) {
							event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_run", true));	
						}else {
							event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_walk", true));
						}			
					}else{
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.2h_idle", true));
					}
			 }else{
				 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
						if(this.isAggressive()) {
							event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_run", true));	
						}else {
							event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_walk", true));
						}			
					}else{
						event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.1h_idle", true));
					}
				
			 }
			break;
	
	}
		 return PlayState.CONTINUE;
	}
	
	private <E extends IAnimatable> PlayState clothPredicate(AnimationEvent<E> event) {
		
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.cloth", true));
		
		 return PlayState.CONTINUE;
	}
	
private <E extends IAnimatable> PlayState rotationPredicate(AnimationEvent<E> event) {
	int animState = this.getAnimationState();	
	switch(animState) {
	case 11:
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.body_rotation_roll", false));
	break;
	case 40:
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.body_rotation_charged", false));
	break;
	default:
	event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gascoigne.body_rotation_default", false));	
	
	break;
	}
		
		
		 return PlayState.CONTINUE;
	}


	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "main_controller", 2, this::predicate));
		data.addAnimationController(new AnimationController<>(this, "cloth_controller", 20, this::clothPredicate));
		data.addAnimationController(new AnimationController<>(this, "rotation_controller", 0, this::rotationPredicate));
		
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
		  
	      this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
	      this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	
	      

       this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
	      
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
	      

	     
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
		 Level worldIn = this.level;
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
      	  Random rng = this.getRandom();
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
		public int tickTimer() {
			
			return tickCount;
		}
	 
}
