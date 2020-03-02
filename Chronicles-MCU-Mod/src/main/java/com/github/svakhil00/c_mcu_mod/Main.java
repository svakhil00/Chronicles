package com.github.svakhil00.c_mcu_mod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.svakhil00.c_mcu_mod.config.Config;
import com.github.svakhil00.c_mcu_mod.init.ModBlocks;
import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;
import com.github.svakhil00.c_mcu_mod.init.ModItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)

public class Main
{
    public static final String MODID = "c_mcu_mod";
    public static final Logger LOGGER = LogManager.getLogger();
    
   
    
    public Main() {
    	final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	
    	modLoadingContext.registerConfig(Type.SERVER, Config.SERVER_CONFIG, "c_mcu_mod-server.toml");
    	modLoadingContext.registerConfig(Type.CLIENT, Config.CLIENT_CONFIG, "c_mcu_mod-client.toml");
    	
    	
    	ModBlocks.BLOCKS.register(modEventBus);
    	ModItems.ITEMS.register(modEventBus);
    	ModEntityTypes.REG.register(modEventBus);
    	LOGGER.debug("Hello from Chronicle's MCU Mod!");
    }
}
