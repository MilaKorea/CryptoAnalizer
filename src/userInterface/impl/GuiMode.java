package userInterface.impl;

import userInterface.AbstractUIMode;

import javax.swing.*;
import java.awt.*;

public class GuiMode extends AbstractUIMode {

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
    protected String ask(String message) {
        String input = JOptionPane.showInputDialog(owner, message);
        if (input == null) {
            closeAndExit();
        }
        return input.trim();
    }

    private void closeAndExit() {
        try {
            owner.dispose();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(owner, message);
    }

    @Override
    protected void showError(String message) {
        JOptionPane.showMessageDialog(owner, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void dispose() {
        owner.dispose();
    }
}

