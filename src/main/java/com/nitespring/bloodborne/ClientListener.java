package com.nitespring.bloodborne;

import com.mojang.blaze3d.platform.ScreenManager;
import com.nitespring.bloodborne.client.render.entities.mobs.beasts.AshenBeastPatientGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.beasts.BeastPatientGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.beasts.CloakedBeastPatientGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.beasts.SilverbeastGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne.FatherGascoigneGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.gascoigne.GascoigneBeastGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash.MicolashGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.bosses.micolash.SkeletalPuppetGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorCrucifixGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorFlamesprayerGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorLanternGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorPistolGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.church.ChurchDoctorScytheGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.huntsmen.HuntsmanAxeGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.huntsmen.HuntsmanCutlassGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.kin.BrainsuckerGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.mobs.kin.CelestialEmissaryGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.ACallBeyondProjectileRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.AugurOfEbrietasGeoRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.BulletModel;
import com.nitespring.bloodborne.client.render.entities.projectiles.BulletRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.FlameProjectileRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.FlameRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.GenericSpellProjectileRenderer;
import com.nitespring.bloodborne.client.render.entities.projectiles.InvisibleProjectileRenderer;
import com.nitespring.bloodborne.client.screens.WorkshopScreen;
import com.nitespring.bloodborne.common.entities.projectiles.BindingSpellProjectileEntity;
import com.nitespring.bloodborne.core.init.ContainerInit;
import com.nitespring.bloodborne.core.init.EntityInit;
import com.nitespring.bloodborne.core.init.ProjectileInit;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
	
	public static final ModelLayerLocation BULLET_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "bullet"), "main");
	public static final ModelLayerLocation GENERIC_BULLET_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "bullet_generic"), "main");
	public static final ModelLayerLocation FLAME_LAYER = new ModelLayerLocation(new ResourceLocation(BloodborneMod.MOD_ID, "flame"), "main");
	
	 @SubscribeEvent
 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
 		
 			event.registerEntityRenderer(ProjectileInit.BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.FIRE_BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.EXPLOSIVE_BULLET_ENTITY.get(), BulletRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.CALL_BEYOND.get(), ACallBeyondProjectileRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.FLAME.get(), FlameRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.AUGUR_OF_EBRIETAS.get(), AugurOfEbrietasGeoRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.BINDING_SPELL.get(), GenericSpellProjectileRenderer::new);
 			
 			event.registerEntityRenderer(ProjectileInit.CALL_BEYOND_AIM_HELPER.get(), InvisibleProjectileRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.AUGUR_PROJECTILE.get(), InvisibleProjectileRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.RED_JEWEL.get(), InvisibleProjectileRenderer::new);
 			event.registerEntityRenderer(ProjectileInit.BLOSSOMED_EYE.get(), InvisibleProjectileRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.FATHER_GASCOIGNE.get(), FatherGascoigneGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.GASCOIGNE_BEAST.get(), GascoigneBeastGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.MICOLASH.get(), MicolashGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.HUNTSMAN_AXE.get(), HuntsmanAxeGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.HUNTSMAN_CUTLASS.get(), HuntsmanCutlassGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.SILVERBEAST.get(), SilverbeastGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CLOAKED_BEAST_PATIENT.get(), CloakedBeastPatientGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), AshenBeastPatientGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.SKELETAL_PUPPET.get(), SkeletalPuppetGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.BRAINSUCKER.get(), BrainsuckerGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CELESTIAL_EMISSARY.get(), CelestialEmissaryGeoRenderer::new);
 			
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), ChurchDoctorScytheGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR.get(), ChurchDoctorGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_LANTERN.get(), ChurchDoctorLanternGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), ChurchDoctorCrucifixGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_PISTOL.get(), ChurchDoctorPistolGeoRenderer::new);
 			event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), ChurchDoctorFlamesprayerGeoRenderer::new);
 			
 			

 		
 	}
	
	 
	    @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(BULLET_LAYER, BulletRenderer.BulletModel::createBodyLayer);
            event.registerLayerDefinition(GENERIC_BULLET_LAYER, BulletModel::createBodyLayer);
            event.registerLayerDefinition(FLAME_LAYER, FlameProjectileRenderer.FlameModel::createBodyLayer);
        }

}
