package com.github.svakhil00.c_mcu_mod.entity.monster;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.block.BlockState;
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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FrostGiantEntity extends MonsterEntity{
	
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
			//this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
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
		this.world.setEntityState(this, (byte)4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8);
		if(flag) {
			if(entityIn instanceof LivingEntity) {
				LivingEntity livingEntity = (PlayerEntity) entityIn;
				livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 200));
			}
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}
	
	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		if(typeIn == ModEntityTypes.FROST_GIANT.get()) {
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
