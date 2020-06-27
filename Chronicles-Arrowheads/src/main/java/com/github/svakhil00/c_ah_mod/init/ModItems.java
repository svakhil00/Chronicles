package com.github.svakhil00.c_ah_mod.init;

import com.github.svakhil00.c_ah_mod.Arrowheads;
import com.github.svakhil00.c_ah_mod.item.FlameArrowItem;
import com.github.svakhil00.c_ah_mod.item.LightningArrowItem;
import com.github.svakhil00.c_ah_mod.item.TNTArrowItem;
import com.github.svakhil00.c_ah_mod.item.TeleportationArrowItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Arrowheads.MODID);
	
	public static final RegistryObject<TNTArrowItem> TNT_ARROW = ITEMS.register("tnt_arrow", () -> new TNTArrowItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<FlameArrowItem> FLAME_ARROW = ITEMS.register("flame_arrow", () -> new FlameArrowItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<LightningArrowItem> LIGHTNING_ARROW = ITEMS.register("lightning_arrow", () -> new LightningArrowItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<TeleportationArrowItem> TELEPORTATION_ARROW = ITEMS.register("teleportation_arrow", () -> new TeleportationArrowItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));



}
