package com.nitespring.bloodborne.common.items;

import java.util.List;

import com.nitespring.bloodborne.core.init.ItemInit;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShardItem extends Item{

	public ShardItem(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	   @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_77624_2_, List<Component> tooltip,
				TooltipFlag p_77624_4_) {
			
			super.appendHoverText(stack, p_77624_2_, tooltip, p_77624_4_);
			 
			     String level = "3";
			     
			     if(stack.getItem()==ItemInit.BLOOD_ROCK.get()) {level = "10";}
			     if(stack.getItem()==ItemInit.BLOOD_STONE_CHUNK.get()) {level = "9";}
			     if(stack.getItem()==ItemInit.TWIN_BLOOD_STONE_SHARDS.get()) {level = "6";}

				 String text = "\u00A75" + "Used at the Hunter's Workshop, in quantity of 3, 5 or 8, to Upgrade Weapons to +" + level;
				 tooltip.add(Component.literal(text));
				 
				 
			    	
			    	
			    	
			    }
	
	@Override
	 public Rarity getRarity(ItemStack p_77613_1_) {

		return Rarity.create("BLOOD_STONE", ChatFormatting.DARK_RED);
	 } 

}
