package com.nitespring.bloodborne.common.items;

import java.util.List;

import com.nitespring.bloodborne.common.entities.utility.RedJewelEntity;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedJewelItem extends Item{

	public RedJewelItem(Properties p_41383_) {
		super(p_41383_);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack p_41452_) {
		
		return UseAnim.BOW;
	}
	@Override
	public int getUseDuration(ItemStack p_41454_) {
		
		return 60;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player playerIn, InteractionHand handIn) {
		playerIn.startUsingItem(handIn);
		return super.use(p_77659_1_, playerIn, handIn);
		
	}
	
	
	@Override
	public void releaseUsing(ItemStack stackIn, Level worldIn, LivingEntity playerIn, int p_77615_4_) {
		
		
	    	Vec3 pos = playerIn.position();
	    	Vec3 aim = playerIn.getLookAngle();
	       
	    	playerIn.playSound(SoundEvents.BELL_BLOCK, 10.0F, 0.5f);
	    	RedJewelEntity summons = new RedJewelEntity(ProjectileInit.RED_JEWEL.get(), worldIn);
	    	summons.setPos(pos.x + aim.x , pos.y + aim.y-0.5, pos.z + aim.z );

	        worldIn.addFreshEntity(summons);

		if (playerIn instanceof Player){
		((Player) playerIn).getCooldowns().addCooldown(stackIn.getItem(), 15);
		if(!((Player) playerIn).isCreative()) {
			stackIn.shrink(1);
		}
		}
		
		
		super.releaseUsing(stackIn, worldIn, playerIn, p_77615_4_);
	}
	
	
	 @Override
	   @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_77624_2_, List<Component> tooltip,
				TooltipFlag p_77624_4_) {
			
			super.appendHoverText(stack, p_77624_2_, tooltip, p_77624_4_);
			 
				

				 String text = "\u00A75" + "The Sweet Blood... It sings to me!";
				 tooltip.add(Component.literal(text));
				 
				 String info = "\u00A78\u00A7oSummons the old hunter Father Gascoigne";
				 tooltip.add(Component.literal(info));
			    
				 String text1 = "\u00A75\u00A7oIn order to survive the beastly scourge, one must not forget their own past";
				 tooltip.add(Component.literal(text1));
			    	
			    	
			    	
			    }
				 
	  
			
		
	
	 @Override
	 public Rarity getRarity(ItemStack p_77613_1_) {

		 return Rarity.EPIC;
	 } 
	
	
	

}
