package com.github.svakhil00.c_mcu_mod.init;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.item.CaptainAmericaChestplateItem;
import com.github.svakhil00.c_mcu_mod.item.CaptainAmericaSuitItem;
import com.github.svakhil00.c_mcu_mod.item.CaptainShieldItem;
import com.github.svakhil00.c_mcu_mod.item.IronManBootsItem;
import com.github.svakhil00.c_mcu_mod.item.IronManHelmetItem;
import com.github.svakhil00.c_mcu_mod.item.IronManSuitItem;
import com.github.svakhil00.c_mcu_mod.item.MjolnirItem;
import com.github.svakhil00.c_mcu_mod.item.PureVibraniumItem;
import com.github.svakhil00.c_mcu_mod.item.ThorChestplateItem;
import com.github.svakhil00.c_mcu_mod.item.ThorSuitItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Main.MODID);
	
	
	
	//blocks, ores
	public static final RegistryObject<Item> STABLE_VIBRANIUM = ITEMS.register("stable_vibranium", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<PureVibraniumItem> PURE_VIBRANIUM = ITEMS.register("pure_vibranium", () -> new PureVibraniumItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> URU = ITEMS.register("uru", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> STEEL = ITEMS.register("steel", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> ASGARDIAN_STEEL = ITEMS.register("asgardian_steel", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	
	//misc
	public static final RegistryObject<Item> HAMMER_HEAD = ITEMS.register("hammer_head", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> HAMMER_HANDLE = ITEMS.register("hammer_handle", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<Item> VIBRANIUM_CAPSULE = ITEMS.register("vibranium_capsule", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	
	//Weapons
	public static final RegistryObject<CaptainShieldItem> CAPTAIN_AMERICA_SHIELD = ITEMS.register("captain_america_shield", () -> new CaptainShieldItem(ModItemTierMaterial.shield, 11, -2.5f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1)));
	public static final RegistryObject<MjolnirItem> MJOLNIR = ITEMS.register("mjolnir", () -> new MjolnirItem(ModItemTierMaterial.mjolnir, -1, -3.0f,new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1)));
	
	
	//armor
	public static final RegistryObject<IronManHelmetItem> IRON_MAN_HELMET = ITEMS.register("iron_man_helmet", () -> new IronManHelmetItem(ModArmorTierMaterial.iron_man_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<IronManSuitItem> IRON_MAN_CHESTPLATE = ITEMS.register("iron_man_chestplate", () -> new IronManSuitItem(ModArmorTierMaterial.iron_man_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<IronManSuitItem> IRON_MAN_LEGGINGS = ITEMS.register("iron_man_leggings", () -> new IronManSuitItem(ModArmorTierMaterial.iron_man_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<IronManBootsItem> IRON_MAN_BOOTS = ITEMS.register("iron_man_boots", () -> new IronManBootsItem(ModArmorTierMaterial.iron_man_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	public static final RegistryObject<ThorSuitItem> THOR_HELMET = ITEMS.register("thor_helmet", () -> new ThorSuitItem(ModArmorTierMaterial.thor_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<ThorChestplateItem> THOR_CHESTPLATE = ITEMS.register("thor_chestplate", () -> new ThorChestplateItem(ModArmorTierMaterial.thor_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<ThorSuitItem> THOR_LEGGINGS = ITEMS.register("thor_leggings", () -> new ThorSuitItem(ModArmorTierMaterial.thor_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<ThorSuitItem> THOR_BOOTS = ITEMS.register("thor_boots", () -> new ThorSuitItem(ModArmorTierMaterial.thor_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	public static final RegistryObject<CaptainAmericaSuitItem> CAPTAIN_AMERICA_HELMET = ITEMS.register("captain_america_helmet", () -> new CaptainAmericaSuitItem(ModArmorTierMaterial.captain_america_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<CaptainAmericaChestplateItem> CAPTAIN_AMERICA_CHESTPLATE = ITEMS.register("captain_america_chestplate", () -> new CaptainAmericaChestplateItem(ModArmorTierMaterial.captain_america_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<CaptainAmericaSuitItem> CAPTAIN_AMERICA_LEGGINGS = ITEMS.register("captain_america_leggings", () -> new CaptainAmericaSuitItem(ModArmorTierMaterial.captain_america_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	public static final RegistryObject<CaptainAmericaSuitItem> CAPTAIN_AMERICA_BOOTS = ITEMS.register("captain_america_boots", () -> new CaptainAmericaSuitItem(ModArmorTierMaterial.captain_america_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	
	public static final RegistryObject<Item> HULK_ITEM = ITEMS.register("hulk", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)));
	
	//entity eggs
	

}
