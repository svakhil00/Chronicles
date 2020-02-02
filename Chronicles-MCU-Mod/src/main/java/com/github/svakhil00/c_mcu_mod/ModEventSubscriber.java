package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.lists.BlockList;
import com.github.svakhil00.c_mcu_mod.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	
		
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				setup(ItemList.CAPTAIN_AMERICA_SHIELD, "captain_america_shield"), 
				setup(ItemList.VIBRANIUM_ORE_BLOCKITEM, "vibranium_ore"),
				setup(ItemList.VIBRANIUM_INGOT, "vibranium_ingot"),
				setup(ItemList.MJOLNIR, "mjolnir")
				);
	}
	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				setup(BlockList.VIBRANIUM_ORE_BLOCK, "vibranium_ore")
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
