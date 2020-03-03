package com.github.svakhil00.c_mcu_mod.init;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.entity.monster.DestroyerEntity;
import com.github.svakhil00.c_mcu_mod.entity.monster.TestEntity;
import com.github.svakhil00.c_mcu_mod.entity.projectile.CaptainAmericaShieldEntity;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	private ModEntityTypes() {
	}

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			Main.MODID);

	// MOBS

	public static final RegistryObject<EntityType<DestroyerEntity>> DESTROYER = ENTITY_TYPES.register("destroyer",
			() -> EntityType.Builder.<DestroyerEntity>create(DestroyerEntity::new, EntityClassification.MONSTER)
					.size(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
					.build(new ResourceLocation(Main.MODID, "destroyer").toString()));
	
	public static final RegistryObject<EntityType<TestEntity>> TEST = ENTITY_TYPES.register("test",
			() -> EntityType.Builder.<TestEntity>create(TestEntity::new, EntityClassification.MONSTER)
					.size(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
					.build(new ResourceLocation(Main.MODID, "test").toString()));

	// PROJECTILES

	public static final RegistryObject<EntityType<MjolnirEntity>> MJOLNIR = ENTITY_TYPES.register("mjolnir",
			() -> EntityType.Builder.<MjolnirEntity>create(MjolnirEntity::new, EntityClassification.MISC)
					.size(5.0F, 5.0F).setTrackingRange(10).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
					.immuneToFire().setCustomClientFactory((message, world) -> new MjolnirEntity(world))
					.build(Main.MODID + ":mjolnir"));

	public static final RegistryObject<EntityType<CaptainAmericaShieldEntity>> CAPTAIN_AMERICA_SHIELD = ENTITY_TYPES
			.register("captain_america_shield", () -> EntityType.Builder
					.<CaptainAmericaShieldEntity>create(CaptainAmericaShieldEntity::new, EntityClassification.MISC)
					.size(5.0F, 5.0F).setTrackingRange(10).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
					.immuneToFire().setCustomClientFactory((message, world) -> new CaptainAmericaShieldEntity(world))
					.build(Main.MODID + ":captain_america_shield"));
}
