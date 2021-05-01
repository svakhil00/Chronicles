package com.github.svakhil00.c_mcu_mod.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.github.svakhil00.c_mcu_mod.Main;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT_CONFIG;
	
	static {
		OregenConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
		
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		Main.LOGGER.info("loading config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		Main.LOGGER.info("Build config: " + path);
		file.load();
		Main.LOGGER.info("Loaded config: " + path);
		config.setConfig(file);
	}
}
