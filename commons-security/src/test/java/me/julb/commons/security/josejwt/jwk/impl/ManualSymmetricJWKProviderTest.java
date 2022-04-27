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
package me.julb.commons.security.josejwt.jwk.impl;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.julb.commons.security.josejwt.exceptions.internalservererror.MissingFieldInKeyFormatException;

import lombok.Getter;
import lombok.Setter;

/**
 * The symmetric JWK.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
class ManualSymmetricJWKProviderTest {

    /**
     * The algorithm to test.
     */
    private String algorithm;

    /**
     * The key ID.
     */
    private String keyId;

    /**
     * The secret key.
     */
    private String secretKey;

    /**
     * Sets-up the test before run.
     */
    @BeforeEach
    public void setUp() {
        this.algorithm = "A256GCM";
        this.keyId = UUID.randomUUID().toString();
        this.secretKey = "aaaaaaaabbbbbbbbccccccccdddddddd";
    }

    /**
     * Test method.
     */
    @Test
    void whenBuildKeyForEncryption_thenReturnValidJSONString() {
        // @formatter:off
        String jsonString = new ManualSymmetricJWKProvider.Builder()
                .algorithm(algorithm)
                .keyId(keyId)
                .secretKey(secretKey)
                .useForEncryption()
                .build()
                .toJSONString();
        // @formatter:on

        JSONObject jsonObject = new JSONObject(jsonString);
        Assertions.assertEquals(algorithm, jsonObject.getString("alg"));
        Assertions.assertEquals("enc", jsonObject.getString("use"));
        Assertions.assertEquals(keyId, jsonObject.getString("kid"));
        Assertions.assertEquals(Base64.encodeBase64URLSafeString(secretKey.getBytes()), jsonObject.getString("k"));
    }

    /**
     * Test method.
     */
    @Test
    void whenBuildKeyForSignature_thenReturnValidJSONString() {
        // @formatter:off
        String jsonString = new ManualSymmetricJWKProvider.Builder()
                .algorithm(algorithm)
                .keyId(keyId)
                .secretKey(secretKey)
                .useForSignature()
                .build()
                .toJSONString();
        // @formatter:on

        JSONObject jsonObject = new JSONObject(jsonString);
        Assertions.assertEquals(algorithm, jsonObject.getString("alg"));
        Assertions.assertEquals("sig", jsonObject.getString("use"));
        Assertions.assertEquals(keyId, jsonObject.getString("kid"));
        Assertions.assertEquals(Base64.encodeBase64URLSafeString(secretKey.getBytes()), jsonObject.getString("k"));
    }

    /**
     * Test method.
     */
    @Test
    void whenMissingAlgorithm_thenThrowException() {
        // @formatter:off
        var manualSymmetricJWKProvider = new ManualSymmetricJWKProvider.Builder()
                .keyId(keyId)
                .secretKey(secretKey)
                .useForSignature()
                .build();
        // @formatter:on

        Assertions.assertThrows(MissingFieldInKeyFormatException.class, () -> {
            manualSymmetricJWKProvider.toJSONString();
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenMissingKeyId_thenThrowException() {
        // @formatter:off
        var manualSymmetricJWKProvider = new ManualSymmetricJWKProvider.Builder()
                .algorithm(algorithm)
                .secretKey(secretKey)
                .useForSignature()
                .build();
        // @formatter:on

        Assertions.assertThrows(MissingFieldInKeyFormatException.class, () -> {
            manualSymmetricJWKProvider.toJSONString();
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenMissingSecretKey_thenThrowException() {
        // @formatter:off
        var manualSymmetricJWKProvider = new ManualSymmetricJWKProvider.Builder()
                .algorithm(algorithm)
                .keyId(keyId)
                .useForSignature()
                .build();
        // @formatter:on

        Assertions.assertThrows(MissingFieldInKeyFormatException.class, () -> {
            manualSymmetricJWKProvider.toJSONString();
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenMissingUse_thenThrowException() {
        // @formatter:off
        var manualSymmetricJWKProvider = new ManualSymmetricJWKProvider.Builder()
                .algorithm(algorithm)
                .keyId(keyId)
                .secretKey(secretKey)
                .build();
        // @formatter:on

        Assertions.assertThrows(MissingFieldInKeyFormatException.class, () -> {
            manualSymmetricJWKProvider.toJSONString();
        });
    }
}
