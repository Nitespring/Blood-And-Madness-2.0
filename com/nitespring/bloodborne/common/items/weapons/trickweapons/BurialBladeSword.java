package com.nitespring.bloodborne.common.items.weapons.trickweapons;

import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;
import com.nitespring.bloodborne.core.init.ItemInit;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.world.item.Item;

public class BurialBladeSword extends TrickWeapon{

	public BurialBladeSword(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
	}
	@Override
	public Item getTransformedWeapon() {
		
		return WeaponInit.BURIAL_BLADE_EXTENDED.get();
	}
	
	
	@Override
	public Item getWeaponPart() {
		
		return ItemInit.BURIAL_BLADE_PART.get();
	}
	
	
	
}
