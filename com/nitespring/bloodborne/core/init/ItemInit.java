package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.EntityModelSpawnEggItem;
import com.nitespring.bloodborne.common.items.HunterMarkItem;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import com.nitespring.bloodborne.common.items.bullets.EffectBulletItem;
import com.nitespring.bloodborne.common.items.bullets.ExplosiveBulletItem;
import com.nitespring.bloodborne.common.items.bullets.FireBulletItem;
import com.nitespring.bloodborne.common.items.bullets.QuickSilverBulletItem;
import com.nitespring.bloodborne.common.tabs.BloodborneDebug;
import com.nitespring.bloodborne.common.tabs.BloodborneEntities;
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
	
	public static final CreativeModeTab bloodborneentities = new BloodborneEntities();
	public static final CreativeModeTab bloodbornedebug = new BloodborneDebug();
	
	//Weapon Parts
	public static final RegistryObject<Item> BURIAL_BLADE_PART = ITEMS.register("weapons/parts/burial_blade_part", 
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHIKAGE_PART = ITEMS.register("weapons/parts/chikage_part", 
			() -> new Item(new Item.Properties()));
	
	
	
	//Utilities
	public static final RegistryObject<HunterMarkItem> HUNTER_MARK = ITEMS.register("hunter_mark", 
			() -> new HunterMarkItem(new Item.Properties()));
	
	
	
	
	//Bullets
	public static final RegistryObject<BulletItem> QUICKSILVER_BULLET = ITEMS.register("bullets/quicksilver_bullet", 
			() -> new QuickSilverBulletItem("Quicksilver", 4.0f, 10, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(20)));
	public static final RegistryObject<BulletItem> COPPER_BULLET = ITEMS.register("bullets/copper_bullet", 
			() -> new BulletItem("Copper", 0.0f, 5, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(32)));
	public static final RegistryObject<BulletItem> IRON_BULLET = ITEMS.register("bullets/iron_bullet", 
			() -> new BulletItem("Iron", 6.0f, 0, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(16)));
	public static final RegistryObject<BulletItem> GOLDEN_BULLET = ITEMS.register("bullets/golden_bullet", 
			() -> new BulletItem("Gold", 4.0f, 10, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(16)));
	public static final RegistryObject<BulletItem> NETHERITE_BULLET = ITEMS.register("bullets/netherite_bullet", 
			() -> new BulletItem("Netherite", 8.0f, 15, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(8)));
	public static final RegistryObject<BulletItem> FIRE_BULLET = ITEMS.register("bullets/fire_bullet", 
			() -> new FireBulletItem("Fire", 1.0f, 5, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(32)));
	public static final RegistryObject<BulletItem> EXPLOSIVE_BULLET = ITEMS.register("bullets/explosive_bullet", 
			() -> new ExplosiveBulletItem("Explosive", 1.0f, 0, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(16)));
	public static final RegistryObject<BulletItem> POISONOUS_BULLET = ITEMS.register("bullets/poisonous_bullet", 
			() -> new EffectBulletItem("Poisonous", 1.0f, 5, MobEffects.POISON, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(32)));
	public static final RegistryObject<BulletItem> WITHERING_BULLET = ITEMS.register("bullets/withering_bullet", 
			() -> new EffectBulletItem("Withering", 2.0f, 0, MobEffects.WITHER, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(8)));
	public static final RegistryObject<BulletItem> GLOWING_BULLET = ITEMS.register("bullets/glowing_bullet", 
			() -> new EffectBulletItem("Glowing", 0.0f, 40, MobEffects.GLOWING, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(16)));
	public static final RegistryObject<BulletItem> ELECTRIC_BULLET = ITEMS.register("bullets/electric_bullet", 
			() -> new BulletItem("Electric", 1.0f, 5, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(16)));
	public static final RegistryObject<BulletItem> SNIPER_BULLET = ITEMS.register("bullets/sniper_bullet", 
			() -> new BulletItem("Sniper", 2.0f, 50, new Item.Properties().tab(WeaponInit.bloodborneweapons).stacksTo(8)));
	
	
	
	//Eggs
	public static final RegistryObject<Item> FATHER_GASCOIGNE_SPAWN_EGG = ITEMS.register("eggs/father_gascoigne_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.FATHER_GASCOIGNE::get, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodborneentities)));
	public static final RegistryObject<Item> GASCOIGNE_BEAST_SPAWN_EGG = ITEMS.register("eggs/gascoigne_beast_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.GASCOIGNE_BEAST::get, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodborneentities)));
	public static final RegistryObject<Item> HUNTSMAN_AXE = ITEMS.register("eggs/huntsman_axe_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.HUNTSMAN_AXE::get, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodborneentities)));
	public static final RegistryObject<Item> HUNTSMAN_CUTLASS = ITEMS.register("eggs/huntsman_cutlass_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.HUNTSMAN_CUTLASS::get, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodborneentities)));
	public static final RegistryObject<Item> SILVERBEAST = ITEMS.register("eggs/silverbeast_spawn_egg", 
			() -> new EntityModelSpawnEggItem(EntityInit.SILVERBEAST::get, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodborneentities)));
	
	
	
	
	
	public static final RegistryObject<Item> IRON_GOLEM_SPAWN_EGG = ITEMS.register("eggs/iron_golem_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.IRON_GOLEM, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodbornedebug)));
	public static final RegistryObject<Item> SNOW_GOLEM_SPAWN_EGG = ITEMS.register("eggs/snow_golem_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.SNOW_GOLEM, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodbornedebug)));
	public static final RegistryObject<Item> WITHER_SPAWN_EGG = ITEMS.register("eggs/wither_spawn_egg", 
			() -> new EntityModelSpawnEggItem(()->EntityType.WITHER, 0xffffff, 0xffffff, new Item.Properties()
					.tab(bloodbornedebug)));
	
	
	//BlockItems
	public static final RegistryObject<BlockItem> FIRE = ITEMS.register("fire_item", 
			() -> new BlockItem(Blocks.FIRE, new Item.Properties().tab(bloodbornedebug)));
	
	
}
