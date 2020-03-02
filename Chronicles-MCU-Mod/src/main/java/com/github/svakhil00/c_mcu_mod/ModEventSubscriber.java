package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.client.renderer.entity.CaptainAmericaShieldRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.MjolnirRenderer;
import com.github.svakhil00.c_mcu_mod.init.ModBlocks;
import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;
import com.github.svakhil00.c_mcu_mod.init.ModItemGroups;
import com.github.svakhil00.c_mcu_mod.world.gen.OreGen;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();	
		
		ModBlocks.BLOCKS.getEntries().stream()
						.map(RegistryObject::get)
						//block item exceptions
						//.filter(block -> needsItemBlock(block))
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

	@SubscribeEvent
	public static void registerModels(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MJOLNIR.get(),
				manager -> new MjolnirRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CAPTAIN_AMERICA_SHIELD.get(),
				manager -> new CaptainAmericaShieldRenderer(manager, Minecraft.getInstance().getItemRenderer()));

	}

	private static SoundEvent register(String key) {
		return Registry.register(Registry.SOUND_EVENT, key, new SoundEvent(new ResourceLocation(key)));
	}

	public static final SoundEvent ITEM_MJOLNIR_FLIGHT = register("c_mcu_mod:item.mjolnir.flight");
	public static final SoundEvent ITEM_MJOLNIR_HIT = register("c_mcu_mod:item.mjolnir.hit");
	public static final SoundEvent ITEM_MJOLNIR_THROW = register("c_mcu_mod:item.mjolnir.throw");
	public static final SoundEvent ITEM_CAPTAIN_AMERICA_SHIELD_HIT = register(
			"c_mcu_mod:item.captain_america_shield.hit");

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
