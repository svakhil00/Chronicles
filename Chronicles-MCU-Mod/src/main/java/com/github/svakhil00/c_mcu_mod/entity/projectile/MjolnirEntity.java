package com.github.svakhil00.c_mcu_mod.entity.projectile;

import com.github.svakhil00.c_mcu_mod.ModEventSubscriber;
import com.github.svakhil00.c_mcu_mod.entity.CustomEntitys;
import com.github.svakhil00.c_mcu_mod.init.ModItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class MjolnirEntity extends AbstractArrowEntity {
	private static final DataParameter<Boolean> field_226571_aq_ = EntityDataManager.createKey(MjolnirEntity.class,
			DataSerializers.BOOLEAN);
	private ItemStack thrownStack = new ItemStack(ModItems.MJOLNIR);
	private boolean dealtDamage;
	public int returningTicks;

	public MjolnirEntity(final World WORLDIN) {
		this(CustomEntitys.MJOLNIR.orElseThrow(IllegalStateException::new), WORLDIN);
	}

	public MjolnirEntity(EntityType<? extends MjolnirEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public MjolnirEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
		super(CustomEntitys.MJOLNIR.orElseThrow(IllegalStateException::new), thrower, worldIn);
		this.thrownStack = thrownStackIn.copy();
		this.dataManager.set(field_226571_aq_, thrownStackIn.hasEffect());
	}

	@OnlyIn(Dist.CLIENT)
	public MjolnirEntity(World worldIn, double x, double y, double z) {
		super(CustomEntitys.MJOLNIR.orElseThrow(IllegalStateException::new), x, y, z, worldIn);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(field_226571_aq_, false);
	}

	public void tick() {
		if (this.timeInGround > 4) {
			this.dealtDamage = true;
		}

		Entity entity = this.getShooter();
		if ((this.dealtDamage || this.getNoClip()) && entity != null) {
			if (!this.shouldReturnToThrower()) {
				if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
					this.entityDropItem(this.getArrowStack(), 0.1F);
				}

				this.remove();
			} else {
				int comeBackSpeed = 3;
				this.setNoClip(true);
				Vec3d vec3d = new Vec3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(),
						entity.getPosZ() - this.getPosZ());
				this.setRawPosition(this.getPosX(), this.getPosY() + vec3d.y * 0.015D * (double) comeBackSpeed,
						this.getPosZ());
				if (this.world.isRemote) {
					this.lastTickPosY = this.getPosY();
				}

				double d0 = 0.05D * (double) comeBackSpeed;
				this.setMotion(this.getMotion().scale(0.95D).add(vec3d.normalize().scale(d0)));
				
				++this.returningTicks;
			}
		}
		super.tick();
	}

	private boolean shouldReturnToThrower() {
		Entity entity = this.getShooter();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
		} else {
			return false;
		}
	}

	protected ItemStack getArrowStack() {
		return this.thrownStack.copy();
	}

	@OnlyIn(Dist.CLIENT)
	public boolean func_226572_w_() {
		return this.dataManager.get(field_226571_aq_);
	}

	protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) {
		return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
	}

	protected void onEntityHit(EntityRayTraceResult result) {
		Entity entity = result.getEntity();
		float damage = 15.0F;

		Entity entity1 = this.getShooter();
		DamageSource damagesource = DamageSource.causeTridentDamage(this, (Entity) (entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = ModEventSubscriber.ITEM_MJOLNIR_HIT;
		if (entity.attackEntityFrom(damagesource, damage)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity1 = (LivingEntity) entity;
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.applyThornEnchantments(livingentity1, entity1);
					EnchantmentHelper.applyArthropodEnchantments((LivingEntity) entity1, livingentity1);
				}

				this.arrowHit(livingentity1);
			}
		}
		this.setMotion(this.getMotion().mul(-0.1D, -0.1D, -0.01D));
		this.playSound(soundevent, 1.0F, 1.0F);
	}

	protected SoundEvent getHitEntitySound() {
		return ModEventSubscriber.ITEM_MJOLNIR_HIT;
	}

	public void onCollideWithPlayer(PlayerEntity entityIn) {
		Entity entity = this.getShooter();
		if (entity == null || entity.getUniqueID() == entityIn.getUniqueID()) {
			super.onCollideWithPlayer(entityIn);
		}
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("Mjolnir", 10)) {
			this.thrownStack = ItemStack.read(compound.getCompound("Mjolnir"));
		}
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.put("Mjolnir", this.thrownStack.write(new CompoundNBT()));
		compound.putBoolean("DealtDamage", this.dealtDamage);
	}

	protected float getWaterDrag() {
		return 0.99F;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}

	public ItemStack getItemStack() {
		return thrownStack;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static MjolnirEntity spawnOnClient(FMLPlayMessages.SpawnEntity spawnPacket, World world) {
		return new MjolnirEntity(world);
	}

}
