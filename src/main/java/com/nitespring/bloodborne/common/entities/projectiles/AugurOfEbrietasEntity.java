package com.nitespring.bloodborne.common.entities.projectiles;



import java.util.Random;

import com.nitespring.bloodborne.common.entities.GenericAnimatedEntity;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AugurOfEbrietasEntity extends GenericAnimatedEntity implements GeoEntity{

	
	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	private Vec3 aim = new Vec3(0,1,0);
	private final float damage;
	private int lifetime=30;
	public boolean isPersistent=true;
	
	public AugurOfEbrietasEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	    this.damage=6.0f;
	}
	public AugurOfEbrietasEntity(EntityType<?> p_19870_, Level p_19871_, float damageIn) {
		super(p_19870_, p_19871_);
	    this.damage=damageIn;
	}
	public AugurOfEbrietasEntity(EntityType<?> e, Level level, float damageIn, int i) {
		this(e, level, damageIn);
		this.lifetime=i;
		this.isPersistent=false;
		
	}
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();	
		
		switch(animState) {
		case 1:
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.augur_of_ebrietas.loop"));
			break;
		default:
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.augur_of_ebrietas.start"));
			break;
		}
	
		 return PlayState.CONTINUE;
	}
	


	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 0, this::predicate));
		
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}
	
	
	@Override
	public void tick() {
		super.tick();
		if(this.getOwner() != null  && this.getOwner() instanceof Player) {
			Player playerIn = (Player)(this.getOwner());
				if(playerIn.isUsingItem()) {
					Vec3 pos = playerIn.position();
					Vec3 aim = playerIn.getLookAngle();
					this.setAim(aim);
					this.setPos(pos.x+0.75*aim.x, pos.y+1.25+0.75*aim.y, pos.z+0.75*aim.z);
					this.setXRot(playerIn.getViewXRot(1.0f)+90);
					this.setYRot(180-playerIn.getViewYRot(1.0f));
					
				}else {
					
					this.remove(RemovalReason.DISCARDED);
				}
			
		}
		if(this.livingTick>=25) {
			
			AugurOfEbrietasProjectileEntity p = new AugurOfEbrietasProjectileEntity(ProjectileInit.AUGUR_PROJECTILE.get(), this.level(), damage, 8);
			p.setPos(this.position().add(new Random().nextFloat()*0.5-0.25 -0.26*aim.x, new Random().nextFloat()*0.5-0.25-0.26*aim.y, new Random().nextFloat()*0.5-0.25-0.26*aim.z));
			p.setDeltaMovement(this.getAim().add(new Random().nextFloat()*0.60-0.30, new Random().nextFloat()*0.60-0.30, new Random().nextFloat()*0.60-0.30));
			if(this.getOwner()!=null) {
				p.setOwner(this.getOwner());
			}
			this.level().addFreshEntity(p);
			
			
			if(this.getAnimationState()!=1) {
				
				this.setAnimationState(1);
			}
		}
		
		if(!this.isPersistent) {
			if(this.livingTick>=lifetime) {
				this.remove(RemovalReason.DISCARDED);
			}
		}

		
	}
	public Vec3 getAim() {
		return aim;
	}
	public void setAim(Vec3 aim) {
		this.aim = aim;
	}
	

	
	
	
	
	
	

}
