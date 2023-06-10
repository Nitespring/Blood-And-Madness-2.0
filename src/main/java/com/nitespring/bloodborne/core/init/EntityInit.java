package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.SkeletalPuppet;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.BeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.CloakedBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.MicolashBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctor;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorCrucifix;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorFlamesprayer;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorLantern;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorPistol;
import com.nitespring.bloodborne.common.entities.mobs.church.ChurchDoctorScythe;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
import com.nitespring.bloodborne.common.entities.mobs.kin.Brainsucker;
import com.nitespring.bloodborne.common.entities.mobs.kin.CelestialEmissary;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
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
	public static final RegistryObject<EntityType<MicolashBossEntity>> MICOLASH = ENTITIES.register("micolash",
			() -> EntityType.Builder.<MicolashBossEntity>of(MicolashBossEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("micolash"));
	

	//Beasts
	public static final RegistryObject<EntityType<SilverbeastEntity>> SILVERBEAST = ENTITIES.register("silverbeast",
			() -> EntityType.Builder.<SilverbeastEntity>of(SilverbeastEntity::new, MobCategory.MONSTER)
			.sized(1.2f, 2.6f)
			.build("silverbeast"));
	public static final RegistryObject<EntityType<BeastPatient>> BEAST_PATIENT = ENTITIES.register("beast_patient",
			() -> EntityType.Builder.<BeastPatient>of(BeastPatient::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("beast_patient"));
	public static final RegistryObject<EntityType<CloakedBeastPatient>> CLOAKED_BEAST_PATIENT = ENTITIES.register("cloaked_beast_patient",
			() -> EntityType.Builder.<CloakedBeastPatient>of(CloakedBeastPatient::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("cloaked_beast_patient"));
	public static final RegistryObject<EntityType<AshenBeastPatient>> ASHEN_BLOOD_BEAST_PATIENT = ENTITIES.register("ashen_blood_beast_patient",
			() -> EntityType.Builder.<AshenBeastPatient>of(AshenBeastPatient::new, MobCategory.MONSTER)
			.sized(1.2f, 2.2f)
			.build("ashen_beast_patient"));
	
	
	
	//Huntsmen
	public static final RegistryObject<EntityType<HuntsmanAxeEntity>> HUNTSMAN_AXE = ENTITIES.register("huntsman_axe",
			() -> EntityType.Builder.<HuntsmanAxeEntity>of(HuntsmanAxeEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("huntsman_axe"));
	public static final RegistryObject<EntityType<HuntsmanCutlassEntity>> HUNTSMAN_CUTLASS = ENTITIES.register("huntsman_cutlass",
			() -> EntityType.Builder.<HuntsmanCutlassEntity>of(HuntsmanCutlassEntity::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("huntsman_cutlass"));

	
	//Church
	public static final RegistryObject<EntityType<ChurchDoctorScythe>> CHURCH_DOCTOR_SCYTHE = ENTITIES.register("church_doctor_scythe",
			() -> EntityType.Builder.<ChurchDoctorScythe>of(ChurchDoctorScythe::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor_scythe"));
	public static final RegistryObject<EntityType<ChurchDoctor>> CHURCH_DOCTOR = ENTITIES.register("church_doctor",
			() -> EntityType.Builder.<ChurchDoctor>of(ChurchDoctor::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor"));
	public static final RegistryObject<EntityType<ChurchDoctorLantern>> CHURCH_DOCTOR_LANTERN = ENTITIES.register("church_doctor_lantern",
			() -> EntityType.Builder.<ChurchDoctorLantern>of(ChurchDoctorLantern::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor_lantern"));
	public static final RegistryObject<EntityType<ChurchDoctorCrucifix>> CHURCH_DOCTOR_CRUCIFIX = ENTITIES.register("church_doctor_crucifix",
			() -> EntityType.Builder.<ChurchDoctorCrucifix>of(ChurchDoctorCrucifix::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor_crucifix"));
	public static final RegistryObject<EntityType<ChurchDoctorPistol>> CHURCH_DOCTOR_PISTOL = ENTITIES.register("church_doctor_pistol",
			() -> EntityType.Builder.<ChurchDoctorPistol>of(ChurchDoctorPistol::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor_pistol"));
	public static final RegistryObject<EntityType<ChurchDoctorFlamesprayer>> CHURCH_DOCTOR_FLAMESPRAYER = ENTITIES.register("church_doctor_flamesprayer",
			() -> EntityType.Builder.<ChurchDoctorFlamesprayer>of(ChurchDoctorFlamesprayer::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("church_doctor_flamesprayer"));
	
	
	
	//Kin
	public static final RegistryObject<EntityType<Brainsucker>> BRAINSUCKER = ENTITIES.register("brainsucker",
			() -> EntityType.Builder.<Brainsucker>of(Brainsucker::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("brainsucker"));
	public static final RegistryObject<EntityType<CelestialEmissary>> CELESTIAL_EMISSARY = ENTITIES.register("celestial_emissary",
			() -> EntityType.Builder.<CelestialEmissary>of(CelestialEmissary::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("celestial_emissary"));
	
	//Miscellanous
	public static final RegistryObject<EntityType<SkeletalPuppet>> SKELETAL_PUPPET = ENTITIES.register("skeletal_puppet",
			() -> EntityType.Builder.<SkeletalPuppet>of(SkeletalPuppet::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("skeletal_puppet"));
	
	
//TridentItem
	
}
