package com.github.svakhil00.c_ah_mod.entity.projectile;

import com.github.svakhil00.c_ah_mod.init.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TNTArrowEntity extends AbstractArrowEntity {

	protected TNTArrowEntity(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z,
			World worldIn) {
		super(type, x, y, z, worldIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		// TODO Auto-generated method stub
		super.onHit(raytraceResultIn);
		this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 4.0F,
				Explosion.Mode.BREAK);
	}

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return new ItemStack(ModItems.TNT_ARROW.get());
	}

}
