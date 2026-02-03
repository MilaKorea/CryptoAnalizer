package userInterface;

import exceptions.MyException;

public enum CryptoAnalyzerMode {
    ENCRYPTION("1", true),
    DECRYPTION_WITH_KEY("2", true),
    BRUTE_FORCE("3", false),
    STATISTICAL_DECRYPTION("4", false);

    private final String code;
    private final boolean isKeyNeed;

    CryptoAnalyzerMode(String code, boolean isKeyNeed) {
        this.code = code;
        this.isKeyNeed = isKeyNeed;
    }

    public boolean isKeyNeed() {
        return isKeyNeed;
    }

    public static CryptoAnalyzerMode choiceByUser(String input) {
        for (CryptoAnalyzerMode mode : values()) {
            if (mode.code.equals(input)) {
                return mode;
            }
        }
        throw new MyException("Invalid crypto mode. Please choose 1 or 2 or 3 or 4.");
    }
}
