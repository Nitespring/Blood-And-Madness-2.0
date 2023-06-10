package com.nitespring.bloodborne.common.items.weapons.special;

import java.util.function.Consumer;

import com.nitespring.bloodborne.client.render.items.animatedweapons.ThreadedCaneGeoRenderer;
import com.nitespring.bloodborne.common.items.weapons.parent.IAnimatedWeapon;
import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.network.GeckoLibNetwork;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ThreadedCaneWhip extends TrickWeapon implements IAnimatedWeapon, GeoItem{
	private static final RawAnimation ANIM = RawAnimation.begin().thenPlay("animation.threaded_cane_trick.swing");
	 public static final String CONTROLLER_NAME = "swingController";
	 private static final int SWING = 0;
	 protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

	public ThreadedCaneWhip(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
		
	}
	
	
     private <P extends Item & GeoAnimatable> PlayState predicate(AnimationState<P> event) {
		
		return PlayState.CONTINUE;
	 }


	@Override
	public void registerControllers(ControllerRegistrar data) {
		AnimationController<ThreadedCaneWhip> controller = new AnimationController<ThreadedCaneWhip>(this, CONTROLLER_NAME, 1, state -> PlayState.STOP)
				.triggerableAnim("swing", ANIM);
		data.add(controller);
		
	}
	
	

	

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	@Override
	public void leftClickAnimatedAction(Player playerIn, ItemStack mainHand) {
		Level worldIn = playerIn.level();
		
		
		if (worldIn instanceof ServerLevel serverLevel) {
			triggerAnim(playerIn, GeoItem.getOrAssignId(playerIn.getItemInHand(InteractionHand.MAIN_HAND), serverLevel), CONTROLLER_NAME, "swing");
		}
		
	
		
	}
	
	
	
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			private final GeoItemRenderer<ThreadedCaneWhip> renderer = new ThreadedCaneGeoRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		});
	}

	@Override
	public Item getTransformedWeapon() {
		
		return WeaponInit.THREADED_CANE_CANE.get();
	}

}
