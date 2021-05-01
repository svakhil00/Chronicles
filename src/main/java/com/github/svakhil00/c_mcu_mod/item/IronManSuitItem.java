package com.github.svakhil00.c_mcu_mod.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.svakhil00.c_mcu_mod.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IronManSuitItem extends ArmorItem{

	public IronManSuitItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		
	}

	@OnlyIn(Dist.CLIENT)
	   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		 if(KeyboardHelper.isHoldingShift()) {
			 tooltip.add(new StringTextComponent("Suit must be charged to use abilities\nEquipping the entire suit gives you resistance to the elements"));
		 }else {
			 tooltip.add(new StringTextComponent("Hold SHIFT for more information"));
		 }
	   }
}
