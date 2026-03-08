package BobsCircus;
/**
 *  Class: CMSC203 CRN 32608
 Program: Assignment 3
 Instructor: Farnaz Eivazi
 Summary of Description: This is a program that encrypt and decrypt a phrase using three different approaches.
The first approach is called the Vigenere Cipher. Vigenere encryption is a method of encrypting alphabetic text based on the letters of a keyword.
The second approach is Playfair Cipher. It encrypts two letters (a digraph) at a time instead of just one.
The third approach is Caesar Cipher which is a simple substitution cipher.
Due Date: 03/08/2026
Integrity Pledge: I pledge that I have completed the programming assignment independently.
I have not copied the code from a student or any source.
Student Name: Ashley Dewitt
 * 
 * @author Ashley Dewitt
 * @version 03/08/2026
 */

public class CryptoManager { 

    private static final char LOWER_RANGE = ' '; // This is a space it corresponds to 32 ASCII
    private static final char UPPER_RANGE = '_'; // This is an undersoce it corresponds to 95 ASCII
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1; 
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    /**
     * Checks if the string is within the allowed ASCII range.
     * @param plainText The text to check.
     * @return true if all characters are in bounds, false otherwise.
     */
    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */ 
    
    /**
     * Encrypts text using the Vigenere Cipher.
     * @param plainText The text to encrypt.
     * @param key The keyword for the shift.
     * @return The encrypted string.
     */

    public static String vigenereEncryption(String plainText, String key) {
         //First check if the text is inside the ASCII range
    	if (!isStringInBounds(plainText)) {
    		return "The selected string is not in bounds, Try again.";
    	}
    	
    	String encryptedText = "";
    	
    	for (int i = 0; i < plainText.length(); i++ ) {
    		
    		// repeat the key so it matches the length of the plaintext
    		char keyChar = key.charAt(i % key.length());
    		
    		int shift = keyChar - LOWER_RANGE;
    		
    		// shifts the plaintext character by the key character
    		int newChar = plainText.charAt(i) + shift;
    		
    		// Wraps around if it exceeds UPPER_RANGE
    		while (newChar > UPPER_RANGE) {
    			newChar -= RANGE;
    		}
    		
    		// adds the encrypted charater to the final string
    		encryptedText += (char)newChar;
            }
    	
    	return encryptedText;
    	}

    /**
     * Decrypts text using the Vigenere Cipher.
     * @param encryptedText The text to decrypt.
     * @param key The keyword for the shift.
     * @return The decrypted string.
     */
    
    public static String vigenereDecryption(String encryptedText, String key) {
    	
        String decryptedText = "";
        
        for (int i = 0; i < encryptedText.length(); i++) {
        	
        	//Repeats the key if its necessary
        	char keyChar = key.charAt(i % key.length());
        	
        	// turns key into the same shift amount used during encryption
        	int shift = keyChar - LOWER_RANGE;
        	
        	int newChar = encryptedText.charAt(i) - shift;
        	
        	// wraps around if it goes below LOWER_RANGE
        	while (newChar < LOWER_RANGE) {
        		newChar += RANGE;
        	}
        	
        	decryptedText += (char)newChar;
        }
        
        return decryptedText;
    }

	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    
    /**
     * Encrypts text using an 8x8 Playfair Cipher matrix.
     * @param plainText The text to encrypt.
     * @param key The keyword for the matrix.
     * @return The encrypted string.
     */

