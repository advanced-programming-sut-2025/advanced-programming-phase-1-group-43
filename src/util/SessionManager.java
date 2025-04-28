package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SessionManager {
    private static final Path SESSION_FILE = Paths.get("session.json");
    private static final Gson gson = new Gson();

    /** ذخیرهٔ نام‌کاربری که باید در اجراهای بعدی هم لاگین بماند */
    public static void save(String username) {
        try {
            // اطمینان از وجود پوشه
            Path parent = SESSION_FILE.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            Map<String, Object> sessionData = new HashMap<>();
            sessionData.put("username", username);
            sessionData.put("stayLoggedIn", true);
            String json = gson.toJson(sessionData);
            // در جاوا 8: از writeAllBytes استفاده می‌کنیم
            Files.write(SESSION_FILE, json.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save session", e);
        }
    }

    /** بارگذاری نام‌کاربری ذخیره‌شده */
    public static Optional<String> load() {
        try {
            if (!Files.exists(SESSION_FILE)) return Optional.empty();
            String json = new String(Files.readAllBytes(SESSION_FILE), StandardCharsets.UTF_8);
            Type type = new TypeToken<Map<String,Object>>(){}.getType();
            Map<String,Object> data = gson.fromJson(json, type);
            if (Boolean.TRUE.equals(data.get("stayLoggedIn")) && data.get("username") instanceof String)
                return Optional.of((String)data.get("username"));
        } catch (IOException e) {
            try { Files.deleteIfExists(SESSION_FILE); } catch (IOException ignored) {}
        }
        return Optional.empty();
    }

    /** پاک کردن session (مثلاً هنگام خروج) */
    public static void clear() throws IOException {
        try {
            Files.deleteIfExists(SESSION_FILE);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clear session", e);
        }
    }
}
