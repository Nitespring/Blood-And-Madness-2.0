package com.nitespring.bloodborne.core.init;



import org.lwjgl.glfw.GLFW;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class KeybindInit {
	public static KeyMapping trickKeybind;
	public static KeyMapping reloadKeybind;
	
	    public static void register()
	    {
	    	
       	 trickKeybind = new KeyMapping("key.trick", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
    	    ClientRegistry.registerKeyBinding(trickKeybind);
    	 reloadKeybind = new KeyMapping("key.reload", GLFW.GLFW_KEY_R, "key.categories.misc");
    	    ClientRegistry.registerKeyBinding(reloadKeybind);
	       
	    }
	
}
