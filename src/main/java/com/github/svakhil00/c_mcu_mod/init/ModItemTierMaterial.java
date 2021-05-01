package com.github.svakhil00.c_mcu_mod.init;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ModItemTierMaterial implements IItemTier{
	shield(12.0f, 9.0f, 10000, 3, 0, ModItems.STABLE_VIBRANIUM.get()),
	mjolnir(15.0f, 9.0f, 10000, 3, 0, ModItems.URU.get())
	;
	
	
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMaterial;
	
	private ModItemTierMaterial(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial) {
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return durability;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return Ingredient.fromItems(repairMaterial);
	}
}
