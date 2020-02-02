package com.github.svakhil00.c_mcu_mod.world;

import com.github.svakhil00.c_mcu_mod.config.OregenConfig;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	public static void setupOreGeneration() {
		if(OregenConfig.generate_overworld.get()) {
			for(Biome biome: ForgeRegistries.BIOMES) {
				//biome.addFeature(Decoration.UNDERGROUND_ORES);
						
						
						//new ConfiguredFeature(Feature.ORE,new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ,OregenConfig.chance.get())));
						
			}
		}
	}
}
