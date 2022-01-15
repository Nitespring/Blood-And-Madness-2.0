package com.nitespring.bloodborne.common.tabs;

import com.nitespring.bloodborne.core.init.BlockInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;




	public class BloodborneBlocks extends CreativeModeTab{

		public BloodborneBlocks() {
			super("bloodborneblocks");
		
		}

		@Override
		public ItemStack makeIcon() {
			
			return new ItemStack(BlockInit.MERCURY_SOAKED_STONE.get());
		}

	}

