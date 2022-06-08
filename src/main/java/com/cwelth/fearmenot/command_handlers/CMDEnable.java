package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class CMDEnable {
    public static LiteralArgumentBuilder<CommandSourceStack> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("enable")
                .requires(cs -> cs.hasPermission(2))
                .executes(cs -> {
                    cs.getSource().sendSuccess(new TranslatableComponent("cmd.enable"), false);
                    Configuration.MOD_ENABLED.set(true);
                    return 0;
                });
    }
}
