package com.nitespring.bloodborne.core.events;

import java.util.Random;

import com.mojang.math.Vector3f;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.client.render.entities.projectiles.ACallBeyondProjectileRenderer;
import com.nitespring.bloodborne.common.customdamage.CustomDamageType;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondProjectileEntity;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID)
public class DamageEvents {
	
	
	@SubscribeEvent
	public static void bloodParticlesEvent(LivingHurtEvent event) {
		
		//boolean spawnBlood = BloodConfig.spawn_blood_particles.get();
		
		
		
		LivingEntity target = event.getEntityLiving();
		Float damage = event.getAmount();
		DamageSource type = event.getSource();
		float maxLife = target.getMaxHealth();
		Random rng = target.getRandom();
		int quantity = 50;
		
		if(damage<=50000) {
			//if(spawnBlood) {
		ParticleOptions blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));
		
		
		if(type.isFire()||type==CustomDamageType.FIRE_DAMAGE) {
			blood = ParticleTypes.FLAME;
		}else if(type.isMagic()||type==CustomDamageType.ARCANE_DAMAGE) {
			 blood = CallBeyondProjectileEntity.particle;
		}
		else if(type==CustomDamageType.BOLT_DAMAGE) {
			 blood = new DustParticleOptions(new Vector3f(0.0f,1,1), 1.0f);
		}
		else if(!(type == DamageSource.DROWN)) {
			Level world = target.level;
			Vec3 pos = new Vec3(target.getX(), target.getEyeY(), target.getZ());
			world.playLocalSound(pos.x, pos.y, pos.z, SoundEvents.GENERIC_SPLASH, SoundSource.AMBIENT, 0.2f, 0.4f /*+ rng.nextFloat()/0.5*/, true);
		if(target instanceof Skeleton) {   blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.BONE_MEAL));  }
		
		if(target instanceof EnderMan || target instanceof EnderDragon || target instanceof Endermite) {   blood = ParticleTypes.DRAGON_BREATH;  }
		if(target instanceof Slime) {   blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.SLIME_BLOCK));  }
		if(target instanceof MagmaCube || target instanceof Blaze) {   blood = ParticleTypes.FLAME;  }		
		if(target instanceof AbstractGolem ) {   blood = ParticleTypes.ENCHANT;  }	
		}
       
		
		float width = target.getBbWidth() * 0.5f;
	    float height = target.getBbHeight() * 0.5f;
		Vec3 pos = new Vec3(target.getX(), target.getEyeY(), target.getZ());
		Level world = target.level;
		
		
		
		
		double size = width * height;
		double rand = 1/(1 + (rng.nextInt(10)-4)*0.1);
		for (int i = 0; i < quantity * damage * damage * rand * size/ maxLife + damage*0.5; ++i) {
			
		Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
					   rng.nextDouble() * width - width / 2);
		if(world instanceof ServerLevel) {
		((ServerLevel) world).sendParticles( blood, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + damage*0.003);
		
		
		
		
		}
		}
		
		//}
		}
		
		
	}
	
	
	  @SubscribeEvent
	   	public static void addResistances(LivingDamageEvent event) {
		  
			Float damage = event.getAmount();
			DamageSource type = event.getSource();
		  if(event.getEntity() instanceof AbstractBloodborneEntity) {
			  
			  AbstractBloodborneEntity target = (AbstractBloodborneEntity)(event.getEntity());
			  if(type.isFire()||type==CustomDamageType.FIRE_DAMAGE) {
				  
				  
				  event.setAmount(damage*(1-target.fireRes()/999));
				}else if(type.isMagic()||type==CustomDamageType.ARCANE_DAMAGE) {
				  event.setAmount(damage*(1-target.arcRes()/999));
				}
				else if(type==CustomDamageType.BOLT_DAMAGE||type==DamageSource.LIGHTNING_BOLT) {
				  event.setAmount(damage*(1-target.boltRes()/999));
				}
				else if(type==CustomDamageType.BLOOD_DAMAGE) {
					  event.setAmount(damage*(1-target.bloodRes()/999));
					}
				else if(!(type == DamageSource.DROWN)) {
				  event.setAmount(damage*(1-target.physRes()/999));
					
				}
				
			 
			  
		  
		  }
	  }
	
	
	
	
	
	
	
	

}
