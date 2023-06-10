package com.nitespring.bloodborne.core.init;


import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.blocks.Workshop;
import com.nitespring.bloodborne.common.items.WorkshopItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			 BloodborneMod.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			 BloodborneMod.MOD_ID);
	
	
	public static final RegistryObject<Workshop> WORKSHOP = BLOCKS.register("workshop",
			()->new Workshop(Block.Properties.of()
					.strength(5.5F, 12.0F).sound(SoundType.WOOD)
					.explosionResistance(2)
				));
	
	
	public static final RegistryObject<Block> MERCURY_SOAKED_STONE = BLOCKS.register("world/mercury_soaked_stone",
			()->new Block(Block.Properties.of()
					.strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()
				));
	public static final RegistryObject<Block> QUICKSILVER_SOAKED_NIGHTMARE_STONE = BLOCKS.register("world/quicksilver_soaked_nightmare_stone",
			()->new Block(Block.Properties.of()
					.strength(5.5F, 12.0F).sound(SoundType.STONE)
					.requiresCorrectToolForDrops().explosionResistance(2)
				));
	public static final RegistryObject<Block> NIGHTMARE_STONE = BLOCKS.register("world/nightmare_stone",
			()->new Block(Block.Properties.of()
					.strength(5.5F, 12.0F).sound(SoundType.STONE)
					.requiresCorrectToolForDrops().explosionResistance(2)
				));
	public static final RegistryObject<Block> ROUGH_NIGHTMARE_STONE = BLOCKS.register("world/rough_nightmare_stone",
			()->new Block(Block.Properties.of()
					.strength(5.5F, 12.0F).sound(SoundType.STONE)
					.requiresCorrectToolForDrops().explosionResistance(2)
				));
	public static final RegistryObject<Block> TILED_NIGHTMARE_STONE = BLOCKS.register("world/tiled_nightmare_stone",
			()->new Block(Block.Properties.of()
					.strength(5.5F, 12.0F).sound(SoundType.STONE)
					.requiresCorrectToolForDrops().explosionResistance(2)
				));
	
	//RenderType.
    //BlockItems
	public static final RegistryObject<BlockItem> WORKSHOP_ITEM = ITEMS.register("blocks/workshop_item", 
			() -> new WorkshopItem(WORKSHOP.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> QUICKSILVER_ORE = ITEMS.register("blocks/mercury_soaked_stone_item", 
			() -> new BlockItem(MERCURY_SOAKED_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> QUICKSILVER_ORE_NIGHTMARE = ITEMS.register("blocks/quicksilver_soaked_nightmare_stone_item", 
			() -> new BlockItem(QUICKSILVER_SOAKED_NIGHTMARE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> NIGHTMARE_STONE_ITEM = ITEMS.register("blocks/nightmare_stone_item", 
			() -> new BlockItem(NIGHTMARE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> ROUGH_NIGHTMARE_STONE_ITEM = ITEMS.register("blocks/rough_nightmare_stone_item", 
			() -> new BlockItem(ROUGH_NIGHTMARE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<BlockItem> TILED_NIGHTMARE_STONE_ITEM = ITEMS.register("blocks/tiled_nightmare_stone_item", 
			() -> new BlockItem(TILED_NIGHTMARE_STONE.get(), new Item.Properties()));
	

}
