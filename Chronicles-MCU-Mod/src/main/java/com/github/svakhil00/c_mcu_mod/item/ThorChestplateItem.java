package com.github.svakhil00.c_mcu_mod.item;

import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ThorChestplateItem extends ThorSuitItem {

	public ThorChestplateItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.THOR_HELMET.get()) {
			if (player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.THOR_LEGGINGS.get()) {
				if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.THOR_BOOTS.get()) {
					if (!world.isRemote) {
						CompoundNBT tag = new CompoundNBT();
						tag = stack.getOrCreateTag();
						player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 1));
						player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 1));
						if (!player.isPotionActive(Effects.HEALTH_BOOST)) {
							player.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 3));
							float health = tag.getFloat("heal");
							if (player.getHealth() > health) {
								health = player.getHealth();
							}
							player.setHealth(health);
						}
						tag.putFloat("heal", player.getHealth());
					}
				}
			}

		}
	}

}
