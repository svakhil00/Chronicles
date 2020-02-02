package com.github.svakhil00.c_mcu_mod;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.svakhil00.c_mcu_mod.config.Config;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)

public class Main
{
    public static final String MODID = "c_mcu_mod";
    public static final Logger LOGGER = LogManager.getLogger();
    public static Main instance;

    public Main() {
    	instance = this;
    	ModLoadingContext.get().registerConfig(Type.SERVER, Config.SERVER_CONFIG, "c_mcu_mod-server.toml");
    	ModLoadingContext.get().registerConfig(Type.CLIENT, Config.CLIENT_CONFIG, "c_mcu_mod-client.toml");
    	
    	Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("c_mcu_mod-server.toml").toString());
    	Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("c_mcu_mod-client.toml").toString());
    	
    	LOGGER.debug("Hello from Chronicle's MCU Mod!");
    }
}
