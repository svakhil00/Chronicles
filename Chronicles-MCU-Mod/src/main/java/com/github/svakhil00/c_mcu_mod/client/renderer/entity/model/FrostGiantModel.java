package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import com.github.svakhil00.c_mcu_mod.entity.monster.FrostGiantEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FrostGiantModel<T extends FrostGiantEntity> extends SegmentedModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	public FrostGiantModel() {
		textureWidth = 256;
		textureHeight = 256;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -8.675F, -0.025F);
		head.addBox(-4.0F, -3.65F, -3.275F, 8, 8, 6, 0.0F);
		head.addBox(2.35F, -3.85F, -3.3F, 1, 1, 6, 0.0F);
		head.addBox(-4.2F, -1.75F, -1.05F, 1, 2, 1, 0.0F);
		head.addBox(-3.775F, -3.85F, -3.3F, 1, 1, 6, 0.0F);
		head.addBox(3.275F, -1.75F, -1.05F, 1, 2, 1, 0.0F);
		head.addBox(-3.7F, -3.45F, -3.5F, 1, 5, 0, 0.0F);
		head.addBox(2.4F, -3.45F, -3.525F, 1, 5, 0, 0.0F);
		head.addBox(-2.125F, -3.125F, -2.65F, 4, 8, 5, 0.0F);
		head.addBox(-0.55F, 0.2F, 2.375F, 1, 1, 0, 0.0F);
		head.addBox(-2.825F, -1.425F, 2.75F, 1, 1, 0, 0.0F);
		head.addBox(1.425F, -1.425F, 2.725F, 1, 1, 0, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 2.975F, 0.0F);
		body.addBox(-7.65F, -6.8F, -2.675F, 15, 8, 5, 0.0F);
		body.addBox(0.475F, -4.9F, 1.975F, 6, 6, 1, 0.0F);
		body.addBox(-7.125F, -4.9F, 1.975F, 6, 6, 1, 0.0F);
		body.addBox(-6.725F, 1.675F, -2.675F, 13, 2, 5, 0.0F);
		body.addBox(-6.225F, 3.775F, -2.675F, 12, 2, 5, 0.0F);
		body.addBox(-6.25F, 6.05F, -2.675F, 12, 1, 5, 0.0F);
		body.addBox(-7.0F, 7.025F, -2.725F, 13, 2, 5, 0.0F);
		body.addBox(-7.0F, 9.05F, -2.75F, 0, 5, 5, 0.0F);
		body.addBox(-6.95F, 9.05F, -2.7F, 11, 5, 0, 0.0F);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-10.025F, 2.725F, 0.0F);
		leftArm.addBox(-1.675F, -5.875F, -2.0F, 4, 14, 4, 0.0F);
		leftArm.addBox(-1.975F, -6.15F, -2.175F, 4, 4, 4, 0.0F);
		leftArm.addBox(-1.975F, 3.725F, -2.175F, 4, 1, 4, 0.0F);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(9.625F, 2.725F, 0.0F);
		rightArm.addBox(-2.05F, -5.85F, -2.0F, 4, 14, 4, 0.0F);
		rightArm.addBox(-2.625F, -6.15F, -2.35F, 4, 4, 4, 0.0F);
		rightArm.addBox(-2.625F, 3.725F, -2.35F, 4, 1, 4, 0.0F);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-3.625F, 17.325F, 0.0F);
		leftLeg.addBox(-2.325F, -5.325F, -2.0F, 4, 12, 4, 0.0F);
		leftLeg.addBox(-2.3F, 4.675F, -1.925F, 4, 2, 6, 0.0F);
		leftLeg.addBox(-2.475F, -5.65F, -1.9F, 4, 5, 4, 0.0F);
		leftLeg.addBox(-3.375F, -5.675F, 2.25F, 6, 5, 0, 0.0F);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(3.6F, 17.175F, 0.0F);
		rightLeg.addBox(-2.6F, -5.175F, -2.0F, 4, 12, 4, 0.0F);
		rightLeg.addBox(-2.575F, 4.8F, -1.925F, 4, 2, 6, 0.0F);
		rightLeg.addBox(0, 54, -2.8F, -5.55F, -2.175F, 4, 5, 4, 0.0F);
		rightLeg.addBox(-4.6F, -4.6F, 2.225F, 4, 4, 0, 0.0F);
	}
	@Override
	public Iterable<ModelRenderer> getParts() {
		// TODO Auto-generated method stub
		return ImmutableList.of(this.head, this.body, this.leftLeg, this.rightLeg, this.rightArm, this.leftArm);
	}
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.leftLeg.rotateAngleX = -this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.rotateAngleX = this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleY = 0.0F;
	}
	
	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}
}
