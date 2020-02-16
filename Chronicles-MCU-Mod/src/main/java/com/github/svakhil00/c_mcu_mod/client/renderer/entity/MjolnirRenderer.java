package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.entity.projectile.MjolnirEntity;
import com.github.svakhil00.c_mcu_mod.lists.ItemList;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MjolnirRenderer<T extends Entity & IRendersAsItem> extends EntityRenderer<MjolnirEntity> {
	private final net.minecraft.client.renderer.ItemRenderer itemRenderer;
	private final float scale;
	private final boolean blockLight;

	public MjolnirRenderer(EntityRendererManager renderManager, net.minecraft.client.renderer.ItemRenderer itemRenderer,
			float scale, boolean blockLight) {
		super(renderManager);
		this.itemRenderer = itemRenderer;
		this.scale = scale;
		this.blockLight = blockLight;
		// TODO Auto-generated constructor stub
	}

	public MjolnirRenderer(EntityRendererManager renderManagerIn,
			net.minecraft.client.renderer.ItemRenderer itemRendererIn) {
		this(renderManagerIn, itemRendererIn, 2.0F, false);
	}

	protected int getBlockLight(MjolnirEntity entityIn, float partialTicks) {
		return this.blockLight ? 15 : super.getBlockLight(entityIn, partialTicks);
	}

	public void render(MjolnirEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.scale(this.scale, this.scale, this.scale);
		matrixStackIn.rotate(this.renderManager.getCameraOrientation());
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
		matrixStackIn.rotate(Vector3f.ZN.rotationDegrees(90.0F));
		matrixStackIn.rotate(Vector3f.XN.rotationDegrees(270.0F));
		this.itemRenderer.renderItem(new ItemStack(ItemList.MJOLNIR), ItemCameraTransforms.TransformType.GROUND,
				packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		
	}

	@Override
	public ResourceLocation getEntityTexture(MjolnirEntity entity) {
		// TODO Auto-generated method stub
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

}
