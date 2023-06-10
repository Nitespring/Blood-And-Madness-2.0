package com.nitespring.bloodborne.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.init.EffectInit;

public class BloodVial extends Item{

	public BloodVial(Properties p_41383_) {
		super(p_41383_);
		
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
		
		playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, CommonConfig.blood_vial_heal_amplifier.get()));
		playerIn.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 7, 0));
		playerIn.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 7, 0));
		playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 5));
		playerIn.addEffect(new MobEffectInstance(EffectInit.TRAPPED.get(), 3, 0));
		playerIn.playSound(SoundEvents.GENERIC_HURT, 1, 1);
		if(!playerIn.isCreative()) {
			playerIn.getItemInHand(handIn).shrink(1);
		}
		playerIn.getCooldowns().addCooldown(this, 45);
		
		return super.use(levelIn, playerIn, handIn);
	}

	
	@Override
	   @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_77624_2_, List<Component> tooltip,
				TooltipFlag p_77624_4_) {
			
			super.appendHoverText(stack, p_77624_2_, tooltip, p_77624_4_);
			 
				

				 String text = "\u00A75" + "Special blood with curative properties";
				 tooltip.add(Component.literal(text));
				 String info = "\u00A78\u00A7oUse to heal";
				 tooltip.add(Component.literal(info));
				 
				
			    	
			    	
			    	
			    }
	
	@Override
	 public Rarity getRarity(ItemStack p_77613_1_) {

		 return Rarity.RARE;
	 } 
	
	
	
	
}
