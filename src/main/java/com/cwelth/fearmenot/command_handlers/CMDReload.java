package com.cwelth.fearmenot.command_handlers;

import com.cwelth.fearmenot.Configuration;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.loading.FMLPaths;

public class CMDReload implements Command<CommandSource> {

    private static final CMDReload CMD = new CMDReload();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("reload")
                .requires(cs -> cs.hasPermissionLevel(2))
                .executes(CMD);
    }


    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        Configuration.loadConfig(Configuration.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("fearmenot-common.toml"));
        context.getSource().sendFeedback(new TranslationTextComponent("cmd.reload"), false);
        return 0;
    }
}
