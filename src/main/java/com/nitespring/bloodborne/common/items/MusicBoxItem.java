package com.nitespring.bloodborne.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


import com.nitespring.bloodborne.client.render.items.MusicBoxGeoRenderer;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractBloodborneEntity;
import com.nitespring.bloodborne.core.init.CourtOfAzathoth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.example.registry.SoundRegistry;
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
import software.bernie.geckolib.util.ClientUtils;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MusicBoxItem extends Item implements GeoItem {
	private static final RawAnimation ANIM = RawAnimation.begin().thenPlay("animation.music_box.play_new");
	private static final String CONTROLLER_NAME = "controller";
	private static final int ANIM_PLAY = 0;


	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

	public MusicBoxItem(Properties properties) {
		super(properties);
		//GeckoLibNetwork.registerSyncedAnimatable(this);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}

	//JackInTheBoxItem
	
	
	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			private final BlockEntityWithoutLevelRenderer renderer = new MusicBoxGeoRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		});
	}

	private <P extends Item & GeoAnimatable> PlayState predicate(AnimationState<P> event) {
		
		
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(ControllerRegistrar data) {
		AnimationController controller = new AnimationController<>(this, CONTROLLER_NAME, 1, state -> PlayState.STOP)
				.triggerableAnim("music_play", ANIM);
				
				
		data.add(controller);
	}


	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.factory;
	}

	
	
	
	
	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		player.playSound(SoundEvents.LEVER_CLICK, 0.4f, 0.4f);
		
	    this.doShit(world, player, hand);
		return super.use(world, player, hand);
	}
	
	
	public void doShit(Level world, Player player, InteractionHand hand) {
		Vec3 pos = player.position();
		double d = 50;
		AABB scanAbove = new AABB(pos.x-d, pos.y - 4*d, pos.z- d, pos.x+ d, pos.y + 2*d, pos.z+ d);
		List<AbstractBloodborneEntity> entities = new ArrayList<>(world.getEntitiesOfClass(AbstractBloodborneEntity.class, scanAbove));
		for(int n = 0; n < entities.size(); n++) {
			
			AbstractBloodborneEntity target = entities.get(n);
			if(target instanceof GascoigneBeastBossEntity || target instanceof FatherGascoigneBossEntity) {
		     target.setAnimationState(5);
			}
			   
		}
		player.playSound(CourtOfAzathoth.MUSIC_BOX.get(), 1, 1);
		player.getCooldowns().addCooldown(this, 600);
		
		if (world instanceof ServerLevel serverLevel) {
			triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), CONTROLLER_NAME, "music_play");
		}
		
		
		
	}

		
		
		
	
	@Override
	public int getMaxStackSize(ItemStack stack) {
		
		return 1;
	}
	
	
	
	@Override
	   @OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack stack, Level p_77624_2_, List<Component> tooltip,
				TooltipFlag p_77624_4_) {
			
			super.appendHoverText(stack, p_77624_2_, tooltip, p_77624_4_);
			 
				

				 String text = "\u00A75" + "Plays a soothing lullaby";
				 tooltip.add(Component.literal(text));
				 
				 String info = "\u00A78\u00A7oIt appears to have a special connection to the Old Hunter Father Gascoigne's past";
				 tooltip.add(Component.literal(info));
			    
				 String text1 = "\u00A75\u00A7oIn order to survive the beastly scourge, one must not forget their own past";
				 tooltip.add(Component.literal(text1));
			    	
			    	
			    	
			    }
	
	@Override
	 public Rarity getRarity(ItemStack p_77613_1_) {

		 return Rarity.RARE;
	 } 
}
