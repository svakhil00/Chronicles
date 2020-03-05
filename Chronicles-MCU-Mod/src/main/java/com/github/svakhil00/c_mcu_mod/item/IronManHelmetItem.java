package com.github.svakhil00.c_mcu_mod.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.svakhil00.c_mcu_mod.init.ModItems;
import com.github.svakhil00.c_mcu_mod.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IronManHelmetItem extends IronManSuitItem {

	public IronManHelmetItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		// TODO Auto-generated constructor stub
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(
					"If powered, gives you night vision and water breathing\nEquipping the entire suit gives you resistance to the elements"));
		} else {
			tooltip.add(new StringTextComponent("Hold SHIFT for more information"));
		}
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.IRON_MAN_CHESTPLATE.get()) {
			if (!world.isRemote) {
				if (player.isInWaterOrBubbleColumn()) {
						player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING));
				}
				if (world.getLight(player.getPosition()) < 8) {
						player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 400));
				}
			}

		}
	}

}
