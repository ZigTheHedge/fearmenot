package com.cwelth.fearmenot;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class Configuration {
    public static final String CATEGORY_OVERALL_TWEAKS = "overall_tweaks";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.ConfigValue<ArrayList<String>> FEARED_LIST;
    public static ForgeConfigSpec.BooleanValue MOD_ENABLED;
    public static ForgeConfigSpec.DoubleValue AVOID_DISTANCE;
    public static ForgeConfigSpec.DoubleValue FAR_SPEED;
    public static ForgeConfigSpec.DoubleValue NEAR_SPEED;

    static {

        COMMON_BUILDER.comment("Overall Tweaks").push(CATEGORY_OVERALL_TWEAKS);

        FEARED_LIST = COMMON_BUILDER.comment("List of player UUIDs which are considered \"The Feared ones\". Mobs will try to evade these players.")
                .define("UUIDs", new ArrayList<>());
        MOD_ENABLED = COMMON_BUILDER.comment("Enable mod functionality.").define("modEnabled", true);
        AVOID_DISTANCE = COMMON_BUILDER.comment("Mob Avoid Distance").defineInRange("avoidDistance", 8D, 0, 32D);
        FAR_SPEED = COMMON_BUILDER.comment("Speed to run at when target is far").defineInRange("farSpeed", 1D, 0, 3D);
        NEAR_SPEED = COMMON_BUILDER.comment("Speed to run at when target is near").defineInRange("nearSpeed", 1.2D, 0, 3D);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
