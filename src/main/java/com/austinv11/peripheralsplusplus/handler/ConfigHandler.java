package com.austinv11.peripheralsplusplus.handler;

import com.austinv11.peripheralsplusplus.reference.Reference;
import com.austinv11.peripheralsplusplus.util.Logger;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler {
	public static Configuration config;

	public static void init(File configFile) {
		if (config == null) {
			config = new Configuration(configFile);
			loadConfig();
		}
	}

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(Reference.MOD_ID)) {
			loadConfig();
		}
	}

	private static void loadConfig() {
		try {
			config.load();
			config.addCustomCategoryComment("Blocks", "");
			config.addCustomCategoryComment("Items", "");
			config.addCustomCategoryComment("Misc", "");
		} catch (Exception e) {
			Logger.error(e);
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}