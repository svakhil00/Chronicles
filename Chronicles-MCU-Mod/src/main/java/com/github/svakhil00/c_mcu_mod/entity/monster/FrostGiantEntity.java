package com.github.svakhil00.c_mcu_mod.entity.monster;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FrostGiantEntity extends MonsterEntity {

	private int attackTimer;

	public FrostGiantEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.getNavigator().setCanSwim(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.0, false));
		this.goalSelector.addGoal(5, new SwimGoal(this));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 50.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TestEntity.class, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		final double baseSpeed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();

		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * .35D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
		this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(4);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			// this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}

		FrostWalkerEnchantment.freezeNearby(this, this.world, this.getPosition(), 2);
		if (!this.world.isRemote) {
			if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
				return;
			}

			int i = MathHelper.floor(this.getPosX());
			int j = MathHelper.floor(this.getPosY());
			int k = MathHelper.floor(this.getPosZ());

			BlockState blockstate = Blocks.SNOW.getDefaultState();

			for (int l = 0; l < 4; ++l) {
				i = MathHelper.floor(this.getPosX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
				j = MathHelper.floor(this.getPosY());
				k = MathHelper.floor(this.getPosZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);
				if (this.world.isAirBlock(blockpos) && this.world.getBiome(blockpos).getTemperature(blockpos) < 0.8F
						&& blockstate.isValidPosition(this.world, blockpos)) {
					this.world.setBlockState(blockpos, blockstate);
				}
			}
		}
	}

	public static void freezeNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {
		if (living.onGround) {
			BlockState blockstate = Blocks.FROSTED_ICE.getDefaultState();
			float f = (float) Math.min(16, 2 + level);
			BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add((double) (-f), -1.0D, (double) (-f)),
					pos.add((double) f, -1.0D, (double) f))) {
				if (blockpos.withinDistance(living.getPositionVec(), (double) f)) {
					blockpos$mutable.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
					BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
					if (blockstate1.isAir(worldIn, blockpos$mutable)) {
						BlockState blockstate2 = worldIn.getBlockState(blockpos);
						boolean isFull = blockstate2.getBlock() == Blocks.WATER
								&& blockstate2.get(FlowingFluidBlock.LEVEL) == 0; // TODO: Forge, modded waters?
						if (blockstate2.getMaterial() == Material.WATER && isFull
								&& blockstate.isValidPosition(worldIn, blockpos)
								&& worldIn.func_226663_a_(blockstate, blockpos, ISelectionContext.dummy())
								&& !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(living,
										new net.minecraftforge.common.util.BlockSnapshot(worldIn, blockpos,
												blockstate2),
										net.minecraft.util.Direction.UP)) {
							worldIn.setBlockState(blockpos, blockstate);
							worldIn.getPendingBlockTicks().scheduleTick(blockpos, Blocks.FROSTED_ICE,
									MathHelper.nextInt(living.getRNG(), 60, 120));
						}
					}
				}
			}

		}
	}

	@Override
	public void tick() {
		super.tick();
		FrostWalkerEnchantment.freezeNearby(this, this.world, this.getPosition(), 2);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8);
		if (flag) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entityIn;
				livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 200));
			}
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		if (typeIn == ModEntityTypes.FROST_GIANT.get()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
