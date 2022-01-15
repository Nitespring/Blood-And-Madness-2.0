package com.nitespring.bloodborne.common.tabs;

import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;




	public class BloodborneDebug extends CreativeModeTab{

		public BloodborneDebug() {
			super("bloodbornedebug");
		
		}

		@Override
		public ItemStack makeIcon() {
			
			return new ItemStack(ItemInit.FIRE.get());
		}

	}

