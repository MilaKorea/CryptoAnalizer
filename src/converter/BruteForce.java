package converter;

import exceptions.MyException;

/*
Реализация перебора всех ключей для взлома.
 */
public class BruteForce {
    private final Cipher cipher;
    private final char[] alphabet;

    public BruteForce(Cipher cipher, char[] alphabet) {
        this.cipher = cipher;
        this.alphabet = alphabet;
    }

    public int findKey(String sampleText) {
        if (sampleText == null || sampleText.isEmpty()) {
            throw new MyException("Sample text is empty. Cannot brute force key.");
        }

        int bestKey = 0;
        int bestScore = Integer.MIN_VALUE;
        for (int key = 1; key < alphabet.length; key++) {
            String decrypted = cipher.decrypt(sampleText, key);
            int score = scoreText(decrypted);
            if (score > bestScore) {
                bestScore = score;
                bestKey = key;
            }
        }
        return bestKey;
    }

    private int scoreText(String text) {
        int score = 0;
        score += count(text, ", ") * 3;
        score += count(text, ". ") * 3;
        score += count(text, "! ") * 3;
        score += count(text, "? ") * 3;

        // пунктуация внутри слова
        score -= countPunctuationInsideWords(text) * 5;
        return score;
    }

    private int count(String text, String simple) {
        int count = 0;
        int index = 0;
        while (true) {
            int found = text.indexOf(simple, index);
            if (found == -1) {
                break;
            }
            count++;
            index = found + simple.length();
        }
        return count;
    }

    private int countPunctuationInsideWords(String text) {
        int count = 0;

        for (int i = 1; i < text.length() - 1; i++) {
            char prev = text.charAt(i - 1);
            char cur = text.charAt(i);
            char next = text.charAt(i + 1);

            if (isLetter(prev) && isPunctuation(cur) && isLetter(next)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPunctuation(char ch) {
        return ch == ',' || ch == '.' || ch == '!' || ch == '?';
    }

    private boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }
}



