package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.lists.ItemList;

import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ModEventBusSubscriberForge {
	
	public static void anvilListener(AnvilUpdateEvent event) {
		//item stack
		if(event.getLeft().getItem().equals(ItemList.MJOLNIR) || event.getRight().getItem().equals(ItemList.MJOLNIR)) {
			event.setCanceled(true);
		}
	}
}
