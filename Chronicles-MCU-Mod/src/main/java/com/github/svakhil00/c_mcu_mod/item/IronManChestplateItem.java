package com.github.svakhil00.c_mcu_mod.item;

import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class IronManChestplateItem extends IronManSuitItem {

	public IronManChestplateItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.IRON_MAN_HELMET.get()) {
			if (player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.IRON_MAN_LEGGINGS
					.get()) {
				if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.IRON_MAN_BOOTS
						.get()) {
					if (!world.isRemote) {
						player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 400, 0));
					}
				}
			}
		}
		
		
		
		
		CompoundNBT tag = stack.getOrCreateTag();
		int energy = tag.getInt("energy");

		if (energy < 999) {
			energy += 1;
		}
		tag.putInt("energy", energy);
	}

}
