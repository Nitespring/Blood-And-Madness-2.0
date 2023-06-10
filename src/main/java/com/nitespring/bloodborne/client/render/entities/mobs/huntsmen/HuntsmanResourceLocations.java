package com.nitespring.bloodborne.client.render.entities.mobs.huntsmen;

import java.util.Random;

import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.entities.mobs.parent.AbstractHuntsmanEntity;

import net.minecraft.resources.ResourceLocation;

public class HuntsmanResourceLocations {

	public static final ResourceLocation BLACK_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_black.png");
	public static final ResourceLocation RED_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_red.png");
	public static final ResourceLocation BROWN_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_brown.png");
	public static final ResourceLocation GREY_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_grey.png");
	public static final ResourceLocation DARK_BROWN_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_dark_brown.png");
	public static final ResourceLocation BLONDE_HAIR = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_hair_blonde.png");
	
	public static final ResourceLocation BLACK = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_black.png");
	public static final ResourceLocation GREY = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_grey.png");
	public static final ResourceLocation GREEN = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_green.png");
	public static final ResourceLocation WHITE = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_white.png");
	public static final ResourceLocation ORANGE = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_orange.png");
	public static final ResourceLocation LIGHT_BROWN = new ResourceLocation(BloodborneMod.MOD_ID, "textures/entities/huntsman/huntsman_light_brown.png");
	
	
	
	public static final void randomizeHuntsmanColours(AbstractHuntsmanEntity e) {
			
		 e.setHairColour(new Random().nextInt(20)); 
		 e.setCoatColour(new Random().nextInt(20)); 
		
	}
	
   public static final ResourceLocation getHuntsmanCoatColourLocation(AbstractHuntsmanEntity e) {
				
            	int i = e.getCoatColour();
              	switch(i) {
              	case 1, 2, 3:
              		return GREY;
              	case 4, 5, 6:
              		return WHITE;
              	case 7, 8:
              		return GREEN;
              	case 9:
              		return ORANGE;
              	case 10,11:
              		return LIGHT_BROWN;
              	default:
              		return BLACK;
              	}
              	
   }
   
   public static final ResourceLocation getHuntsmanHairColourLocation(AbstractHuntsmanEntity e) {
		
 	  		int i = e.getHairColour();
 	  		switch(i) {
 	  		case 1,2,3,4,5 :
 	  			return DARK_BROWN_HAIR;
 	  		case 6,7,8,9:
 	  			return BROWN_HAIR;
 	  		case 10,11,12:
 	  			return RED_HAIR;
 	  		case 13:
 	  			return BLONDE_HAIR;
 	  		case 14:
 	  			return GREY_HAIR;
 	  		default:
 	  			return BLACK_HAIR;
   	}
	
   }
	
	
	
	
	
	
	
	
	
	
}
