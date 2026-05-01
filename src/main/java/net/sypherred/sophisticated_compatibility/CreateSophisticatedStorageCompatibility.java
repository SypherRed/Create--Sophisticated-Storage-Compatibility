package net.sypherred.sophisticated_compatibility;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateSophisticatedStorageCompatibility implements ModInitializer {
	public static final String MOD_ID = "create-sophisticated-storage-compatibility";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Create Sophisticated Storage Compatibility");

		if (FabricLoader.getInstance().isModLoaded("create") && FabricLoader.getInstance().isModLoaded("sophisticatedstorage")) {
			LOGGER.info("Both Create and Sophisticated Storage are loaded. Enabling compatibility.");
			CompatibilityHandler.init();
		} else {
			LOGGER.warn("Create or Sophisticated Storage is not loaded. Compatibility not enabled.");
		}
	}
}