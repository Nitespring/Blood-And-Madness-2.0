package com.nitespring.bloodborne.common.tabs;

import com.nitespring.bloodborne.BloodborneMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public class CustomCreativeModeTabs extends CreativeModeTabs{
	
	public static final ResourceKey<CreativeModeTab> WEAPON_TAB = createKey(BloodborneMod.MOD_ID+ "equipment_tab");
	   public static final ResourceKey<CreativeModeTab> ENTITY_TAB = createKey(BloodborneMod.MOD_ID+ "entity_tab");
	   public static final ResourceKey<CreativeModeTab> MATERIAL_TAB = createKey(BloodborneMod.MOD_ID+ "material_tab");
	   
	   
	   private static ResourceKey<CreativeModeTab> createKey(String p_281544_) {
		      return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(p_281544_));
		   }

}
