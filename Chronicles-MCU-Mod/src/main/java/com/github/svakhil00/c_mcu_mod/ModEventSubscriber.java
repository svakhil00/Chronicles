package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.lists.BlockList;
import com.github.svakhil00.c_mcu_mod.lists.ItemList;
import com.github.svakhil00.c_mcu_mod.world.gen.OreGen;
import com.google.common.base.Function;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.CaptainAmericaShieldRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.MjolnirRenderer;
import com.github.svakhil00.c_mcu_mod.entity.CustomEntityType;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(setup(ItemList.CAPTAIN_AMERICA_SHIELD, "captain_america_shield"),
				setup(ItemList.VIBRANIUM_ORE_BLOCKITEM, "vibranium_ore"),
				setup(ItemList.VIBRANIUM_INGOT, "vibranium_ingot"), setup(ItemList.MJOLNIR, "mjolnir"),
				setup(ItemList.PURE_VIBRANIUM, "pure_vibranium"), setup(ItemList.IRON_MAN_HELMET, "iron_man_helmet"),
				setup(ItemList.IRON_MAN_CHESTPLATE, "iron_man_chestplate"),
				setup(ItemList.IRON_MAN_LEGGINGS, "iron_man_leggings"),
				setup(ItemList.IRON_MAN_BOOTS, "iron_man_boots"), setup(ItemList.THOR_HELMET, "thor_helmet"),
				setup(ItemList.THOR_CHESTPLATE, "thor_chestplate"), setup(ItemList.THOR_LEGGINGS, "thor_leggings"),
				setup(ItemList.THOR_BOOTS, "thor_boots"),
				setup(ItemList.CAPTAIN_AMERICA_HELMET, "captain_america_helmet"),
				setup(ItemList.CAPTAIN_AMERICA_CHESTPLATE, "captain_america_chestplate"),
				setup(ItemList.CAPTAIN_AMERICA_LEGGINGS, "captain_america_leggings"),
				setup(ItemList.CAPTAIN_AMERICA_BOOTS, "captain_america_boots"));
	}

	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(setup(BlockList.VIBRANIUM_ORE_BLOCK, "vibranium_ore"));
	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		OreGen.generateOre();
	}
	
	@SubscribeEvent
	public static void registerModels(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntityType.MJOLNIR.get(),
				manager -> new MjolnirRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(CustomEntityType.CAPTAIN_AMERICA_SHIELD.get(),
				manager -> new CaptainAmericaShieldRenderer(manager, Minecraft.getInstance().getItemRenderer()));

	}

	
	private static SoundEvent register(String key) {
		return Registry.register(Registry.SOUND_EVENT, key, new SoundEvent(new ResourceLocation(key)));
	}

	public static final SoundEvent ITEM_MJOLNIR_FLIGHT = register("c_mcu_mod:item.mjolnir.flight");
	public static final SoundEvent ITEM_MJOLNIR_HIT = register("c_mcu_mod:item.mjolnir.hit");
	public static final SoundEvent ITEM_MJOLNIR_THROW = register("c_mcu_mod:item.mjolnir.throw");
	public static final SoundEvent ITEM_CAPTAIN_AMERICA_SHIELD_HIT = register("c_mcu_mod:item.captain_america_shield.hit");
	

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
		return setup(entry, new ResourceLocation(Main.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
		entry.setRegistryName(registryName);
		return entry;
	}
}
