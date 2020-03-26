package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.FrostGiantModel;
import com.github.svakhil00.c_mcu_mod.entity.monster.FrostGiantEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FrostGiantRenderer extends MobRenderer<FrostGiantEntity, FrostGiantModel<FrostGiantEntity>>{

	private static final ResourceLocation FROST_GIANT_TEXTURES = new ResourceLocation(Main.MODID, "textures/entity/frost_giant.png");

	public FrostGiantRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FrostGiantModel<>(), .7F);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResourceLocation getEntityTexture(FrostGiantEntity entity) {
		// TODO Auto-generated method stub
		return FROST_GIANT_TEXTURES;
	}
	
	protected void applyRotations(FrostGiantEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
	      super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
	      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180));
	      if (!((double)entityLiving.limbSwingAmount < 0.01D)) {
	         float f = 13.0F;
	         float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
	         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
	         matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
	      }
	   }

}
