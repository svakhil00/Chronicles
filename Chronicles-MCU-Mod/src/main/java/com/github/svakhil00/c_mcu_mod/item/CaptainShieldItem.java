package com.github.svakhil00.c_mcu_mod.item;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.svakhil00.c_mcu_mod.ModEventSubscriber;
import com.github.svakhil00.c_mcu_mod.entity.projectile.CaptainAmericaShieldEntity;
import com.github.svakhil00.c_mcu_mod.item.MjolnirItem.Mode;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CaptainShieldItem extends TieredItem {
	private final float ATTACKDAMAGE, ATTACKSPEED;

	public CaptainShieldItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tierIn, builder);
		this.addPropertyOverride(new ResourceLocation("blocking"), (stack, p_210314_1_, playerIn) -> {
			CompoundNBT tag = stack.getOrCreateTag();
			Mode mode = Mode.byName(tag.getString("mode"));
			return playerIn != null && playerIn.isHandActive() && playerIn.getActiveItemStack() == stack && mode == Mode.STRAPPED
					? 1.0F
					: 0.0F;
		});
		ATTACKDAMAGE = attackDamageIn;
		ATTACKSPEED = attackSpeedIn;

	}



	public int getUseDuration(ItemStack stack) {
		return 72000;
	}


	public float getAttackDamage() {
		return this.ATTACKDAMAGE;
	}

	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
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

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if (state.getBlockHardness(worldIn, pos) != 0.0F) {
			stack.damageItem(2, entityLiving, (p_220044_0_) -> {
				p_220044_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		return true;
	}

	public UseAction getUseAction(ItemStack stack) {
		CompoundNBT tag = stack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));
		if(mode == Mode.STRAPPED) {
			return UseAction.BLOCK;
		}else if(mode == Mode.UNSTRAPPED){
			return UseAction.NONE;
		}
		return UseAction.NONE;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		CompoundNBT tag = itemStack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));

		if (playerIn.isShiftKeyDown()) {
			tag.putString("mode", mode.cycle().getName());
			return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);

		}

		tag.putString("mode", mode.getName());

		if (mode == Mode.STRAPPED) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(ActionResultType.CONSUME, itemStack);
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		CompoundNBT tag = stack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));

		if (mode == Mode.UNSTRAPPED) {
			if (entityLiving instanceof PlayerEntity) {
				PlayerEntity playerEntity = (PlayerEntity) entityLiving;
				if (!worldIn.isRemote) {
					stack.damageItem(1, playerEntity, (p_220047_1_) -> {
						p_220047_1_.sendBreakAnimation(playerEntity.getActiveHand());
					});
					CaptainAmericaShieldEntity shieldEntity = new CaptainAmericaShieldEntity(worldIn, playerEntity,
							stack);
					shieldEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 2.5F,
							1.0F);
					worldIn.addEntity(shieldEntity);
					playerEntity.inventory.deleteStack(stack);
					worldIn.playMovingSound((PlayerEntity) null, shieldEntity, ModEventSubscriber.ITEM_MJOLNIR_THROW,
							SoundCategory.PLAYERS, 2.0F, 1.0F);
				}
			}
		}
	}

	public boolean canHarvestBlock(BlockState blockIn) {
		return blockIn.getBlock() == Blocks.COBWEB;
	}

	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", (double) this.ATTACKDAMAGE, AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER,
					"Weapon modifier", (double) this.ATTACKSPEED, AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

	public enum Mode {
		STRAPPED("strapped"), UNSTRAPPED("unstrapped");
		private static final Map<String, Mode> NAMED = Arrays.stream(values())
				.collect(Collectors.toMap(Mode::getName, v -> v));

		private final String name;

		Mode(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public static Mode byName(String name) {
			return NAMED.getOrDefault(name, Mode.STRAPPED);
		}

		public Mode cycle() {
			switch (this) {
			case STRAPPED:
				return UNSTRAPPED;
			case UNSTRAPPED:
				return STRAPPED;
			default:
				throw new IllegalStateException(this.getName());
			}
		}
	}
}
