package com.nitespring.bloodborne.networking;


import java.util.function.Supplier;

import com.nitespring.bloodborne.common.items.weapons.parent.IAnimatedWeapon;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;


public class PerformItemAnimation {
	        private final int id;
	       
	       
	      

	        public PerformItemAnimation(FriendlyByteBuf buf) {
	            id = buf.readInt();
	         
	        }
	        public PerformItemAnimation(int id) {
	            this.id = id;
	     
	        }
	        public void toBytes(FriendlyByteBuf buf) {
	            buf.writeInt(id);  
	           
	        }
	        public boolean handle(Supplier<NetworkEvent.Context> ctx) {
	            ctx.get().enqueueWork(() -> {
	            	Player playerIn = ctx.get().getSender();
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof IAnimatedWeapon) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.9) {
	    			((IAnimatedWeapon)mainHand.getItem()).leftClickAnimatedAction(playerIn, mainHand);
	    				  }
	    			}
	            	
	           
	            });
	            
	            return true;
	        } 
	      
}

