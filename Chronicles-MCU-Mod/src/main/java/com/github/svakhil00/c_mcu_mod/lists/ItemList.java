package com.github.svakhil00.c_mcu_mod.lists;

import com.github.svakhil00.c_mcu_mod.init.ModItemGroups;
import com.github.svakhil00.c_mcu_mod.item.CaptainShieldItem;
import com.github.svakhil00.c_mcu_mod.item.MjolnirItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;

public class ItemList {
	public static final Item CAPTAIN_AMERICA_SHIELD = new CaptainShieldItem(ItemTierMaterialList.shield,11, -3.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	public static final BlockItem VIBRANIUM_ORE_BLOCKITEM = new BlockItem(BlockList.VIBRANIUM_ORE_BLOCK, new BlockItem.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item VIBRANIUM_INGOT = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
	public static final Item MJOLNIR = new MjolnirItem(ItemTierMaterialList.mjolnir, -1, -3.0f,new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1));
	public static final Item PURE_VIBRANIUM = new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));
}
