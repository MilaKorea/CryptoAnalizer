# CryptoAnalyzer

CryptoAnalyzer is a Java application for encrypting and decrypting text files using the Caesar cipher.  
The program supports both console and graphical user interface modes and is designed to work with large text files.

---

## Features

- Encrypt a text file using a specified key
- Decrypt a text file using a known key
- Brute force decryption (automatic key search)
- Console mode
- GUI mode (Swing)

---

## Available Modes

After запуск the application, the user can choose one of the following modes:

1. **Encryption** — encrypts the input file using a provided key
2. **Decryption with key** — decrypts the file using a known key
3. **Brute Force** — attempts to decrypt the file by automatically finding the correct key
4. **Statistical Decryption** — reserved for future implementation

---

## How It Works

The application reads the input file, applies the selected cryptographic operation, and writes the result to an output file.  
File processing is performed in a streaming manner, which allows working with large files without loading them entirely into memory.

---

## User Interface

CryptoAnalyzer can be used in two ways:

- **Console mode** — interaction via command line
- **GUI mode** — interaction through dialog windows (Swing)

The user selects the preferred interface at startup.

---

## Project Structure

- Cipher logic and converters
- File processing and validation
- Console and GUI user interfaces
- Brute force key search logic

---

## Notes

- The statistical decryption mode is currently under further development and will be expanded in future versions.
