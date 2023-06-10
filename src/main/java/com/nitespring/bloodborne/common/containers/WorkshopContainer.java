package com.nitespring.bloodborne.common.containers;

import java.awt.Container;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.items.ShardItem;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;
import com.nitespring.bloodborne.core.init.BlockInit;
import com.nitespring.bloodborne.core.init.ContainerInit;
import com.nitespring.bloodborne.core.init.ItemInit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WorkshopContainer extends AbstractContainerMenu{
	
	
	//AnvilMenu
	
	   private final ContainerLevelAccess access;
	public static final TranslatableContents TITLE = new TranslatableContents("container." + BloodborneMod.MOD_ID + ".workshop", null, null);
	
	 private Runnable slotUpdateListener = () -> {
	   };
	 private final Slot weaponSlot;
	   private final Slot shardSlot;
	   private final Slot resultSlot;
	   private final net.minecraft.world.Container inputContainer = new SimpleContainer(3) {
		      public void setChanged() {
		         super.setChanged();
		         WorkshopContainer.this.slotsChanged(this);
		         WorkshopContainer.this.slotUpdateListener.run();
		      }
		   };
		   private final net.minecraft.world.Container outputContainer = new SimpleContainer(1) {
		      public void setChanged() {
		         super.setChanged();
		         WorkshopContainer.this.slotUpdateListener.run();
		      }
		   };
	//LoomContainer
	//EnchantmentContainer
		   
		   
	
		   
     public WorkshopContainer(int a, Inventory inv) {
			      this(a, inv, ContainerLevelAccess.NULL);
			   
     }
	public WorkshopContainer(int a,Inventory inv, final ContainerLevelAccess posCal) {
		super(ContainerInit.WORKSHOP.get(), a);
		 this.access = posCal;
		this.weaponSlot = this.addSlot(new Slot(this.inputContainer, 1, 45, 23) {
	         public boolean mayPlace(ItemStack stack) {
	            return stack.getItem() instanceof BloodborneWeapon;
	         }
	      });
	      this.shardSlot = this.addSlot(new Slot(this.inputContainer, 2, 45, 76) {
	         public boolean mayPlace(ItemStack stack1) {
	            return stack1.getItem() instanceof ShardItem || (stack1.getItem() instanceof BlockItem &&((BlockItem)stack1.getItem()) == Items.BEDROCK);
	         }
	      });
	    
	      this.resultSlot = this.addSlot(new Slot(this.outputContainer, 0, 115, 49) {
	         public boolean mayPlace(ItemStack stack2) {
	            return false;
	         }
	         
	         public void onTake(Player playerIn, ItemStack stack3) {
	        	 CompoundTag nbt;
	        	 ItemStack weaponStack = weaponSlot.getItem();
		         ItemStack shardStack = shardSlot.getItem();
		         int quantity = shardStack.getCount();
		         if (weaponStack.hasTag())
			      {
			          nbt = weaponStack.getTag();
			      }
			      else
			      {
			          nbt = new CompoundTag();
			      }
		         if (nbt.contains("BBLevel"))
			      {
			    	  int i = nbt.getInt("BBLevel");
			    	  //ItemStack needed = getMaterialByLevel(i);
			    	  int cost = getCostbyLevel(i);
			    	  //if(shardStack==needed || quantity == cost) {
			    	  if(quantity >= cost) {
			    		  WorkshopContainer.this.shardSlot.remove(cost);
			    	  }
			      }
			      else
			      {
			    	  int cost = getCostbyLevel(0);
			    	  if(quantity >= cost) {	
			    		  WorkshopContainer.this.shardSlot.remove(cost);

			    	  }
			      }
	        	 
	        	 
	        	 
	        	 
	        	 
	        	 WorkshopContainer.this.weaponSlot.remove(1);
	        	 
	        
	             //return super.onTake(playerIn, stack3);
	          }
	       });
	      for(int z = 0; z < 3; ++z) {
	          for(int j = 0; j < 9; ++j) {
	             this.addSlot(new Slot(inv, j + z * 9 + 9, 8 + j * 18, 116 + z * 18));
	          }
	       }

	       for(int k = 0; k < 9; ++k) {
	          this.addSlot(new Slot(inv, k, 8 + k * 18, 174));
	       }
	}
	
	
	
	
	
	   @Override
	   public void slotsChanged(net.minecraft.world.Container p_75130_1_) {
		      ItemStack itemstack = this.weaponSlot.getItem();
		      ItemStack itemstack1 = this.shardSlot.getItem();
		      
		      ItemStack itemstack3 = this.resultSlot.getItem();
		      
		      if (!itemstack.isEmpty() && !itemstack1.isEmpty() ) {
		        
		          //this.setupResultSlot();
		         
		      } else {
		         this.resultSlot.set(ItemStack.EMPTY);
		         
		      }

		      this.setupResultSlot();
		      this.broadcastChanges();
		   }

	

	@Override
	public boolean stillValid(Player playerIn) {
		
		return  stillValid(this.access, playerIn, BlockInit.WORKSHOP.get());
	}
	
	@Override
	public boolean clickMenuButton(Player p_75140_1_, int p_75140_2_) {
	    
	         
	         this.setupResultSlot();
	         return true;
	      
	       
	   }
	
	 private void setupResultSlot() {
	    
	         ItemStack weaponStack = this.weaponSlot.getItem();
	         ItemStack shardStack = this.shardSlot.getItem();
	         int quantity = shardStack.getCount();
	         ItemStack resultStack = ItemStack.EMPTY;
	         Component name = weaponStack.getDisplayName();
	         Item upgradeItem = weaponStack.getItem();
	         
	         if (!weaponStack.isEmpty() && !shardStack.isEmpty()) {
	        	  CompoundTag nbt;
	         if (weaponStack.hasTag())
		      {
		          nbt = weaponStack.getTag();
		      }
		      else
		      {
		          nbt = new CompoundTag();
		      }
	         CompoundTag nbtresult = nbt.copy();
	         if (nbtresult.contains("BBLevel"))
		      {
		    	  int i = nbtresult.getInt("BBLevel");
		    	  ItemStack needed = this.getMaterialByLevel(i);
		    	  int cost = this.getCostbyLevel(i);
		    	  
		    	  
		    	  
		    	  if(shardStack.getItem()==needed.getItem() && quantity >= cost) {
		    		 
		    		  nbtresult.remove("BBLevel");
			    	  nbtresult.putInt("BBLevel", i + 1);
			    	  ItemStack upgrade = new ItemStack(upgradeItem, 1, nbtresult).setHoverName(name);
				      resultSlot.set(upgrade);
				      upgrade.setTag(nbtresult);
		    	 
		    	  }
		      }
		      else
		      {
		    	  
		    	  if(shardStack.getItem()==this.getMaterialByLevel(0).getItem()&&quantity >= this.getCostbyLevel(0)) {
		    		 	
		    	  nbtresult.putInt("BBLevel", 1);
		    	  ItemStack upgrade = new ItemStack(upgradeItem, 1, nbtresult).setHoverName(name);
			      resultSlot.set(upgrade);
			      upgrade.setTag(nbtresult);
		    	  
		    	  
		      } 
		      }
	         
	         } 
	         
	 }
	 
	 public ItemStack getMaterialByLevel(int level) {
		   if(level<=2) {
			   return new ItemStack(ItemInit.BLOOD_STONE_SHARD.get());
		   }
		   else if(level>=3&&level<=5) {
			   return new ItemStack(ItemInit.TWIN_BLOOD_STONE_SHARDS.get());
		   }
		   else if(level>=6&&level<=8) {
			   return new ItemStack(ItemInit.BLOOD_STONE_CHUNK.get());
		   }
		   else if(level==9) {
			   return new ItemStack(ItemInit.BLOOD_ROCK.get()); 
		   }else {
		return new ItemStack(Items.BEDROCK);
		   }
		   }
		   
		   public int getCostbyLevel(int level) {
			   if(level<=0||level==3||level==6) {
				   return 3;
			   }
			   else if(level==1||level==4||level==7) {
				   return 5;
			   }
			   else if(level==2||level==5||level==8) {
				   return 8;
			   }
			   else if(level==9) {
				   return 1; 
			   }else {
				   return 1; 
			  
			   } 
			   
		   }
	         
	 
	 @OnlyIn(Dist.CLIENT)
	   public Slot getWeaponSlot() {
	      return this.weaponSlot;
	   }

	   @OnlyIn(Dist.CLIENT)
	  public Slot getShardSlot() {
	      return this.shardSlot;
	   }
	   
	   @OnlyIn(Dist.CLIENT)
	   public Slot getResultSlot() {
	      return this.resultSlot;
	   }
	  
	   
	   

	   @OnlyIn(Dist.CLIENT)
	   public void registerUpdateListener(Runnable p_217020_1_) {
	      this.slotUpdateListener = p_217020_1_;
	   }
	   
	   
	   
      @Override
	   public ItemStack quickMoveStack(Player playerIn, int i) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.slots.get(i);
	      if (slot != null && slot.hasItem()) {
	         ItemStack itemstack1 = slot.getItem();
	         itemstack = itemstack1.copy();
	         if (i == this.resultSlot.index) {
	            if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
	               return ItemStack.EMPTY;
	            }

	            slot.onQuickCraft(itemstack1, itemstack);
	         } else if (i != this.weaponSlot.index && i != this.shardSlot.index) {
	            if (itemstack1.getItem() instanceof BloodborneWeapon) {
	               if (!this.moveItemStackTo(itemstack1, this.weaponSlot.index, this.weaponSlot.index + 1, false)) {
	                  return ItemStack.EMPTY;
	               }
	            } else if (itemstack1.getItem() instanceof ShardItem) {
	               if (!this.moveItemStackTo(itemstack1, this.shardSlot.index, this.shardSlot.index + 1, false)) {
	                  return ItemStack.EMPTY;
	               }
	            } else if (i >= 4 && i < 30) {
	               if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
	                  return ItemStack.EMPTY;
	               }
	            } else if (i >= 30 && i < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.set(ItemStack.EMPTY);
	         } else {
	            slot.setChanged();
	         }

	         if (itemstack1.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         slot.onTake(playerIn, itemstack1);
	      }

	      return itemstack;
	   }
@Override
	   public void removed(Player p_75134_1_) {
	      super.removed(p_75134_1_);
	      this.access.execute((p_217028_2_, p_217028_3_) -> {
	         this.clearContainer(p_75134_1_, this.inputContainer);
	      });
	   }


	   //AnvilBlock
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
		   
		   }
	 
	 

