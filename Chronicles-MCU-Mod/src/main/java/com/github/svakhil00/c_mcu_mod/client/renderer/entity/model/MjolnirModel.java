package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import com.github.svakhil00.c_mcu_mod.Main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class MjolnirModel extends Model {
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Main.MODID, "textures/entity/mjolnir.png");
	private final ModelRenderer modelRenderer = new ModelRenderer(32, 32, 0, 6);

	public MjolnirModel() {
		super(RenderType::entitySolid);
		
		this.modelRenderer.addBox(-.5F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F);
		ModelRenderer modelRenderer1 = new ModelRenderer(16, 16, 4, 0);
		modelRenderer1.addBox(-1.5F, 0.0F, 10.0F, 2.0F, 2.0F, 10.0F, 1.0F);
		//this.modelRenderer.addChild(modelRenderer1);
		/*
		this.modelRenderer.addBox(-.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F, 0.0F);
		ModelRenderer modelrenderer = new ModelRenderer(32, 32, 4, 0);
		modelrenderer.addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F);
		this.modelRenderer.addChild(modelrenderer);
		ModelRenderer modelrenderer1 = new ModelRenderer(32, 32, 4, 3);
		modelrenderer1.addBox(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
		this.modelRenderer.addChild(modelrenderer1);
		ModelRenderer modelrenderer2 = new ModelRenderer(32, 32, 0, 0);
		modelrenderer2.addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.modelRenderer.addChild(modelrenderer2);
		ModelRenderer modelrenderer3 = new ModelRenderer(32, 32, 4, 3);
		modelrenderer3.mirror = true;
		modelrenderer3.addBox(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F);
		this.modelRenderer.addChild(modelrenderer3);
		*/
		
		
	}
	
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
	      this.modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	   }
}
