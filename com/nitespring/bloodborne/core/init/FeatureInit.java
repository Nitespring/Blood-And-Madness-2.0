package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import com.nitespring.bloodborne.common.items.bullets.QuickSilverBulletItem;
import com.nitespring.bloodborne.common.world.features.NightmareColumnFeature;

import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureInit {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			 BloodborneMod.MOD_ID);
	
	
	
	public static final RegistryObject<Feature<ColumnFeatureConfiguration>> NIGHTMARE_PILLAR = FEATURES.register("nightmare_pillar", 
			() -> new NightmareColumnFeature(ColumnFeatureConfiguration.CODEC));
	
	
	
	
	
	
	//Feature<FeatureConfiguration>
	

	
}
