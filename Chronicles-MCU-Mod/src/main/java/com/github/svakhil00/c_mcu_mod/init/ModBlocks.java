package com.github.svakhil00.c_mcu_mod.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ModBlocks {
	public static final Block VIBRANIUM_ORE_BLOCK = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(30.0F).lightValue(6).sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE));
}
