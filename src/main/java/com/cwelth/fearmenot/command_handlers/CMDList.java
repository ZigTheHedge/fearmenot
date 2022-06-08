package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class CMDList {
    public static LiteralArgumentBuilder<CommandSourceStack> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("list")
                .requires(cs -> cs.hasPermission(2))
                .executes(cs -> {
                    cs.getSource().sendSuccess(new TranslatableComponent("cmd.list"), false);
                    boolean singleOne = false;
                    for(int i = 0; i < Configuration.FEARED_LIST.get().size(); i++)
                    {
                        singleOne = true;
                        cs.getSource().sendSuccess(new TextComponent("* " + Configuration.FEARED_LIST.get().get(i)), false);
                    }
                    if(!singleOne)
                        cs.getSource().sendSuccess(new TranslatableComponent("cmd.list.empty"), false);
                    return 0;
                });
    }
}
