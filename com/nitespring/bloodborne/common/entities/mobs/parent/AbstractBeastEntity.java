package com.nitespring.bloodborne.common.entities.mobs.parent;




import com.nitespring.bloodborne.common.entities.mobs.boss.FatherGascoigneBossEntity;
import com.nitespring.bloodborne.common.entities.mobs.boss.GascoigneBeastBossEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.StrollThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class AbstractBeastEntity extends AbstractBloodborneEntity{

	
	
	
	
	public AbstractBeastEntity(EntityType<? extends PathfinderMob> p_i48575_1_, Level p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
	
	}
	

	
	
	protected void getBeastGoals(){
		  this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
	      this.goalSelector.addGoal(1, new StrollThroughVillageGoal(this, 400));
	     
	      
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this).setAlertOthers(AbstractBeastEntity.class)));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FatherGascoigneBossEntity.class, true));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractHuntsmanEntity.class, true));
		
		
	}
	
	

}
