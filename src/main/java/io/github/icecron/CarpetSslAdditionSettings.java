package io.github.icecron;

import static io.github.icecron.CarpetSslRuleCategory.SSL;

import carpet.api.settings.Rule;

public class CarpetSslAdditionSettings {
    @Rule(options = { "bone_block", "all", "OFF" }, categories = { SSL })
    public static String endGatewayDoNotAddLoadTicket = "OFF";

    @Rule(categories = { SSL })
    public static boolean fixExtendedPistonDeleteFrontBlock = false;

    @Rule(categories = { SSL })
    public static boolean scheduledRandomTickCactus = false;

    @Rule(options = { "bone_block", "wither_skeleton_skull", "note_block", "OFF" }, categories = { SSL })
    public static String noteBlockChunkLoader = "OFF";

    @Rule(options = { "bone_block", "bedrock", "all", "OFF" }, categories = { SSL })
    public static String pistonBlockChunkLoader = "OFF";

    @Rule(categories = { SSL })
    public static boolean softDeepslate = false;

    @Rule(categories = { SSL })
    public static boolean softObsidian = false;

    @Rule(categories = { SSL })
    public static boolean enderPearlChunkLoader = false;
}
