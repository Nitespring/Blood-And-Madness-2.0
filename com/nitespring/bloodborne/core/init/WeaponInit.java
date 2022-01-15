package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.magic.CallBeyondItem;
import com.nitespring.bloodborne.common.items.weapons.guns.FlamesprayerItem;
import com.nitespring.bloodborne.common.items.weapons.guns.GunItem;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;
import com.nitespring.bloodborne.common.items.weapons.special.LitTorch;
import com.nitespring.bloodborne.common.items.weapons.special.ThreadedCaneWhip;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.BurialBladeScythe;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.BurialBladeSword;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.ChikageBlood;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.ChikageNormal;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.HunterAxe;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.HunterAxeExtended;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.SawCleaver;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.SawCleaverExtended;
import com.nitespring.bloodborne.common.items.weapons.trickweapons.ThreadedCaneCane;
import com.nitespring.bloodborne.common.tabs.BloodborneWeapons;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeaponInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			 BloodborneMod.MOD_ID);
	
	public static final CreativeModeTab bloodborneweapons = new BloodborneWeapons();
	
	
	
	public static final RegistryObject<BloodborneWeapon> SHADOW_BLADE = ITEMS.register("weapons/shadow_blade", 
			() -> new BloodborneWeapon( 8.0f, 0.0f, 0.0f, 4.0f, 2.0f, 1.8f, 0.2f, true, true, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> TORCH = ITEMS.register("weapons/torch", 
			() -> new BloodborneWeapon( 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 1.4f, 0.1f, false, false, new Item.Properties()));
	public static final RegistryObject<BloodborneWeapon> LIT_TORCH = ITEMS.register("weapons/lit_torch", 
			() -> new LitTorch( 1.0f, 3.0f, 0.0f, 0.0f, 0.0f, 1.4f, 0.1f, false, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> CUTLASS = ITEMS.register("weapons/cutlass", 
			() -> new BloodborneWeapon( 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.6f, 0.1f, false, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> HUNTING_AXE = ITEMS.register("weapons/hunting_axe", 
			() -> new BloodborneWeapon( 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.2f, 0.3f, false, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> PITCHFORK = ITEMS.register("weapons/pitchfork", 
			() -> new BloodborneWeapon( 7.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.2f, false, false, new Item.Properties().tab(bloodborneweapons)));
	
	
	//Guns
	public static final RegistryObject<GunItem> HUNTER_PISTOL = ITEMS.register("weapons/hunter_pistol", 
			()-> new GunItem(4.0f, 20, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<FlamesprayerItem> FLAMESPRAYER = ITEMS.register("weapons/flamesprayer", 
			()-> new FlamesprayerItem(4.0f, new Item.Properties().tab(bloodborneweapons)));
	
	//Hunter Tools
	public static final RegistryObject<CallBeyondItem> A_CALL_BEYOND = ITEMS.register("magic/a_call_beyond", 
			() -> new CallBeyondItem(new Item.Properties().tab(bloodborneweapons)));
	
	
	
	
	
	
	
	//Trick Weapons
	public static final RegistryObject<BloodborneWeapon> BURIAL_BLADE = ITEMS.register("weapons/burial_blade", 
			() -> new BurialBladeSword( 6.0f, 0.0f, 0.0f, 1.2f, 0.0f, 2.1f, 0.2f, false, false, new Item.Properties()));
	public static final RegistryObject<BloodborneWeapon> BURIAL_BLADE_EXTENDED = ITEMS.register("weapons/burial_blade_extended", 
			() -> new BurialBladeScythe( 8.0f, 0.0f, 0.0f, 2.0f, 0.0f, 1.4f, 0.4f, false, false, new Item.Properties().tab(bloodborneweapons)));
	
	public static final RegistryObject<BloodborneWeapon> CHIKAGE = ITEMS.register("weapons/chikage", 
			() -> new ChikageNormal( 7.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 0.3f, false, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> CHIKAGE_EXTENDED = ITEMS.register("weapons/chikage_extended", 
			() -> new ChikageBlood( 2.0f, 0.0f, 0.0f, 0.0f, 10.0f, 1.6f, 0.6f, false, false, new Item.Properties()));
	
	public static final RegistryObject<BloodborneWeapon> HUNTER_AXE = ITEMS.register("weapons/hunter_axe", 
			() -> new HunterAxe( 9.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.2f, 0.6f, false, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> HUNTER_AXE_EXTENDED = ITEMS.register("weapons/hunter_axe_extended", 
			() -> new HunterAxeExtended( 12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.9f, 1.0f, false, false, new Item.Properties()));
	
	public static final RegistryObject<BloodborneWeapon> SAW_CLEAVER = ITEMS.register("weapons/saw_cleaver", 
			() -> new SawCleaver( 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.4f, 0.4f, true, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> SAW_CLEAVER_EXTENDED = ITEMS.register("weapons/saw_cleaver_extended", 
			() -> new SawCleaverExtended( 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.6f, false, false, new Item.Properties()));
	
	public static final RegistryObject<BloodborneWeapon> THREADED_CANE_WHIP = ITEMS.register("weapons/threaded_cane_whip", 
			() -> new ThreadedCaneWhip( 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.7f, 0.3f, true, false, new Item.Properties().tab(bloodborneweapons)));
	public static final RegistryObject<BloodborneWeapon> THREADED_CANE_CANE = ITEMS.register("weapons/threaded_cane_cane", 
			() -> new ThreadedCaneCane( 6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.2f, 0.1f, false, true, new Item.Properties()));
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
