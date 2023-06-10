package com.nitespring.bloodborne.networking;


import java.util.function.Supplier;


import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;


public class TrickPacket {
	        private final int id;
	       
	       
	      

	        public TrickPacket(FriendlyByteBuf buf) {
	            id = buf.readInt();
	         
	        }
	        public TrickPacket(int id) {
	            this.id = id;
	     
	        }
	        public void toBytes(FriendlyByteBuf buf) {
	            buf.writeInt(id);  
	           
	        }
	        public boolean handle(Supplier<NetworkEvent.Context> ctx) {
	            ctx.get().enqueueWork(() -> {
	            	Player playerIn = ctx.get().getSender();
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof TrickWeapon) {
	    			
	    			((TrickWeapon)mainHand.getItem()).transform(playerIn, playerIn.level());
	    				
	    			}
	            	
	           
	            });
	            
	            return true;
	        } 
	      
}

