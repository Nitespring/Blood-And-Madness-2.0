package com.nitespring.bloodborne.core.init;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.effects.ChikageEffect;
import com.nitespring.bloodborne.common.effects.UneffectiveEffect;
import com.nitespring.bloodborne.common.effects.TrappedEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInit {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
			 BloodborneMod.MOD_ID);
	
	
	//MobEffects
	 public static final RegistryObject<MobEffect> TRAPPED =   EFFECTS.register("trapped",
             () -> new TrappedEffect(MobEffectCategory.HARMFUL, 57855)
             .addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", 
             		(double)-1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL)
             .addAttributeModifier(Attributes.FLYING_SPEED, "7107De5E-7CE8-4030-940E-514C1F160890", 
             		(double)-1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL)
             .addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
             		-666.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
             .addAttributeModifier(Attributes.ATTACK_KNOCKBACK, "648d7064-6A60-4F59-8ABE-C2C23A6DD7A9", 
             		-666.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	 
	 public static final RegistryObject<MobEffect> CHIKAGE=EFFECTS.register("chikage",
             () -> new ChikageEffect(MobEffectCategory.HARMFUL, 57855));
	 
	 public static final RegistryObject<MobEffect> FAKE_POISON=EFFECTS.register("fake_poison",
             () -> new UneffectiveEffect(MobEffectCategory.NEUTRAL, 5149489));

}
