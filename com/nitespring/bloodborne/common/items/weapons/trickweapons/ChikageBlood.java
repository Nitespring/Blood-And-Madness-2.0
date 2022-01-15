package com.nitespring.bloodborne.common.items.weapons.trickweapons;

import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChikageBlood extends TrickWeapon{

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
	
	
}
