package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.google.gson.internal.$Gson$Preconditions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class CMDList {
    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("list")
                .requires(cs -> cs.hasPermissionLevel(2))
                .executes(cs -> {
                    cs.getSource().sendFeedback(new TranslationTextComponent("cmd.list"), false);
                    boolean singleOne = false;
                    for(int i = 0; i < Configuration.FEARED_LIST.get().size(); i++)
                    {
                        singleOne = true;
                        cs.getSource().sendFeedback(new StringTextComponent("* " + Configuration.FEARED_LIST.get().get(i)), false);
                    }
                    if(!singleOne)
                        cs.getSource().sendFeedback(new TranslationTextComponent("cmd.list.empty"), false);
                    return 0;
                });
    }
}
