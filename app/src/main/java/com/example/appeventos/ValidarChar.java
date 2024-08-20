package com.example.appeventos;

public class ValidarChar {
    private static final String UPPERCASE_REGEX = ".*[A-Z].*";
    private static final String LOWERCASE_REGEX = ".*[a-z].*";
    private static final String DIGIT_REGEX = ".*[0-9].*";

    private final String value;

    public ValidarChar(String value) {
        this.value = value;
    }

    public boolean hasUppercase() {
        return value.matches(UPPERCASE_REGEX);
    }

    public boolean hasLowercase() {
        return value.matches(LOWERCASE_REGEX);
    }

    public boolean hasDigit() {
        return value.matches(DIGIT_REGEX);
    }

}
