package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.Main;
import com.github.svakhil00.c_mcu_mod.client.renderer.entity.model.DestroyerBeamModel;
import com.github.svakhil00.c_mcu_mod.entity.projectile.DestroyerBeamEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class DestroyerBeamRenderer extends EntityRenderer<DestroyerBeamEntity> {
	public static final ResourceLocation BEAM = new ResourceLocation(Main.MODID, "textures/entity/beam.png");
	private final DestroyerBeamModel destroyerBeamModel = new DestroyerBeamModel();

	public DestroyerBeamRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	public DestroyerBeamRenderer(EntityRendererManager renderManager,
			net.minecraft.client.renderer.ItemRenderer itemRenderer, float scale, boolean blockLight) {
		super(renderManager);
		// TODO Auto-generated constructor stub
	}

	public DestroyerBeamRenderer(EntityRendererManager renderManagerIn,
			net.minecraft.client.renderer.ItemRenderer itemRendererIn) {
		this(renderManagerIn, itemRendererIn, 2.0F, false);
	}
	
	@Override
	protected int getBlockLight(DestroyerBeamEntity entityIn, float partialTicks) {
		return 15;
	}

	public void render(DestroyerBeamEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		IVertexBuilder ivertexbuilder = bufferIn
				.getBuffer(this.destroyerBeamModel.getRenderType(this.getEntityTexture(entityIn)));
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
