package com.github.svakhil00.c_mcu_mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OregenConfig {
	public static ForgeConfigSpec.IntValue chance;
	public static ForgeConfigSpec.BooleanValue generate_overworld;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("Oregen Config");
		
		chance = server
				.comment("Maxium number of ore veins of MCU ore that can spawn in one chunk.")
				.defineInRange("oregen.chance", 100, 1, 100000);
		
		generate_overworld = server
				.comment("Decide if you want MCU ores to spawn in the overworld")
				.define("oregen.generate overworld", true);
	}
}
