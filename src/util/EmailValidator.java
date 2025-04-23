package util;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern PATTERN = Pattern.compile(
            "^[A-Za-z0-9](?:[A-Za-z0-9._-]*[A-Za-z0-9])?@[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z]{2,})+$"
    );

    public static boolean isValid(String email) {
        if (email == null) return false;
        return PATTERN.matcher(email).matches();
    }
}