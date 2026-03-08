package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {


	@Test
	void testIsStringInBounds() {
        assertTrue(CryptoManager.isStringInBounds("HELLO WORLD"));
        assertTrue(CryptoManager.isStringInBounds("GOOD MORNING"));
        assertFalse(CryptoManager.isStringInBounds("hello world")); // lowercase is out of bounds
	}
	
	  // Caesar Cipher tests
	@Test
	void testCaesarEncryption() {
		assertEquals("KHOOR", CryptoManager.caesarEncryption("HELLO", 3));
	}

	@Test
	void testCaesarDecryption() {
		assertEquals("HELLO", CryptoManager.caesarDecryption("KHOOR", 3));
	}

    // Vigenere Cipher tests
    @Test
    void testVigenereEncryption() {
        String encrypted = CryptoManager.vigenereEncryption("PROGRAM", "ABC");
        assertNotEquals("PROGRAM", encrypted);  // Should change the text
        assertTrue(CryptoManager.isStringInBounds(encrypted)); 
    }

    @Test
    void testVigenereDecryption() {
        String encrypted = CryptoManager.vigenereEncryption("PROGRAM", "ABC");
        String decrypted = CryptoManager.vigenereDecryption(encrypted, "ABC");
        assertEquals("PROGRAM", decrypted); // Decryption returns original
    }

    // Playfair Cipher tests
    @Test
    void testPlayfairEncryption() {
        String encrypted = CryptoManager.playfairEncryption("JAVA", "KEY");
        assertNotEquals("JAVA", encrypted);  // Encryption changes the text
        assertTrue(CryptoManager.isStringInBounds(encrypted));
    }

    @Test
    void testPlayfairDecryption() {
        String encrypted = CryptoManager.playfairEncryption("JAVA", "KEY");
        String decrypted = CryptoManager.playfairDecryption(encrypted, "KEY");
        assertEquals("JAVA", decrypted); // Decryption returns original
    }
}