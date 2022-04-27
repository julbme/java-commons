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
import java.time.Instant;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import me.julb.commons.constants.Integers;
import me.julb.commons.security.josejwt.digest.TokenDigestUtility;
import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingAudienceInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingExpirationInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingIssuerInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.TokenNotParseableJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.ExpiredTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidAudienceInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidIssuerInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidSignatureInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnresolvableKeyJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnsupportedKeyTypeJOSEJWTException;
import me.julb.commons.security.josejwt.jwk.IJWKSetProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * The operation that signs a token with a private key.
 * <br>
 * @author Julb.
 */
@Slf4j
public class TokenVerifierOperation {

    /**
     * The valid public keys used to check the token signature.
     */
    private IJWKSetProvider jwkSetProvider;

    /**
     * The audience of the token.
     */
    private String expectedAudience;

    /**
     * The issuer of the token.
     */
    private String expectedIssuer;

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     * @param jwkSetProvider the JWK set provider.
     */
    public TokenVerifierOperation(IJWKSetProvider jwkSetProvider) {
        this(jwkSetProvider, null, null);
    }

    /**
     * Constructor.
     * @param jwkSetProvider the JWK set provider.
     * @param expectedAudience the expected audience.
     * @param expectedIssuer the expected issuer.
     */
    public TokenVerifierOperation(IJWKSetProvider jwkSetProvider, String expectedAudience, String expectedIssuer) {
        super();
        this.jwkSetProvider = jwkSetProvider;
        this.expectedAudience = expectedAudience;
        this.expectedIssuer = expectedIssuer;
    }

    // ------------------------------------------ Utility methods.

