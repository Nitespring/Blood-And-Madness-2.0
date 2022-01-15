package com.nitespring.bloodborne.networking;


import java.util.function.Supplier;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import com.nitespring.bloodborne.common.items.weapons.guns.GunItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;


public class ReloadPacket {
	        private final int id;
	       
	       
	      

	        public ReloadPacket(FriendlyByteBuf buf) {
	            id = buf.readInt();
	         
	        }
	        public ReloadPacket(int id) {
	            this.id = id;
	     
	        }
	        public void toBytes(FriendlyByteBuf buf) {
	            buf.writeInt(id);  
	           
	        }
	        public boolean handle(Supplier<NetworkEvent.Context> ctx) {
	            ctx.get().enqueueWork(() -> {
	            	Player playerIn = ctx.get().getSender();
	            	ItemStack mainHand = playerIn.getMainHandItem();
	            	ItemStack offHand = playerIn.getOffhandItem();
	            	
	    			if(mainHand.getItem() instanceof GunItem) {
	    				CompoundTag nbt;
	    		         if (mainHand.hasTag())
	    			      {
	    			          nbt = mainHand.getTag();
	    			      }
	    			      else
	    			      {
	    			          nbt = new CompoundTag();
	    			      }
	    				if(!nbt.contains("Charged")) {
	    					System.out.println("lezzo");
	    					nbt.putString("Charged", ((BulletItem)this.findAmmo(playerIn).getItem()).typeTag);
	    				    if(!playerIn.isCreative()) {
	    				    	this.findAmmo(playerIn).shrink(1);
	    				    }
	    					mainHand.setTag(nbt);
	    				}
	    					playerIn.getCooldowns().addCooldown(mainHand.getItem(), 30);
	    					
	    			}else if(offHand.getItem() instanceof GunItem){
	    				CompoundTag nbt;
	    		         if (offHand.hasTag())
	    			      {
	    			          nbt = offHand.getTag();
	    			      }
	    			      else
	    			      {
	    			          nbt = new CompoundTag();
	    			      }
	    					if(!nbt.contains("Charged")) {
	    						System.out.println("lezzo");
	    						nbt.putString("Charged", ((BulletItem)this.findAmmo(playerIn).getItem()).typeTag);
	    						if(!playerIn.isCreative()) {
		    						this.findAmmo(playerIn).shrink(1);
		    						}
	    						offHand.setTag(nbt);
	    					}
	    					playerIn.getCooldowns().addCooldown(offHand.getItem(), 30);
	    					playerIn.level.playSound(null, playerIn.blockPosition(), SoundEvents.LEVER_CLICK, SoundSource.PLAYERS, 1.5f, 1.8f);
	    				
	    			}
	            	
	           
	            });
	            
	            return true;
	        } 
	      
	        
	        private ItemStack findAmmo(Player playerIn){
	        	
	        	if(playerIn.getMainHandItem().getItem() instanceof BulletItem) {
	        		return playerIn.getMainHandItem();
	        	}else if(playerIn.getOffhandItem().getItem() instanceof BulletItem) {
	        		return playerIn.getOffhandItem();
	        	}else {
	        		 for(int i = 0; i < playerIn.getInventory().getContainerSize(); ++i) {
	                     ItemStack itemstack = playerIn.getInventory().getItem(i);
	                     if (itemstack.getItem() instanceof BulletItem) {
	                        return itemstack;
	                     }
	                  }
	        	}
	        	
	        	return null;
	        	
	        }
	        
	        
}

