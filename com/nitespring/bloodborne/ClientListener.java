package com.nitespring.bloodborne;

import com.nitespring.bloodborne.client.render.entities.mobs.beasts.SilverbeastGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne.FatherGascoigneGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne.GascoigneBeastGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.huntsmen.HuntsmanAxeGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.huntsmen.HuntsmanCutlassGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.ACallBeyondProjectileRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.BulletRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.FlameProjectileRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.InvisibleProjectileRenderer;
import com.nitespring.bloodborne.core.init.EntityInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
	
	public static final ModelLayerLocation BULLET_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "bullet"), "main");
	public static final ModelLayerLocation CALL_BEYOND_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "a_call_beyond"), "main");
	public static final ModelLayerLocation FLAME_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "flame"), "main");
	
	 @SubscribeEvent
 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
 		
 			event.registerEntityRenderer(ProjectileInit.BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.FIRE_BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.EXPLOSIVE_BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.CALL_BEYOND.get(), ACallBeyondProjectileRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.FLAME.get(), FlameProjectileRenderer::new);
 			
 			event.registerEntityRenderer(ProjectileInit.CALL_BEYOND_AIM_HELPER.get(), InvisibleProjectileRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.FATHER_GASCOIGNE.get(), FatherGascoigneGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.GASCOIGNE_BEAST.get(), GascoigneBeastGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.HUNTSMAN_AXE.get(), HuntsmanAxeGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.HUNTSMAN_CUTLASS.get(), HuntsmanCutlassGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.SILVERBEAST.get(), SilverbeastGeoRenderer::new);
 			
 			
 		
 	}
	
	 
	    @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(BULLET_LAYER, BulletRenderer.BulletModel::createBodyLayer);
            event.registerLayerDefinition(CALL_BEYOND_LAYER, ACallBeyondProjectileRenderer.CallBeyondModel::createBodyLayer);
            event.registerLayerDefinition(FLAME_LAYER, FlameProjectileRenderer.FlameModel::createBodyLayer);
        }

}
