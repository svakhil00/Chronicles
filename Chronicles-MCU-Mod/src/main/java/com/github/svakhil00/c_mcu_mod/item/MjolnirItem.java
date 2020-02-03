package com.github.svakhil00.c_mcu_mod.item;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MjolnirItem extends TieredItem {
	private final float ATTACKDAMAGE, ATTACKSPEED;

	public MjolnirItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tierIn, builder);
		ATTACKDAMAGE = (float) attackDamageIn + tierIn.getAttackDamage();
		ATTACKSPEED = attackSpeedIn;
	}

	public float getAttackDamage() {
		return ATTACKDAMAGE;
	}

	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return true;
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL
					&& !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
	      stack.damageItem(1, attacker, (p_220045_0_) -> {
	         p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	      });
	      return true;
	   }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
			ItemStack item = playerIn.getHeldItem(handIn);
			Vec3d look = playerIn.getLookVec();
			
			LightningBoltEntity lightning = new LightningBoltEntity(worldIn, 1D, 1D, 1D, false);
			lightning.setPosition(playerIn.lastTickPosX + look.x * 1.5D, playerIn.lastTickPosY + look.y * 1.5D, playerIn.lastTickPosZ + look.z * 1.5D);
			lightning.setGlowing(true);
			
			((ServerWorld) worldIn).addLightningBolt(lightning);
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
	   } 

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
	      if (state.getBlockHardness(worldIn, pos) != 0.0F) {
	         stack.damageItem(2, entityLiving, (p_220044_0_) -> {
	            p_220044_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	         });
	      }

	      return true;
	   }
	
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
	      Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
	      if (equipmentSlot == EquipmentSlotType.MAINHAND) {
	         multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.ATTACKDAMAGE, AttributeModifier.Operation.ADDITION));
	         multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.ATTACKSPEED, AttributeModifier.Operation.ADDITION));
	      }

	      return multimap;
	   }
}
