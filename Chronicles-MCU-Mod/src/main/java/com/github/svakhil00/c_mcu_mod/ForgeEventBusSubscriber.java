package com.github.svakhil00.c_mcu_mod;



import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	public static void lightning(EntityStruckByLightningEvent event) {
		if(event.getEntity() instanceof PlayerEntity) {
			//do something
		}
		
		
	}
	
	
}
