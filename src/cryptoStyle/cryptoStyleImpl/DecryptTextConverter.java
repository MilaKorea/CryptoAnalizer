package cryptoStyle.cryptoStyleImpl;

import cryptoStyle.Cipher;
import cryptoStyle.TextConverter;

public class DecryptTextConverter implements TextConverter {
    private final Cipher cipher;
    private final int key;

    public DecryptTextConverter(Cipher cipher, int key) {
        this.cipher = cipher;
        this.key = key;
    }

    @Override
    public String convert(String text) {
        return cipher.decrypt(text, key);
    }
}


