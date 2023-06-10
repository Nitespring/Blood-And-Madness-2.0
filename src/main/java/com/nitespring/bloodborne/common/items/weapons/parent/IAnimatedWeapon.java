package com.nitespring.bloodborne.common.items.weapons.parent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IAnimatedWeapon {
	
	
	
	public abstract void leftClickAnimatedAction(Player playerIn, ItemStack mainHand);

}
