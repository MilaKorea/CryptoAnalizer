package app;

import alphabets.Alphabet;
import converter.BruteForce;
import converter.Cipher;
import converter.impl.DecryptTextConverter;
import converter.impl.EncryptTextConverter;
import converter.TextConverter;
import service.FileService;
import userInterface.CryptoAnalyzerMode;
import userInterface.impl.ConsoleMode;
import userInterface.impl.GuiMode;
import userInterface.UIMode;

import java.nio.file.Path;
import java.util.Scanner;

public class CryptoAnalyzerApp {
    private static final int SAMPLE_SIZE = 20_000;

    private static final String UI_CONSOLE = "1";
    private static final String UI_GUI = "2";

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Welcome to Crypto Analyzer!");
        System.out.println("😎 😎 😎 😎 😎 😎 😎 😎 😎 😎 😎");
        System.out.println("Please choose the mode you want to use:");
        System.out.println("1 - Console mode");
        System.out.println("2 - GUI mode");
        System.out.print("Enter your choice (1 or 2): ");
        System.out.println();

        UIMode ui = chooseUi(scanner);
        ui.showMessage((ui instanceof ConsoleMode) ? "Console mode selected." : "GUI mode selected.");

        CryptoAnalyzerMode mode = ui.chooseCryptoMode();
        Path inputPath = ui.inputFilePath();
        Path outputPath = ui.outputFilePath(inputPath);

        Integer key = null;
        if (mode.isKeyNeed()) {
            key = ui.chooseKey();
        }

        ui.showMessage("Mode: " + mode);
        ui.showMessage("Input file: " + inputPath);
        ui.showMessage("Output file: " + outputPath);
        if (mode.isKeyNeed()) {
            ui.showMessage("Key: " + key);
        }

        runCrypto(mode, inputPath, outputPath, key, ui);

        ui.showMessage("Done ✅ Output saved to: " + outputPath);
    }

    private UIMode chooseUi(Scanner scanner) {
        while (true) {
            String choice = scanner.nextLine().trim();
            if (UI_CONSOLE.equals(choice)) return new ConsoleMode();
            if (UI_GUI.equals(choice)) return new GuiMode();
            System.out.print("Invalid choice😒. Please try again (1 or 2): ");
        }
    }

    private void runCrypto(CryptoAnalyzerMode mode, Path inputPath, Path outputPath, Integer key, UIMode ui) {
        char[] alphabet = Alphabet.getRussianAlphabet();
        Cipher cipher = new Cipher(alphabet);
        FileService fileManager = new FileService();

        TextConverter converter = createConverter(mode, inputPath, key, cipher, alphabet, fileManager, ui);

        fileManager.processFile(inputPath, outputPath, converter);
    }

    private TextConverter createConverter(
            CryptoAnalyzerMode mode,
            Path inputPath,
            Integer key,
            Cipher cipher,
            char[] alphabet,
            FileService fileManager,
            UIMode ui
    ) {
        switch (mode) {
            case ENCRYPTION:
                return new EncryptTextConverter(cipher, key);
            case DECRYPTION_WITH_KEY:
                return new DecryptTextConverter(cipher, key);
            case BRUTE_FORCE:
                String sample = fileManager.readSample(inputPath, SAMPLE_SIZE);
                BruteForce bruteForce = new BruteForce(cipher, alphabet);
                int usedKey = bruteForce.findKey(sample);
                ui.showMessage("Key used: " + usedKey);
                return new DecryptTextConverter(cipher, usedKey);
            case STATISTICAL_DECRYPTION:
                ui.showMessage("Statistical decryption is not implemented yet.");
                throw new IllegalStateException("Statistical decryption is not implemented.");
            default:
                throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }
}
