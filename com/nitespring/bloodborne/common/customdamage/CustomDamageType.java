package com.nitespring.bloodborne.common.customdamage;


import net.minecraft.world.damagesource.DamageSource;



public class CustomDamageType extends DamageSource{


		
	
	   public static final DamageSource FIRE_DAMAGE = (new DamageSource("fire")).bypassArmor().setIsFire();
	   public static final DamageSource BOLT_DAMAGE = new DamageSource("bolt").bypassArmor();
	   public static final DamageSource BLOOD_DAMAGE = (new DamageSource("blood")).bypassArmor();
	   public static final DamageSource ARCANE_DAMAGE = (new DamageSource("unknown")).setMagic();
	   
	   

	   
	public CustomDamageType(String p_i1566_1_) {
		super(p_i1566_1_);
		
	}
	
}
