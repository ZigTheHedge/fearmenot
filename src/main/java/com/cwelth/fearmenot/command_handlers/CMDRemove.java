package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class CMDRemove {
    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("remove")
                .requires(cs -> cs.hasPermissionLevel(2))
                .then(Commands.argument("fearedone", EntityArgument.player())
                        .executes(cs -> {
                            ServerPlayerEntity dest = EntityArgument.getPlayer(cs, "fearedone");
                            ArrayList<String> str = Configuration.FEARED_LIST.get();
                            if(str.contains(dest.getUniqueID().toString())) {
                                cs.getSource().sendFeedback(new TranslationTextComponent("cmd.remove.success", dest.getDisplayName().getFormattedText()), false);
                                str.remove(dest.getUniqueID().toString());
                                Configuration.FEARED_LIST.set(str);
                            } else
                            {
                                cs.getSource().sendFeedback(new TranslationTextComponent("cmd.remove.fail", dest.getDisplayName().getFormattedText()), false);
                            }
                            return 0;
                        })
                );
    }
}
