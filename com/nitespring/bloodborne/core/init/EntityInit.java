package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			 BloodborneMod.MOD_ID);
   //Bosses
	public static final RegistryObject<EntityType<FatherGascoigneBossEntity>> FATHER_GASCOIGNE = ENTITIES.register("father_gascoigne",
			() -> EntityType.Builder.<FatherGascoigneBossEntity>of(FatherGascoigneBossEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("father_gascoigne"));
	public static final RegistryObject<EntityType<GascoigneBeastBossEntity>> GASCOIGNE_BEAST = ENTITIES.register("gascoigne_beast",
			() -> EntityType.Builder.<GascoigneBeastBossEntity>of(GascoigneBeastBossEntity::new, MobCategory.MONSTER)
			.sized(1.4f, 2.7f)
			.build("gascoigne_beast"));
	
	
	
	//Beasts
	public static final RegistryObject<EntityType<SilverbeastEntity>> SILVERBEAST = ENTITIES.register("silverbeast",
			() -> EntityType.Builder.<SilverbeastEntity>of(SilverbeastEntity::new, MobCategory.MONSTER)
			.sized(1.2f, 2.6f)
			.build("silverbeast"));
	
	
	//Huntsmen
	public static final RegistryObject<EntityType<HuntsmanAxeEntity>> HUNTSMAN_AXE = ENTITIES.register("huntsman_axe",
			() -> EntityType.Builder.<HuntsmanAxeEntity>of(HuntsmanAxeEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("huntsman_axe"));
	public static final RegistryObject<EntityType<HuntsmanCutlassEntity>> HUNTSMAN_CUTLASS = ENTITIES.register("huntsman_cutlass",
			() -> EntityType.Builder.<HuntsmanCutlassEntity>of(HuntsmanCutlassEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("huntsman_cutlass"));

}
