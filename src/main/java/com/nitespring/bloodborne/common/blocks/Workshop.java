package com.nitespring.bloodborne.common.blocks;

import javax.annotation.Nullable;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.containers.WorkshopContainer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Workshop extends HorizontalDirectionalBlock  {
	
	   private static final Component CONTAINER_TITLE = Component.translatable("container." + BloodborneMod.MOD_ID + ".workshop");

	private static final VoxelShape TO_WEST = Block.box(0.01D, 0.0D, -7.99D, 16.00D, 20.0D, 23.99D);
	   private static final VoxelShape TO_EAST = Block.box(0.01D, 0.0D, -7.99D, 16.00D, 20.0D, 23.99D);
	   private static final VoxelShape TO_NORTH = Block.box(-7.99D, 0.0D, 0.01D, 23.99D, 20.0D, 15.99D);
	   private static final VoxelShape TO_SOUTH = Block.box(-7.99D, 0.0D, 0.01D, 23.99D, 20.0D, 15.99D);
	 public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	 
	 
	  public Workshop(Properties p_i48377_1_) {
			super(p_i48377_1_.noOcclusion());
			this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
		}
	  @Override
		public boolean dropFromExplosion(Explosion p_149659_1_) {
		
			return true;
		}
	  @Nullable
	   @OnlyIn(Dist.CLIENT)
	   public static Direction getWorkshopOrientation(BlockGetter p_220174_0_, BlockPos p_220174_1_) {
	      BlockState blockstate = p_220174_0_.getBlockState(p_220174_1_);
	      return blockstate.getBlock() instanceof Workshop ? blockstate.getValue(FACING) : null;
	   }
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter p_220071_2_, BlockPos pos,
			CollisionContext p_220071_4_) {
		Vec3 vector3d = state.getOffset(p_220071_2_, pos);
		
		
		
		return getVoxelShape(state);
     //return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
		
	}
	
	 private VoxelShape getVoxelShape(BlockState p_220128_1_) {
	      Direction direction = p_220128_1_.getValue(FACING);
	     
	    
	     if (direction == Direction.NORTH) {
	         return TO_NORTH;
	      } else if (direction == Direction.SOUTH) {
	         return TO_SOUTH;
	      } else {
	         return direction == Direction.EAST ? TO_EAST : TO_WEST;
	      }
	   }
	
	@Override
	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
			CollisionContext p_220053_4_) {
		
		return getVoxelShape(p_220053_1_);
		//return SHAPE;
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	@Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
	
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
				//context.getNearestLookingDirection().getOpposite());
	}
	
	@Override
	public MapColor defaultMapColor() {
		
		return MapColor.WOOD;
	
	}
	
	@Override
	public InteractionResult use(BlockState p_225533_1_, Level worldIn, BlockPos pos, Player playerIn, InteractionHand p_225533_5_, BlockHitResult p_225533_6_) {
	      if (worldIn.isClientSide) {
	         return InteractionResult.SUCCESS;
	      } else {
	    	  
		         
	         playerIn.openMenu(p_225533_1_.getMenuProvider(worldIn, pos));
	         
	         //p_225533_4_.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
	       
	         return InteractionResult.CONSUME;
	      }
	   }
	
	//CraftingTableBlock
     @Override
	   public MenuProvider getMenuProvider(BlockState p_220052_1_, Level worldIn, BlockPos pos) {
	      return new SimpleMenuProvider((p_220270_2_, p_220270_3_, p_220270_4_) -> {
	    	  
	    	  return new WorkshopContainer( p_220270_2_, p_220270_3_, ContainerLevelAccess.create(worldIn, pos));
	     }, CONTAINER_TITLE);
	   }
}
