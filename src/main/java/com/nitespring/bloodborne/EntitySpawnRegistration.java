package com.nitespring.bloodborne;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import com.nitespring.bloodborne.core.init.EntityInit;
import com.nitespring.bloodborne.common.entities.mobs.beasts.AshenBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.BeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.CloakedBeastPatient;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
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
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;

@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            
            SpawnPlacements.register(EntityInit.SILVERBEAST.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		SilverbeastEntity::checkSilverbeastSpawnRules);
            SpawnPlacements.register(EntityInit.BEAST_PATIENT.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		BeastPatient::checkBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.CLOAKED_BEAST_PATIENT.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		CloakedBeastPatient::checkCloakedBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		AshenBeastPatient::checkAshenBloodBeastPatientSpawnRules);
            
            SpawnPlacements.register(EntityInit.HUNTSMAN_AXE.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		HuntsmanAxeEntity::checkHuntsmanAxeSpawnRules);
            SpawnPlacements.register(EntityInit.HUNTSMAN_CUTLASS.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		HuntsmanCutlassEntity::checkHuntsmanCutlassSpawnRules);
            
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctor::checkChurchDoctorSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_LANTERN.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctorLantern::checkChurchDoctorLanternSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctorFlamesprayer::checkChurchDoctorFlamesprayerSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_PISTOL.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctorPistol::checkChurchDoctorPistolSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctorCrucifix::checkChurchDoctorCrucifixSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.WORLD_SURFACE, 
            		ChurchDoctorScythe::checkChurchDoctorScytheSpawnRules);
            
            SpawnPlacements.register(EntityInit.BRAINSUCKER.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		Brainsucker::checkBrainsuckerSpawnRules);
            SpawnPlacements.register(EntityInit.CELESTIAL_EMISSARY.get(), 
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		CelestialEmissary::checkCelestialEmissarySpawnRules);
            
            
       
            
            
      
        });
    }
	

	

}