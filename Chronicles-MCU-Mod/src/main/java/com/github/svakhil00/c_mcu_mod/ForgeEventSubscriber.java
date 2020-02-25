package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.init.ModItems;
import com.github.svakhil00.c_mcu_mod.item.MjolnirItem.Mode;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)

public class ForgeEventSubscriber {

	@SubscribeEvent
	public static void lightning(EntityStruckByLightningEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
			if (playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModItems.THOR_HELMET) {
				if (playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModItems.THOR_CHESTPLATE) {
					if (playerEntity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ModItems.THOR_LEGGINGS) {
						if (playerEntity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ModItems.THOR_BOOTS) {
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onCraft(ItemCraftedEvent event) {
		if (event.getCrafting().getItem().getItem() == ModItems.MJOLNIR) {
			PlayerEntity playerEntity = event.getPlayer();
			World world = playerEntity.world;
			LightningBoltEntity lightning = new LightningBoltEntity(world, playerEntity.getPosX(),
					playerEntity.getPosY(), playerEntity.getPosZ(), false);
			lightning.setGlowing(true);
			if (!world.isRemote) {
				((ServerWorld) world).addLightningBolt(lightning);
			}
		}
	}

	private static boolean needToPop = false;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
		if (isFlying(event.getPlayer())) {
			if (event.getPlayer().world.isRemote) {
				ClientPlayerEntity playerEntity = (ClientPlayerEntity) event.getPlayer();
				float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
				double interpolatedYaw = (playerEntity.prevRotationYaw
						+ (playerEntity.rotationYaw - playerEntity.prevRotationYaw) * partialTicks);
				MatrixStack matrixStack = event.getMatrixStack();
				float pitch = playerEntity.rotationPitch;

				playerEntity.limbSwingAmount = 0;
				matrixStack.push();
				matrixStack.rotate(new Quaternion(90, 0, (float) interpolatedYaw, true));
				matrixStack.translate(0, -playerEntity.getHeight() / 2, -playerEntity.getHeight() * 1 / 2);
				matrixStack.rotate(new Quaternion(pitch, (float) interpolatedYaw, 0, true));
				needToPop = true;
			}
		}

	}

	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public static void onRender(RenderPlayerEvent.Post event) {
		if (needToPop) {
			needToPop = false;
			MatrixStack matrixStack = event.getMatrixStack();
			matrixStack.pop();
		}
	}

	// ----------------------------------------------------------------------helpers------------------------------------------------------------------------

	private static boolean isFlying(PlayerEntity playerEntityIn) {

		ItemStack itemStack = playerEntityIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		ItemStack bootsItemStack = playerEntityIn.getItemStackFromSlot(EquipmentSlotType.FEET);
		if (itemStack.getItem() == ModItems.MJOLNIR) {
			CompoundNBT tag = itemStack.getOrCreateTag();
			Mode mode = Mode.byName(tag.getString("mode"));
			if (mode == Mode.FLIGHT) {
				if (playerEntityIn.isElytraFlying()) {
					return false;
				}
				if (playerEntityIn.isSwimming()) {
					return false;
				}
				if (playerEntityIn instanceof ClientPlayerEntity) {
					CompoundNBT tag2 = itemStack.getOrCreateTag();
					return tag2.getBoolean("flight");
				}

			}
		} else if (bootsItemStack.getItem() == ModItems.IRON_MAN_BOOTS) {
			if (playerEntityIn.isElytraFlying()) {
				return false;
			}
			if (playerEntityIn.isSwimming()) {
				return false;
			}
			CompoundNBT tag = bootsItemStack.getOrCreateTag();
			return tag.getBoolean("flight");

		}

		return false;
	}

}
