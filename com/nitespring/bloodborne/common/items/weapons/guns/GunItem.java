package com.nitespring.bloodborne.common.items.weapons.guns;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;
import com.nitespring.bloodborne.common.entities.projectiles.BulletProjectileEntity;
import com.nitespring.bloodborne.common.items.bullets.BulletItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GunItem extends Item{
	
	
	
	
	public int lifeTime;
	public float damage;
	
	public GunItem(float bulletDamage, int bulletLifeTime, Properties p_41383_) {
		super(p_41383_);
		this.damage=bulletDamage;
		this.lifeTime=bulletLifeTime;
	}
	
	
	
	
	
	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		
		ItemStack stack = playerIn.getItemInHand(handIn);
		CompoundTag nbt;
		
        if (stack.hasTag())
	      {
	          nbt = stack.getTag();
	          if(nbt.contains("Charged")) {
	        	  float levelModifier = 1;
	     		 if (nbt.contains("BBLevel")){
	     	    		 int level = nbt.getInt("BBLevel");
	     	    		 levelModifier = levelModifier + level*0.4f;         
	     			}
	        	  
	         BulletItem item = BulletItem.getTypeByTag(nbt.getString("Charged"));
	  		Vec3 aim = playerIn.getLookAngle();
	  		System.out.println("lezzo");
	  		BulletProjectileEntity bullet = new BulletProjectileEntity(BulletItem.getEntityByTag(nbt.getString("Charged")), damage*levelModifier, (int) (lifeTime + item.lifeTime), item, false, playerIn, worldIn);
	  		
	  		bullet.setPos(playerIn.position().x + aim.x*1.25,playerIn.position().y + 1.5,playerIn.position().z + aim.z*1.25);
	  		bullet.xPower = aim.x*0.3;
	  		bullet.yPower = aim.y*0.3;
	  		bullet.zPower = aim.z*0.3;
	  		
	  		worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0f, 1.6f);
	  		worldIn.addFreshEntity(bullet);
	  		nbt.remove("Charged");
	  		playerIn.getCooldowns().addCooldown(stack.getItem(), 20);
	          }
	      }
		
		
		
		
		return super.use(worldIn, playerIn, handIn);
	}
	
	
	
	  @Override
	  @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
		  if (stack.hasTag()&&stack.getTag().contains("Charged"))
	      {
	         
		  String reload = "\u00A78Loaded";
			tooltip.add(new TextComponent(reload));
	      }else {
	    	  String reload = "\u00A78Press R to Reload";
				tooltip.add(new TextComponent(reload)); 
	    	  
	    	  
	      }
		  
		 
					 
		String info = "\u00A78\u00A7oPress shift for more information";
		tooltip.add(new TextComponent(info));
		    
		if(InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			 float levelModifier = 1;
			if (stack.hasTag()&&stack.getTag().contains("BBLevel"))
		      {
		     	   int level = stack.getTag().getInt("BBLevel");
		     	   levelModifier = levelModifier + level*0.4f;         	
		      }
			
			
			String dmg = "\u00A77" 
			        	 + Math.round((damage*levelModifier)  * 100.0 )/ 100.0 
			        	 + " Base Damage";
			tooltip.add(new TextComponent(dmg));
					 
		}
			
		}
	  
	  @Override
	public int getItemStackLimit(ItemStack stack) {
		
		return 1;
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
		public Rarity getRarity(ItemStack p_41461_) {
			
			return Rarity.create("WORKSHOP", ChatFormatting.RED);
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
}
