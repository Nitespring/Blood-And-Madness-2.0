package com.nitespring.bloodborne.networking;


import com.nitespring.bloodborne.BloodborneMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;


public class BloodborneModPacketHandler {
	public static SimpleChannel INSTANCE;
	private static final String PROTOCOL_VERSION = "1";
	private static int ID = 0;
    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
    	//public static final SimpleChannel 
    	INSTANCE = NetworkRegistry.newSimpleChannel(
    		    new ResourceLocation(BloodborneMod.MOD_ID, "main"),
    		    () -> PROTOCOL_VERSION,
    		    PROTOCOL_VERSION::equals,
    		    PROTOCOL_VERSION::equals
    		  
    		);
    	
    	 INSTANCE.messageBuilder(TrickPacket.class, nextID())
	        .encoder(TrickPacket::toBytes)
	        .decoder(TrickPacket::new)
	        .consumer(TrickPacket::handle)
	        .add();
    	 INSTANCE.messageBuilder(ReloadPacket.class, nextID())
	        .encoder(ReloadPacket::toBytes)
	        .decoder(ReloadPacket::new)
	        .consumer(ReloadPacket::handle)
	        .add();
    	 INSTANCE.messageBuilder(PerformItemAnimation.class, nextID())
	        .encoder(PerformItemAnimation::toBytes)
	        .decoder(PerformItemAnimation::new)
	        .consumer(PerformItemAnimation::handle)
	        .add();
    	
    }
    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
    
    

}
