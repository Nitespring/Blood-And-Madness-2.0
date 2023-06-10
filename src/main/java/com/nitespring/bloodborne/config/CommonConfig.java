package com.nitespring.bloodborne.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	
	public static ForgeConfigSpec.BooleanValue drop_blood_dew;
	public static ForgeConfigSpec.BooleanValue spawn_blood_particles;
	public static ForgeConfigSpec.BooleanValue apply_dawncraft_patch;
	public static ForgeConfigSpec.DoubleValue upgrade_level_scaling;
	public static ForgeConfigSpec.IntValue weapon_durability;
	public static ForgeConfigSpec.IntValue blood_vial_heal_amplifier;
	
	public static ForgeConfigSpec.BooleanValue should_silverbeast_spawn;
	public static ForgeConfigSpec.BooleanValue should_huntsman_axe_spawn;
	public static ForgeConfigSpec.BooleanValue should_huntsman_cutlass_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_pistol_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_flamesprayer_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_crucifix_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_lantern_spawn;
	public static ForgeConfigSpec.BooleanValue should_church_doctor_scythe_spawn;
	public static ForgeConfigSpec.BooleanValue should_brainsucker_spawn;
	public static ForgeConfigSpec.BooleanValue should_celestial_emissary_spawn;
	public static ForgeConfigSpec.BooleanValue should_beast_patient_spawn;
	public static ForgeConfigSpec.BooleanValue should_cloaked_beast_patient_spawn;
	public static ForgeConfigSpec.BooleanValue should_ashen_beast_patient_spawn;
	
	public static ForgeConfigSpec.DoubleValue father_gascoigne_hp;
	public static ForgeConfigSpec.DoubleValue micolash_hp;
	public static ForgeConfigSpec.DoubleValue puppet_hp;
	public static ForgeConfigSpec.DoubleValue silverbeast_hp;
	public static ForgeConfigSpec.DoubleValue beast_patient_hp;
	public static ForgeConfigSpec.DoubleValue ashen_blood_beast_patient_hp;
	public static ForgeConfigSpec.DoubleValue cloaked_beast_patient_hp;
	public static ForgeConfigSpec.DoubleValue celestial_emissary_hp;
	public static ForgeConfigSpec.DoubleValue huntsman_axe_hp;
	public static ForgeConfigSpec.DoubleValue huntsman_cutlass_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_pistol_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_flamesprayer_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_crucifix_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_lantern_hp;
	public static ForgeConfigSpec.DoubleValue church_doctor_scythe_hp;
	public static ForgeConfigSpec.DoubleValue brainsucker_hp;
	
	
	
