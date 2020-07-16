package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CMDEnable {
    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("enable")
                .requires(cs -> cs.hasPermissionLevel(2))
                .executes(cs -> {
                    cs.getSource().sendFeedback(new TranslationTextComponent("cmd.enable"), false);
                    Configuration.MOD_ENABLED.set(true);
                    return 0;
                });
    }
}
