package com.javarush.task.jdk13.task53.task5307;

import java.util.Scanner;

import com.javarush.task.jdk13.task53.task5307.modeStyle.ConsoleMode;
import com.javarush.task.jdk13.task53.task5307.modeStyle.GuiMode;

/*
Это главный класс, откуда начинается выполнение программы.
 Отвечает за обработку команд пользователя, вызов соответствующих методов
  и управление потоком работы программы.
 */


public class MainApp {
    public static void main(String[] args) {
        System.out.println("Hello! Welcome to Crypto Analyzer!");
        System.out.println("😎 😎 😎 😎 😎 😎 😎 😎 😎 😎 😎");
        System.out.println("Please choose the mode you want to use:");
        System.out.println("1 - Console mode");
        System.out.println("2 - GUI mode");
        System.out.print("Enter your choice (1 or 2): ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String choice = scanner.nextLine().trim();
            if ("1".equals(choice)) {
                new ConsoleMode().start();
                break;
            }
            if ("2".equals(choice)) {
                new GuiMode().start();
                break;
            }
            System.out.print("Invalid choice😒. Please try again (1 or 2): ");
        }
    }
}

