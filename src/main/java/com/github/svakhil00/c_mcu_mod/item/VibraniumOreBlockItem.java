package com.github.svakhil00.c_mcu_mod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class VibraniumOreBlockItem extends BlockItem{

	public VibraniumOreBlockItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
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
