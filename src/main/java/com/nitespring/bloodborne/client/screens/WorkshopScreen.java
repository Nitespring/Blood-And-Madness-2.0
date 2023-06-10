package com.nitespring.bloodborne.client.screens;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.nitespring.bloodborne.BloodborneMod;
import com.nitespring.bloodborne.common.containers.WorkshopContainer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class WorkshopScreen extends AbstractContainerScreen<WorkshopContainer> {
	private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(BloodborneMod.MOD_ID + ":textures/gui/workshop_gui.png");
	//private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("textures/gui/container/loom.png");
	
	
	
	//LoomScreen
	//AnvilScreen
	   Inventory playerInv;
	public WorkshopScreen(WorkshopContainer p_i51105_1_, Inventory playerInventory, Component p_i51105_3_) {
		super(p_i51105_1_, playerInventory, p_i51105_3_);
		this.imageWidth = 175;
		this.imageHeight = 197;
		this.playerInv=playerInventory;
	}

	   @Override
		public void render(GuiGraphics stack, int x, int y, float partialTicks) {
			   
				this.renderBackground(stack);
				super.render(stack, x, y, partialTicks);
				this.renderTooltip(stack, x, y);
		}

		@Override
		protected void renderBg(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
			
			 int xStart = (this.width - this.imageWidth) / 2;
		      int yStart = (this.height - this.imageHeight) / 2;
			
			// draw the background
		      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		      RenderSystem.setShaderTexture(0, GUI_TEXTURE);
			this.minecraft.getTextureManager().bindForSetup(GUI_TEXTURE);
			stack.blit(GUI_TEXTURE, xStart, yStart, 0,0, this.imageWidth, this.imageHeight);
		
		}
		
		
	    @Override
	    protected void renderLabels(GuiGraphics stack, int mouseX, int mouseY) {
	    	//
	    	stack.drawString(this.font, this.title, 8, 6, 4210752);
	    	stack.drawString(this.font, this.playerInv.getDisplayName(), 8, this.imageHeight - 96 + 2, 4210752);
	    }
}
