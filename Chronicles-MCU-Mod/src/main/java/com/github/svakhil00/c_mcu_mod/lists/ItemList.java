package com.github.svakhil00.c_mcu_mod.lists;

import com.github.svakhil00.c_mcu_mod.init.ModItemGroups;
import com.github.svakhil00.c_mcu_mod.item.CaptainAmericaSuitItem;
import com.github.svakhil00.c_mcu_mod.item.CaptainShieldItem;
import com.github.svakhil00.c_mcu_mod.item.IronManSuitItem;
import com.github.svakhil00.c_mcu_mod.item.MjolnirItem;
import com.github.svakhil00.c_mcu_mod.item.ThorSuitItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;

public class ItemList {
	//Weapons
	public static final Item CAPTAIN_AMERICA_SHIELD = new CaptainShieldItem(ItemTierMaterialList.shield,11, -2.5f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	public static final Item MJOLNIR = new MjolnirItem(ItemTierMaterialList.mjolnir, -1, -3.0f,new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	
	//blocks, ores
	public static final BlockItem VIBRANIUM_ORE_BLOCKITEM = new BlockItem(BlockList.VIBRANIUM_ORE_BLOCK, new BlockItem.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item VIBRANIUM_INGOT = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item PURE_VIBRANIUM = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item URU = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item STEEL =  new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item ASGARDIAN_STEEL = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	
	//misc
	public static final Item HAMMER_HEAD = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	public static final Item HAMMER_HANDLE = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	
	
	//armor
	public static final Item IRON_MAN_HELMET = new IronManSuitItem(ArmorTierMaterialList.iron_man_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item IRON_MAN_CHESTPLATE = new IronManSuitItem(ArmorTierMaterialList.iron_man_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item IRON_MAN_LEGGINGS= new IronManSuitItem(ArmorTierMaterialList.iron_man_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item IRON_MAN_BOOTS= new IronManSuitItem(ArmorTierMaterialList.iron_man_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	
	public static final Item THOR_HELMET = new ThorSuitItem(ArmorTierMaterialList.thor_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item THOR_CHESTPLATE = new ThorSuitItem(ArmorTierMaterialList.thor_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item THOR_LEGGINGS = new ThorSuitItem(ArmorTierMaterialList.thor_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item THOR_BOOTS = new ThorSuitItem(ArmorTierMaterialList.thor_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	
	public static final Item CAPTAIN_AMERICA_HELMET = new CaptainAmericaSuitItem(ArmorTierMaterialList.captain_america_suit, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item CAPTAIN_AMERICA_CHESTPLATE = new CaptainAmericaSuitItem(ArmorTierMaterialList.captain_america_suit, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item CAPTAIN_AMERICA_LEGGINGS = new CaptainAmericaSuitItem(ArmorTierMaterialList.captain_america_suit, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item CAPTAIN_AMERICA_BOOTS = new CaptainAmericaSuitItem(ArmorTierMaterialList.captain_america_suit, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	
	//entity eggs
	//SpawnEggItem item = new SpawnEggItem(EntityType<?>, color1, color2, name);
	

}
