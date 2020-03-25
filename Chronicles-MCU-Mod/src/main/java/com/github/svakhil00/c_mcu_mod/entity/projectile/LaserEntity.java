package com.github.svakhil00.c_mcu_mod.entity.projectile;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class LaserEntity extends DamagingProjectileEntity {

	public LaserEntity(final World WORLDIN) {
		this(ModEntityTypes.LASER.orElseThrow(IllegalStateException::new), WORLDIN);
	}

	public LaserEntity(EntityType<? extends LaserEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public LaserEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
		super(ModEntityTypes.LASER.orElseThrow(IllegalStateException::new), worldIn);
	}

	public LaserEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.LASER.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public LaserEntity(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.LASER.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if (!this.world.isRemote) {
			if (result.getType() == RayTraceResult.Type.ENTITY) {
				Entity entity = ((EntityRayTraceResult) result).getEntity();
				if (this.shootingEntity != null) {
					entity.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 19);
				}
			}
		}
	}
	
	@Override
	public void checkDespawn() {
		if(this.shootingEntity == null) {
			this.remove();
		}
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBePushed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
