package com.nitespring.bloodborne.common.items.magic;

import java.util.UUID;

import com.nitespring.bloodborne.common.entities.projectiles.AugurOfEbrietasEntity;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AugurOfEbrietasItem extends Item{

	public AugurOfEbrietasItem(Properties p_41383_) {
		super(p_41383_);
	}
	
	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack stackIn = playerIn.getItemInHand(handIn);
		
		
		
		
		if(!playerIn.isUsingItem()) {

		Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();
		
		
		AugurOfEbrietasEntity tentacle = new AugurOfEbrietasEntity(ProjectileInit.AUGUR_OF_EBRIETAS.get(), worldIn, 7.0f);
		
		tentacle.setPos(pos.x+0.75*aim.x, pos.y+1.25+0.75*aim.y, pos.z+0.75*aim.z);
		tentacle.setXRot(playerIn.getViewXRot(1.0f)+90);
		tentacle.setYRot(180-playerIn.getViewYRot(1.0f));
		tentacle.setAim(aim);
		tentacle.setOwner(playerIn);
		
		worldIn.addFreshEntity(tentacle);
		
		
		playerIn.startUsingItem(handIn);
		
		return InteractionResultHolder.consume(stackIn);
		
		}else {
			
			return InteractionResultHolder.pass(stackIn);
		}

		
		}
	
	
	@Override
	public int getUseDuration(ItemStack stackIn) {
		
		return 100;
	}
	
	@Override
	public void releaseUsing(ItemStack stackIn, Level worldIn, LivingEntity playerIn, int p_41415_) {
		
		super.releaseUsing(stackIn, worldIn, playerIn, p_41415_);
		
		((Player)playerIn).getCooldowns().addCooldown(stackIn.getItem(), 40);
	}
	
	@Override
	public int getMaxStackSize(ItemStack stack) {
		
		return 1;
	}
	
	
	//BowItem
	
	
	
	
	/*
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		
		
		
		ItemStack stackIn = playerIn.getItemInHand(handIn);
		Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();
		
		
		if(!(stackIn.hasTag()&&stackIn.getTag().hasUUID("Tentacle"))) {
		
		AugurOfEbrietasEntity tentacle = new AugurOfEbrietasEntity(ProjectileInit.AUGUR_OF_EBRIETAS.get(), worldIn);
		
		tentacle.setPos(pos.x+0.5*aim.x, pos.y+1.5+0.5*aim.y, pos.z+0.5*aim.z);
		
		worldIn.addFreshEntity(tentacle);
		
		
		if(stackIn.hasTag()) {
		stackIn.getTag().putUUID("Tentacle", tentacle.getUUID());
		}else {
		CompoundTag tag = new CompoundTag();
		tag.putUUID("Tentacle", tentacle.getUUID());
		stackIn.setTag(tag);
		
		
		}
		
		
		playerIn.startUsingItem(handIn);
		
		
		}
		
		
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stackIn);
		
		
	}
	
	
	
	
	@Override
	public void onUseTick(Level worldIn, LivingEntity playerIn, ItemStack stackIn, int useTick) {
		
		Vec3 pos = playerIn.position();
		Vec3 aim = playerIn.getLookAngle();

		UUID tentacleUUID = stackIn.getTag().getUUID("Tentacle");
		
		AugurOfEbrietasEntity tentacle = (AugurOfEbrietasEntity)(((ServerLevel)worldIn).getEntity(tentacleUUID));
		
		tentacle.setPos(pos.x+0.5*aim.x, pos.y+1.5+0.5*aim.y, pos.z+0.5*aim.z);
		
	}
	
	@Override
	public void releaseUsing(ItemStack stackIn, Level worldIn, LivingEntity playerIn, int i) {
		
		UUID tentacleUUID = stackIn.getTag().getUUID("Tentacle");
		
		AugurOfEbrietasEntity tentacle = (AugurOfEbrietasEntity)(((ServerLevel)worldIn).getEntity(tentacleUUID));
		
		stackIn.removeTagKey("Tentacle");;
		tentacle.remove(RemovalReason.DISCARDED);
		
		super.releaseUsing(stackIn, worldIn, playerIn, i);
	}
	*/
	
	
	

}
