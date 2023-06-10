package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.BloodVial;
import com.nitespring.bloodborne.common.items.BlossomedEyeItem;
import com.nitespring.bloodborne.common.items.EntityModelSpawnEggItem;
import com.nitespring.bloodborne.common.items.HunterMarkItem;
import com.nitespring.bloodborne.common.items.RedJewelItem;
import com.nitespring.bloodborne.common.items.ShardItem;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import com.nitespring.bloodborne.common.items.bullets.EffectBulletItem;
import com.nitespring.bloodborne.common.items.bullets.ExplosiveBulletItem;
import com.nitespring.bloodborne.common.items.bullets.FireBulletItem;
import com.nitespring.bloodborne.common.items.bullets.QuickSilverBulletItem;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			 BloodborneMod.MOD_ID);
	
	
	//Weapon Parts
	public static final RegistryObject<Item> BURIAL_BLADE_PART = ITEMS.register("weapons/parts/burial_blade_part", 
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHIKAGE_PART = ITEMS.register("weapons/parts/chikage_part", 
			() -> new Item(new Item.Properties()));
	
	
	
	//Utilities
	public static final RegistryObject<HunterMarkItem> HUNTER_MARK = ITEMS.register("hunter_mark", 
			() -> new HunterMarkItem(new Item.Properties()));
	public static final RegistryObject<BloodVial> BLOOD_VIAL = ITEMS.register("blood_vial", 
			() -> new BloodVial(new Item.Properties().stacksTo(16)));
	
	//Materials
	public static final RegistryObject<ShardItem> BLOOD_STONE_SHARD = ITEMS.register("blood_stone_shard", 
			() -> new ShardItem(new Item.Properties()));
	public static final RegistryObject<ShardItem> TWIN_BLOOD_STONE_SHARDS = ITEMS.register("twin_blood_stone_shards", 
			() -> new ShardItem(new Item.Properties()));
	public static final RegistryObject<ShardItem> BLOOD_STONE_CHUNK = ITEMS.register("blood_stone_chunk", 
			() -> new ShardItem(new Item.Properties()));
	public static final RegistryObject<ShardItem> BLOOD_ROCK = ITEMS.register("blood_rock", 
			() -> new ShardItem(new Item.Properties()));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("sanguisteel_ingot", 
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("sanguisteel_nugget", 
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_INGOT = ITEMS.register("siderite_ingot", 
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_NUGGET = ITEMS.register("siderite_nugget", 
			() -> new Item(new Item.Properties()));
	
	public static final RegistryObject<Item> REINFORCED_HANDLE = ITEMS.register("reinforced_handle", 
			() -> new Item(new Item.Properties()));
	
	public static final RegistryObject<Item> BEASTBLOOD_CLUMP = ITEMS.register("beastblood_clump", 
			() -> new Item(new Item.Properties()));
	
	public static final RegistryObject<Item> PURE_QUICKSILVER = ITEMS.register("pure_quicksilver", 
			() -> new Item(new Item.Properties()));
	
	
	
	//Bullets
	public static final RegistryObject<BulletItem> QUICKSILVER_BULLET = ITEMS.register("bullets/quicksilver_bullet", 
			() -> new QuickSilverBulletItem("Quicksilver", 4.0f, 10, new Item.Properties().stacksTo(20)));
	public static final RegistryObject<BulletItem> COPPER_BULLET = ITEMS.register("bullets/copper_bullet", 
			() -> new BulletItem("Copper", 0.0f, 5, new Item.Properties().stacksTo(32)));
	public static final RegistryObject<BulletItem> IRON_BULLET = ITEMS.register("bullets/iron_bullet", 
			() -> new BulletItem("Iron", 6.0f, 0, new Item.Properties().stacksTo(16)));
	public static final RegistryObject<BulletItem> GOLDEN_BULLET = ITEMS.register("bullets/golden_bullet", 
			() -> new BulletItem("Gold", 4.0f, 10, new Item.Properties().stacksTo(16)));
	public static final RegistryObject<BulletItem> NETHERITE_BULLET = ITEMS.register("bullets/netherite_bullet", 
			() -> new BulletItem("Netherite", 8.0f, 15, new Item.Properties().stacksTo(8)));
	public static final RegistryObject<BulletItem> FIRE_BULLET = ITEMS.register("bullets/fire_bullet", 
			() -> new FireBulletItem("Fire", 1.0f, 5, new Item.Properties().stacksTo(32)));
	public static final RegistryObject<BulletItem> EXPLOSIVE_BULLET = ITEMS.register("bullets/explosive_bullet", 
			() -> new ExplosiveBulletItem("Explosive", 1.0f, 0, new Item.Properties().stacksTo(16)));
	public static final RegistryObject<BulletItem> POISONOUS_BULLET = ITEMS.register("bullets/poisonous_bullet", 
			() -> new EffectBulletItem("Poisonous", 1.0f, 5, MobEffects.POISON, new Item.Properties().stacksTo(32)));
	public static final RegistryObject<BulletItem> WITHERING_BULLET = ITEMS.register("bullets/withering_bullet", 
			() -> new EffectBulletItem("Withering", 2.0f, 0, MobEffects.WITHER, new Item.Properties().stacksTo(8)));
	public static final RegistryObject<BulletItem> GLOWING_BULLET = ITEMS.register("bullets/glowing_bullet", 
			() -> new EffectBulletItem("Glowing", 0.0f, 40, MobEffects.GLOWING, new Item.Properties().stacksTo(16)));
	public static final RegistryObject<BulletItem> ELECTRIC_BULLET = ITEMS.register("bullets/electric_bullet", 
			() -> new BulletItem("Electric", 1.0f, 5, new Item.Properties().stacksTo(16)));
	public static final RegistryObject<BulletItem> SNIPER_BULLET = ITEMS.register("bullets/sniper_bullet", 
			() -> new BulletItem("Sniper", 2.0f, 50, new Item.Properties().stacksTo(8)));
	
	
	//BossSummoners
	public static final RegistryObject<RedJewelItem> RED_JEWEL = ITEMS.register("red_jewel", 
			() -> new RedJewelItem(new Item.Properties()));
	public static final RegistryObject<BlossomedEyeItem> BLOSSOMED_EYE = ITEMS.register("blossomed_eye", 
			() -> new BlossomedEyeItem(new Item.Properties()));
	
	
	//Eggs
	public static final RegistryObject<Item> FATHER_GASCOIGNE_SPAWN_EGG = ITEMS.register("eggs/father_gascoigne_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.FATHER_GASCOIGNE::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> GASCOIGNE_BEAST_SPAWN_EGG = ITEMS.register("eggs/gascoigne_beast_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.GASCOIGNE_BEAST::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> HUNTSMAN_AXE = ITEMS.register("eggs/huntsman_axe_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.HUNTSMAN_AXE::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> HUNTSMAN_CUTLASS = ITEMS.register("eggs/huntsman_cutlass_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.HUNTSMAN_CUTLASS::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> SILVERBEAST = ITEMS.register("eggs/silverbeast_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.SILVERBEAST::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> BEAST_PATIENT = ITEMS.register("eggs/beast_patient_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.BEAST_PATIENT::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CLOAKED_BEAST_PATIENT = ITEMS.register("eggs/cloaked_beast_patient_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CLOAKED_BEAST_PATIENT::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> ASHEN_BEAST_PATIENT = ITEMS.register("eggs/ashen_blood_beast_patient_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.ASHEN_BLOOD_BEAST_PATIENT::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> MICOLASH = ITEMS.register("eggs/micolash_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.MICOLASH::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> SKELETAL_PUPPET = ITEMS.register("eggs/skeletal_puppet_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.SKELETAL_PUPPET::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_SCYTHE = ITEMS.register("eggs/church_doctor_scythe_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR_SCYTHE::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR = ITEMS.register("eggs/church_doctor_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_LANTERN = ITEMS.register("eggs/church_doctor_lantern_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR_LANTERN::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_PISTOL = ITEMS.register("eggs/church_doctor_pistol_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR_PISTOL::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_FLAMESPRAYER = ITEMS.register("eggs/church_doctor_flamesprayer_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_CRUCIFIX = ITEMS.register("eggs/church_doctor_crucifix_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CHURCH_DOCTOR_CRUCIFIX::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> BRAINSUCKER = ITEMS.register("eggs/brainsucker_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.BRAINSUCKER::get, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> CELESTIAL_EMISSARY = ITEMS.register("eggs/celestial_emissary_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.CELESTIAL_EMISSARY::get, 0xffffff, 0xffffff, new Item.Properties()));
	
	
	
	
	
	public static final RegistryObject<Item> IRON_GOLEM_SPAWN_EGG = ITEMS.register("eggs/iron_golem_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.IRON_GOLEM, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> SNOW_GOLEM_SPAWN_EGG = ITEMS.register("eggs/snow_golem_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.SNOW_GOLEM, 0xffffff, 0xffffff, new Item.Properties()));
	public static final RegistryObject<Item> WITHER_SPAWN_EGG = ITEMS.register("eggs/wither_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.WITHER, 0xffffff, 0xffffff, new Item.Properties()));
	
	
	//BlockItems
	public static final RegistryObject<BlockItem> FIRE = ITEMS.register("fire_item", 
			() -> new BlockItem(Blocks.FIRE, new Item.Properties()));
	
	
}
