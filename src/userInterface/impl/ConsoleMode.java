package userInterface.impl;

import userInterface.AbstractUIMode;

import java.util.Scanner;

public class ConsoleMode extends AbstractUIMode {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected String ask(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    protected void showError(String message) {
        System.out.println(message);
    }
}


