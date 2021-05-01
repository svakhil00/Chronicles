package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import com.github.svakhil00.c_mcu_mod.Main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class DestroyerBeamModel extends Model {
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Main.MODID, "textures/entity/beam.png");
	private final ModelRenderer modelRenderer;

	public DestroyerBeamModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 128;
		textureHeight = 128;


		modelRenderer = new ModelRenderer(this);
		modelRenderer.addBox(-1.0F, -11.0F, -3.0F, 2, 2, 1, 0.0F);
		modelRenderer.addBox(-1.0F, -11.0F, 2.0F, 2, 2, 1, 0.0F);
		modelRenderer.addBox(-2.0F, -12.0F, -2.0F, 4, 4, 4, 0.0F);
		modelRenderer.addBox(-1.0F, -8.0F, -1.0F, 2, 1, 2, 0.0F);
		modelRenderer.addBox(-1.0F, -13.0F, -1.0F, 2, 1, 2, 0.0F);
		modelRenderer.addBox(2.0F, -11.0F, -1.0F, 1, 2, 2, 0.0F);
		modelRenderer.addBox(-3.0F, -11.0F, -1.0F, 1, 2, 2, 0.0F);
		modelRenderer.addBox(-2.0F, -12.0F, 1.0F, 5, 0, 0, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		this.modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
}
