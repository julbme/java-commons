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
package me.julb.commons.security.josejwt.operations;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.ECDHEncrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;

import me.julb.commons.security.josejwt.digest.TokenDigestUtility;
import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnsupportedKeyTypeJOSEJWTException;
import me.julb.commons.security.josejwt.jwk.IJWKProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * The operation that signs a token with a private key.
 * <br>
 * @author Julb.
 */
@Slf4j
public class TokenEncryptionOperation {

    /**
     * The JWK provider.
     */
    private IJWKProvider jwkProvider;

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     * @param jwkProvider the JWK provider.
     */
    public TokenEncryptionOperation(IJWKProvider jwkProvider) {
        super();
        this.jwkProvider = jwkProvider;
    }

    // ------------------------------------------ Utility methods.

    /**
     * Executes the operation.
     * @param token the JWT token.
     * @return the token signed with the private key.
     * @throws JOSEJWTException if an error occurs.
     */
    public String execute(String token) throws JOSEJWTException {
        try {
            var safeHash = TokenDigestUtility.hash(token);

            LOGGER.debug("Token <{}> - Encrypting the token.", safeHash);

            // Extract JWK.
            var jwk = this.jwkProvider.toJWK();

            // Encrypt the token.
            // @formatter:off
            var jweHeader = new JWEHeader.Builder(getJWEAlgorithm(jwk), getEncryptionMethod())
                    .contentType("JWT")
                    .keyID(jwk.getKeyID())
                    .build();
            // @formatter:on

            var encryptedJWT = new JWEObject(jweHeader, new Payload(token));
            encryptedJWT.encrypt(TokenEncryptionOperation.getJWEEncrypter(jwk));
            var serialize = encryptedJWT.serialize();

            LOGGER.debug("Token <{}> - Token encrypted successfully.", safeHash);

            return serialize;
        } catch (JOSEException e) {
            throw new JOSEJWTException(e);
        }
    }

    // ------------------------------------------ Private methods.

    /**
     * Returns a {@link JWEEncrypter} instance based on the public key.
     * @param jwk the JWK holding the key.
     * @return the appropriate JWE encrypter.
     * @throws JOSEJWTException if an error occurs.
     */
    private static JWEEncrypter getJWEEncrypter(JWK jwk) throws JOSEJWTException {
        try {
            if (jwk instanceof ECKey ecKey) {
                return new ECDHEncrypter(ecKey);
            } else if (jwk instanceof RSAKey rsaKey) {
                return new RSAEncrypter(rsaKey);
            } else if (jwk instanceof OctetSequenceKey octetSequenceKey) {
                return new DirectEncrypter(octetSequenceKey);
            } else {
                throw new UnsupportedKeyTypeJOSEJWTException(jwk.getKeyType().getValue());
            }
        } catch (JOSEException e) {
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Returns a {@link JWEAlgorithm} to use based on the public key.
     * @param jwk the JWK holding the key.
     * @return the appropriate JWE algorithm.
     * @throws JOSEJWTException if an error occurs.
     */
    private static JWEAlgorithm getJWEAlgorithm(JWK jwk) throws JOSEJWTException {
        if (jwk.getAlgorithm() != null) {
            return new JWEAlgorithm(jwk.getAlgorithm().getName());
        } else if (jwk instanceof ECKey) {
            return JWEAlgorithm.ECDH_ES;
        } else if (jwk instanceof RSAKey) {
            return JWEAlgorithm.RSA_OAEP_256;
        } else if (jwk instanceof OctetSequenceKey) {
            return JWEAlgorithm.DIR;
        } else {
            throw new UnsupportedKeyTypeJOSEJWTException(jwk.getKeyType().getValue());
        }
    }

    /**
     * Returns a {@link EncryptionMethod} to use.
     * @return the appropriate encryption method.
     * @throws JOSEJWTException if an error occurs.
     */
    private static EncryptionMethod getEncryptionMethod() throws JOSEJWTException {
        return EncryptionMethod.A256GCM;
    }
}
