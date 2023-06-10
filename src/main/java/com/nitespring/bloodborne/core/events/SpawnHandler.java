package com.nitespring.bloodborne.core.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;

import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.core.init.EntityInit;

//@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID)
public class SpawnHandler {
	
	
	/*
	@SubscribeEvent
	public static void onBiomeLoading(BiomeLoadingEvent event) {
		
    	 ResourceLocation biomeName = event.getName();
    	 BiomeCategory biomeGroup = event.getCategory();
    	 
    
    	 
   
     
     if(!(biomeGroup==BiomeCategory.NETHER) && !(biomeGroup==BiomeCategory.THEEND) ) {
     //System.out.println("Added beasts to biome: " + biomeName.toString() + "!");
   
     if(!(biomeGroup==BiomeCategory.OCEAN)) {
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR.get(), 60, 1, 3));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR_LANTERN.get(), 50, 1, 2));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), 40, 1, 2));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR_PISTOL.get(), 40, 1, 2));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), 30, 1, 2));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), 30, 1, 3));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.BEAST_PATIENT.get(), 100, 2, 5));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CLOAKED_BEAST_PATIENT.get(), 80, 2, 4));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), 20, 1, 2));
     }
     if(!(biomeGroup==BiomeCategory.DESERT) && !(biomeGroup==BiomeCategory.MUSHROOM) 
    	&& !(biomeGroup==BiomeCategory.OCEAN) && !(biomeGroup==BiomeCategory.ICY) 
        && !(biomeGroup==BiomeCategory.EXTREME_HILLS) 
    	&& !(biomeName.toString()=="minecraft.dark_forest")
    	&& !(biomeName.toString()=="minecraft.dark_forest_hills")) {
    	 //System.out.println("Added huntsmen to biome: " + biomeName.toString() + "!");
    	 event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.HUNTSMAN_AXE.get(), 100, 2, 5));
    	 event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.HUNTSMAN_CUTLASS.get(), 95, 2, 3));
     }
     
     if(biomeGroup==BiomeCategory.DESERT||biomeGroup==BiomeCategory.MESA) {
    	 //System.out.println("Added silverbeasts to biome: " + biomeName.toString() + "!");
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.SILVERBEAST.get(), 75, 1, 1));	 
     }
       }
     
    
     if(biomeGroup==BiomeCategory.THEEND||biomeGroup==BiomeCategory.EXTREME_HILLS) {
    	 //System.out.println("Added brainsuckers to biome: " + biomeName.toString() + "!");
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.BRAINSUCKER.get(), 50, 1, 2));
     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CELESTIAL_EMISSARY.get(), 50, 1, 3));	
     }
     if(biomeGroup==BiomeCategory.SWAMP||biomeGroup==BiomeCategory.FOREST) {
    	 //System.out.println("Added brainsuckers to biome: " + biomeName.toString() + "!");

     event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(EntityInit.CELESTIAL_EMISSARY.get(), 5, 1, 1));	
     }
       }
*/
}
