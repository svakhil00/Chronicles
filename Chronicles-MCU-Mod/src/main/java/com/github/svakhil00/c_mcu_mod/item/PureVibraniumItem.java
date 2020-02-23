package com.github.svakhil00.c_mcu_mod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class PureVibraniumItem extends Item {

	public PureVibraniumItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityIn;
			if (!playerEntity.isPotionActive(Effect.get(19))) {
				playerEntity.addPotionEffect(new EffectInstance(Effects.POISON, 40, 2));
			}
		}
	}

}
