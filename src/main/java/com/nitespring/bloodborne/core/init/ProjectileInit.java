package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasEntity;
import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.BindingSpellProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondAimHelperEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.ExplosiveBulletEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FireBulletEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FlameProjectileEntity;
import com.nitespring.bloodborne.common.entities.utility.BlossomedEyeEntity;
import com.nitespring.bloodborne.common.entities.utility.RedJewelEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ProjectileInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
			 BloodborneMod.MOD_ID);

	public static final RegistryObject<EntityType<RedJewelEntity>> RED_JEWEL = ENTITIES.register("red_jewel",
			() -> EntityType.Builder.<RedJewelEntity>of(RedJewelEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("red_jewel"));
	public static final RegistryObject<EntityType<BlossomedEyeEntity>> BLOSSOMED_EYE = ENTITIES.register("blossomed_eye",
			() -> EntityType.Builder.<BlossomedEyeEntity>of(BlossomedEyeEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("blossomed_eye"));
	
	
	
	public static final RegistryObject<EntityType<BulletProjectileEntity>> BULLET_ENTITY = ENTITIES.register("bullet",
			() -> EntityType.Builder.<BulletProjectileEntity>of(BulletProjectileEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("bullet"));
	public static final RegistryObject<EntityType<FireBulletEntity>> FIRE_BULLET_ENTITY = ENTITIES.register("fire_bullet",
			() -> EntityType.Builder.<FireBulletEntity>of(FireBulletEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("fire_bullet"));
	public static final RegistryObject<EntityType<ExplosiveBulletEntity>> EXPLOSIVE_BULLET_ENTITY = ENTITIES.register("explosive_bullet",
			() -> EntityType.Builder.<ExplosiveBulletEntity>of(ExplosiveBulletEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("explosive_bullet"));
	public static final RegistryObject<EntityType<CallBeyondProjectileEntity>> CALL_BEYOND = ENTITIES.register("call_beyond",
			() -> EntityType.Builder.<CallBeyondProjectileEntity>of(CallBeyondProjectileEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("call_beyond"));
	public static final RegistryObject<EntityType<CallBeyondAimHelperEntity>> CALL_BEYOND_AIM_HELPER = ENTITIES.register("call_beyond_aim_helper",
			() -> EntityType.Builder.<CallBeyondAimHelperEntity>of(CallBeyondAimHelperEntity::new, MobCategory.MISC)
			.sized(1.5f, 1.5f)
			.build("call_beyond_aim_helper"));
	public static final RegistryObject<EntityType<AugurOfEbrietasEntity>> AUGUR_OF_EBRIETAS = ENTITIES.register("augur_of_ebrietas",
			() -> EntityType.Builder.<AugurOfEbrietasEntity>of(AugurOfEbrietasEntity::new, MobCategory.MISC)
			.sized(0.1f, 0.1f)
			.build("augur_of_ebrietas"));
	public static final RegistryObject<EntityType<FlameProjectileEntity>> FLAME = ENTITIES.register("flame",
			() -> EntityType.Builder.<FlameProjectileEntity>of(FlameProjectileEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("flame"));
	public static final RegistryObject<EntityType<AugurOfEbrietasProjectileEntity>> AUGUR_PROJECTILE = ENTITIES.register("augur_projectile",
			() -> EntityType.Builder.<AugurOfEbrietasProjectileEntity>of(AugurOfEbrietasProjectileEntity::new, MobCategory.MISC)
			.sized(0.5f, 0.5f)
			.build("augur_projectile"));
	public static final RegistryObject<EntityType<BindingSpellProjectileEntity>> BINDING_SPELL = ENTITIES.register("binding_spell",
			() -> EntityType.Builder.<BindingSpellProjectileEntity>of(BindingSpellProjectileEntity::new, MobCategory.MISC)
			.sized(0.5f, 0.5f)
			.build("binding_spell"));
	
	
	
}
