package com.nitespring.bloodborne.common.tabs;

import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;




	public class BloodborneWeapons extends CreativeModeTab{

		public BloodborneWeapons() {
			super("bloodborneweapons");
		
		}

		@Override
		public ItemStack makeIcon() {
			
			return new ItemStack(WeaponInit.BURIAL_BLADE_EXTENDED.get());
		}

	}

