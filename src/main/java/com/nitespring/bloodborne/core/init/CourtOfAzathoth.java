package com.nitespring.bloodborne.core.init;



import com.nitespring.bloodborne.BloodborneMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BloodborneMod.MOD_ID)
public class CourtOfAzathoth {
	
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
			BloodborneMod.MOD_ID);
	
	
	
	    public static final RegistryObject<SoundEvent> MUSIC_BOX = build("general.music_box");
	    public static final RegistryObject<SoundEvent> SCREAM = build("entity.beast_patient_scream");
	    
	    private static RegistryObject<SoundEvent> build(String id)
	    {
	        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BloodborneMod.MOD_ID, id)));
	    }

}
