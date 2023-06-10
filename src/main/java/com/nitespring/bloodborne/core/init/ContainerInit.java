package com.nitespring.bloodborne.core.init;

import java.util.function.Supplier;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.containers.WorkshopContainer;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerInit {
	
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
			 BloodborneMod.MOD_ID);
	
	
	
	public static final RegistryObject<MenuType<WorkshopContainer>> WORKSHOP = CONTAINERS.register("screens/workshop",
			()->new MenuType<WorkshopContainer>(WorkshopContainer::new, null));

}