    /**
     * Executes the operation.
     * @param token the JWT claims set.
     * @return the token verified against signature.
     * @throws JOSEJWTException if an error occurs.
     */
    public String execute(String token) throws JOSEJWTException {
        try {
            var safeHash = TokenDigestUtility.hash(token);

            LOGGER.debug("Token <{}> - Verifying the token.", safeHash);

            var jwsObject = JWSObject.parse(token);

            // Get the key ID for the signature.
            var alg = jwsObject.getHeader().getAlgorithm().getName();
            var kid = jwsObject.getHeader().getKeyID();

            LOGGER.debug("Token <{}> - alg/kid used to sign the token is <{},{}>.", safeHash, alg, kid);

            // Get the corresponding JWK.
            LOGGER.debug("Token <{}> - Searching JWK among the JWKSet.", safeHash);
            var jwk = getSignatureJWK(kid, KeyUse.SIGNATURE);
            LOGGER.debug("Token <{}> - Key has been resolved.", safeHash);

            // Checking the signature.
            if (!jwsObject.verify(getJWSVerifier(jwk))) {
                LOGGER.warn("Token <{}> - The signature used does not match any provided one. Reject token.", safeHash);
                throw new InvalidSignatureInTokenJOSEJWTException();
            }

            LOGGER.debug("Token <{}> - Signature OK.", safeHash);

            // Parse token.
            var signedJWT = SignedJWT.parse(token);

            // Check expiration time present
            var expirationDateTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationDateTime == null) {
                LOGGER.warn("Token <{}> - The token does not contain any expiration.", safeHash);
                throw new MissingExpirationInTokenJOSEJWTException();
            }

            // Verify Expiration time
            var nowEpochSeconds = Instant.now().getEpochSecond();
            var expirationEpochSeconds = expirationDateTime.toInstant().getEpochSecond();
            if (expirationEpochSeconds < nowEpochSeconds) {
                LOGGER.debug("Token <{}> - The token is expired.", safeHash);
                throw new ExpiredTokenJOSEJWTException(expirationEpochSeconds, nowEpochSeconds);
            }

            LOGGER.debug("Token <{}> - Expiration OK.", safeHash);

            // Verify Issuer
            if (StringUtils.isNotBlank(this.expectedIssuer)) {
                var tokenIssuer = signedJWT.getJWTClaimsSet().getIssuer();
                if (StringUtils.isBlank(tokenIssuer)) {
                    LOGGER.warn("Token <{}> - The token does not contain an issuer.", safeHash);
                    throw new MissingIssuerInTokenJOSEJWTException();
                }
                if (!tokenIssuer.equalsIgnoreCase(this.expectedIssuer)) {
                    LOGGER.debug("Token <{}> - The token does not provide a valid issuer.", safeHash);
                    throw new InvalidIssuerInTokenJOSEJWTException(this.expectedIssuer, tokenIssuer);
                }

                LOGGER.debug("Token <{}> - Issuer OK.", safeHash);
            } else {
                LOGGER.debug("Token <{}> - Issuer not checked (not specified).", safeHash);
            }

            // Verify Audience
            if (StringUtils.isNotBlank(this.expectedAudience)) {
                var tokenAudiences = signedJWT.getJWTClaimsSet().getAudience();
                if (CollectionUtils.isEmpty(tokenAudiences)) {
                    LOGGER.warn("Token <{}> - The token does not contain any audience.", safeHash);
                    throw new MissingAudienceInTokenJOSEJWTException();
                }
                var tokenAudienceMatches = tokenAudiences.stream()
                        .anyMatch(tokenAudience -> StringUtils.equalsIgnoreCase(tokenAudience, this.expectedAudience));
                if (!tokenAudienceMatches) {
                    LOGGER.debug("Token <{}> - The token audience does not match the expected ones.", safeHash);
                    throw new InvalidAudienceInTokenJOSEJWTException(this.expectedAudience, tokenAudiences);
                }

                LOGGER.debug("Token <{}> - Audience OK.", safeHash);
            } else {
                LOGGER.debug("Token <{}> - Audience not checked (not specified).", safeHash);
            }

            LOGGER.debug("Token <{}> - Token is valid.", safeHash);

            return signedJWT.getPayload().toString();
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
     * Returns a {@link JWSVerifier} instance based on the public key.
     * @return the appropriate JWS verifier.
     * @throws JOSEJWTException if an error occurs.
     */
    private JWSVerifier getJWSVerifier(JWK jwk) throws JOSEJWTException {
        try {
            if (jwk instanceof ECKey ecKey) {
                return new ECDSAVerifier(ecKey);
            } else if (jwk instanceof RSAKey rsaKey) {
                return new RSASSAVerifier(rsaKey);
            } else if (jwk instanceof OctetSequenceKey octetSequenceKey) {
                return new MACVerifier(octetSequenceKey);
            } else {
                throw new UnsupportedKeyTypeJOSEJWTException(jwk.getKeyType().getValue());
            }
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage(), e);
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Gets the signature JWK.
     * @param kid the KID.
     * @param keyUse the key use.
     * @return the corresponding JWK.
     */
    private JWK getSignatureJWK(String kid, KeyUse keyUse) throws JOSEJWTException {
        // @formatter:off
        var jwkMatcherBuilder = new JWKMatcher.Builder().keyUse(keyUse).keyID(kid);
        // @formatter:on

        // Get the JWK matching.
        var jwkSet = this.jwkSetProvider.toJWKSet();
        var jwkSelector = new JWKSelector(jwkMatcherBuilder.build());
        var jwks = jwkSelector.select(jwkSet);

        // No key matching. Refresh & Retry.
        if (jwks.isEmpty() && this.jwkSetProvider.refreshJWKSet()) {
            jwkSet = this.jwkSetProvider.toJWKSet();
            jwks = jwkSelector.select(jwkSet);
        }

        var keyCount = jwks.size();

        // Reject if no keys.
        if (keyCount == Integers.ZERO) {
            LOGGER.error("No key found for kid <{}> and use <{}>.", kid, keyUse);
            throw new UnresolvableKeyJOSEJWTException(kid, keyUse.identifier());
        }

        // Reject if too much keys.
        if (keyCount > Integers.ONE) {
            LOGGER.error(
                    "<{}> keys found for kid <{}> and use <{}>. Please check your JWKS configuration.",
                    keyCount,
                    kid,
                    keyUse);
            throw new UnresolvableKeyJOSEJWTException(kid, keyUse.identifier());
        }

        return jwks.get(0);
    }
}
