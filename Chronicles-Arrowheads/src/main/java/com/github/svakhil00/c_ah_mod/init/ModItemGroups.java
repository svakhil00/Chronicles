package com.github.svakhil00.c_ah_mod.init;


import java.util.function.Supplier;

import com.github.svakhil00.c_ah_mod.Arrowheads;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Arrowheads.MODID, () -> new ItemStack(ModItems.TNT_ARROW.get()));
}

class ModItemGroup extends ItemGroup{
	
	private final Supplier<ItemStack> iconSupplier;
	
	public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
		super(name);
		this.iconSupplier = iconSupplier;
	}
	
	@Override
	public ItemStack createIcon() {
		return iconSupplier.get();
	} 
}