package com.nitespring.bloodborne.common.items.weapons.parent;

import java.awt.TextComponent;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.platform.InputConstants;
import com.nitespring.bloodborne.config.CommonConfig;
import com.nitespring.bloodborne.core.helpers.AttackDamageCalculators;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.ComponentArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BloodborneWeapon extends Item{
	 private final float attackDamage;
	 private final float attackSpeed;
	 private final float knockback;
	 
	 public final boolean isSerrated;
	 public final boolean isRighteous;
	 
	 private final float physicalDamage;
	 private final float fireDamage;
	 private final float boltDamage;
	 private final float arcaneDamage;
	 private final float bloodDamage;
	 //private double dmgVar = CommonConfig.upgrade_level_scaling.get();
	 
	 private final int RepairType;
	
	  private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	 
	  public BloodborneWeapon(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn, float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous, Item.Properties properties) {
		  
	  this(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn, isSerrated, isRighteous, 0, properties);
	  }
	 public BloodborneWeapon(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn, float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,int repairType, Item.Properties properties) {
	      super(properties);
	      this.physicalDamage = (float)physicalDamageIn;
	      this.fireDamage = (float)fireDamageIn;
	      this.arcaneDamage = (float)arcaneDamageIn;
	      this.boltDamage = (float)boltDamageIn;
	      this.bloodDamage = (float)bloodDamageIn;
	      this.attackDamage = physicalDamageIn + fireDamageIn + arcaneDamageIn + boltDamageIn + bloodDamageIn;
	      this.attackSpeed = (float)attackSpeedIn;
	      this.knockback = (float)knockbackIn;
	      this.isSerrated = isSerrated;
	      this.isRighteous = isRighteous;
	      this.RepairType = repairType;
	     
	      Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	      builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage - 1.0f, AttributeModifier.Operation.ADDITION));
	      builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed - 4.0f, AttributeModifier.Operation.ADDITION));
	      this.defaultModifiers = builder.build();
	   }
	 @Override
	   public boolean canAttackBlock(BlockState p_195938_1_, Level p_195938_2_, BlockPos p_195938_3_, Player p_195938_4_) {
	      return !p_195938_4_.isCreative();
	   }
     
	
	  @Override
			public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
				   
	             
		    	if (stack.hasTag() && stack.getTag().contains("BBLevel"))
				{
		    		          int level =	stack.getTag().getInt("BBLevel");
		    		          
		    		          
		    		          Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		    		         
					                    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 
					                    		(double)(this.attackDamage*(1+CommonConfig.upgrade_level_scaling.get()*level)) - 1.0f, AttributeModifier.Operation.ADDITION));
					                    		
					                    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", 
					                    		(double)this.attackSpeed - 4.0f, AttributeModifier.Operation.ADDITION));
					                    Multimap<Attribute, AttributeModifier> defaultModifiers = builder.build();
				      
				      
				   
					                    return slot == EquipmentSlot.MAINHAND 
				   
					                    		? defaultModifiers : super.getAttributeModifiers(slot, stack);
		    		      
				}else {
				  	
					return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
				     
				    } 
		   }
	 
	  
	  @Override
	  @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
		
		   
		  if (this.isSerrated)
			{
		            String string = "\u00A77\u00A7oSerrated";
		            tooltip.add(Component.literal(string));
			}	
		  if (this.isRighteous)
			{
		            String string = "\u00A77\u00A7oRighteous";
		            tooltip.add(Component.literal(string));
			}		
					 
		String info = "\u00A78\u00A7oPress shift for more information";
		tooltip.add(Component.literal(info));
		    
		if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			float dmgModifier =	AttackDamageCalculators.calculateDisplayDamageValues(stack);
				
			String dmgPhys = "\u00A77" 
			        		 + Math.round((physicalDamage*dmgModifier)  * 100.0 )/ 100.0 
			        		 + " Physical Damage";
			tooltip.add(Component.literal(dmgPhys));
					 
			String dmgFire = "\u00A76" 
							 + Math.round((fireDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Fire Damage";
			tooltip.add(Component.literal(dmgFire));
			
			String dmgLightning = "\u00A7b" 
							 + Math.round((boltDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Bolt Damage";
			tooltip.add(Component.literal(dmgLightning));
			
			String dmgMagic = "\u00A73" 
							 + Math.round((arcaneDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Arcane Damage";
			tooltip.add(Component.literal(dmgMagic));
			
			String dmgDark = "\u00A74"
							 + Math.round((bloodDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Blood Damage";
			tooltip.add(Component.literal(dmgDark));
			}	
			
		}
	 @Override
	  public Component getName(ItemStack stack) {
		 if (stack.hasTag() && stack.getTag().contains("BBLevel"))
			{
	    		    int level =	stack.getTag().getInt("BBLevel");
	    		    String string =  " +" + level;
		           
		            return Component.translatable(this.getDescriptionId()).append(string);
		            
		            
			}else {	
		 
		 
	      return super.getName(stack);
			}
	   }
	  
	
	  
	  
	  @Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		   if (player.getAttackStrengthScale(0)>=0.4) {
		AttackDamageCalculators.calculateAttackDamage(stack, player, entity, physicalDamage, fireDamage, boltDamage, arcaneDamage, bloodDamage, knockback);
		if(fireDamage>0) {
			entity.setSecondsOnFire(1);
		}
		if(!player.isCreative()) {
			   stack.hurtAndBreak(1, player, (p_43296_) -> {
			         p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			      });
		  }
		   
		   
		   }
		  return true;
	}
	  
	

		@Override
	public int getMaxDamage(ItemStack stack) {
		
		return CommonConfig.weapon_durability.get();
	}
	  
	@Override
	public boolean isDamageable(ItemStack stack) {
		
		return true;
	}
	   
	   @Override
	   public boolean isFoil(ItemStack p_77636_1_) {
		
		   return false;
	   	}
	   
	   @Override
	public int getMaxStackSize(ItemStack stack) {

		   return 1;
	}
	   
	 
	 @Override
	public Rarity getRarity(ItemStack p_41461_) {
		
		return Rarity.create("WORKSHOP", ChatFormatting.RED);
	}
	   @Override
	 public boolean isCorrectToolForDrops(BlockState p_43298_) {
	      return p_43298_.is(Blocks.COBWEB);
	   }
	   
	   //SwordItem
	   @Override
	   public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
		      if (p_43289_.is(Blocks.COBWEB)) {
		         return 15.0F;
		      } else return super.getDestroySpeed(p_43288_, p_43289_); /*else {
		         Material material = p_43289_.get.getMaterial();
		         return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !p_43289_.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
		      }*/
		   }
	   
	   @Override
	   public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
		      p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
		         p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		      });
		      return true;
		   }
       @Override
		public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
		      if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
		         p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
		            p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		         });
		      }

		      return true;
		   }
	 
