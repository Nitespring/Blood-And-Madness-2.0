package com.nitespring.bloodborne.common.items.weapons.special;

import java.util.function.Consumer;

import com.nitespring.bloodborne.client.render.items.LitTorchRenderer;
import com.nitespring.bloodborne.common.items.weapons.parent.BloodborneWeapon;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.client.IItemRenderProperties;

public class LitTorch extends BloodborneWeapon{

	public LitTorch(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
		
	}
	
	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IItemRenderProperties() {
			private final BlockEntityWithoutLevelRenderer renderer = new LitTorchRenderer<>();

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return renderer;
			}
		});
	}
	
	
	@Override
	public InteractionResult useOn(UseOnContext p_41297_) {
	      Player player = p_41297_.getPlayer();
	      Level level = p_41297_.getLevel();
	      BlockPos blockpos = p_41297_.getClickedPos();
	      BlockState blockstate = level.getBlockState(blockpos);
	      if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
	         BlockPos blockpos1 = blockpos.relative(p_41297_.getClickedFace());
	         if (BaseFireBlock.canBePlacedAt(level, blockpos1, p_41297_.getHorizontalDirection())) {
	            level.playSound(player, blockpos1, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
	            BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
	            level.setBlock(blockpos1, blockstate1, 11);
	            level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
	            ItemStack itemstack = p_41297_.getItemInHand();
	            if (player instanceof ServerPlayer) {
	               CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos1, itemstack);
	            }

	            return InteractionResult.sidedSuccess(level.isClientSide());
	         } else {
	            return InteractionResult.FAIL;
	         }
	      } else {
	         level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
	         level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
	         level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
	       
	         return InteractionResult.sidedSuccess(level.isClientSide());
	      }
	   }
	
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_,
			InteractionHand p_41401_) {
		
		p_41400_.setSecondsOnFire(10);
		p_41400_.setLastHurtByMob(p_41399_);
		p_41399_.swing(p_41401_);
		
		return super.interactLivingEntity(p_41398_, p_41399_, p_41400_, p_41401_);
	}
	
	
	@Override
	public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
		if(p_41406_ instanceof LivingEntity) {
		if(p_41404_==((LivingEntity)p_41406_).getMainHandItem()||p_41404_==((LivingEntity)p_41406_).getOffhandItem()) {
		p_41405_.playSound(null, p_41406_.blockPosition(), SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 
				p_41406_.level.getRandom().nextFloat() * 0.4F + 0.8F);
		}
		}
		super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
	}

}
