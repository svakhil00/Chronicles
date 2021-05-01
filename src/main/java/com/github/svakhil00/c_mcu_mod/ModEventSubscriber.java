package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.init.ModBlocks;
import com.github.svakhil00.c_mcu_mod.init.ModItemGroups;
import com.github.svakhil00.c_mcu_mod.item.ModdedSpawnEggItem;
import com.github.svakhil00.c_mcu_mod.world.gen.OreGen;

import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
				// block item exceptions
				// .filter(block -> needsItemBlock(block))
				.forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);
					final BlockItem blockItem = new BlockItem(block, properties);

					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});
		Main.LOGGER.debug("Registered BlockItems");
	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		OreGen.generateOre();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
		ModdedSpawnEggItem.initUnaddedEggs();
	}

	private static SoundEvent register(String key) {
		return Registry.register(Registry.SOUND_EVENT, key, new SoundEvent(new ResourceLocation(key)));
	}

	public static final SoundEvent ITEM_MJOLNIR_FLIGHT = register("c_mcu_mod:item.mjolnir.flight");
	public static final SoundEvent ITEM_MJOLNIR_HIT = register("c_mcu_mod:item.mjolnir.hit");
	public static final SoundEvent ITEM_MJOLNIR_THROW = register("c_mcu_mod:item.mjolnir.throw");
	public static final SoundEvent ITEM_CAPTAIN_AMERICA_SHIELD_HIT = register(
			"c_mcu_mod:item.captain_america_shield.hit");

}
