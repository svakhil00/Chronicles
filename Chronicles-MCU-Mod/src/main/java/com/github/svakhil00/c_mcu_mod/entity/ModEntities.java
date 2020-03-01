package com.github.svakhil00.c_mcu_mod.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.entity.projectile.CaptainAmericaShieldEntity;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomEntitys {
	private CustomEntitys() {
	}

	public static final DeferredRegister<EntityType<?>> REG = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			Main.MODID);

	public static final RegistryObject<EntityType<MjolnirEntity>> MJOLNIR = REG.register("mjolnir", () ->
		EntityType.Builder.<MjolnirEntity>create(MjolnirEntity::new, EntityClassification.MISC)
			.size(5.0F, 5.0F)
			.setTrackingRange(10)
			.setUpdateInterval(1)
			.setShouldReceiveVelocityUpdates(true)
			.immuneToFire()
			.setCustomClientFactory((message, world) -> new MjolnirEntity(world))
			.build(Main.MODID + ":mjolnir")	
			);
	
	public static final RegistryObject<EntityType<CaptainAmericaShieldEntity>> CAPTAIN_AMERICA_SHIELD = REG.register("captain_america_shield", () ->
		EntityType.Builder.<CaptainAmericaShieldEntity>create(CaptainAmericaShieldEntity::new, EntityClassification.MISC)
		.size(5.0F, 5.0F)
		.setTrackingRange(10)
		.setUpdateInterval(1)
		.setShouldReceiveVelocityUpdates(true)
		.immuneToFire()
		.setCustomClientFactory((message, world) -> new CaptainAmericaShieldEntity(world))
		.build(Main.MODID + ":captain_america_shield")	
		);
}
