package com.github.svakhil00.c_mcu_mod.item;

import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class CaptainAmericaChestplateItem extends CaptainAmericaSuitItem{

	public CaptainAmericaChestplateItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityIn;
			if(playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.CAPTAIN_AMERICA_CHESTPLATE) {
				if(playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.CAPTAIN_AMERICA_HELMET) {
					if(playerEntity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.CAPTAIN_AMERICA_LEGGINGS) {
						if(playerEntity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.CAPTAIN_AMERICA_BOOTS) {
							if (!playerEntity.isPotionActive(Effects.SPEED)) {
								playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 2));
							}
							
							if (!playerEntity.isPotionActive(Effects.HEALTH_BOOST)) {
								playerEntity.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 1200, 2));
								playerEntity.heal(6);
							}
							if (!playerEntity.isPotionActive(Effects.STRENGTH)) {
								playerEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 0));
							}
							if (!playerEntity.isPotionActive(Effects.JUMP_BOOST)) {
								playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 1));
							}
						}
					}
				}
			}
		}
	}
	
}
