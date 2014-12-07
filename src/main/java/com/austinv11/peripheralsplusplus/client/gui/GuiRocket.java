package com.austinv11.peripheralsplusplus.client.gui;

import com.austinv11.peripheralsplusplus.entities.EntityRocket;
import com.austinv11.peripheralsplusplus.reference.Reference;
import com.austinv11.peripheralsplusplus.tiles.containers.ContainerRocket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.opengl.GL11;

public class GuiRocket extends GuiContainer {

	private int sizeX, sizeY;
	public EntityRocket rocket;
	private ResourceLocation backgroundimage = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + "textures/gui/guiRocket.png");
	public EntityPlayer player;

	public GuiRocket(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerRocket(player, (EntityRocket)world.getEntityByID(x), 176, 166));
		sizeX = 176;
		sizeY = 166;
		rocket = (EntityRocket)world.getEntityByID(x);
		this.player = player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(backgroundimage);
		int x = (width - sizeX) / 2;
		int y = (height - sizeY) / 2;
		drawTexturedModalRect(x, y, 0, 0, sizeX, sizeY);
		fontRendererObj.drawString(StatCollector.translateToLocal("peripheralsplusplus.inv.rocket.0"), x+72, y+3, 0x313131);
		fontRendererObj.drawString(StatCollector.translateToLocal("peripheralsplusplus.inv.rocket.1"), x+3, y+23, 0x313131);
		fontRendererObj.drawString(rocket.fuel+"", x+3, y+33, 0x313131);
		fontRendererObj.drawString(StatCollector.translateToLocal("peripheralsplusplus.inv.rocket.2"), x+3, y+49, 0x313131);
		fontRendererObj.drawString(rocket.oxidizer+"", x+3, y+59, 0x313131);
	}

	@Override
	public void initGui() {
		super.initGui();
		int x = (width - sizeX) / 2;
		int y = (height - sizeY) / 2;
		String color = Reference.Colors.GREEN;
		if (!rocket.isUsable)
			color = Reference.Colors.RED;
		buttonList.add(new GuiButton(0/*id*/, x+63/*xpos*/, y+60/*ypos*/, 50 /*width*/, 20/*height*/, color+StatCollector.translateToLocal("peripheralsplusplus.button.rocket")));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public static class EventHandler {

		@SubscribeEvent
		public void onMouseClicked(GuiScreenEvent.ActionPerformedEvent.Post event) {
			if (event.gui instanceof GuiRocket) {
				GuiRocket rocket = (GuiRocket) event.gui;
				if (event.button.id == 0) {
					if (rocket.rocket.isUsable) {
						rocket.player.closeScreen();
						rocket.rocket.setActive();
					}else {
						rocket.player.addChatComponentMessage(new ChatComponentText(Reference.Colors.RED+StatCollector.translateToLocal("peripheralsplusplus.chat.launchFailure")));
					}
				}
			}
		}
	}
}
