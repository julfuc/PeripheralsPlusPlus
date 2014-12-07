package com.austinv11.peripheralsplusplus.proxy;

import com.austinv11.peripheralsplusplus.PeripheralsPlusPlus;
import com.austinv11.peripheralsplusplus.client.gui.GuiRocket;
import com.austinv11.peripheralsplusplus.client.models.RenderRocket;
import com.austinv11.peripheralsplusplus.entities.EntityRocket;
import com.austinv11.peripheralsplusplus.reference.Reference;
import com.austinv11.peripheralsplusplus.turtles.TurtleCompass;
import com.austinv11.peripheralsplusplus.utils.IconManager;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void setupVillagers() {
		super.setupVillagers();
		VillagerRegistry.instance().registerVillagerSkin(PeripheralsPlusPlus.VILLAGER_ID, new ResourceLocation(Reference.MOD_ID.toLowerCase()+":textures/models/CCVillager.png"));
	}

	@Override
	public void iconManagerInit() {
		IconManager.upgrades.add(new TurtleCompass());
		MinecraftForge.EVENT_BUS.register(new IconManager());
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
	}

	@Override
	public void prepareGuis() {
		MinecraftForge.EVENT_BUS.register(new GuiRocket.EventHandler());
	}
}
