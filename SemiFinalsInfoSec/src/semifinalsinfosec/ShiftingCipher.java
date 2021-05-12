/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semifinalsinfosec;

/**
 *
 * @author Jerrell
 */
public final class ShiftingCipher implements IEncryptionMethod {

    private final String input;
    private final int shift;
    private final int testPositiveShift = 3;
    private final int testNegativeShift = -3;
    private final String testExpectedBackwardOutput = "XxYyZzAaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWw";
    private final String testExpectedForwardOutput = "DdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZzAaBbCc";
    
    public ShiftingCipher(final String input, int shift) {
        this.input = input;
        
        // Check if the passed shift is negative
        if (shift < 0) {
            shift *= HelperClass.NEGATIVE_ONE;
            shift %= HelperClass.ALPHABET_LENGTH;
            shift *= HelperClass.NEGATIVE_ONE;
            this.shift = shift;
        } else {
            this.shift = shift % HelperClass.ALPHABET_LENGTH;
        }
    }
    
    @Override
    public String encrypt() {
        final StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) { 
            // temp holds char to be checked 
            final char temp = input.charAt(i);
           
            if (Character.isLetter(temp)) { 
                final char c = (char)(temp + shift);
                if (Character.isLowerCase(temp)) {                    
                    if (shift > HelperClass.ZERO) {
                        if (c > HelperClass.LOWERCASE_Z) {
                            builder.append((char)(temp - (HelperClass.ALPHABET_LENGTH - (shift))));
                        } else {
                            builder.append(c);
                        }
                    } else {
                        if (c < HelperClass.LOWERCASE_A) {
                            builder.append((char)(temp + (HelperClass.ALPHABET_LENGTH + (shift))));
                        } else {
                            builder.append(c);
                        }
                    }
                } else if (Character.isUpperCase(temp)) {
                    if (shift > HelperClass.ZERO) {
                        if (c > HelperClass.UPPERCASE_Z) {
                            builder.append((char)(temp - (HelperClass.ALPHABET_LENGTH - (shift))));
                        } else {
                            builder.append(c);
                        }
                    } else {
                        if (c < HelperClass.UPPERCASE_A) {
                            builder.append((char)(temp + (HelperClass.ALPHABET_LENGTH + (shift))));
                        } else {
                            builder.append(c);
                        }
                    }
                }
            } else {
                builder.append(temp);
            }
        }
                
        return builder.toString();
    }

    @Override
    public String decrypt() {
        final StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) { 
            // temp holds char to be checked 
            final char temp = input.charAt(i);
           
            if (Character.isLetter(temp)) { 
                final char c = (char)(temp - shift);
                if (Character.isLowerCase(temp)) {
                    if (shift < HelperClass.ZERO) {
                        if (c > HelperClass.LOWERCASE_Z) {
                            builder.append((char)(temp - (HelperClass.ALPHABET_LENGTH + (shift))));
                        } else {
                            builder.append(c);
                        }
                    } else {
                        if (c < HelperClass.LOWERCASE_A) {
                            builder.append((char)(temp + (HelperClass.ALPHABET_LENGTH - (shift))));
                        } else {
                            builder.append(c);
                        }
                    }
                } else if (Character.isUpperCase(temp)) {
                    if (shift < HelperClass.ZERO) {
                        if (c > HelperClass.UPPERCASE_Z) {
                            builder.append((char)(temp - (HelperClass.ALPHABET_LENGTH + (shift))));
                        } else {
                            builder.append(c);
                        }
                    } else {
                        if (c < HelperClass.UPPERCASE_A) {
                            builder.append((char)(temp + (HelperClass.ALPHABET_LENGTH - (shift))));
                        } else {
                            builder.append(c);
                        }
                    }
                } else {
                    builder.append(temp);
                }
            }
        }
                
        return builder.toString();
    }
    
    public void testEncryptPositive() {
        // When
        final ShiftingCipher test = new ShiftingCipher(HelperClass.SHIFT_CASE_ALPHABET, testPositiveShift);
        
        // Verify
        System.out.println("testEncryptPositive(): test passed: " + test.encrypt().equals(testExpectedForwardOutput));
    }
    
    public void testEncryptNegative() {
        // When
        final ShiftingCipher test = new ShiftingCipher(HelperClass.SHIFT_CASE_ALPHABET, testNegativeShift);
        
        // Verify
        System.out.println("testEncryptNegative(): test passed: " + test.encrypt().equals(testExpectedBackwardOutput));
    }
    
    public void testDecryptPositive() {
        // When
        final ShiftingCipher test = new ShiftingCipher(HelperClass.SHIFT_CASE_ALPHABET, testPositiveShift);
        
        // Verify
        System.out.println("testDecryptPositive(): test passed: " + test.decrypt().equals(testExpectedBackwardOutput));
    }
    
    public void testDecryptNegative() {
        // When
        final ShiftingCipher test = new ShiftingCipher(HelperClass.SHIFT_CASE_ALPHABET, testNegativeShift);
        
        // Verify
        System.out.println("testDecryptNegative(): test passed: " + test.decrypt().equals(testExpectedForwardOutput));
    }
}
