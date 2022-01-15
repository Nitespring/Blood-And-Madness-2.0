package com.nitespring.bloodborne.common.tabs;

import com.nitespring.bloodborne.core.init.ItemInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;




	public class BloodborneEntities extends CreativeModeTab{

		public BloodborneEntities() {
			super("bloodborneentities");
		
		}

		@Override
		public ItemStack makeIcon() {
			
			return new ItemStack(ItemInit.FATHER_GASCOIGNE_SPAWN_EGG.get());
		}

	}

