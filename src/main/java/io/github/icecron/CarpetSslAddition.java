package io.github.icecron;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarpetSslAddition implements CarpetExtension, ModInitializer {
    @Getter
    public static final Logger LOGGER = LoggerFactory.getLogger(CarpetSslAddition.class);
    @Getter
    public static final String MOD_NAME = "carpet-ssl-addition";
    @Getter
    public static final String MOD_ID = "carpet-ssl-addition";

    @Override
    public String version() {
        return CarpetSslAddition.MOD_NAME;
    }

    public static void loadExtension() {
        CarpetServer.manageExtension(new CarpetSslAddition());
    }

    @Override
    public void onInitialize() {
        CarpetSslAddition.loadExtension();
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetSslAdditionSettings.class);
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return CarpetSslTranslations.getTranslationFromResourcePath(lang);
    }
}
