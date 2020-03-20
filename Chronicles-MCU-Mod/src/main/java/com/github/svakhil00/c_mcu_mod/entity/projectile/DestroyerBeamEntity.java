package com.github.svakhil00.c_mcu_mod.entity.projectile;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

	public boolean isBurning() {
		return false;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if(!this.world.isRemote) {
			if(result.getType() == RayTraceResult.Type.ENTITY) {
				System.out.println("hit entity");
				Entity entity = ((EntityRayTraceResult)result).getEntity();
				if(this.shootingEntity != null) {
					entity.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 20);
				}
			}
		}
	}

}
