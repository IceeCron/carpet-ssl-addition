package io.github.icecron;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class CarpetSslTranslations {
    public static Map<String, String> getTranslationFromResourcePath(String lang) {
        try (InputStream langFile = CarpetSslTranslations.class
                .getClassLoader()
                .getResourceAsStream("assets/carpet-ssl-addition/lang/%s.json".formatted(lang))) {
            if (langFile == null) {
                return Collections.emptyMap();
            }
            String jsonData = new String(langFile.readAllBytes(), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(jsonData, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }
}
