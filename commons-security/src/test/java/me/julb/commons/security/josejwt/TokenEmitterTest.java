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
package me.julb.commons.security.josejwt;

import java.util.Calendar;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;

import me.julb.commons.security.josejwt.jwk.IJWKProvider;
import me.julb.commons.security.josejwt.jwk.impl.ManualAsymmetricJWKProvider;
import me.julb.commons.security.josejwt.jwk.impl.ManualSymmetricJWKProvider;
import me.julb.commons.security.josejwt.operations.TokenDecryptionOperation;

/**
 * Unit test class for {@link TokenDecryptionOperation}.
 * <br>
 * @author Julb.
 */
class TokenEmitterTest {

    /**
     * A raw JSON web token.
     */
    private JWTClaimsSet jwtClaimsSet;

    /**
     * The symmetric JWK provider.
     */
    private IJWKProvider symmetricJWKProvider;

    /**
     * The asymmetric RSA JWK provider.
     */
    private IJWKProvider asymmetricRSAJWKProvider;

    // ------------------------------------------ Constructors.

    // ------------------------------------------ Before/After methods.

    /**
     * Sets-up the test.
     */
    @BeforeEach
    public void setUp() throws Exception {
        Calendar issueTime = Calendar.getInstance();

        Calendar expirationTime = Calendar.getInstance();
        expirationTime.add(Calendar.HOUR, 1);

        // @formatter:off
        this.jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("API_Gateway")
                .jwtID("jwtId")
                .subject("contact@julb.io")
                .claim("typ", "U2M")
                .issueTime(issueTime.getTime())
                .expirationTime(expirationTime.getTime())
                .build();
        // @formatter:on

        // @formatter:off
        RSAKey rsaKey = new RSAKeyGenerator(2048).generate();
        this.asymmetricRSAJWKProvider = new ManualAsymmetricJWKProvider.Builder()
                .algorithm(JWSAlgorithm.RS384.getName())
                .keyId(UUID.randomUUID().toString())
                .keyPair(rsaKey.toRSAPrivateKey(), rsaKey.toRSAPublicKey())
                .useForSignature()
                .build();
        // @formatter:on

        // @formatter:off
        this.symmetricJWKProvider = new ManualSymmetricJWKProvider.Builder()
                .algorithm("dir")
                .keyId(UUID.randomUUID().toString())
                .secretKey("aaaaaaaabbbbbbbbccccccccdddddddd")
                .useForEncryption()
                .build();
        // @formatter:on
    }

    /**
     * Test method.
     */
    @Test
    void whenEmittingToken_thenReturnValidJWE() throws Exception {
        // @formatter:off
        String emittedToken = new TokenEmitter()
                .setSignatureJWKProvider(asymmetricRSAJWKProvider)
                .setEncryptionJWKProvider(symmetricJWKProvider)
                .emit(this.jwtClaimsSet.toString());
        // @formatter:on

        Assertions.assertNotNull(emittedToken);
    }

    /**
     * Test method.
     */
    @Test
    void whenEmittingTokenWithoutSignature_thenThrowIllegalArgumentException() throws Exception {
        var tokenEmitter = new TokenEmitter().setEncryptionJWKProvider(symmetricJWKProvider);
        var jwtClaimsSetString = this.jwtClaimsSet.toString();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tokenEmitter.emit(jwtClaimsSetString);
        });
    }
}
