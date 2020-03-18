package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.DestroyerBeamModel;
import com.github.svakhil00.c_mcu_mod.entity.projectile.DestroyerBeamEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class DestroyerBeamRenderer extends EntityRenderer<DestroyerBeamEntity> {
	public static final ResourceLocation BEAM = new ResourceLocation("textures/entity/trident.png");
	private final DestroyerBeamModel destroyerBeamModel = new DestroyerBeamModel();

	public DestroyerBeamRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void render(DestroyerBeamEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(
				MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(
				MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));
		IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn,
				this.destroyerBeamModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
		this.destroyerBeamModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F,
				1.0F, 1.0F, 1.0F);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(DestroyerBeamEntity entity) {
		return BEAM;
	}
}
