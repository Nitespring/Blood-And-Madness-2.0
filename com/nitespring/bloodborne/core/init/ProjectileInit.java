package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondAimHelperEntity;
import com.nitespring.bloodborne.common.entities.projectiles.CallBeyondProjectileEntity;
import com.nitespring.bloodborne.common.entities.projectiles.ExplosiveBulletEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FireBulletEntity;
import com.nitespring.bloodborne.common.entities.projectiles.FlameProjectileEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ProjectileInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			 BloodborneMod.MOD_ID);

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
	public static final RegistryObject<EntityType<FlameProjectileEntity>> FLAME = ENTITIES.register("flame",
			() -> EntityType.Builder.<FlameProjectileEntity>of(FlameProjectileEntity::new, MobCategory.MISC)
			.sized(0.2f, 0.2f)
			.build("flame"));
	
	
}
