/**
 * MIT License
 *
 * Copyright (c) 2017-2022 Julb
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package me.julb.commons.security.crypto.aes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class enables encryption and decryption of a {@link String} using AES256.
 * <br>
 * @author Julb.
 */
class AESEncryptorTest {

    /**
     * Test method.
     */
    @Test
    void whenEncryptingDecryptingSameKey_thenItWorks() {
        String rawText = "textToEncrypt";
        String key = "aaaabbbbccccddddaaaabbbbccccdddd";
        String salt = "aaaabbbbccccaaaabbbbccccaaaabbbb";

        String cipheredText = AESEncryptor.encrypt(rawText, key, salt);
        String plainText = AESEncryptor.decrypt(cipheredText, key, salt);
        Assertions.assertEquals(rawText, plainText);
    }

    /**
     * Test method.
     */
    @Test
    void whenEncryptingDecryptingDifferentKey_thenThrow() {
        String rawText = "textToEncrypt";
        String key = "aaaabbbbccccddddaaaabbbbccccdddd";
        String salt = "aaaabbbbccccaaaabbbbccccaaaabbbb";

        String falseKey = "bbbbccccddddaaaabbbbccccddddaaaa";
        String falseSalt = "bbbbccccddddaaaabbbbccccddddaaaa";

        String cipheredText = AESEncryptor.encrypt(rawText, key, salt);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            AESEncryptor.decrypt(cipheredText, falseKey, falseSalt);
        });
    }
}