public CommonConfig(final ForgeConfigSpec.Builder common) {
		
	common.comment("Configs:");
		
		/*drop_blood_dew = common
				.comment("Will enemies drop coldblood dew items?")
				.define("spawnconfig.drop_blood_dew", true);*/
		spawn_blood_particles = common
				.comment("Will entities spawn blood particles when damaged?")
				.define("bloodandmadness.spawn_blood_particles", true);
		apply_dawncraft_patch = common
				.comment("Apply Dawncraft patch")
				.define("bloodandmadness.apply_dawncraft_patch", false);
		upgrade_level_scaling = common
				.comment("How much will weapon damage scale for each level")
				.defineInRange("bloodandmadness.upgrade_level_scaling", 0.20D, 0, 255);
		weapon_durability = common
				.comment("max durability of Blood And Madness weapons")
				.defineInRange("bloodandmadness.weapon_duraility", 4096, 0, 1048576);
		blood_vial_heal_amplifier = common
				.comment("max durability of Blood And Madness weapons")
				.defineInRange("bloodandmadness.blood_vial_heal_amplifier", 1, 0, 333);
				
		
		

	
		should_silverbeast_spawn = common
				.comment("Should Silverbeasts naturally spawn?")
				.define("bloodandmadness.should_silverbeast_spawn", true);
		
		should_brainsucker_spawn = common
				.comment("Should Brainsuckers naturally spawn?")
				.define("bloodandmadness.should_brainsucker_spawn", true);
		
		should_huntsman_axe_spawn = common
				.comment("Should Huntsmen with Axe naturally spawn?")
				.define("bloodandmadness.should_huntsman_axe_spawn", true);
		
		should_huntsman_cutlass_spawn = common
				.comment("Should Huntsmen with Cutlass naturally spawn?")
				.define("bloodandmadness.should_huntsman_cutlass_spawn", true);
			
		should_church_doctor_spawn = common
				.comment("Should Church Doctors naturally spawn?")
				.define("bloodandmadness.should_church_doctor_spawn", true);
		
		should_church_doctor_lantern_spawn = common
				.comment("Should Church Doctors with Lantern naturally spawn?")
				.define("bloodandmadness.should_church_doctor_lantern_spawn", true);
		
		should_church_doctor_pistol_spawn = common
				.comment("Should Church Doctors with Pistol naturally spawn?")
				.define("bloodandmadness.should_church_doctor_pistol_spawn", true);
		
		should_church_doctor_flamesprayer_spawn = common
				.comment("Should Church Doctors with Flamesprayer naturally spawn?")
				.define("bloodandmadness.should_church_doctor_flamesprayer_spawn", true);
		
		should_church_doctor_scythe_spawn = common
				.comment("Should Church Doctors with Scythe naturally spawn?")
				.define("bloodandmadness.should_church_doctor_scythe_spawn", true);
		
		should_church_doctor_crucifix_spawn = common
				.comment("Should Church Doctors with Crucifix naturally spawn?")
				.define("bloodandmadness.should_church_doctor_crucifix_spawn", true);
		
		should_celestial_emissary_spawn = common
				.comment("Should Celestial Emissaries naturally spawn?")
				.define("bloodandmadness.should_celestial_emissary_spawn", true);
		
		should_beast_patient_spawn = common
				.comment("Should Beast Patients naturally spawn?")
				.define("bloodandmadness.should_beast_patients_spawn", true);
		should_cloaked_beast_patient_spawn = common
				.comment("Should Cloaked Beast Patients naturally spawn?")
				.define("bloodandmadness.should_cloaked_beast_patients_spawn", true);
		should_ashen_beast_patient_spawn = common
				.comment("Should Ashen Blood Beast Patients naturally spawn?")
				.define("bloodandmadness.should_ashen_beast_patients_spawn", true);

		father_gascoigne_hp = common
				.comment("Father Gascoigne HP (total)")
				.defineInRange("bloodandmadness.father_gascoigne_hp", 1000.0D, 1, 1048576);
		micolash_hp = common
				.comment("Micolash HP")
				.defineInRange("bloodandmadness.micolash_hp", 1500.0D, 1, 1048576);
		puppet_hp = common
				.comment("Skeletal Puppet HP")
				.defineInRange("bloodandmadness.puppet_hp", 60.0D, 1, 1048576);
		silverbeast_hp = common
				.comment("Silverbeast HP")
				.defineInRange("bloodandmadness.silverbeast_hp", 200.0D, 1, 1048576);
		beast_patient_hp = common
				.comment("Beast Patient HP")
				.defineInRange("bloodandmadness.beast_patient_hp", 50.0D, 1, 1048576);
		cloaked_beast_patient_hp = common
				.comment("Beast Patient HP")
				.defineInRange("bloodandmadness.cloaked_beast_patient_hp", 60.0D, 1, 1048576);
		ashen_blood_beast_patient_hp = common
				.comment("Ashen Blood Beast Patient HP")
				.defineInRange("bloodandmadness.ashen_blood_beast_patient_hp", 80.0D, 1, 1048576);
		brainsucker_hp = common
				.comment("Brainsucker HP")
				.defineInRange("bloodandmadness.brainsucker_hp", 80.0D, 1, 1048576);
		celestial_emissary_hp = common
				.comment("Celestial Emissary HP")
				.defineInRange("bloodandmadness.celestial_emissary_hp", 60.0D, 1, 1048576);
		huntsman_axe_hp = common
				.comment("Huntsman (Axe) HP")
				.defineInRange("bloodandmadness.huntsman_axe_hp", 32.0D, 1, 1048576);
		huntsman_cutlass_hp = common
				.comment("Huntsman (Cutlass) HP")
				.defineInRange("bloodandmadness.huntsman_cutlass_hp", 28.0D, 1, 1048576);
		church_doctor_hp = common
				.comment("Church Doctor (Normal) HP")
				.defineInRange("bloodandmadness.church_doctor_hp", 80.0D, 1, 1048576);
		church_doctor_pistol_hp = common
				.comment("Church Doctor (Pistol) HP")
				.defineInRange("bloodandmadness.church_doctor_pistol_hp", 80.0D, 1, 1048576);
		church_doctor_flamesprayer_hp = common
				.comment("Church Doctor (Flamesprayer) HP")
				.defineInRange("bloodandmadness.church_doctor_flamesprayer_hp", 80.0D, 1, 1048576);
		church_doctor_crucifix_hp = common
				.comment("Church Doctor (Crucifix) HP")
				.defineInRange("bloodandmadness.church_doctor_crucifix_hp", 80.0D, 1, 1048576);
		church_doctor_lantern_hp = common
				.comment("Church Doctor (Lantern) HP")
				.defineInRange("bloodandmadness.church_doctor_lantern_hp", 80.0D, 1, 1048576);
		church_doctor_scythe_hp = common
				.comment("Church Doctor (Scythe) HP")
				.defineInRange("bloodandmadness.church_doctor_scythe_hp", 80.0D, 1, 1048576);
		
		

		

		
		
	}

}