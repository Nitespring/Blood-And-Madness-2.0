package com.nitespring.bloodborne;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nitespring.bloodborne.core.init.BlockInit;
import com.nitespring.bloodborne.core.init.EntityInit;
import com.nitespring.bloodborne.core.init.FeatureInit;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.KeybindInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;
import com.nitespring.bloodborne.core.init.WeaponInit;
import com.nitespring.bloodborne.networking.BloodborneModPacketHandler;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(BloodborneMod.MOD_ID)
public class BloodborneMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "bloodandmadness";

    public BloodborneMod() {
    	
    	
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	bus.addListener(this::setup);
    	GeckoLib.initialize();
    	FeatureInit.FEATURES.register(bus);
    	ItemInit.ITEMS.register(bus);
    	WeaponInit.ITEMS.register(bus);
    	ProjectileInit.ENTITIES.register(bus);
    	EntityInit.ENTITIES.register(bus);
        BlockInit.BLOCKS.register(bus);
        BlockInit.ITEMS.register(bus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event){
    	BloodborneModPacketHandler.registerMessages();
    	
    	
    }
    
    
    @Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
 
            
        @SubscribeEvent
        static void clientSetup(final FMLClientSetupEvent event)
        {
        	 KeybindInit.register();
        	
        		}
        
        
       
    }

}
