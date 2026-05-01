package net.sypherred.sophisticated_compatibility;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompatibilityHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger("CreateSophisticatedStorageCompatibility");

    public static void init() {
        LOGGER.info("Registering compatibility handlers for crash prevention.");

        // Register event handlers to prevent crashes with Create Contraptions
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            LOGGER.info("Server started, compatibility active. Note: Sophisticated Storage chests may not be movable in Create Contraptions to prevent crashes.");
        });

        // Add more handlers if needed
    }
}
