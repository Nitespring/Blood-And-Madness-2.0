package com.nitespring.bloodborne.common.items.weapons.trickweapons;

import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;
import com.nitespring.bloodborne.common.customdamage.CustomDamageSource;
import com.nitespring.bloodborne.core.init.EffectInit;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChikageBlood extends TrickWeapon{
	private static int TICK = 0;
	public ChikageBlood(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
	}
	@Override
	public Item getTransformedWeapon() {
		
		return WeaponInit.CHIKAGE.get();
	}
	
	
	@Override
	public Item getWeaponPart() {
		
		return ItemInit.CHIKAGE_PART.get();
	}
	
	@Override
	public SoundEvent getEquipSound() {
		
		return SoundEvents.GENERIC_DRINK;
	}
	
	
	@Override

	public void playTrickSound(Level worldIn, Vec3 pos) {
		
		 worldIn.playSound((Player)null, pos.x, pos.y, pos.z, getEquipSound(), SoundSource.PLAYERS, 0.6f, 2.0f);
		
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean bool) {
		//System.out.println(i);
		if(entity instanceof Player) {
			Player player = (Player)entity;
			if((!player.isCreative())&&(player.getItemInHand(InteractionHand.MAIN_HAND)==stack)) {
				if(!player.hasEffect(EffectInit.CHIKAGE.get())) {
					player.addEffect(new MobEffectInstance(EffectInit.CHIKAGE.get(), 30));
				}
		
				
				/*TICK++;
				System.out.println(TICK);
				if(TICK>=10||TICK<0) {
				    player.hurt(CustomDamageType.BLOOD_DAMAGE, 0.5f);
				    TICK=0;
				}
				*/
			
			}
		}
		super.inventoryTick(stack, level, entity, i, bool);
	}
	
	
}
