package com.nitespring.bloodborne.common.items;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HunterMarkItem extends Item{

	public HunterMarkItem(Properties p_41383_) {
		super(p_41383_);
		
	}
	
	
	//CompassItem
	//NetherPortalBlock
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
	
		teleport(worldIn, playerIn);
		
		playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), 2000);
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
	
	}
	
	

	
	
	public void teleport(Level worldIn, Player playerIn) {
		
		if(worldIn instanceof ServerLevel && playerIn instanceof ServerPlayer) {
			
			BlockPos pos = ((ServerPlayer) playerIn).getRespawnPosition();
			Vec3i pos1 = new Vec3i(worldIn.getLevelData().getXSpawn(), worldIn.getLevelData().getYSpawn(), worldIn.getLevelData().getZSpawn());
			
			ResourceKey<Level> resLevel =((ServerPlayer) playerIn).getRespawnDimension();
			
			
			 ServerLevel serverlevel = ((ServerLevel)worldIn).getServer().getLevel(resLevel);
			 if (serverlevel == null) {
				 return;
		         }
			 
			 if(!playerIn.isPassenger() && !playerIn.isVehicle()) {
				 if(resLevel != playerIn.level().dimension()) {
					 if(serverlevel!=null && playerIn.canChangeDimensions()) {
			
						 if(pos != null) {
				              teleportEntityToDimension(((ServerPlayer) playerIn), pos, resLevel);
						 }else {
							 teleportEntityToOverworld(((ServerPlayer) playerIn), new BlockPos(pos1));
						 }
						 
			      
					 }   
			   
			
			
			 }else if(pos != null) {
		      playerIn.teleportTo(pos.getX(), pos.getY(), pos.getZ());
		      
		     
		   }else {
			   playerIn.teleportTo(pos1.getX(), pos1.getY(), pos1.getZ());
		   }
			
			}
			 }
			 
		
		
	}
	
	
	//Thanks to AbsolemJackdaw
	
	public static void teleportEntityToDimension(ServerPlayer player, BlockPos pos, ResourceKey<Level> dimension) {

        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, dimension))
            return;

        ServerLevel nextWorld = player.getServer().getLevel(dimension);
      
        if (nextWorld != null) {
            nextWorld.getChunk(pos); // make sure the chunk is loaded
            player.teleportTo(nextWorld, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
        }

	}
	
	
	public static void teleportEntityToOverworld(ServerPlayer player, BlockPos pos) {

        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, Level.OVERWORLD))
            return;

        ServerLevel nextWorld = player.getServer().getLevel(Level.OVERWORLD);
      
        if (nextWorld != null) {
            nextWorld.getChunk(pos);
            player.teleportTo(nextWorld, pos.getX(), pos.getY(), pos.getZ(), player.getYRot(), player.getXRot());
        }

	}
	

}
