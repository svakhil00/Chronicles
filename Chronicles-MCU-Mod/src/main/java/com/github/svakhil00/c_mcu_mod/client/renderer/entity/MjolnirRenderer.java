package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.MjolnirModel;
import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class MjolnirRenderer extends EntityRenderer<MjolnirEntity> {
	public static final ResourceLocation MJOLNIR = new ResourceLocation(Main.MODID, "textures/entity/mjolnir.png");
	private final MjolnirModel mjolnirModel = new MjolnirModel();
	
	public MjolnirRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		// TODO Auto-generated constructor stub
	}

	public void render(MjolnirEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
	      matrixStackIn.push();
	      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
	      matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
	      IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.mjolnirModel.getRenderType(this.getEntityTexture(entityIn)), false, entityIn.func_226572_w_());
	      this.mjolnirModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.DEFAULT_LIGHT, 1.0F, 1.0F, 1.0F, 1.0F);
	      matrixStackIn.pop();
	      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	   }
	
	
	@Override
	public ResourceLocation getEntityTexture(MjolnirEntity entity) {
		// TODO Auto-generated method stub
		return MJOLNIR;
	}

}
