package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CMDDisable {
    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("disable")
                .requires(cs -> cs.hasPermissionLevel(2))
                .executes(cs -> {
                    cs.getSource().sendFeedback(new TranslationTextComponent("cmd.disable"), false);
                    Configuration.MOD_ENABLED.set(false);
                    return 0;
                });
    }

}
