package com.github.svakhil00.c_mcu_mod.world.gen;

import com.github.svakhil00.c_mcu_mod.lists.BlockList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGen {
	public static void generateOre() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome == Biomes.JUNGLE) {
				
				ConfiguredPlacement<CountRangeConfig> customConfig = Placement.COUNT_RANGE
						.func_227446_a_(new CountRangeConfig(20, 0, 5, 100));
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
						Feature.ORE
								.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
										BlockList.VIBRANIUM_ORE_BLOCK.getDefaultState(), 10))
								.withPlacement(customConfig));
			
			}
			if (biome == Biomes.JUNGLE_EDGE) {

			}
			if (biome == Biomes.JUNGLE_HILLS) {

			}
			if (biome == Biomes.BAMBOO_JUNGLE) {

			}
			if (biome == Biomes.BAMBOO_JUNGLE_HILLS) {

			}
			if (biome == Biomes.MODIFIED_JUNGLE) {

			}
			if (biome == Biomes.MODIFIED_JUNGLE_EDGE) {

			}
		}

	}
}