public Item getWeaponPart() {
	return ItemStack.EMPTY.getItem();
   	 
     }   
	 
	   @Override
	public boolean isEnchantable(ItemStack p_41456_) {
		
		return true;
	}
	  @Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		
		return true;
	}
	  

@Override
public int getEnchantmentValue(ItemStack stack) {

	return this.getEnchantmentValue();
}

	@Override
	public int getEnchantmentValue() {

		return 22;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		
		if(enchantment.category==EnchantmentCategory.BREAKABLE||enchantment.category==EnchantmentCategory.VANISHABLE||enchantment.category==EnchantmentCategory.WEAPON) {
			return true;
		}
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack p_41402_, ItemStack p_41403_) {
		switch(RepairType)
		{
		case(1):
			return p_41403_.getItem()==ItemInit.SIDERITE_INGOT.get() ||super.isValidRepairItem(p_41402_, p_41403_);
		case(2):
			return p_41403_.getItem()==Items.STICK ||super.isValidRepairItem(p_41402_, p_41403_);
		default:
			return p_41403_.getItem()==ItemInit.STEEL_INGOT.get() ||super.isValidRepairItem(p_41402_, p_41403_);
		
		}
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {

		return true;
	}
	
	@Override
	public boolean isFireResistant() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	//SwordItem
	//BowItem
	  
}
