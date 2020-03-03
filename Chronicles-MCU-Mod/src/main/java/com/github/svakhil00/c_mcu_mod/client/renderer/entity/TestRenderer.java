package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.entity.monster.TestEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TestRenderer extends MobRenderer<TestEntity, IronGolemModel<TestEntity>>{

	private static final ResourceLocation TEST_TEXTURE = new ResourceLocation("textures/entity/iron_golem/iron_golem.png");
	
	public TestRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new IronGolemModel<>(), .7F);
		// TODO Auto-generated constructor stub
	}
	final 
	@Override
	public ResourceLocation getEntityTexture(final TestEntity entity) {
		return TEST_TEXTURE;
	}

}
