package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.entity.projectile.LaserEntity;
import com.github.svakhil00.c_mcu_mod.init.ModItems;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LaserRenderer extends EntityRenderer<LaserEntity> {
	
	private final net.minecraft.client.renderer.ItemRenderer itemRenderer;
	private final float scale;
	private final boolean blockLight;

	public LaserRenderer(EntityRendererManager renderManager,
			net.minecraft.client.renderer.ItemRenderer itemRenderer, float scale, boolean blockLight) {
		super(renderManager);
		this.itemRenderer = itemRenderer;
		this.scale = scale;
		this.blockLight = blockLight;
	}

	public LaserRenderer(EntityRendererManager renderManagerIn,
			net.minecraft.client.renderer.ItemRenderer itemRendererIn) {
		this(renderManagerIn, itemRendererIn, 2.0F, false);
	}
	
	@Override
	protected int getBlockLight(LaserEntity entityIn, float partialTicks) {
		return this.blockLight ? 15 : super.getBlockLight(entityIn, partialTicks);
	}

	@Override
	public void render(LaserEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.scale(this.scale, this.scale, this.scale);
		matrixStackIn.rotate(this.renderManager.getCameraOrientation());
		this.itemRenderer.renderItem(new ItemStack(ModItems.LASER.get()), ItemCameraTransforms.TransformType.GROUND,
				packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	public ResourceLocation getEntityTexture(LaserEntity entity) {
		// TODO Auto-generated method stub
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

}
