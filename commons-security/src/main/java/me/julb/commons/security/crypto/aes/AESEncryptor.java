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

import org.springframework.security.crypto.encrypt.Encryptors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class enables encryption and decryption of a {@link String} using AES256.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AESEncryptor {

    /**
     * Encrypts the value.
     * @param plainText the text to encrypt.
     * @param key the key.
     * @param salt the salt.
     * @return the ciphered value.
     */
    public static String encrypt(String plainText, String key, String salt) {
        return Encryptors.text(key, salt).encrypt(plainText);
    }

    /**
     * Decrypts the value.
     * @param cipherText the text to encrypt.
     * @param key the key.
     * @param salt the salt.
     * @return the raw value.
     */
    public static String decrypt(String cipherText, String key, String salt) {
        return Encryptors.text(key, salt).decrypt(cipherText);
    }
}
