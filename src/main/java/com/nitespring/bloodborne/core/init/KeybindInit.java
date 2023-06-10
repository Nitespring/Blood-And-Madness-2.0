package com.nitespring.bloodborne.core.init;



import org.lwjgl.glfw.GLFW;

import com.nitespring.bloodborne.BloodborneMod;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeybindInit {
	public static KeyMapping trickKeybind;
	public static KeyMapping reloadKeybind;
	
	    /*public static void register()
	    {
	    	
       	 trickKeybind = new KeyMapping("key.trick", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
       	//.registerKeyBinding(trickKeybind);
    	 reloadKeybind = new KeyMapping("key.reload", GLFW.GLFW_KEY_R, "key.categories.misc");
    	//.registerKeyBinding(reloadKeybind);
	       
	    }*/
	    
	    
	    @SubscribeEvent
	    public static void registerKeyBinds(RegisterKeyMappingsEvent event) {
	    	
	    	event.register(trickKeybind = new KeyMapping("key.trick", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc"));
	       
	    	event.register(reloadKeybind = new KeyMapping("key.reload", GLFW.GLFW_KEY_R, "key.categories.misc"));
	    	
	    }
	
}
