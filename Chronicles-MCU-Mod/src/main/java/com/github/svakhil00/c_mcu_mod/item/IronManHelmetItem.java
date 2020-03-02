package com.github.svakhil00.c_mcu_mod.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.svakhil00.c_mcu_mod.init.ModItems;
import com.github.svakhil00.c_mcu_mod.util.helpers.KeyboardHelper;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
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
			tooltip.add(new StringTextComponent("If powered, gives you night vision and water breathing\nEquipping the entire suit gives you resistance to the elements"));
		} else {
			tooltip.add(new StringTextComponent("Hold SHIFT for more information"));
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if (entityIn instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entityIn;

			if (playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.IRON_MAN_CHESTPLATE.get()) {
				if (playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.IRON_MAN_HELMET.get()) {

					if (!worldIn.isRemote) {
							playerEntity.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING));
						
							playerEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 400));
					}
				}
			}
		}
	}
}
