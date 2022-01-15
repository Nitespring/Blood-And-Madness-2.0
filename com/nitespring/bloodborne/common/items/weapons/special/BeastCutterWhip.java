package com.nitespring.bloodborne.common.items.weapons.special;

import java.util.function.Consumer;

import com.nitespring.bloodborne.client.render.items.animatedweapons.BeastCutterGeoRenderer;
import com.nitespring.bloodborne.client.render.items.animatedweapons.ThreadedCaneGeoRenderer;
import com.nitespring.bloodborne.common.items.weapons.parent.IAnimatedWeapon;
import com.nitespring.bloodborne.common.items.weapons.parent.TrickWeapon;
import com.nitespring.bloodborne.core.init.WeaponInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BeastCutterWhip extends TrickWeapon implements IAnimatedWeapon, IAnimatable, ISyncable{
	
	 public static final String CONTROLLER_NAME = "swingController";
	 private static final int SWING = 0;
	 public AnimationFactory factory = new AnimationFactory(this);

	public BeastCutterWhip(float physicalDamageIn, float fireDamageIn, float boltDamageIn, float arcaneDamageIn,
			float bloodDamageIn, float attackSpeedIn, float knockbackIn, boolean isSerrated, boolean isRighteous,
			Properties properties) {
		super(physicalDamageIn, fireDamageIn, boltDamageIn, arcaneDamageIn, bloodDamageIn, attackSpeedIn, knockbackIn,
				isSerrated, isRighteous, properties);
		GeckoLibNetwork.registerSyncable(this);
		
	}
	
	
     private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		
		return PlayState.CONTINUE;
	 }


	@Override
	public void registerControllers(AnimationData data) {
		AnimationController<BeastCutterWhip> controller = new AnimationController<BeastCutterWhip>(this, CONTROLLER_NAME, 1, this::predicate);
		data.addAnimationController(controller);
		
	}
	
	

	

	@Override
	public AnimationFactory getFactory() {
		
		return this.factory;
	}

	@Override
	public void leftClickAnimatedAction(Player playerIn, ItemStack mainHand) {
		Level worldIn = playerIn.level;
		if (!worldIn.isClientSide) {
		
			
			final int id = GeckoLibUtil.guaranteeIDForStack(mainHand, (ServerLevel) worldIn);
			
			final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn);
			GeckoLibNetwork.syncAnimation(target, this, id, SWING);
		}
		
	}
	
	@Override
	public void onAnimationSync(int id, int state) {
		if (state == SWING) {
			final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);
			if (controller.getAnimationState() == AnimationState.Stopped) {
				Minecraft instance = Minecraft.getInstance();
				final LocalPlayer player = instance.player;
				if (player != null) {
					
				}
				
				controller.markNeedsReload();
				
				controller.setAnimation(new AnimationBuilder().addAnimation("animation.beastcutter.swing", false));
				
			}
		}
		
	}
	
	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IItemRenderProperties() {
			private final GeoItemRenderer<BeastCutterWhip> renderer = new BeastCutterGeoRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return renderer;
			}
		});
	}

	@Override
	public Item getTransformedWeapon() {
		
		return WeaponInit.THREADED_CANE_CANE.get();
	}

}
