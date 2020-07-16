package com.cwelth.fearmenot.event_handlers;

import com.cwelth.fearmenot.Configuration;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MainEvents {

    @SubscribeEvent
    public void onBecomeTarget(LivingSetAttackTargetEvent ev) {
        LivingEntity entity = ev.getEntityLiving();
        LivingEntity target = ev.getTarget();

        if (target != null && Configuration.FEARED_LIST.get().contains(target.getUniqueID().toString())) {
            ((MobEntity)entity).setAttackTarget(null);
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent e) {
        if(Configuration.MOD_ENABLED.get())
        {
            if(e.getEntity() instanceof IMob && e.getEntity() instanceof CreatureEntity)
            {
                CreatureEntity entity = (CreatureEntity)e.getEntity();
                entity.goalSelector.addGoal(0, new AvoidEntityGoal<PlayerEntity>(entity, PlayerEntity.class,
                        livingEntity -> livingEntity instanceof PlayerEntity && Configuration.FEARED_LIST.get().contains(livingEntity.getUniqueID().toString()),
                        Configuration.AVOID_DISTANCE.get().floatValue(), Configuration.FAR_SPEED.get(), Configuration.NEAR_SPEED.get(),
                        EntityPredicates.IS_ALIVE::test));
                String ssTest = entity.goalSelector.toString();
            }
        }
    }
}