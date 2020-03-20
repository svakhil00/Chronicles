package com.github.svakhil00.c_mcu_mod.client;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.CaptainAmericaShieldRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.DestroyerBeamRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.DestroyerRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.MjolnirRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.RedSkullRenderer;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.TestRenderer;
import com.github.svakhil00.c_mcu_mod.init.ModEntityTypes;

import net.minecraft.client.Minecraft;
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
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RED_SKULL.get(), RedSkullRenderer::new);
		
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MJOLNIR.get(),
				manager -> new MjolnirRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CAPTAIN_AMERICA_SHIELD.get(),
				manager -> new CaptainAmericaShieldRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DESTROYER_BEAM.get(),
				manager -> new DestroyerBeamRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		
		Main.LOGGER.debug("Registered Entity Renderers");
	}
}
