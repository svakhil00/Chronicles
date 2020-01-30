package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	
	public static final Item CAPTAIN_AMERICA_SHIELD = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Block VIBRANIUM_ORE_BLOCK = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F));
	public static final BlockItem VIBRANIUM_ORE_BLOCKITEM = new BlockItem(VIBRANIUM_ORE_BLOCK, new BlockItem.Properties().group(ModItemGroups.MOD_ITEM_GROUP2));
	public static final Item VIBRANIUM_INGOT = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP3));

	
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				setup(CAPTAIN_AMERICA_SHIELD, "captain_america_shield"), 
				setup(VIBRANIUM_ORE_BLOCKITEM, "vibranium_ore"),
				setup(VIBRANIUM_INGOT, "vibranium_ingot")
				);
	}
	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				setup(VIBRANIUM_ORE_BLOCK, "vibranium_ore")
				);
	}
	
	
	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
