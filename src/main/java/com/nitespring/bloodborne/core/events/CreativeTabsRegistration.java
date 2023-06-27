package com.nitespring.bloodborne.core.events;



import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.tabs.CustomCreativeModeTabs;
import com.nitespring.bloodborne.core.init.BlockInit;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabsRegistration {
	
	
	 public static ResourceKey<CreativeModeTab> WEAPON_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(BloodborneMod.MOD_ID+ "equipment_tab"));
	 public static ResourceKey<CreativeModeTab> ENTITY_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(BloodborneMod.MOD_ID+ "entity_tab"));
	 public static ResourceKey<CreativeModeTab> MATERIAL_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(BloodborneMod.MOD_ID+ "material_tab"));
	
	@SubscribeEvent
	  public static void addItemsToTabs(BuildCreativeModeTabContentsEvent output)
	    {
	        if (output.getTabKey() == CreativeModeTabs.COMBAT)
	        {
	        	output.accept(WeaponInit.SAW_CLEAVER.get());
                output.accept(WeaponInit.CHIKAGE.get());
                output.accept(WeaponInit.BURIAL_BLADE.get());
                output.accept(WeaponInit.HUNTER_AXE.get());
                output.accept(WeaponInit.HUNTER_PISTOL.get());
                output.accept(WeaponInit.THREADED_CANE_CANE.get());
                output.accept(WeaponInit.FLAMESPRAYER.get());
                output.accept(WeaponInit.CHURCH_SCYTHE.get());
                output.accept(WeaponInit.SHADOW_BLADE.get());
                output.accept(WeaponInit.CUTLASS.get());
                output.accept(WeaponInit.HUNTING_AXE.get());
                output.accept(WeaponInit.LIT_TORCH.get());
                output.accept(ItemInit.BLOOD_VIAL.get());
                output.accept(ItemInit.QUICKSILVER_BULLET.get());
                output.accept(ItemInit.COPPER_BULLET.get());
                output.accept(ItemInit.IRON_BULLET.get());
                output.accept(ItemInit.GOLDEN_BULLET.get());
                output.accept(ItemInit.NETHERITE_BULLET.get());
                output.accept(ItemInit.FIRE_BULLET.get());
                output.accept(ItemInit.EXPLOSIVE_BULLET.get());
                output.accept(ItemInit.POISONOUS_BULLET.get());
                output.accept(ItemInit.WITHERING_BULLET.get());
                output.accept(ItemInit.GLOWING_BULLET.get());
                output.accept(ItemInit.ELECTRIC_BULLET.get());
                output.accept(ItemInit.SNIPER_BULLET.get());
                output.accept(WeaponInit.MUSIC_BOX.get());
                output.accept(WeaponInit.A_CALL_BEYOND.get());
                output.accept(WeaponInit.AUGUR_OF_EBRIETAS.get());
	        }
	        if (output.getTabKey() == CreativeModeTabs.INGREDIENTS)
	        {

	        	output.accept(ItemInit.BLOOD_STONE_SHARD.get());
                output.accept(ItemInit.TWIN_BLOOD_STONE_SHARDS.get());
                output.accept(ItemInit.BLOOD_STONE_CHUNK.get());
                output.accept(ItemInit.BLOOD_ROCK.get());
                output.accept(ItemInit.BEASTBLOOD_CLUMP.get());
                output.accept(ItemInit.PURE_QUICKSILVER.get());
                output.accept(ItemInit.STEEL_INGOT.get());
                output.accept(ItemInit.STEEL_NUGGET.get());
                output.accept(ItemInit.SIDERITE_INGOT.get());
                output.accept(ItemInit.SIDERITE_NUGGET.get());
                output.accept(ItemInit.REINFORCED_HANDLE.get());
                output.accept(BlockInit.WORKSHOP_ITEM.get());
                output.accept(BlockInit.QUICKSILVER_ORE.get());
                output.accept(BlockInit.QUICKSILVER_ORE_NIGHTMARE.get());
                output.accept(BlockInit.NIGHTMARE_STONE_ITEM.get());
                output.accept(BlockInit.ROUGH_NIGHTMARE_STONE_ITEM.get());
                output.accept(BlockInit.TILED_NIGHTMARE_STONE_ITEM.get());
	        }
	        if (output.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
	        {
	        	output.accept(ItemInit.RED_JEWEL.get());
                output.accept(ItemInit.BLOSSOMED_EYE.get());
                output.accept(ItemInit.FATHER_GASCOIGNE_SPAWN_EGG.get());
                output.accept(ItemInit.GASCOIGNE_BEAST_SPAWN_EGG.get());
                output.accept(ItemInit.MICOLASH.get());
                output.accept(ItemInit.SKELETAL_PUPPET.get());
                output.accept(ItemInit.CELESTIAL_EMISSARY.get());
                output.accept(ItemInit.BRAINSUCKER.get());
                output.accept(ItemInit.CHURCH_DOCTOR.get());
                output.accept(ItemInit.CHURCH_DOCTOR_CRUCIFIX.get());
                output.accept(ItemInit.CHURCH_DOCTOR_FLAMESPRAYER.get());
                output.accept(ItemInit.CHURCH_DOCTOR_LANTERN.get());
                output.accept(ItemInit.CHURCH_DOCTOR_PISTOL.get());
                output.accept(ItemInit.CHURCH_DOCTOR_SCYTHE.get());
                output.accept(ItemInit.BEAST_PATIENT.get());
                output.accept(ItemInit.CLOAKED_BEAST_PATIENT.get());
                output.accept(ItemInit.ASHEN_BEAST_PATIENT.get());
                output.accept(ItemInit.SILVERBEAST.get());
                output.accept(ItemInit.HUNTSMAN_AXE.get());
                output.accept(ItemInit.HUNTSMAN_CUTLASS.get());
	        }
	    }

	
	 
	 @SubscribeEvent
	  public static void addItemsToTabs2(BuildCreativeModeTabContentsEvent event)
	    {
	        if (event.getTabKey() == CreativeModeTabs.COMBAT)
	        {
	            event.accept(ItemInit.WITHER_SPAWN_EGG);
	            event.accept(ItemInit.IRON_GOLEM_SPAWN_EGG);
	            event.accept(ItemInit.SNOW_GOLEM_SPAWN_EGG);
	            event.accept(ItemInit.FIRE);
	            
	        }
	        
	    }
	
	 
	 
	
	

}
