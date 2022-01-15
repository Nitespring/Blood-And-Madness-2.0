package com.nitespring.bloodborne.common.items.weapons.parent;



import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class TrickWeapon extends BloodborneWeapon{
	
	public abstract Item getTransformedWeapon();
	
	
	public TrickWeapon(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
			}
	
	/*@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand p_41434_) {
		
		
		transform(playerIn, worldIn);
		return super.use(worldIn, playerIn, p_41434_);
	}*/
	
	public SoundEvent getEquipSound() { 
		return SoundEvents.ARMOR_EQUIP_CHAIN;
		}
	
	
	public void transform(Player playerIn, Level worldIn) {
		
	
        ItemStack itemstack = playerIn.getItemInHand(InteractionHand.MAIN_HAND);
        Component name = itemstack.getDisplayName();
        CompoundTag compound = itemstack.getTag();
       
        Vec3 pos = playerIn.position();
        
       
        	ItemStack trick = new ItemStack(getTransformedWeapon(), 1, compound).setHoverName(name);
           	playerIn.setItemInHand(InteractionHand.MAIN_HAND, trick);
           	trick.setTag(compound);
    
        	
        	
            playTrickSound(worldIn, pos);
       
      
        }

	
		public void playTrickSound(Level worldIn, Vec3 pos ) {
			
			  worldIn.playSound((Player)null, pos.x, pos.y, pos.z, getEquipSound(), SoundSource.PLAYERS, 0.6f, 0.4f);
			
		}

		  @Override
		  @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
			  
			  
			  String info = "\u00A78\u00A7oPress left_alt to transform";
				tooltip.add(new TextComponent(info));
			  
			  
			  
			  super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
		  }
	

}
