package io.github.icecron;

import carpet.api.settings.Rule;

import static io.github.icecron.CarpetSslRuleCategory.SSL;

public class CarpetSslAdditionSettings {
    @Rule(options = { "bone_block", "all", "OFF" }, categories = { SSL })
    public static String endGatewayDoNotAddLoadTicket = "OFF";

    @Rule(categories = { SSL })
    public static boolean dolphinPickupIntercept = true;

    @Rule(categories = { SSL })
    public static boolean endGatewayCustomLanding = false;
}

