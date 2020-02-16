package com.github.svakhil00.c_mcu_mod.entity.player;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CustomPlayerEntity extends PlayerEntity{

	public CustomPlayerEntity(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updatePose() {
	      if (this.isPoseClear(Pose.SWIMMING)) {
	         Pose pose;
	         if (this.isElytraFlying()) {
	            pose = Pose.FALL_FLYING;
	         } else if (this.isSleeping()) {
	            pose = Pose.SLEEPING;
	         } else if (this.isSwimming()) {
	            pose = Pose.SWIMMING;
	         } else if (this.isSpinAttacking()) {
	            pose = Pose.FALL_FLYING;
	         } else if (this.isShiftKeyDown() && !this.abilities.isFlying) {
	            pose = Pose.CROUCHING;
	         } else {
	            pose = Pose.STANDING;
	         }

	         Pose pose1;
	         if (!this.isSpectator() && !this.isPassenger() && !this.isPoseClear(pose)) {
	            if (this.isPoseClear(Pose.CROUCHING)) {
	               pose1 = Pose.CROUCHING;
	            } else {
	               pose1 = Pose.SWIMMING;
	            }
	         } else {
	            pose1 = pose;
	         }

	         this.setPose(pose1);
	      }
	   }
	
	@Override
	public boolean isSpectator() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCreative() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
