package com.github.svakhil00.c_mcu_mod.entity.projectile;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DestroyerBeamEntity extends DamagingProjectileEntity {

	public DestroyerBeamEntity(final World WORLDIN) {
		this(ModEntityTypes.DESTROYER_BEAM.orElseThrow(IllegalStateException::new), WORLDIN);
	}

	public DestroyerBeamEntity(EntityType<? extends DestroyerBeamEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public DestroyerBeamEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
		super(ModEntityTypes.DESTROYER_BEAM.orElseThrow(IllegalStateException::new), worldIn);
	}

	public DestroyerBeamEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModEntityTypes.DESTROYER_BEAM.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	public DestroyerBeamEntity(World worldIn, double x, double y, double z, double accelX, double accelY,
			double accelZ) {
		super(ModEntityTypes.DESTROYER_BEAM.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if (!this.world.isRemote) {
			if (result.getType() == RayTraceResult.Type.ENTITY) {
				Entity entity = ((EntityRayTraceResult) result).getEntity();
				if (this.shootingEntity != null) {
					if (entity.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 10)) {
						if(entity.isAlive()) {
							entity.setFire(5);
							this.applyEnchantments(this.shootingEntity, entity);
						}else {
							this.shootingEntity.heal(5.0F);
						}
					}
				}
			} else if (result.getType() == RayTraceResult.Type.BLOCK) {
				Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(
						this.world, this.shootingEntity) ? Explosion.Mode.BREAK : Explosion.Mode.NONE;
				this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, true,
						explosion$mode);
			}
			this.remove();
		}
	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isBurning() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean canBeCollidedWith() {
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
