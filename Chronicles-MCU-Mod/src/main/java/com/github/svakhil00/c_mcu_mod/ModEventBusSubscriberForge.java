package com.github.svakhil00.c_mcu_mod;

import com.github.svakhil00.c_mcu_mod.lists.ItemList;

import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class ModEventBusSubscriberForge {
	@SubscribeEvent
	public static void anvilListener(AnvilUpdateEvent event) {
		if(event.getLeft().getItem().equals(ItemList.MJOLNIR)) {
			event.setCanceled(true);
		}
		if(event.getLeft().getItem().equals(ItemList.CAPTAIN_AMERICA_SHIELD)) {
			event.setCanceled(true);
		}
		if(event.getLeft().getItem().equals(ItemList.VIBRANIUM_INGOT)) {
			event.setCanceled(true);
		}
		if(event.getLeft().getItem().equals(ItemList.VIBRANIUM_ORE_BLOCKITEM)) {
			event.setCanceled(true);
		}
	}
}
