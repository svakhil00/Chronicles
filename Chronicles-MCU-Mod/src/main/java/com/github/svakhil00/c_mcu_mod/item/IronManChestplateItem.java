package com.github.svakhil00.c_mcu_mod.item;

import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class IronManChestplateItem extends IronManSuitItem {

	public IronManChestplateItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		CompoundNBT tag = stack.getOrCreateTag();
		int energy = tag.getInt("energy");
		
		if(energy < 3999) {
			energy += 2;
		}else {
			energy = 4000;
		}
		tag.putInt("energy", energy);
	}

}
