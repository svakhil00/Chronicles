package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.lists.BlockList;
import com.github.svakhil00.c_mcu_mod.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	
		
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(setup(ItemList.CAPTAIN_AMERICA_SHIELD, "captain_america_shield"), 
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
	
	private static SoundEvent register(String key) {
		return Registry.register(Registry.SOUND_EVENT, key, new SoundEvent(new ResourceLocation(key)));
	}
	
	public static final SoundEvent ITEM_MJOLNIR_JUMP = register("c_mcu_mod:item.mjolnir.jump");
	public static final SoundEvent ITEM_MJOLNIR_HIT = register("c_mcu_mod:item.mjolnir.hit");
	public static final SoundEvent ITEM_MJOLNIR_LIGHTNING = register("c_mcu_mod:item.mjolnir.lightning");

	
	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
