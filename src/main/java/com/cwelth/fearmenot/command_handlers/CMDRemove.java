package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;

public class CMDRemove {
    public static LiteralArgumentBuilder<CommandSourceStack> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("remove")
                .requires(cs -> cs.hasPermission(2))
                .then(Commands.argument("fearedone", EntityArgument.player())
                        .executes(cs -> {
                            ServerPlayer dest = EntityArgument.getPlayer(cs, "fearedone");
                            ArrayList<String> str = Configuration.FEARED_LIST.get();
                            if(str.contains(dest.getUUID().toString())) {
                                cs.getSource().sendSuccess(Component.translatable("cmd.remove.success", dest.getDisplayName()), false);
                                str.remove(dest.getUUID().toString());
                                Configuration.FEARED_LIST.set(str);
                            } else
                            {
                                cs.getSource().sendFailure(Component.translatable("cmd.remove.fail", dest.getDisplayName()));
                            }
                            return 0;
                        })
                );
    }
}