    public static String playfairEncryption(String plainText, String key) {

        // Check if the plaintext contains only characters within the bounds
        if (!isStringInBounds(plainText)) {
            // If not, return an error message
            return "The selected string is not in bounds, Try again.";
        }

        // Create the 8x8 matrix used for the Playfair cipher
        char[][] matrix = new char[8][8];

        // This string will store all characters that have already been used in the matrix
        String charactersUsed = "";
        
        // This string will store the final encrypted message
        String encryptedText = "";

        // Add characters from the key first
    
        // Loop through each character in the key
        for (int i = 0; i < key.length(); i++) {

            // Get the current character from the key
            char keyChar = key.charAt(i);

            // Assume the character has not been used yet
            boolean alreadyUsed = false;

            // Check if this character is already inside charactersUsed
            for (int j = 0; j < charactersUsed.length(); j++) {

                // If we find the same character, mark it as already used
                if (charactersUsed.charAt(j) == keyChar) {
                    alreadyUsed = true;
                }
            }

            // If the character has not been used yet
            if (!alreadyUsed) {
                // Add it to the charactersUsed string
                charactersUsed = charactersUsed + keyChar;
            }
        }

        // Add the rest of the characters from ALPHABET64

        // Loop through every character in the provided alphabet
        for (int i = 0; i < ALPHABET64.length(); i++) {

            // Get the current character from the alphabet
            char alphabetChar = ALPHABET64.charAt(i);

            // Assume the character has not been used yet
            boolean alreadyUsed = false;

            // Check if this character already exists in charactersUsed
            for (int j = 0; j < charactersUsed.length(); j++) {

                // If the character is found, mark it as already used
                if (charactersUsed.charAt(j) == alphabetChar) {
                    alreadyUsed = true;
                }
            }

            // If the character has not been used yet
            if (!alreadyUsed) {
                // Add it to the charactersUsed string
                charactersUsed = charactersUsed + alphabetChar;
            }
        }

        // Fill the 8x8 matrix

        // This variable keeps track of which character from charactersUsed i'm inserting
        int matrixIndex = 0;

        // Loop through each row of the matrix
        for (int row = 0; row < 8; row++) {

            // Loop through each column of the matrix
            for (int col = 0; col < 8; col++) {

                // Place the next character from charactersUsed into the matrix
                matrix[row][col] = charactersUsed.charAt(matrixIndex);

                // Move to the next character
                matrixIndex++;
            }
        }
        
        // Process the plaintext two characters at a time

        // Move through the plaintext in steps of 2
        for (int i = 0; i < plainText.length(); i += 2) {

            // Get the first character of the pair
            char firstChar = plainText.charAt(i);

            // Variable to store the second character
            char secondChar;

            // Check if a second character exists
            if (i + 1 < plainText.length()) {

                // If yes, use it
                secondChar = plainText.charAt(i + 1);

            } else {
                // If not, add a padding X
                secondChar = 'X';
            }
            // Variables to store the matrix position of the first character
            int firstRow = 0;
            int firstCol = 0;

            // Variables to store the matrix position of the second character
            int secondRow = 0;
            int secondCol = 0;

            // Find positions of both characters in the matrix

            // Loop through all rows
            for (int row = 0; row < 8; row++) {

                // Loop through all columns
                for (int col = 0; col < 8; col++) {

                    // If this matrix matches the first character
                    if (matrix[row][col] == firstChar) {

                        // Save its position
                        firstRow = row;
                        firstCol = col;
                    }

                    // If this matrix matches the second character
                    if (matrix[row][col] == secondChar) {

                        // Save its position
                        secondRow = row;
                        secondCol = col;
                    }
                }
            }
            
            // Apply Playfair encryption rules

            // If both letters are in the same row
            if (firstRow == secondRow) {

                // Move both letters one column to the right
                firstCol = (firstCol + 1) % 8;
                secondCol = (secondCol + 1) % 8;
            }
            
            // If both letters are in the same column
            else if (firstCol == secondCol) {

                // Move both letters one row down
                firstRow = (firstRow + 1) % 8;
                secondRow = (secondRow + 1) % 8;
            }

            // Rectangle rule
            else {
            	
                // Swap the columns of the two letters
                int tempColumn = firstCol;

                firstCol = secondCol;

                secondCol = tempColumn;
            }

            // Add encrypted characters to the result

            // Add the encrypted version of the first character
            encryptedText = encryptedText + matrix[firstRow][firstCol];

            // Add the encrypted version of the second character
            encryptedText = encryptedText + matrix[secondRow][secondCol];
        }

        // Return the final encrypted message
        return encryptedText;
    }
  
    /**
     * Decrypts text using an 8x8 Playfair Cipher matrix.
     * @param encryptedText The text to decrypt.
     * @param key The keyword for the matrix.
     * @return The decrypted string.
     */
    
