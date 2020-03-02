package com.github.svakhil00.c_mcu_mod.init;

import java.util.function.Supplier;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Main.MODID, () -> new ItemStack(ModItems.CAPTAIN_AMERICA_SHIELD.get()));
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