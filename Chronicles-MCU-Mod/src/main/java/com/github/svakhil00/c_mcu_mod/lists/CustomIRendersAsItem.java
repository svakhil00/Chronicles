package com.github.svakhil00.c_mcu_mod.lists;

import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.ItemStack;

public enum CustomIRendersAsItem implements IRendersAsItem{
	mjolnir(new ItemStack(ItemList.MJOLNIR));
	
	private ItemStack itemStack;
	
	private CustomIRendersAsItem(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	@Override
	public ItemStack getItem() {
		// TODO Auto-generated method stub
		return itemStack;
	}
	;
	

}
