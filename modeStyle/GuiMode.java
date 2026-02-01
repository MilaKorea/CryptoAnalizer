package com.javarush.task.jdk13.task53.task5307.modeStyle;

import com.javarush.task.jdk13.task53.task5307.alphabets.Alphabet;
import com.javarush.task.jdk13.task53.task5307.exceptions.MyExeption;
import com.javarush.task.jdk13.task53.task5307.service.Validator;

import javax.swing.*;
import java.nio.file.Path;

public class GuiMode {

    public void start() {

        JOptionPane.showMessageDialog(null, "GUI mode selected.");

        Path filePath = getFilePathGui();
        if (filePath == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }

        Integer key = getKeyGui();
        if (key == null) {
            JOptionPane.showMessageDialog(null, "Operation cancelled.");
            return;
        }

        // 4. Дальше будет то же самое, что в ConsoleMode
        JOptionPane.showMessageDialog(
                null,
                "Input file:\n" + filePath + "\n\nKey: " + key
        );

        // TODO: FileManager.transformFile(...)
    }
    private Path getFilePathGui() {
        while (true) {
            String filePath = JOptionPane.showInputDialog(
                    null,
                    "Please enter the FILE PATH:"
            );

            if (filePath == null) {
                return null;
            }

            try {
                return Validator.validationPath(filePath);
            } catch (MyExeption e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Invalid file path",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    private Integer getKeyGui() {
        while (true) {
            String keyStr = JOptionPane.showInputDialog(
                    null,
                    "Enter the KEY (must be a number):"
            );

            if (keyStr == null) {
                return null;
            }

            try {
                int key = Integer.parseInt(keyStr.trim());
                Validator.validateKey(key, Alphabet.getRussianAlphabet());
                return key;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Key must be a number.",
                        "Invalid key",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (MyExeption e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Invalid key",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

