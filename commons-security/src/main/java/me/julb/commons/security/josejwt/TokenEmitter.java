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

import org.apache.commons.lang3.StringUtils;

import me.julb.commons.security.josejwt.digest.TokenDigestUtility;
import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;
import me.julb.commons.security.josejwt.jwk.IJWKProvider;
import me.julb.commons.security.josejwt.operations.TokenEncryptionOperation;
import me.julb.commons.security.josejwt.operations.TokenSignatureOperation;

import lombok.extern.slf4j.Slf4j;

/**
 * The JWT emitter.
 * <br>
 * @author Julb.
 */
@Slf4j
public class TokenEmitter {

    /**
     * The signature private key.
     */
    private IJWKProvider signatureJWKProvider;

    /**
     * The encryption key.
     */
    private IJWKProvider encryptionJWKProvider;

    // ------------------------------------------ Constructors.

    /**
     * Default Constructor.
     */
    public TokenEmitter() {
        super();
    }

    // ------------------------------------------ Builder methods.

    /**
     * Setter for property encryptionJWKProvider.
     * @param encryptionJWKProvider New value of property encryptionJWKProvider.
     * @return the current instance.
     */
    public TokenEmitter setEncryptionJWKProvider(IJWKProvider encryptionJWKProvider) {
        this.encryptionJWKProvider = encryptionJWKProvider;
        return this;
    }

    /**
     * Setter for property signatureJWKProvider.
     * @param signatureJWKProvider New value of property signatureJWKProvider.
     * @return the current instance.
     */
    public TokenEmitter setSignatureJWKProvider(IJWKProvider signatureJWKProvider) {
        this.signatureJWKProvider = signatureJWKProvider;
        return this;
    }

    // ------------------------------------------ Utility methods.

    /**
     * Emits a JSON web token signed and encrypted.
     * @param jwtClaims the raw JSON web token.
     * @return the JSON web token signed and encrypted.
     * @throws JOSEJWTException if an error occurs.
     */
    public String emit(String jwtClaims) throws JOSEJWTException {
        try {
            // Check all informations are provided.
            if (StringUtils.isBlank(jwtClaims)) {
                throw new IllegalArgumentException("token must not be blank");
            }

            if (this.signatureJWKProvider == null) {
                throw new IllegalArgumentException("signature private key must not be null");
            }

            // Hash for tracking purpose.
            var hash = TokenDigestUtility.hash(jwtClaims);
            LOGGER.debug("Token <{}> - Start emitting.", hash);

            // 1. Signing the token.
            var tokenSignatureOperation = new TokenSignatureOperation(this.signatureJWKProvider);
            var signedToken = tokenSignatureOperation.execute(jwtClaims);

            // 2. Encrypting the token.
            if (this.encryptionJWKProvider != null) {
                var tokenEncryptionOperation = new TokenEncryptionOperation(this.encryptionJWKProvider);
                var encryptedToken = tokenEncryptionOperation.execute(signedToken);

                // 3. Return the token
                LOGGER.debug("Emitting token {} - Finish.", hash);
                return encryptedToken;
            } else {
                // 3. Return the token
                LOGGER.debug("Emitting token {} - Finish.", hash);
                return signedToken;
            }
        } catch (JOSEJWTException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
