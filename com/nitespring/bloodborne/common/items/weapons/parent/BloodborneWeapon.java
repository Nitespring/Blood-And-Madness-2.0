package com.nitespring.bloodborne.common.items.weapons.parent;

import java.util.List;
import org.lwjgl.glfw.GLFW;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.platform.InputConstants;
import com.nitespring.bloodborne.core.helpers.AttackDamageCalculators;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BloodborneWeapon extends Item{
	 //private final float attackDamage;
	 private final float attackSpeed;
	 private final float knockback;
	 
	 public final boolean isSerrated;
	 public final boolean isRighteous;
	 
	 private final float physicalDamage;
	 private final float fireDamage;
	 private final float boltDamage;
	 private final float arcaneDamage;
	 private final float bloodDamage;
	
	  private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	 
	 public BloodborneWeapon(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn, float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous, Item.Properties properties) {
	      super(properties);
	      this.physicalDamage = (float)physicalDamageIn;
	      this.fireDamage = (float)fireDamageIn;
	      this.arcaneDamage = (float)arcaneDamageIn;
	      this.boltDamage = (float)boltDamageIn;
	      this.bloodDamage = (float)bloodDamageIn;
	      //this.attackDamage = physicalDamageIn + fireDamageIn + arcaneDamageIn + boltDamageIn + bloodDamageIn;
	      this.attackSpeed = (float)attackSpeedIn;
	      this.knockback = (float)knockbackIn;
	      this.isSerrated = isSerrated;
	      this.isRighteous = isRighteous;
	     
	      Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	      //builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage - 1.0f, AttributeModifier.Operation.ADDITION));
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
		    		          //int level =	stack.getTag().getInt("BBLevel");
		    		          
		    		          
		    		          Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		    		         
					                    //builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 
					                    //		(double)(this.attackDamage*(1+dmgVar*level)) - 1.0f, AttributeModifier.Operation.ADDITION));
					                    		
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
		            tooltip.add(new TextComponent(string));
			}	
		  if (this.isRighteous)
			{
		            String string = "\u00A77\u00A7oRighteous";
		            tooltip.add(new TextComponent(string));
			}		
					 
		String info = "\u00A78\u00A7oPress shift for more information";
		tooltip.add(new TextComponent(info));
		    
		if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			float dmgModifier =	AttackDamageCalculators.calculateDisplayDamageValues(stack);
				
			String dmgPhys = "\u00A77" 
			        		 + Math.round((physicalDamage*dmgModifier)  * 100.0 )/ 100.0 
			        		 + " Physical Damage";
			tooltip.add(new TextComponent(dmgPhys));
					 
			String dmgFire = "\u00A76" 
							 + Math.round((fireDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Fire Damage";
			tooltip.add(new TextComponent(dmgFire));
			
			String dmgLightning = "\u00A7b" 
							 + Math.round((boltDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Bolt Damage";
			tooltip.add(new TextComponent(dmgLightning));
			
			String dmgMagic = "\u00A73" 
							 + Math.round((arcaneDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Arcane Damage";
			tooltip.add(new TextComponent(dmgMagic));
			
			String dmgDark = "\u00A74"
							 + Math.round((bloodDamage*dmgModifier)  * 100.0 )/ 100.0 
							 + " Blood Damage";
			tooltip.add(new TextComponent(dmgDark));
			}	
			
		}
	 @Override
	  public Component getName(ItemStack stack) {
		 if (stack.hasTag() && stack.getTag().contains("BBLevel"))
			{
	    		    int level =	stack.getTag().getInt("BBLevel");
	    		    String string =  " +" + level;
		           
		            return new TextComponent(new TranslatableComponent(this.getDescriptionId(stack)).getString().concat(string));
		            
		            
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
		  }
		  return true;
	}
	  
	

	  
	  
	  @Override
		public int getDamage(ItemStack stack) {

			return 0;
		}
	   
	   @Override
	   public boolean isFoil(ItemStack p_77636_1_) {
		
		   return false;
	   	}
	   
	   @Override
			public int getItemStackLimit(ItemStack stack) {
				
				return 1;
			}
	 
	 @Override
	public Rarity getRarity(ItemStack p_41461_) {
		
		return Rarity.create("WORKSHOP", ChatFormatting.RED);
	}
	   
	 
	 
	 
public Item getWeaponPart() {
	return ItemStack.EMPTY.getItem();
   	 
     }   
	 
	   

}
