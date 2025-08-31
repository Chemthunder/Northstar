package dev.chemthunder.northstar;

import dev.chemthunder.northstar.init.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Northstar implements ModInitializer {
	public static final String MOD_ID = "northstar";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        NorthItemGroup.init();
        NorthItems.initialize();
        NorthBlocks.init();
        NorthSounds.init();
        NorthEffects.init();

	}

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }
}