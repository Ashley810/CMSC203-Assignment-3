package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {


    @Test
    void testIsStringInBounds() {
        assertTrue(CryptoManager.isStringInBounds("HELLO WORLD"));
        assertFalse(CryptoManager.isStringInBounds("hello")); // lowercase
        assertFalse(CryptoManager.isStringInBounds("{"));     // outside 64-set
    }

    // Caesar Cipher tests
    @Test
    void testCaesarEncryption() {
        assertEquals("KHOOR", CryptoManager.caesarEncryption("HELLO", 3));
        assertEquals("345", CryptoManager.caesarEncryption("012", 3)); // 64-alphabet test
    }

    @Test
    void testCaesarDecryption() {
        assertEquals("HELLO", CryptoManager.caesarDecryption("KHOOR", 3));
        assertEquals("012", CryptoManager.caesarDecryption("345", 3)); // 64-alphabet test
    }

    // Vigenere Cipher tests
    @Test
    void testVigenereEncryption() {
        assertEquals("BCDE", CryptoManager.vigenereEncryption("ABCD", "B"));
    }

    @Test
    void testVigenereDecryption() {
        String encrypted = CryptoManager.vigenereEncryption("PROGRAM", "ABC");
        assertEquals("PROGRAM", CryptoManager.vigenereDecryption(encrypted, "ABC"));
    }

    // Playfair Cipher tests
    @Test
    void testPlayfairEncryption() {
        String encrypted = CryptoManager.playfairEncryption("JAVA", "KEY");
        assertNotEquals("JAVA", encrypted);
        assertTrue(CryptoManager.isStringInBounds(encrypted));
    }

    @Test
    void testPlayfairDecryption() {
        String encrypted = CryptoManager.playfairEncryption("JAVA", "KEY");
        assertEquals("JAVA", CryptoManager.playfairDecryption(encrypted, "KEY"));
    }
}