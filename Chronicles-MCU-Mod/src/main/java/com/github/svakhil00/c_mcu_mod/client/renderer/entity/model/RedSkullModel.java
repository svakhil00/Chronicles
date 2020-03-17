package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import com.github.svakhil00.c_mcu_mod.entity.monster.RedSkullEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class RedSkullModel<T extends RedSkullEntity> extends SegmentedModel<T> {

	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	
	public RedSkullModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		body.setTextureOffset(48, 0).addBox(1.0F, 0.0F, -2.0F, 3, 12, 4, 0.5F);
		body.setTextureOffset(28, 52).addBox(-4.0F, 0.0F, -2.0F, 3, 12, 4, 0.5F);
		body.setTextureOffset(52, 32).addBox(0.0F, 0.0F, -1.0F, 1, 12, 3, 0.5F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		body.addChild(rightArm);
		rightArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		rightArm.setTextureOffset(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4, 0.5F);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		body.addChild(leftArm);
		leftArm.setTextureOffset(16, 40).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		leftArm.setTextureOffset(0, 48).addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4, 0.5F);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg.setTextureOffset(36, 36).addBox(-1.85F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		rightLeg.setTextureOffset(0, 32).addBox(-1.65F, 0.0F, -2.0F, 4, 11, 4, 0.25F);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftLeg.setTextureOffset(32, 0).addBox(-2.15F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		leftLeg.setTextureOffset(24, 24).addBox(-2.35F, 0.0F, -2.0F, 4, 11, 4, 0.25F);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
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
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
	     
		this.rightArm.rotateAngleX = (-0.2F + this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
        this.leftArm.rotateAngleX = (-0.2F - this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
	     

	   }
	
	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}

}
