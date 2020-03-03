package com.github.svakhil00.c_mcu_mod.tileentity;

import com.github.svakhil00.c_mcu_mod.init.ModTileEntityTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public class SuitChargerTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	public SuitChargerTileEntity() {
		super(ModTileEntityTypes.SUIT_CHARGER.get());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
