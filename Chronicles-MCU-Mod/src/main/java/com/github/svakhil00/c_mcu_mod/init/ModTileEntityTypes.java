package com.github.svakhil00.c_mcu_mod.init;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.tileentity.SuitChargerTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, Main.MODID);

	public static final RegistryObject<TileEntityType<SuitChargerTileEntity>> SUIT_CHARGER = TILE_ENTITY_TYPES.register(
			"suit_charger",
			() -> TileEntityType.Builder.create(SuitChargerTileEntity::new, ModBlocks.SUIT_CHARGER.get()).build(null));

}
