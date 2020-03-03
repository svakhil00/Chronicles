package com.github.svakhil00.c_mcu_mod.client.renderer.entity.model;

import com.github.svakhil00.c_mcu_mod.entity.monster.DestroyerEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
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
		textureWidth = 16;
		textureHeight = 16;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 21.0F, -0.5F);
		body.addBox(0, 0, -1.0F, -2.0F, -0.5F, 2, 2, 1, 0.0F);
		body.addBox(0, 0, -2.0F, -3.0F, -0.5F, 4, 1, 1, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 0.0F);
		body.addChild(head);
		head.addBox(0, 0, -0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(0.0F, 3.0F, 0.5F);
		body.addChild(leftarm);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(0.0F, 3.0F, 0.5F);
		body.addChild(rightarm);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(1.0F, 21.0F, -0.5F);
		leftleg.addBox(0, 0, 0.5F, 0.0F, -0.5F, -1, 3, 1, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-1.0F, 21.0F, -0.5F);
		rightleg.addBox(0, 0, -0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
	}

	/*
	 * @Override public void render(Entity entity, float f, float f1, float f2,
	 * float f3, float f4, float f5) { body.render(f5); leftleg.render(f5);
	 * rightleg.render(f5); }
	 */

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
