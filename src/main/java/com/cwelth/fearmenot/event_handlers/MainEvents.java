package com.cwelth.fearmenot.event_handlers;

import com.cwelth.fearmenot.Configuration;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MainEvents {

    @SubscribeEvent
    public void onBecomeTarget(LivingChangeTargetEvent ev) {
        LivingEntity entity = ev.getEntity();
        LivingEntity target = ev.getNewTarget();

        if (target != null && Configuration.FEARED_LIST.get().contains(target.getUUID().toString())) {
            ((Mob)entity).setTarget(null);
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent e) {
        if(Configuration.MOD_ENABLED.get())
        {
            if(e.getEntity() instanceof PathfinderMob)
            {
                PathfinderMob entity = (PathfinderMob)e.getEntity();
                entity.goalSelector.addGoal(0, new AvoidEntityGoal<Player>(entity, Player.class,
                        livingEntity -> livingEntity instanceof Player && Configuration.FEARED_LIST.get().contains(livingEntity.getUUID().toString()),
                        Configuration.AVOID_DISTANCE.get().floatValue(), Configuration.FAR_SPEED.get(), Configuration.NEAR_SPEED.get(),
                        livingEntity -> livingEntity.isAlive()));
                String ssTest = entity.goalSelector.toString();
            }
        }
    }
}