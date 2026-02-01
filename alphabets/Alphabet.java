package com.javarush.task.jdk13.task53.task5307.alphabets;

import java.util.Arrays;

public class Alphabet {
    private Alphabet() {}

    private static final char[] RUSSIAN =(
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + " ,.: -!?").toCharArray();

    public static char[] getRussianAlphabet() {
        return Arrays.copyOf(RUSSIAN, RUSSIAN.length);
    }

}
