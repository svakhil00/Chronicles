package com.github.svakhil00.c_mcu_mod.entity.projectile;

import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.world.World;

public class DestroyerBeamEntity extends AbstractArrowEntity {

	public DestroyerBeamEntity(final World WORLDIN) {
		this(ModEntityTypes.DESTROYER_BEAM.orElseThrow(IllegalStateException::new), WORLDIN);
	}

	public DestroyerBeamEntity(EntityType<? extends DestroyerBeamEntity> p_i50172_1_, World p_i50172_2_) {
		super(p_i50172_1_, p_i50172_2_);
	}

	public DestroyerBeamEntity(World worldIn, double x, double y, double z) {
		super(ModEntityTypes.DESTROYER_BEAM.get(), x, y, z, worldIn);
	}

	public DestroyerBeamEntity(World worldIn, LivingEntity shooter) {
		super(ModEntityTypes.DESTROYER_BEAM.get(), shooter, worldIn);
	}

	public void tick() {
	}

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}
}
