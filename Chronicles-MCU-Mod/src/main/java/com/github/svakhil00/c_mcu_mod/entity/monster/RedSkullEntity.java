package com.github.svakhil00.c_mcu_mod.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class RedSkullEntity extends MonsterEntity implements IRangedAttackMob {

	public RedSkullEntity(EntityType<? extends RedSkullEntity> type, World worldIn) {
		super(type, worldIn);
		this.getNavigator().setCanSwim(true);
		// TODO Auto-generated constructor stub
	}

	protected void registerGoals() {
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TestEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}
	
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.35F);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	   }

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		this.launchWitherSkullToEntity(0, target);
	}
	
	private void launchWitherSkullToEntity(int p_82216_1_, LivingEntity p_82216_2_) {
	      this.launchWitherSkullToCoords(p_82216_1_, p_82216_2_.getPosX(), p_82216_2_.getPosY() + (double)p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.getPosZ(), p_82216_1_ == 0 && this.rand.nextFloat() < 0.001F);
	   }
	
	private void launchWitherSkullToCoords(int p_82209_1_, double x, double y, double z, boolean invulnerable) {
	      this.world.playEvent((PlayerEntity)null, 1024, new BlockPos(this), 0);
	      double d0 = this.getPosX();
	      double d1 = this.getPosY();
	      double d2 = this.getPosZ();
	      double d3 = x - d0;
	      double d4 = y - d1;
	      double d5 = z - d2;
	      WitherSkullEntity witherskullentity = new WitherSkullEntity(this.world, this, d3, d4, d5);
	      if (invulnerable) {
	         witherskullentity.setSkullInvulnerable(true);
	      }

	      witherskullentity.setRawPosition(d0, d1, d2);
	      this.world.addEntity(witherskullentity);
	   }
	
}
