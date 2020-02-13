package com.github.svakhil00.c_mcu_mod.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomEntityType {
	private CustomEntityType() {}
	
	public static final DeferredRegister<EntityType<?>> REG = new DeferredRegister<>(ForgeRegistries.ENTITIES, Main.MODID);
	
	public static final RegistryObject<EntityType<MjolnirEntity>> MJOLNIR = REG.register("mjolnir", () ->
		EntityType.Builder.<MjolnirEntity>create(MjolnirEntity::new, EntityClassification.MISC)
			.size(20.0F, 20.0F)
			.setTrackingRange(10)
			.setUpdateInterval(1)
			.setShouldReceiveVelocityUpdates(true)
			.setCustomClientFactory((message, world) -> new MjolnirEntity(world))
			.build(Main.MODID + ":mjolnir")	
			);
}
