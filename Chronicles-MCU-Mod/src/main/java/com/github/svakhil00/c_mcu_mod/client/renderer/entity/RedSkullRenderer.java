package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.DestroyerModel;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.RedSkullModel;
import com.github.svakhil00.c_mcu_mod.entity.monster.DestroyerEntity;
import com.github.svakhil00.c_mcu_mod.entity.monster.RedSkullEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RedSkullRenderer extends MobRenderer<RedSkullEntity, RedSkullModel<RedSkullEntity>>{

private static final ResourceLocation RED_SKULL_TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/destroyer.png");
	
	public RedSkullRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RedSkullModel<>(), .7F);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getEntityTexture(RedSkullEntity entity) {
		// TODO Auto-generated method stub
		return RED_SKULL_TEXTURES;
	}
	
	protected void applyRotations(RedSkullEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
	      super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
	      if (!((double)entityLiving.limbSwingAmount < 0.01D)) {
	         float f = 13.0F;
	         float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
	         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
	         matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
	      }
	   }
}
