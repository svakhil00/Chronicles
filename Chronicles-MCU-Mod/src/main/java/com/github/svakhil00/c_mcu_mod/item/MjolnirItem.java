package com.github.svakhil00.c_mcu_mod.item;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.svakhil00.c_mcu_mod.ModEventSubscriber;
import com.github.svakhil00.c_mcu_mod.entity.player.CustomPlayerEntity;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;
import com.google.common.collect.Multimap;

import net.minecraft.block.BlockState;
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
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MjolnirItem extends TieredItem {
	private final float ATTACKDAMAGE, ATTACKSPEED;

	public MjolnirItem(IItemTier tierIn, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tierIn, builder);
		ATTACKDAMAGE = (float) attackDamageIn + tierIn.getAttackDamage();
		ATTACKSPEED = attackSpeedIn;
		this.addPropertyOverride(new ResourceLocation("throwing"), (p_210315_0_, p_210315_1_, p_210315_2_) -> {
			return p_210315_2_ != null && p_210315_2_.isHandActive() && p_210315_2_.getActiveItemStack() == p_210315_0_
					? 1.0F
					: 0.0F;
		});
	}

	public float getAttackDamage() {
		return ATTACKDAMAGE;
	}

	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL
				&& !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = attacker.world;
		world.playSound(null, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(),
				ModEventSubscriber.ITEM_MJOLNIR_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		stack.damageItem(1, attacker, (p_220045_0_) -> {
			p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		return true;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		CompoundNBT tag = stack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));
		if (mode == Mode.LIGHTNING) {
			return UseAction.NONE;
		} else if (mode == Mode.FLIGHT) {
			return UseAction.SPEAR;
		} else if (mode == Mode.PROJECTILE) {
			return UseAction.SPEAR;
		}
		return UseAction.NONE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		CustomPlayerEntity playerTest = new CustomPlayerEntity(playerIn.world, playerIn.getGameProfile());
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		CompoundNBT tag = itemStack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));

		if (playerIn.isShiftKeyDown()) {
			tag.putString("mode", mode.cycle().getName());
			// playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);
		}
		tag.putString("mode", mode.getName());
		if (mode == Mode.FLIGHT) {
			if (!playerIn.isInWaterOrBubbleColumn()) {
				worldIn.playMovingSound((PlayerEntity) null, playerIn, ModEventSubscriber.ITEM_MJOLNIR_FLIGHT,
						SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
			float yaw = playerIn.rotationYaw;
			float pitch = playerIn.rotationPitch;
			int launchFactor = 1;
			float f1 = -MathHelper.sin(yaw * ((float) Math.PI / 180F))
					* MathHelper.cos(pitch * ((float) Math.PI / 180F));
			float f2 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
			;
			float f3 = MathHelper.cos(yaw * ((float) Math.PI / 180F))
					* MathHelper.cos(pitch * ((float) Math.PI / 180F));
			float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
			float f5 = 3.0F * ((1.0F + (float) launchFactor) / 4.0F);

			f1 = f1 * (f5 / f4);
			f2 = f2 * (f5 / f4);
			f3 = f3 * (f5 / f4);
			playerIn.startSpinAttack(20);
			playerIn.setVelocity((double) f1, (double) f2, (double) f3);
			return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);
		} else if (mode == Mode.LIGHTNING) {
			Vec3d look = new Vec3d(0, 0, 0);
			boolean block = false;
			if (!playerIn.pick(100.0D, 1.0F, false).getType().equals(Type.MISS)) {
				// worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(),
				// playerIn.getPosZ(),ModEventSubscriber.ITEM_MJOLNIR_LIGHTNING,
				// SoundCategory.PLAYERS, 2.0F, 1.0F);
				look = playerIn.pick(100.D, 1.0F, false).getHitVec();
				block = true;
			}
			if (block) {
				LightningBoltEntity lightning = new LightningBoltEntity(worldIn, look.x, look.y, look.z, false);
				lightning.setGlowing(true);
				if (!worldIn.isRemote) {
					((ServerWorld) worldIn).addLightningBolt(lightning);
					playerIn.getCooldownTracker().setCooldown(this, 20);
				}
			}
			playerIn.setActiveHand(handIn);

			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
		} else if (mode == Mode.PROJECTILE) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(ActionResultType.CONSUME, itemStack);
		}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);

	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		CompoundNBT tag = stack.getOrCreateTag();
		Mode mode = Mode.byName(tag.getString("mode"));
		if (mode == Mode.PROJECTILE) {
			if (entityLiving instanceof PlayerEntity) {
				PlayerEntity playerentity = (PlayerEntity) entityLiving;
				if (!worldIn.isRemote) {
					stack.damageItem(1, playerentity, (p_220047_1_) -> {
						p_220047_1_.sendBreakAnimation(entityLiving.getActiveHand());
					});
					MjolnirEntity mjolnirentity = new MjolnirEntity(worldIn, playerentity, stack);
					mjolnirentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, 2.5F,
							1.0F);
					worldIn.addEntity(mjolnirentity);
					playerentity.inventory.deleteStack(stack);
					worldIn.playMovingSound((PlayerEntity) null, mjolnirentity, ModEventSubscriber.ITEM_MJOLNIR_THROW,
							SoundCategory.PLAYERS, 2.0F, 1.0F);
				}
			}
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos,
			LivingEntity entityLiving) {
		if (state.getBlockHardness(worldIn, pos) != 0.0F) {
			stack.damageItem(2, entityLiving, (p_220044_0_) -> {
				p_220044_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		return true;
	}

	@Override
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
		LIGHTNING("lightning"), FLIGHT("flight"), PROJECTILE("projectile");
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
			return NAMED.getOrDefault(name, Mode.LIGHTNING);
		}

		public Mode cycle() {
			switch (this) {
			case LIGHTNING:
				return FLIGHT;
			case FLIGHT:
				return PROJECTILE;
			case PROJECTILE:
				return LIGHTNING;
			default:
				throw new IllegalStateException(this.getName());
			}
		}
	}
}
