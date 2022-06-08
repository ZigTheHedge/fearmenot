package com.cwelth.fearmenot;

import com.cwelth.fearmenot.command_handlers.*;
import com.cwelth.fearmenot.event_handlers.MainEvents;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(ModMain.MODID)
public class ModMain {

    public static final String MODID = "fearmenot";
    public static final Logger LOGGER = LogManager.getLogger();

    public ModMain()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Configuration.loadConfig(Configuration.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("fearmenot-common.toml"));

    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        MinecraftForge.EVENT_BUS.register(new MainEvents());
    }

    public class ForgeEventHandlers {

        @SubscribeEvent
        public void serverLoad(ServerStartingEvent event) {
            CommandDispatcher<CommandSourceStack> dispatcher = event.getServer().getCommands().getDispatcher();
            LiteralCommandNode<CommandSourceStack> cmds = dispatcher.register(
                    Commands.literal("fearme")
                            .then(CMDRemove.register(dispatcher))
                            .then(CMDAdd.register(dispatcher))
                            .then(CMDReload.register(dispatcher))
                            .then(CMDEnable.register(dispatcher))
                            .then(CMDDisable.register(dispatcher))
                            .then(CMDList.register(dispatcher))
            );

            dispatcher.register(Commands.literal(ModMain.MODID).redirect(cmds));
        }

    }

}
