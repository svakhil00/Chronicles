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
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityIn;
			if (playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST)
					.getItem() == ModItems.CAPTAIN_AMERICA_CHESTPLATE.get()) {
				if (playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD)
						.getItem() == ModItems.CAPTAIN_AMERICA_HELMET.get()) {
					if (playerEntity.getItemStackFromSlot(EquipmentSlotType.LEGS)
							.getItem() == ModItems.CAPTAIN_AMERICA_LEGGINGS.get()) {
						if (playerEntity.getItemStackFromSlot(EquipmentSlotType.FEET)
								.getItem() == ModItems.CAPTAIN_AMERICA_BOOTS.get()) {
							if (!worldIn.isRemote) {
								CompoundNBT tag = new CompoundNBT();
								tag = stack.getOrCreateTag();
								playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 2));
								playerEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 0));
								playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 1));
								if (!playerEntity.isPotionActive(Effects.HEALTH_BOOST)) {
									playerEntity.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 2));
									float health = tag.getFloat("heal");
									if (health == 0) {
										playerEntity.heal(12);
									} else {
										playerEntity.setHealth(health);
									}
								}
								tag.putFloat("heal", playerEntity.getHealth());
							}
						}
					}
				}
			}
		}
	}

}
