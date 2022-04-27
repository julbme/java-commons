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

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import me.julb.commons.security.josejwt.digest.TokenDigestUtility;
import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.TokenNotParseableJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.internalservererror.UnsupportedJWSAlgorithmJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnsupportedKeyTypeJOSEJWTException;
import me.julb.commons.security.josejwt.jwk.IJWKProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * The operation that signs a token with a private key.
 * <br>
 * @author Julb.
 */
@Slf4j
public class TokenSignatureOperation {

    /**
     * The private key used to sign the token.
     */
    private IJWKProvider jwkProvider;

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     * @param jwkProvider the JWK provider.
     */
    public TokenSignatureOperation(IJWKProvider jwkProvider) {
        super();
        this.jwkProvider = jwkProvider;
    }

    // ------------------------------------------ Utility methods.

    /**
     * Execute the operation.
     * @param token the token to sign.
     * @return the token signed with the key.
     * @throws JOSEJWTException if an error occurs.
     */
    public String execute(String token) throws JOSEJWTException {
        try {
            var safeHash = TokenDigestUtility.hash(token);

            LOGGER.debug("Token <{}> - Signing the token.", safeHash);

            // Extract JWK.
            var jwk = this.jwkProvider.toJWK();
            var jwsSigner = TokenSignatureOperation.getJWSSigner(jwk);
            var jwsAlgorithm = TokenSignatureOperation.getJWSAlgorithm(jwk);
            var supportedJWSAlgorithms = jwsSigner.supportedJWSAlgorithms();
            if (!supportedJWSAlgorithms.contains(jwsAlgorithm)) {
                var algorithmList = supportedJWSAlgorithms.stream()
                        .map(JWSAlgorithm::getName)
                        .toList();
                throw new UnsupportedJWSAlgorithmJOSEJWTException(algorithmList, jwsAlgorithm.getName());
            }

            // Parse the token
            var jwtClaimsSet = JWTClaimsSet.parse(token);

            // Sign the token.
            // @formatter:off
            var jwsHeader =
                    new JWSHeader.Builder(jwsAlgorithm).keyID(jwk.getKeyID()).build();
            // @formatter:on

            var signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
            signedJWT.sign(jwsSigner);
            var signedSerializedToken = signedJWT.serialize();

            LOGGER.debug("Token <{}> - Token signed successfully.", safeHash);

            return signedSerializedToken;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new TokenNotParseableJOSEJWTException(e);
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage(), e);
            throw new JOSEJWTException(e);
        }
    }

    // ------------------------------------------ Private methods.

    /**
     * Returns a {@link JWSSigner} instance based on the private key.
     * @param jwk the JWK.
     * @return the appropriate JWS signer.
     * @throws JOSEJWTException if an error occurs.
     */
    private static JWSSigner getJWSSigner(JWK jwk) throws JOSEJWTException {
        try {
            if (jwk instanceof ECKey ecKey) {
                return new ECDSASigner(ecKey);
            } else if (jwk instanceof RSAKey rsaKey) {
                return new RSASSASigner(rsaKey);
            } else if (jwk instanceof OctetSequenceKey octetSequenceKey) {
                return new MACSigner(octetSequenceKey);
            } else {
                throw new UnsupportedKeyTypeJOSEJWTException(jwk.getKeyType().getValue());
            }
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage(), e);
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Returns a {@link JWSAlgorithm} to use based on the private key.
     * @param jwk the JWK.
     * @return the appropriate JWS algorithm.
     * @throws JOSEJWTException if an error occurs.
     */
    private static JWSAlgorithm getJWSAlgorithm(JWK jwk) throws JOSEJWTException {
        if (jwk.getAlgorithm() != null) {
            return new JWSAlgorithm(jwk.getAlgorithm().getName());
        } else if (jwk instanceof ECKey) {
            return JWSAlgorithm.ES384;
        } else if (jwk instanceof RSAKey) {
            return JWSAlgorithm.RS384;
        } else if (jwk instanceof OctetSequenceKey) {
            return JWSAlgorithm.HS256;
        } else {
            throw new UnsupportedKeyTypeJOSEJWTException(jwk.getKeyType().getValue());
        }
    }
}
