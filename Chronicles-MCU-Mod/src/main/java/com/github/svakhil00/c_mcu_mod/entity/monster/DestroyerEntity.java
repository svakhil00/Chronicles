package com.github.svakhil00.c_mcu_mod.entity.monster;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
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

public class DestroyerEntity extends MonsterEntity {
	
	private int attackTimer;

	public DestroyerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.getNavigator().setCanSwim(true);
		// TODO Auto-generated constructor stub
	}

	 @OnlyIn(Dist.CLIENT)
	   public int getAttackTimer() {
	      return this.attackTimer;
	   }
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TestEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(6, new SwimGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	public void livingTick() {
	      super.livingTick();
	      if (this.attackTimer > 0) {
	         --this.attackTimer;
	      }
	      this.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1, 10));
	   }
	
	public boolean attackEntityAsMob(Entity entityIn) {
	      this.attackTimer = 10;
	      this.world.setEntityState(this, (byte)4);
	      float f = 20;
	      float f1 = f > 0.0F ? f / 2.0F + (float)this.rand.nextInt((int)f) : 0.0F;
	      boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f1);
	      if (flag) {
	         entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.4F, 0.0D));
	         this.applyEnchantments(this, entityIn);
	      }
	      return flag;	      
	   }
	
	@Override
	public boolean canSwim() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

	@Override
	public void baseTick() {
		// TODO Auto-generated method stub
		super.baseTick();
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		final double baseSpeed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();

		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * .35D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20);
		this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
