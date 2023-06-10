package com.nitespring.bloodborne.core.events;

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
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.init.EntityInit;

import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributes {

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		event.put(EntityInit.FATHER_GASCOIGNE.get(), FatherGascoigneBossEntity.setCustomAttributes().build());
		event.put(EntityInit.GASCOIGNE_BEAST.get(), GascoigneBeastBossEntity.setCustomAttributes().build());
		event.put(EntityInit.MICOLASH.get(), MicolashBossEntity.setCustomAttributes().build());
		
		event.put(EntityInit.HUNTSMAN_AXE.get(), HuntsmanAxeEntity.setCustomAttributes().build());
		event.put(EntityInit.HUNTSMAN_CUTLASS.get(), HuntsmanCutlassEntity.setCustomAttributes().build());
		
		event.put(EntityInit.SILVERBEAST.get(), SilverbeastEntity.setCustomAttributes().build());
		event.put(EntityInit.BEAST_PATIENT.get(), BeastPatient.setCustomAttributes().build());
		event.put(EntityInit.CLOAKED_BEAST_PATIENT.get(), CloakedBeastPatient.setCustomAttributes().build());
		event.put(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), AshenBeastPatient.setCustomAttributes().build());
		
		event.put(EntityInit.SKELETAL_PUPPET.get(), SkeletalPuppet.setCustomAttributes().build());
		
		event.put(EntityInit.BRAINSUCKER.get(), Brainsucker.setCustomAttributes().build());
		event.put(EntityInit.CELESTIAL_EMISSARY.get(), CelestialEmissary.setCustomAttributes().build());
		
		event.put(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), ChurchDoctorScythe.setCustomAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR.get(), ChurchDoctor.setCustomAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_LANTERN.get(), ChurchDoctorLantern.setCustomAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), ChurchDoctorCrucifix.setCustomAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_PISTOL.get(), ChurchDoctorPistol.setCustomAttributes().build());
		event.put(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), ChurchDoctorFlamesprayer.setCustomAttributes().build());
	}
	
	//@SubscribeEvent
	public static void updateAttributes(EntityAttributeModificationEvent event) {
		/*
		event.add(EntityInit.FATHER_GASCOIGNE.get(), Attributes.MAX_HEALTH, CommonConfig.father_gascoigne_hp.get());
		event.add(EntityInit.GASCOIGNE_BEAST.get(), Attributes.MAX_HEALTH, CommonConfig.father_gascoigne_hp.get());
		event.add(EntityInit.MICOLASH.get(), Attributes.MAX_HEALTH, CommonConfig.micolash_hp.get());
		event.add(EntityInit.SKELETAL_PUPPET.get(), Attributes.MAX_HEALTH, CommonConfig.puppet_hp.get());
		event.add(EntityInit.SILVERBEAST.get(), Attributes.MAX_HEALTH, CommonConfig.silverbeast_hp.get());
		event.add(EntityInit.BEAST_PATIENT.get(), Attributes.MAX_HEALTH, CommonConfig.beast_patient_hp.get());
		event.add(EntityInit.CLOAKED_BEAST_PATIENT.get(), Attributes.MAX_HEALTH, CommonConfig.cloaked_beast_patient_hp.get());
		event.add(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), Attributes.MAX_HEALTH, CommonConfig.ashen_blood_beast_patient_hp.get());
		event.add(EntityInit.CELESTIAL_EMISSARY.get(), Attributes.MAX_HEALTH, CommonConfig.celestial_emissary_hp.get());
		event.add(EntityInit.BRAINSUCKER.get(), Attributes.MAX_HEALTH, CommonConfig.brainsucker_hp.get());
		event.add(EntityInit.HUNTSMAN_AXE.get(), Attributes.MAX_HEALTH, CommonConfig.huntsman_axe_hp.get());
		event.add(EntityInit.HUNTSMAN_CUTLASS.get(), Attributes.MAX_HEALTH, CommonConfig.huntsman_cutlass_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR_LANTERN.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_lantern_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR_PISTOL.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_pistol_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_flamesprayer_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_scythe_hp.get());
		event.add(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), Attributes.MAX_HEALTH, CommonConfig.church_doctor_crucifix_hp.get());
		*/
	}
	
	
	
	
	
}
