package com.github.svakhil00.c_mcu_mod.init;

import com.github.svakhil00.c_mcu_mod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MODID);

	public static final RegistryObject<Block> VIBRANIUM_ORE_BLOCK = BLOCKS.register("vibranium_ore",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(30.0F).lightValue(6)
					.sound(SoundType.STONE).harvestLevel(3).harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> TITANIUM_ORE_BLOCK = BLOCKS.register("titanium_ore",
			() -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)
					.sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
}
