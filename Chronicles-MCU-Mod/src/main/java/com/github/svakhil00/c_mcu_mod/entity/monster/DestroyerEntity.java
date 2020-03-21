package com.github.svakhil00.c_mcu_mod.entity.monster;

import com.github.svakhil00.c_mcu_mod.entity.projectile.DestroyerBeamEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DestroyerEntity extends MonsterEntity implements IRangedAttackMob {

	private int attackTimer;

	public DestroyerEntity(EntityType<? extends DestroyerEntity> type, World worldIn) {
		super(type, worldIn);
		this.getNavigator().setCanSwim(true);
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new DestroyerEntity.DestroyerAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 2.0D, 1, 50.0F));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 50.0F));
		//this.goalSelector.addGoal(6, new SwimGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TestEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		}
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
		this.world.setEntityState(this, (byte) 4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 20);
		if (flag) {
			entityIn.setMotion(entityIn.getMotion().add(0.0D, (double) .5F, 0.0D));
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

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		this.launchBeamToEntity(distanceFactor, target);
	}

	private void launchBeamToEntity(float distanceFactor, LivingEntity entityIn) {
		this.launchBeamToCoords(distanceFactor, entityIn.getPosX(),
				entityIn.getPosY() + (double) entityIn.getEyeHeight() * 0.5D, entityIn.getPosZ());
	}

	private void launchBeamToCoords(float p_82209_1_, double x, double y, double z) {
		// this.world.playEvent((PlayerEntity)null, 1024, new BlockPos(this), 0);
		double d0 = this.getPosX();
		double d1 = this.getPosYEye();
		double d2 = this.getPosZ();
		double d3 = x - d0;
		double d4 = y - d1;
		double d5 = z - d2;
		DestroyerBeamEntity destroyerbeamentity = new DestroyerBeamEntity(this.world, this, d3, d4, d5);
		destroyerbeamentity.setInvulnerable(true);
		destroyerbeamentity.setRawPosition(d0, d1, d2);
		this.world.addEntity(destroyerbeamentity);
	}
	
	class DestroyerAttackGoal extends MeleeAttackGoal{
		
		
		public DestroyerAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}
		
		@Override
		public boolean shouldExecute() {
			LivingEntity target = super.attacker.getAttackTarget();
			if(target != null) {
				double x = Math.abs(target.getPosX() - super.attacker.getPosX());
				double y = Math.abs(target.getPosY() - super.attacker.getPosY());
				double z = Math.abs(target.getPosZ() - super.attacker.getPosZ());
				if(x < 3 && y < 3 && z < 3) {
					return super.shouldExecute();
				}
			}
			return false;
		}
		
		
	}
}
