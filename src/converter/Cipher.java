package converter;

import java.util.HashMap;
import java.util.Map;

/*
Класс, реализующий функциональность шифра Цезаря и дешифровки
 */
public class Cipher {
    private final char[] alphabet;
    private final Map<Character, Integer> alphabetOnIndex;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
        this.alphabetOnIndex = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetOnIndex.put(alphabet[i], i);
        }
    }

    public String encrypt(String text, int key) {
        return transformText(text, key);
    }

    public String decrypt(String text, int key) {
        return transformText(text, -key);
    }

    private String transformText(String text, int key) {
        int n = alphabet.length;
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            Integer idx = alphabetOnIndex.get(ch);
            if (idx == null) {
                sb.append(ch);
                continue;
            }
            int newIdx = (idx + key + n) % n;
            sb.append(alphabet[newIdx]);
        }
        return sb.toString();
    }
}

