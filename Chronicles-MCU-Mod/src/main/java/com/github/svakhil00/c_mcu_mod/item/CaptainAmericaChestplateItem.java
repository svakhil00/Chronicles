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

public class CaptainAmericaChestplateItem extends CaptainAmericaSuitItem {

	public CaptainAmericaChestplateItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.CAPTAIN_AMERICA_HELMET.get()) {
			if (player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.CAPTAIN_AMERICA_LEGGINGS
					.get()) {
				if (player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.CAPTAIN_AMERICA_BOOTS
						.get()) {
					if (!world.isRemote) {
						CompoundNBT tag = new CompoundNBT();
						tag = stack.getOrCreateTag();
						player.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 2));
						player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 0));
						player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 1));
						if (!player.isPotionActive(Effects.HEALTH_BOOST)) {
							player.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 2));
							float health = tag.getFloat("heal");
							if (health == 0) {
								player.heal(12);
							} else {
								player.setHealth(health);
							}
						}
						tag.putFloat("heal", player.getHealth());
					}
				}
			}
		}
	}

}
