package com.nitespring.bloodborne.core.events;




import org.lwjgl.glfw.GLFW;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.core.init.KeybindInit;
import com.nitespring.bloodborne.networking.BloodborneModPacketHandler;
import com.nitespring.bloodborne.networking.PerformItemAnimation;
import com.nitespring.bloodborne.networking.ReloadPacket;
import com.nitespring.bloodborne.networking.TrickPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid=BloodborneMod.MOD_ID, value = Dist.CLIENT)
public class KeybindEvents {
	private static boolean isReloadKeyDown =false;
	private static boolean isTrickKeyDown =false;
	private static boolean isAttackKeyDown =false;
	
	
	@SubscribeEvent
	 public static void trickKeybind(ClientTickEvent event) {
	 if(KeybindInit.trickKeybind.isDown()) {
		 if(isTrickKeyDown==false) {
			 BloodborneModPacketHandler.INSTANCE.sendToServer(new TrickPacket(1));
			 isTrickKeyDown=true;
		 }
	 }else {
		 isTrickKeyDown=false; 
	 }
	 
	 }
	
	@SubscribeEvent
	 public static void reloadKeybind(ClientTickEvent event) {
	 if(KeybindInit.reloadKeybind.isDown()) {
		 if(isReloadKeyDown==false) {
			 BloodborneModPacketHandler.INSTANCE.sendToServer(new ReloadPacket(1));
			 isReloadKeyDown=true;
		 }
	 }else {
		 isReloadKeyDown=false; 
	 }
	 
	 }
	
	@SubscribeEvent
	 public static void performItemAttackAnimation(ClientTickEvent event) {
	 Minecraft instance = Minecraft.getInstance();
	if(instance.options.keyAttack.isDown()) {
		 if(isAttackKeyDown==false) {
			 BloodborneModPacketHandler.INSTANCE.sendToServer(new PerformItemAnimation(1));
			 isAttackKeyDown=true;
		 }
	 }else {
		 isAttackKeyDown=false; 
	 }
	 
	 }
	
	
	
	/*
	
	 @OnlyIn(Dist.CLIENT)
     //@SubscribeEvent
         public static void onPlayerLeftClick(MouseInputEvent event) {
		
		 Minecraft instance = Minecraft.getInstance();
		if (instance.player != null) {
			
			 if (instance.screen == null) {
				 
				
 
      if (event.getButton()==GLFW.GLFW_MOUSE_BUTTON_LEFT&&event.getAction()==GLFW.GLFW_PRESS){
             PerformItemAnimation packet = new PerformItemAnimation(1);
             BloodborneModPacketHandler.INSTANCE.sendToServer(packet);
         } 
				 }
		 }   
		
		
      }
	
	 
	
	
	*/
	
	
	/*
	 //@SubscribeEvent
	    public static void trickKeybind(KeyInputEvent event) {
	    	
	    	if(event.getKey()==KeybindInit.trickKeybind.getKey().getValue()) {
	    		
	    	
	    		if(event.getAction()==GLFW.GLFW_PRESS) {
	    			//System.out.println("lezzo");
	    		BloodborneModPacketHandler.INSTANCE.sendToServer(new TrickPacket(1));
	    		}
	    		
	    	}
	 }
	 
	 //@SubscribeEvent
	 public static void trickKeybindOld(ClientTickEvent event) {
	 if(KeybindInit.trickKeybind.consumeClick()) {
		 BloodborneModPacketHandler.INSTANCE.sendToServer(new TrickPacket(1));
		 KeybindInit.trickKeybind.setDown(false);
	 }
	 
	 }
	 
	*/
	 
	 
}
