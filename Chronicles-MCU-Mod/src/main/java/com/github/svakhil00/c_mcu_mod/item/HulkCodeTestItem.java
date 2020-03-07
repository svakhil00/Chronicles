package com.github.svakhil00.c_mcu_mod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class HulkCodeTestItem extends Item {

	public HulkCodeTestItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityIn;

			if (!worldIn.isRemote) {
				CompoundNBT tag = new CompoundNBT();
				tag = stack.getOrCreateTag();
				playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 0));
				playerEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 7));
				playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 3));
				playerEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 3));
				if (playerEntity.getHealth() < 8) {
					playerEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0));
				}
				if (!playerEntity.isPotionActive(Effects.HEALTH_BOOST)) {
					playerEntity.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 4));
					float health = tag.getFloat("heal");
					if (playerEntity.getHealth() > health) {
						health = playerEntity.getHealth();
					}
					playerEntity.setHealth(health);
				}
				tag.putFloat("heal", playerEntity.getHealth());
			}

		}
	}
}
