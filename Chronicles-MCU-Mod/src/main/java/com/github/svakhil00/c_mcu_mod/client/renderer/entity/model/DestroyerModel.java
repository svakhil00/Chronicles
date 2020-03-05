package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import javax.swing.Renderer;

import com.github.svakhil00.c_mcu_mod.entity.monster.DestroyerEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DestroyerModel<T extends DestroyerEntity> extends SegmentedModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public DestroyerModel() {

		textureWidth = 128;
		textureHeight = 128;

		body = (new ModelRenderer(this)).setTextureSize(128, 128);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.addBox(-8.0F, -2.0F, -6.0F, 16, 8, 11, 0.0F);
		body.addBox(-6.5F, 5.0F, -3.0F, 13, 10, 6, 0.5F);

		head = (new ModelRenderer(this)).setTextureSize(128, 128);
		head.setTextureOffset(100, 100).setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(0, 112).addBox(-2.0F, -10.0F, -3.5F, 4, 8, 8, 0.0F);
		head.setTextureOffset(94, 114).addBox(2.0F, -12.0F, -6.0F, 5, 2, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, 3.0F, 12, 3, 2, 0.0F);
		head.setTextureOffset(0, 0).addBox(-5.0F, -7.0F, 3.0F, 10, 3, 2, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, 3.0F, 8, 2, 2, 0.0F);
		head.setTextureOffset(0, 0).addBox(-2.0F, -11.0F, -5.0F, 4, 1, 11, 0.0F);
		head.setTextureOffset(94, 117).addBox(-7.0F, -12.0F, -6.0F, 5, 2, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, -5.0F, 5, 3, 8, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -10.0F, -5.0F, 5, 3, 8, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -4.0F, -5.0F, 3, 2, 8, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -5.0F, 3, 2, 8, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -7.0F, -5.0F, 4, 3, 8, 0.0F);
		head.setTextureOffset(0, 0).addBox(-5.0F, -7.0F, -5.0F, 4, 3, 8, 0.0F);

		leftarm = (new ModelRenderer(this)).setTextureSize(128, 128);
		leftarm.setRotationPoint(0.0F, -7.0F, 0.0F);
		leftarm.setTextureOffset(0, 0).addBox(8.0F, -2.5F, -3.0F, 5, 18, 6, 0.0F);

		rightarm = (new ModelRenderer(this)).setTextureSize(128, 128);
		rightarm.setRotationPoint(0.0F, -7.0F, 0.0F);
		rightarm.setTextureOffset(0, 0).addBox(-13.0F, -2.5F, -3.0F, 5, 18, 6, 0.0F);

		leftleg = (new ModelRenderer(this)).setTextureSize(128, 128);
		leftleg.setRotationPoint(4.0F, 11.0F, 0.0F);
		leftleg.setTextureOffset(0, 0).addBox(-2.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);

		rightleg = (new ModelRenderer(this)).setTextureSize(128, 128);
		rightleg.setRotationPoint(-5.0F, 11.0F, 0.0F);
		rightleg.setTextureOffset(0, 0).addBox(-2.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
	}

	

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.body, this.leftleg, this.rightleg, this.rightarm, this.leftarm);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.leftleg.rotateAngleX = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightleg.rotateAngleX = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightleg.rotateAngleY = 0.0F;
		
	}
	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}
}
