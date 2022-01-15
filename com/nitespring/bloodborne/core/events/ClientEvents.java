package com.nitespring.bloodborne.core.events;

import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.client.render.layers.WeaponPartLayer;
import com.nitespring.bloodborne.core.init.KeybindInit;
import com.nitespring.bloodborne.networking.BloodborneModPacketHandler;
import com.nitespring.bloodborne.networking.TrickPacket;

import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	private static final HashMap<RenderLayer<?, ?>, PlayerRenderer> mappedfashion = new HashMap<>();
	
	
	 
	 
	  @SubscribeEvent
	    public static void addLayers(EntityRenderersEvent.AddLayers event) {

	        mappedfashion.clear();
		  
	       event.getSkins().forEach(skinTypeName -> { 
	        if (event.getSkin(skinTypeName) instanceof PlayerRenderer) {
	        
	        PlayerRenderer renderer = (PlayerRenderer)event.getSkin(skinTypeName);
	       
	        renderer.addLayer(new WeaponPartLayer(renderer));
	        mappedfashion.put(new WeaponPartLayer(renderer), renderer);
	                
	            }
	        });
	    }

	    public static HashMap<RenderLayer<?, ?>, PlayerRenderer> getMappedfashion() {

	        return mappedfashion;
	    }
	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
