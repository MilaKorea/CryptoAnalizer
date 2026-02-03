package userInterface;

import alphabets.Alphabet;
import exceptions.MyException;
import service.Validator;

import javax.swing.*;
import java.nio.file.Path;
import java.awt.*;

public class GuiMode implements UIMode {
    private final JDialog owner = createOwner();

    private static JDialog createOwner() {
        JDialog dialog = new JDialog((Frame) null, "Crypto Analyzer", false);
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);
        dialog.setType(Window.Type.UTILITY);
        dialog.setSize(1, 1);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(owner, message);
    }

    @Override
    public CryptoAnalyzerMode chooseCryptoMode() {
        String[] options = {
                "1 - Encryption",
                "2 - Decryption with key",
                "3 - Brute force",
                "4 - Statistical decryption"
        };
        while (true) {
            int choiceIndex = JOptionPane.showOptionDialog(
                    owner,
                    "Choose crypto mode:",
                    "Crypto Analyzer",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (choiceIndex == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(owner, "Please choose a crypto mode.");
                continue;
            }
            String input = String.valueOf(choiceIndex + 1);
            try {
                return CryptoAnalyzerMode.choiceByUser(input);
            } catch (MyException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage(),
                        "Invalid crypto mode",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    @Override
    public Path inputFilePath() {
        while (true) {
            String filePath = JOptionPane.showInputDialog(
                    owner,
                    "Please enter the INPUT FILE PATH:"
            );
            if (filePath == null) {
                JOptionPane.showMessageDialog(owner, "Please enter the INPUT FILE PATH.");
                continue;
            }
            try {
                return Validator.validateInputPath(filePath);
            } catch (MyException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage(),
                        "Invalid file path",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public Path outputFilePath(Path inputFilePath) {
        while (true) {
            String filePath = JOptionPane.showInputDialog(
                    owner,
                    "Please enter the OUTPUT FILE PATH:"
            );
            if (filePath == null) {
                JOptionPane.showMessageDialog(owner, "Please enter the OUTPUT FILE PATH.");
                continue;
            }
            try {
                Path outputPath = Validator.validateOutputPath(filePath);
                Validator.validateInputAndOutput(inputFilePath, outputPath);
                return outputPath;
            } catch (MyException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage(),
                        "Invalid file path",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    @Override
    public int chooseKey() {
        while (true) {
            String keyStr = JOptionPane.showInputDialog(
                    owner,
                    "Enter the KEY (must be a number):"
            );
            if (keyStr == null || keyStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(owner, "Please enter the KEY.");
                continue;
            }
            try {
                int key = Integer.parseInt(keyStr.trim());
                Validator.validateKey(key, Alphabet.getRussianAlphabet());
                return key;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        "Key must be a number.",
                        "Invalid key",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (MyException e) {
                JOptionPane.showMessageDialog(
                        owner,
                        e.getMessage(),
                        "Invalid key",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public void dispose() {
        owner.dispose();
    }
}

