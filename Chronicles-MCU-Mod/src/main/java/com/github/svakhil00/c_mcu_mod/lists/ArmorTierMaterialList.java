package com.github.svakhil00.c_mcu_mod.lists;

import com.github.svakhil00.c_mcu_mod.Main;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ArmorTierMaterialList implements IArmorMaterial{
	iron_man_suit("iron_man_suit", 400, new int[] {5, 10, 8, 5}, 30, ItemList.STEEL, null, 4.0F),
	thor_suit("thor_suit", 400, new int[] {5, 10, 8, 5}, 30, ItemList.ASGARDIAN_STEEL, null, 4.0F),
	captain_america_suit("captain_america_suit", 400, new int[] {5, 10, 8, 5}, 30, ItemList.VIBRANIUM_INGOT, null, 4.0F);

	
	
	private String name, equipSound;
	private int durability, enchantability;
	private Item repairItem;
	private int[] damageReductionAmounts;
	private float toughness;
	
	private ArmorTierMaterialList(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness) {
		this.name = name;
		this.durability = durability;
		this.damageReductionAmounts = damageReductionAmounts;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.equipSound = equipSound;
		this.toughness = toughness;
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return durability;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return damageReductionAmounts[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		// TODO Auto-generated method stub
		return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return Ingredient.fromItems(repairItem);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Main.MODID + ":" + name;
	}

	@Override
	public float getToughness() {
		// TODO Auto-generated method stub
		return toughness;
	}
}
