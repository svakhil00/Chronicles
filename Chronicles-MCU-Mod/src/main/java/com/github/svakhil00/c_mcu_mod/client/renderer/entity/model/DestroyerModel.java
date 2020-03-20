package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

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
		body.addBox(-12.0F, -19.0F, -9.0F, 24, 12, 17, 0.0F);
		body.addBox(-10.25F, -8.5F, -4.5F, 20, 15, 9, 0.5F);

		head = (new ModelRenderer(this)).setTextureSize(128, 128);
		head.setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(0, 104).addBox(-3.0F, -31.0F, -5.25F, 6, 12, 12, 0.0F);
		head.setTextureOffset(94, 114).addBox(2.5F, -34.0F, -9.0F, 8, 3, 18, 0.0F);
		head.setTextureOffset(0, 0).addBox(-9.0F, -31.5F, 4.5F, 18, 5, 3, 0.0F);
		head.setTextureOffset(0, 0).addBox(-7.5F, -27.0F, 4.5F, 15, 5, 3, 0.0F);
		head.setTextureOffset(0, 0).addBox(-6.0F, -22.0F, 4.5F, 12, 3, 3, 0.0F);
		head.setTextureOffset(0, 0).addBox(-3.0F, -33.0F, -7.5F, 6, 2, 17, 0.0F);
		head.setTextureOffset(94, 117).addBox(-11.0F, -34.0F, -9.0F, 8, 3, 18, 0.0F);
		head.setTextureOffset(0, 0).addBox(-9.5F, -31.5F, -7.5F, 8, 5, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -31.5F, -7.5F, 8, 5, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -22.0F, -7.5F, 5, 3, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(-6.5F, -22.0F, -7.5F, 5, 3, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(1.0F, -27.0F, -7.5F, 6, 5, 12, 0.0F);
		head.setTextureOffset(0, 0).addBox(-7.5F, -27.0F, -7.5F, 6, 5, 12, 0.0F);

		leftarm = (new ModelRenderer(this)).setTextureSize(128, 128);
		leftarm.setRotationPoint(15.5F, -25.25F, 0.0F);
		leftarm.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.5F, 8, 29, 9, 0.0F);

		rightarm = (new ModelRenderer(this)).setTextureSize(128, 128);
		rightarm.setRotationPoint(-16.0F, -25.25F, 0.0F);
		rightarm.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.5F, 8, 29, 9, 0.0F);

		leftleg = (new ModelRenderer(this)).setTextureSize(128, 128);
		leftleg.setRotationPoint(5.75F, 0.0F, -0.5F);
		leftleg.setTextureOffset(0, 0).addBox(-4.5F, 0.0F, -4.0F, 9, 24, 8, 0.0F);

		rightleg = (new ModelRenderer(this)).setTextureSize(128, 128);
		rightleg.setRotationPoint(-6.25F, 0.0F, -0.5F);
		rightleg.setTextureOffset(0, 0).addBox(-4.5F, 0.0F, -4.0F, 9, 24, 8, 0.0F);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.body, this.leftleg, this.rightleg, this.rightarm, this.leftarm);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.leftleg.rotateAngleX = -this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightleg.rotateAngleX = this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightleg.rotateAngleY = 0.0F;

	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int i = entityIn.getAttackTimer();
		if (i > 0) {
			this.rightarm.rotateAngleX = -2.0F + this.triangleWave((float) i - partialTick, 10.0F);
			this.leftarm.rotateAngleX = -2.0F + this.triangleWave((float) i - partialTick, 10.0F);

		} else {
			this.rightarm.rotateAngleX = (-0.2F + this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftarm.rotateAngleX = (-0.2F - this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;

			
		}

	}

	private float triangleWave(float p_78172_1_, float p_78172_2_) {
		return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
	}
}
