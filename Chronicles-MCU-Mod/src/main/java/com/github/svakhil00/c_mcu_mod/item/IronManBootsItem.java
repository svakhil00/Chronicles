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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IronManBootsItem extends IronManSuitItem {

	public IronManBootsItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		// TODO Auto-generated constructor stub
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent(
					"If powered, allows you to fly\nEquipping the entire suit gives you resistance to the elements"));
		} else {
			tooltip.add(new StringTextComponent("Hold SHIFT for more information"));
		}
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity) player;
		CompoundNBT tag = stack.getOrCreateTag();

		if (KeyboardHelper.isHoldingCtrl() && !player.onGround) {
			tag.putBoolean("flight", true);
		}
		if (tag.getBoolean("flight")) {
			if (clientPlayerEntity.movementInput.forwardKeyDown && !player.onGround) {
				// flight
				player.limbSwingAmount = 0;
				float yaw = player.rotationYaw;
				float pitch = player.rotationPitch;
				float speed = 1;

				float f1 = -MathHelper.sin(yaw * ((float) Math.PI / 180F))
						* MathHelper.cos(pitch * ((float) Math.PI / 180F));
				float f2 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
				float f3 = MathHelper.cos(yaw * ((float) Math.PI / 180F))
						* MathHelper.cos(pitch * ((float) Math.PI / 180F));
				float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
				float f5 = 3.0F * ((1.0F + speed) / 4.0F);

				f1 = f1 * (f5 / f4);
				f2 = f2 * (f5 / f4);
				f3 = f3 * (f5 / f4);

				player.setVelocity((double) f1, (double) f2, (double) f3);

			} else {
				tag.putBoolean("flight", false);
			}

		} else if (clientPlayerEntity.movementInput.jump) {
			player.limbSwingAmount = 0;
			if (player.getMotion().y < 2) {
				player.addVelocity(0, .15, 0);
			}
		}
	}
	
}
