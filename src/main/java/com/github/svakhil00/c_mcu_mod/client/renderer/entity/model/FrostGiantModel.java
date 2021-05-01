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
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.675F, -0.025F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -7.8F, -3.275F, 8, 8, 6, 0.0F);
		head.setTextureOffset(116, 0).addBox(2.35F, -8.0F, -3.3F, 1, 1, 6, 0.0F);
		head.setTextureOffset(116, 0).addBox(-4.2F, -5.9F, -1.05F, 1, 2, 1, 0.0F);
		head.setTextureOffset(114, 0).addBox(-3.775F, -8.0F, -3.3F, 1, 1, 6, 0.0F);
		head.setTextureOffset(116, 0).addBox(3.275F, -5.9F, -1.05F, 1, 2, 1, 0.0F);
		head.setTextureOffset(118, 0).addBox(-3.7F, -7.6F, -3.5F, 1, 5, 0, 0.0F);
		head.setTextureOffset(117, 0).addBox(2.4F, -7.6F, -3.525F, 1, 5, 0, 0.0F);
		head.setTextureOffset(0, 0).addBox(-2.125F, -7.275F, -2.65F, 4, 8, 5, 0.0F);
		head.setTextureOffset(76, 35).addBox(-0.55F, -4.8F, 2.375F, 1, 1, 0, 0.0F);
		head.setTextureOffset(0, 0).addBox(-2.825F, -6.425F, 2.75F, 1, 1, 0, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.425F, -6.425F, 2.725F, 1, 1, 0, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 9.975F, 0.0F);
		body.setTextureOffset(60, 51).addBox(-7.65F, -12.95F, -2.675F, 15, 8, 5, 0.0F);
		body.setTextureOffset(14, 50).addBox(0.475F, -11.05F, 1.975F, 6, 6, 1, 0.0F);
		body.setTextureOffset(0, 57).addBox(-7.125F, -11.05F, 1.975F, 6, 6, 1, 0.0F);
		body.setTextureOffset(59, 11).addBox(-6.725F, -4.925F, -2.675F, 13, 2, 5, 0.0F);
		body.setTextureOffset(16, 16).addBox(-6.225F, -2.95F, -2.675F, 12, 2, 5, 0.0F);
		body.setTextureOffset(40, 0).addBox(-6.25F, -0.95F, -2.675F, 12, 1, 5, 0.0F);
		body.setTextureOffset(64, 18).addBox(-7.0F, 0.025F, -2.725F, 13, 2, 5, 0.0F);
		body.setTextureOffset(64, 18).addBox(-7.0F, 2.05F, -2.75F, 0, 5, 5, 0.0F);
		body.setTextureOffset(64, 18).addBox(-6.95F, 2.05F, -2.7F, 11, 5, 0, 0.0F);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-10.025F, -2.275F, 0.0F);
		leftArm.setTextureOffset(19, 30).addBox(-1.675F, -0.025F, -2.0F, 4, 14, 4, 0.0F);
		leftArm.setTextureOffset(95, 0).addBox(-1.975F, -0.3F, -2.175F, 4, 4, 4, 0.0F);
		leftArm.setTextureOffset(95, 0).addBox(-1.974F, -0.299F, -1.85F, 4, 4, 4, 0.0F);
		leftArm.setTextureOffset(40, 16).addBox(-1.975F, 8.725F, -2.075F, 4, 1, 4, 0.0F);
		leftArm.setTextureOffset(40, 16).addBox(-1.965F, 8.725F, -1.625F, 4, 1, 4, 0.0F);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(9.625F, -2.275F, 0.0F);
		rightArm.setTextureOffset(92, 30).addBox(-2.3F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
		rightArm.setTextureOffset(111, 50).addBox(-1.95F, -0.299F, -1.85F, 4, 4, 4, 0.0F);
		rightArm.setTextureOffset(111, 50).addBox(-1.951F, -0.298F, -2.1F, 4, 4, 4, 0.0F);
		rightArm.setTextureOffset(40, 16).addBox(-2.45F, 8.725F, -2.075F, 4, 1, 4, 0.0F);
		rightArm.setTextureOffset(40, 16).addBox(-2.44F, 8.725F, -1.625F, 4, 1, 4, 0.0F);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-3.625F, 12.325F, 0.0F);
		leftLeg.setTextureOffset(0, 16).addBox(-2.325F, -0.325F, -2.0F, 4, 12, 4, 0.0F);
		leftLeg.setTextureOffset(0, 16).addBox(-2.3F, 9.675F, -1.925F, 4, 2, 6, 0.0F);
		leftLeg.setTextureOffset(0, 16).addBox(-2.475F, -0.65F, -1.9F, 4, 5, 4, 0.0F);
		leftLeg.setTextureOffset(64, 18).addBox(-3.375F, -0.675F, 2.25F, 6, 5, 0, 0.0F);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(3.6F, 12.175F, 0.0F);
		rightLeg.setTextureOffset(0, 16).addBox(-2.6F, -0.175F, -2.0F, 4, 12, 4, 0.0F);
		rightLeg.setTextureOffset(0, 16).addBox(-2.575F, 9.8F, -1.925F, 4, 2, 6, 0.0F);
		rightLeg.setTextureOffset(3, 54).addBox(-2.6F, -0.55F, -1.875F, 4, 5, 4, 0.0F);
		rightLeg.setTextureOffset(64, 18).addBox(-4.6F, 0.4F, 2.225F, 4, 4, 0, 0.0F);
		rightLeg.setTextureOffset(64, 18).addBox(-4.6F, -0.15F, 2.224F, 4, 4, 0, 0.0F);

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
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int i = entityIn.getAttackTimer();
		if (i > 0) {
			this.rightArm.rotateAngleX = -(-2.0F + this.triangleWave((float) i - partialTick, 10.0F));
			this.leftArm.rotateAngleX = -(-2.0F + this.triangleWave((float) i - partialTick, 10.0F));
		} else {
			this.rightArm.rotateAngleX = -((-0.2F + this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount);
			this.leftArm.rotateAngleX = -((-0.2F - this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount);
		}
	}
	
	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}
}
