package net.sypherred.sophisticated_compatibility;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateSophisticatedStorageCompatibility implements ModInitializer {
	public static final String MOD_ID = "create-sophisticated-storage-compatibility";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}