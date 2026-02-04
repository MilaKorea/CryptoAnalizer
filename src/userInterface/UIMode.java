package userInterface;

import java.nio.file.Path;

public interface UIMode {
    CryptoAnalyzerMode chooseCryptoMode(); // encrypt/decrypt/brute/stat
    Path inputFilePath();
    Path outputFilePath(Path inputFilePath);
    int chooseKey();
    void showMessage(String message);
    default void close() {}
}
