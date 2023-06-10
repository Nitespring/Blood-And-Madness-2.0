package com.nitespring.bloodborne.common.customdamage;


import com.nitespring.bloodborne.BloodborneMod;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;



public class CustomDamageSource{


	public static final ResourceKey<DamageType> FIRE_DAMAGE = register("flame");
    public static final ResourceKey<DamageType> BOLT_DAMAGE = register("bolt");
    public static final ResourceKey<DamageType> BLOOD_DAMAGE = register("blood");
    public static final ResourceKey<DamageType> ARCANE_DAMAGE = register("arcane");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(BloodborneMod.MOD_ID, name));
    }
	   

	   
	
	
}
