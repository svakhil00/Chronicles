package com.github.svakhil00.c_mcu_mod;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)

public class Main
{
    public static final String MODID = "c_mcu_mod";
    private static final Logger LOGGER = LogManager.getLogger();
    public static Main instance;

    public Main() {
    	LOGGER.debug("Hello from Chronicle's MCU Mod!");
    }
}
