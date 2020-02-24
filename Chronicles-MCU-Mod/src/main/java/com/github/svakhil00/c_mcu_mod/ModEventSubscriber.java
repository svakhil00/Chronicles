package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.client.renderer.entity.CaptainAmericaShieldRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.MjolnirRenderer;
import com.github.svakhil00.c_mcu_mod.entity.CustomEntitys;
import com.github.svakhil00.c_mcu_mod.init.ModBlocks;
import com.github.svakhil00.c_mcu_mod.init.ModItems;
import com.github.svakhil00.c_mcu_mod.world.gen.OreGen;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(setup(ModItems.CAPTAIN_AMERICA_SHIELD, "captain_america_shield"),
				setup(ModItems.VIBRANIUM_ORE_BLOCKITEM, "vibranium_ore"),
				setup(ModItems.STABLE_VIBRANIUM, "stable_vibranium"), 
				setup(ModItems.MJOLNIR, "mjolnir"),
				setup(ModItems.PURE_VIBRANIUM, "pure_vibranium"), 
				setup(ModItems.IRON_MAN_HELMET, "iron_man_helmet"),
				setup(ModItems.IRON_MAN_CHESTPLATE, "iron_man_chestplate"),
				setup(ModItems.IRON_MAN_LEGGINGS, "iron_man_leggings"),
				setup(ModItems.IRON_MAN_BOOTS, "iron_man_boots"), 
				setup(ModItems.THOR_HELMET, "thor_helmet"),
				setup(ModItems.THOR_CHESTPLATE, "thor_chestplate"), 
				setup(ModItems.THOR_LEGGINGS, "thor_leggings"),
				setup(ModItems.THOR_BOOTS, "thor_boots"),
				setup(ModItems.CAPTAIN_AMERICA_HELMET, "captain_america_helmet"),
				setup(ModItems.CAPTAIN_AMERICA_CHESTPLATE, "captain_america_chestplate"),
				setup(ModItems.CAPTAIN_AMERICA_LEGGINGS, "captain_america_leggings"),
				setup(ModItems.CAPTAIN_AMERICA_BOOTS, "captain_america_boots"),
				setup(ModItems.STEEL, "steel"),
				setup(ModItems.URU, "uru"),
				setup(ModItems.HAMMER_HANDLE, "hammer_handle"),
				setup(ModItems.HAMMER_HEAD, "hammer_head"),
				setup(ModItems.ASGARDIAN_STEEL, "asgardian_steel"),
				setup(ModItems.VIBRANIUM_CAPSULE, "vibranium_capsule")
				);
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(setup(ModBlocks.VIBRANIUM_ORE_BLOCK, "vibranium_ore"));
	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		OreGen.generateOre();
	}

	@SubscribeEvent
	public static void registerModels(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntitys.MJOLNIR.get(),
				manager -> new MjolnirRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(CustomEntitys.CAPTAIN_AMERICA_SHIELD.get(),
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