    public static String playfairDecryption(String encryptedText, String key) {
    	
    	// create an 8x8 matrix
    	char[][] matrix = new char[8][8];

        // This will store the final decrypted message
    	String charactersUsed = "";
    	
        // This will store the decrypted message
        String decryptedText = "";
    	
    	// Add key characters
        for (int i = 0; i < key.length(); i++) {
            char keyChar = key.charAt(i);
            boolean alreadyUsed = false;
            for (int j = 0; j < charactersUsed.length(); j++) {
                if (charactersUsed.charAt(j) == keyChar) {
                    alreadyUsed = true;
                }
            }
            if (!alreadyUsed) {
                charactersUsed = charactersUsed + keyChar;
            }
        }

        // Add ALPHABET64 characters
        for (int i = 0; i < ALPHABET64.length(); i++) {
            char alphabetChar = ALPHABET64.charAt(i);
            boolean alreadyUsed = false;
            for (int j = 0; j < charactersUsed.length(); j++) {
                if (charactersUsed.charAt(j) == alphabetChar) {
                    alreadyUsed = true;
                }
            }
            if (!alreadyUsed) {
                charactersUsed = charactersUsed + alphabetChar;
            }
        }

        // Fill the 8x8 matrix
        int matrixIndex = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                matrix[row][col] = charactersUsed.charAt(matrixIndex);
                matrixIndex++;
            }
        }

        // Loop through the encrypted message two characters at a time
        for (int i = 0; i < encryptedText.length(); i += 2) {

            // Get the first character of the pair
            char firstLetter = encryptedText.charAt(i);

            // Get the second character of the pair
            char secondLetter = encryptedText.charAt(i + 1);

            // These variables will store the row and column of the first letter
            int firstRow = -1;
            int firstColumn = -1;

            // These variables will store the row and column of the second letter
            int secondRow = -1;
            int secondColumn = -1;

            // Search the entire 8x8 matrix to find where the first and second letters are located
            for (int row = 0; row < 8; row++) {

                for (int column = 0; column < 8; column++) {

                    // If this position matches the first letter
                    if (matrix[row][column] == firstLetter) {
                        firstRow = row;
                        firstColumn = column;
                    }

                    // If this position matches the second letter
                    if (matrix[row][column] == secondLetter) {
                        secondRow = row;
                        secondColumn = column;
                    }
                }
            }

            // If both letters are in the same row
            if (firstRow == secondRow) {

                // Move one column LEFT (wrap around if needed)
                int newFirstColumn = (firstColumn - 1 + 8) % 8;

                // Move one column LEFT for the second letter
                int newSecondColumn = (secondColumn - 1 + 8) % 8;

                // Add the decrypted letters to the final message
                decryptedText += matrix[firstRow][newFirstColumn];
                decryptedText += matrix[secondRow][newSecondColumn];
            }

            // If both letters are in the same column
            else if (firstColumn == secondColumn) {

                // Move one row up (wrap around if needed)
                int newFirstRow = (firstRow - 1 + 8) % 8;

                // Move one row up for the second letter
                int newSecondRow = (secondRow - 1 + 8) % 8;

                // Add the decrypted letters to the final message
                decryptedText += matrix[newFirstRow][firstColumn];
                decryptedText += matrix[newSecondRow][secondColumn];
            }

            // If the letters form a rectangle
            else {

                // Take the column from the opposite corner
                decryptedText += matrix[firstRow][secondColumn];

                // Take the column from the opposite corner
                decryptedText += matrix[secondRow][firstColumn];
            }
        }

        // Return the fully decrypted message
        return decryptedText;
    }

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
    
    /**
     * Encrypts text using the Caesar Cipher.
     * @param plainText The text to encrypt.
     * @param key The number of positions to shift.
     * @return The encrypted string.
     */
 
    public static String caesarEncryption(String plainText, int key) {
        // First, check if all characters in plainText are within the allowed ASCII bounds
        if (!isStringInBounds(plainText)) {
            // If any character is out of bounds, return an error message
            return "The selected string is not in bounds, Try again.";
        }

        // This will store the final encrypted text
        String encryptedText = "";

        // Go through each character of the plain text
        for (int i = 0; i < plainText.length(); i++) {
        
            // Shift the character by the key value
            int newChar = (int) plainText.charAt(i) + key;

            // If the shifted character goes beyond the upper bound, wrap it back around
            while (newChar > UPPER_RANGE) {
            	newChar -= RANGE;
            }

            // Add the encrypted character to our final string
            encryptedText += (char)newChar;
        }

        // Return the fully encrypted string
        return encryptedText;
    }

    /**
     * Decrypts text using the Caesar Cipher.
     * @param encryptedText The text to decrypt.
     * @param key The number of positions to shift.
     * @return The decrypted string.
     */
    
    public static String caesarDecryption(String encryptedText, int key) {
    	
    	String decryptedText = "";
    	
    	for (int i = 0; i < encryptedText.length(); i++) {
    		
    		// Convert the charater into a integer and then subtract the key
    		int newChar = encryptedText.charAt(i) - key;
    		
    		// if the character goes below the lower range, wrap it back aorund
    		while (newChar < LOWER_RANGE) {
    			newChar += RANGE;
    		}
    		
    		// Convert the ASCII value back into a character and then add it to the decrypted string
    		decryptedText += (char)newChar;
    	}
    	return decryptedText;
    }    
}