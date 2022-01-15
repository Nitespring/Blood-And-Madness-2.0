package com.nitespring.bloodborne.core.events;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.beasts.SilverbeastEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanAxeEntity;
import com.nitespring.bloodborne.common.entities.mobs.huntsmen.HuntsmanCutlassEntity;
import com.nitespring.bloodborne.core.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributes {

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		event.put(EntityInit.FATHER_GASCOIGNE.get(), FatherGascoigneBossEntity.setCustomAttributes().build());
		event.put(EntityInit.GASCOIGNE_BEAST.get(), GascoigneBeastBossEntity.setCustomAttributes().build());
		
		event.put(EntityInit.HUNTSMAN_AXE.get(), HuntsmanAxeEntity.setCustomAttributes().build());
		event.put(EntityInit.HUNTSMAN_CUTLASS.get(), HuntsmanCutlassEntity.setCustomAttributes().build());
		
		event.put(EntityInit.SILVERBEAST.get(), SilverbeastEntity.setCustomAttributes().build());
	}
	
	
	
	
	
	
	
	
}
