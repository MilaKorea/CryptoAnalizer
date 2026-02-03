package alphabets;

import java.util.Arrays;

public class Alphabet {
    private Alphabet() {}

    private static final char[] RUSSIAN =(
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + " ,.: -!?").toCharArray();

    public static char[] getRussianAlphabet() {
        return Arrays.copyOf(RUSSIAN, RUSSIAN.length);
    }

}
