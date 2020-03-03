package com.github.svakhil00.c_mcu_mod.client;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.DestroyerRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.TestRenderer;
import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TEST.get(), TestRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DESTROYER.get(), DestroyerRenderer::new);
		Main.LOGGER.debug("Registered Entity Renderers");
	}
}
