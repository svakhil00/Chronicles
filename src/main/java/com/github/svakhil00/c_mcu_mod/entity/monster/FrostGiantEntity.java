package com.github.svakhil00.c_mcu_mod.entity.monster;

import java.util.UUID;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FrostGiantEntity extends MonsterEntity {

	private int attackTimer;
	private int angerLevel;
	private int randomSoundDelay;
	private UUID angerTargetUUID;

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
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));//.setCallsForHelp(FrostGiantEntity.class));
		//this.targetSelector.addGoal(1, new FrostGiantEntity.HurtByAggressorGoal(this));
		//this.targetSelector.addGoal(2, new FrostGiantEntity.TargetAggressorGoal(this));
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
	
	@Override
	protected void updateAITasks() {
	      LivingEntity livingentity = this.getRevengeTarget();
	      if (this.isAngry()) {
	         --this.angerLevel;
	         LivingEntity livingentity1 = livingentity != null ? livingentity : this.getAttackTarget();
	         if (!this.isAngry() && livingentity1 != null) {
	            if (!this.canEntityBeSeen(livingentity1)) {
	               this.setRevengeTarget((LivingEntity)null);
	               this.setAttackTarget((LivingEntity)null);
	            } else {
	               this.angerLevel = 1200;
	            }
	         }
	      }

	      if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
	         //this.playSound(SoundEvents.ENTITY_ZOMBIE_PIGMAN_ANGRY, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
	      }

	      if (this.isAngry() && this.angerTargetUUID != null && livingentity == null) {
	         PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
	         this.setRevengeTarget(playerentity);
	         this.attackingPlayer = playerentity;
	         this.recentlyHit = this.getRevengeTimer();
	      }
		super.updateAITasks();
	}

	@Override
	public void setRevengeTarget(LivingEntity livingBase) {
		super.setRevengeTarget(livingBase);
		if (livingBase != null) {
	         this.angerTargetUUID = livingBase.getUniqueID();
	      }
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

	public void writeAdditional(CompoundNBT compound) {
	      super.writeAdditional(compound);
	      compound.putShort("Anger", (short)this.angerLevel);
	      if (this.angerTargetUUID != null) {
	         compound.putString("HurtBy", this.angerTargetUUID.toString());
	      } else {
	         compound.putString("HurtBy", "");
	      }

	   }
	
	public void readAdditional(CompoundNBT compound) {
	      super.readAdditional(compound);
	      this.angerLevel = compound.getShort("Anger");
	      String s = compound.getString("HurtBy");
	      if (!s.isEmpty()) {
	         this.angerTargetUUID = UUID.fromString(s);
	         PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
	         this.setRevengeTarget(playerentity);
	         if (playerentity != null) {
	            this.attackingPlayer = playerentity;
	            this.recentlyHit = this.getRevengeTimer();
	         }
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

	


	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8);
		if (flag) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entityIn;
				System.out.println("test");
				livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 2));
			}
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entity = source.getTrueSource();
        if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative() && this.canEntityBeSeen(entity)) {
        	this.angerLevel = 400 + this.rand.nextInt(400);
            this.randomSoundDelay = this.rand.nextInt(40);
            this.setRevengeTarget((LivingEntity) entity);
        }
		return super.attackEntityFrom(source, amount);
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
	public boolean isPreventingPlayerRest(PlayerEntity playerIn) {
		return this.isAngry();
	}

	private boolean isAngry() {
		return this.angerLevel > 0;
	}
/*
	private boolean setAngry(LivingEntity p_226547_1_) {
		this.angerLevel = 1200;
		this.randomSoundDelay = this.rand.nextInt(40);
		this.setRevengeTarget(p_226547_1_);
		return true;
	}
	*/

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	/*
	static class HurtByAggressorGoal extends HurtByTargetGoal {
		public HurtByAggressorGoal(FrostGiantEntity entity) {
			super(entity);
			this.setCallsForHelp(new Class[] { FrostGiantEntity.class });
		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if (mobIn instanceof FrostGiantEntity && this.goalOwner.canEntityBeSeen(targetIn)
					&& ((FrostGiantEntity) mobIn).setAngry(targetIn)) {
				mobIn.setAttackTarget(targetIn);
			}

		}
	}

	
	static class TargetAggressorGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		public TargetAggressorGoal(FrostGiantEntity entity) {
			super(entity, PlayerEntity.class, true);
		}

		
		public boolean shouldExecute() {
			return ((FrostGiantEntity) this.goalOwner).isAngry() && super.shouldExecute();
		}
	}
*/
}
