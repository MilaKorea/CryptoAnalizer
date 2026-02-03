import alphabets.Alphabet;
import cryptoStyle.*;
import service.FileManager;
import userInterface.ConsoleMode;
import userInterface.CryptoAnalyzerMode;
import userInterface.GuiMode;
import userInterface.UIMode;

import java.nio.file.Path;
import java.util.Scanner;

/*
Это главный класс, откуда начинается выполнение программы.
 Отвечает за обработку команд пользователя, вызов соответствующих методов
  и управление потоком работы программы.
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UIMode ui = null;

        System.out.println("Hello! Welcome to Crypto Analyzer!");
        System.out.println("😎 😎 😎 😎 😎 😎 😎 😎 😎 😎 😎");
        System.out.println("Please choose the mode you want to use:");
        System.out.println("1 - Console mode");
        System.out.println("2 - GUI mode");
        System.out.print("Enter your choice (1 or 2): ");


        while (true) {
            String choice = scanner.nextLine().trim();
            if ("1".equals(choice)) {
                ui = new ConsoleMode();
                break;
            }
            if ("2".equals(choice)) {
                ui = new GuiMode();
                break;
            }
            System.out.print("Invalid choice😒. Please try again (1 or 2): ");
        }
        ui.showMessage(
                (ui instanceof ConsoleMode) ? "Console mode selected." : "GUI mode selected."
        );

        CryptoAnalyzerMode mode = ui.chooseCryptoMode();
        Path inputPath = ui.inputFilePath();
        Path outputPath = ui.outputFilePath(inputPath);

        Integer key = null;
        if (mode.isKeyNeed()) {
            key = ui.chooseKey();
        }

        ui.showMessage("Mode: " + mode);
        ui.showMessage("File: " + inputPath);
        ui.showMessage("File: " + outputPath);
        if (mode.isKeyNeed()) {
            ui.showMessage("Key: " + key);
        }

        char[] alphabet = Alphabet.getRussianAlphabet();
        Cipher cipher = new Cipher(alphabet);
        FileManager fileManager = new FileManager();
        TextConverter converter;
        int usedKey = -1;

        switch (mode) {
            case ENCRYPTION:
                usedKey = key;
                converter = new EncryptTextConverter(cipher, key);
                break;
            case DECRYPTION_WITH_KEY:
                usedKey = key;
                converter = new DecryptTextConverter(cipher, key);
                break;
            case BRUTE_FORCE:
                String sample = fileManager.readSample(inputPath, 20_000);
                BruteForce bruteForce = new BruteForce(cipher, alphabet);
                usedKey = bruteForce.findKey(sample);
                converter = new DecryptTextConverter(cipher, usedKey);
                break;
            case STATISTICAL_DECRYPTION:
                ui.showMessage("Statistical decryption is not implemented yet.");
                return;
            default:
                ui.showMessage("Unknown mode.");
                return;
        }
        fileManager.processFile(inputPath, outputPath, converter);

        ui.showMessage("Done ✅ Output saved to: " + outputPath);
        ui.showMessage("Key used: " + usedKey);

    }
}

