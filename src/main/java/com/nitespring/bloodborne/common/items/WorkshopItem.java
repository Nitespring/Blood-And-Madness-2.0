package com.nitespring.bloodborne.common.items;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WorkshopItem extends BlockItem{

	public WorkshopItem(Block p_40565_, Properties p_40566_) {
		super(p_40565_, p_40566_);
	
	
	}
	
	@Override
	   @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_77624_2_, List<Component> tooltip,
				TooltipFlag p_77624_4_) {
			
			super.appendHoverText(stack, p_77624_2_, tooltip, p_77624_4_);
			 
				

				 String text = "\u00A75" + "Used to upgrade weapons using Blood Stones";
				 tooltip.add(Component.literal(text));
				 
				 
			    	
			    	
			    	
			    }
	
	@Override
	 public Rarity getRarity(ItemStack p_77613_1_) {

		 return Rarity.UNCOMMON;
	 } 
	
	

}
