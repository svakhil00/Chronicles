package com.github.svakhil00.c_mcu_mod.client.renderer.entity;

import com.github.svakhil00.c_mcu_mod.entity.projectile.CaptainAmericaShieldEntity;
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

public class CaptainAmericaShieldRenderer<T extends Entity & IRendersAsItem> extends EntityRenderer<CaptainAmericaShieldEntity> {
	private final net.minecraft.client.renderer.ItemRenderer itemRenderer;
	private final float scale;
	private final boolean blockLight;
	
	public CaptainAmericaShieldRenderer(EntityRendererManager renderManager, net.minecraft.client.renderer.ItemRenderer itemRenderer,
			float scale, boolean blockLight) {
		super(renderManager);
		this.itemRenderer = itemRenderer;
		this.scale = scale;
		this.blockLight = blockLight;
	}

	public CaptainAmericaShieldRenderer(EntityRendererManager renderManagerIn,
			net.minecraft.client.renderer.ItemRenderer itemRendererIn) {
		this(renderManagerIn, itemRendererIn, 2.0F, false);
	}
	protected int getBlockLight(CaptainAmericaShieldEntity entityIn, float partialTicks) {
		return this.blockLight ? 15 : super.getBlockLight(entityIn, partialTicks);
	}
	public void render(CaptainAmericaShieldEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		
		
		matrixStackIn.push();
		matrixStackIn.scale(this.scale, this.scale, this.scale);
		matrixStackIn.rotate(this.renderManager.getCameraOrientation());
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
		matrixStackIn.rotate(Vector3f.ZN.rotationDegrees(30));
		this.itemRenderer.renderItem(new ItemStack(ItemList.CAPTAIN_AMERICA_SHIELD), ItemCameraTransforms.TransformType.GROUND,
				packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		
	}
	
	@Override
	public ResourceLocation getEntityTexture(CaptainAmericaShieldEntity entity) {
		// TODO Auto-generated method stub
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}
}
