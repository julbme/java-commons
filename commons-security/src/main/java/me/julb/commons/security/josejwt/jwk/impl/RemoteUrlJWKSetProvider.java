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

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import com.nimbusds.jose.jwk.JWKSet;

import me.julb.commons.constants.Integers;
import me.julb.commons.security.josejwt.exceptions.badrequest.TokenNotParseableJOSEJWTException;
import me.julb.commons.security.josejwt.jwk.IJWKSetProvider;
import me.julb.commons.time.date.DateUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * A JWKS provider based on a stringified JSON.
 * <br>
 * @author Julb.
 */
@Slf4j
public class RemoteUrlJWKSetProvider implements IJWKSetProvider {

    /**
     * The URL returning the JSON.
     */
    private String url;

    /**
     * The cache validity in seconds (30mn by default).
     */
    private Integer cacheValidityInSeconds = (int) TimeUnit.MINUTES.toSeconds(Integers.THIRTY);

    /**
     * The last JSON string.
     */
    private String lastJSONString;

    /**
     * The not retry before date time.
     */
    private String notRetryBeforeDateTime;

    /**
     * The not retry before internal in seconds.
     */
    private Integer notRetryBeforeIntervalInSeconds = Integers.THIRTY;

    /**
     * The expiry date time.
     */
    private String expiryDateTime;

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     */
    public RemoteUrlJWKSetProvider() {
        super();
    }

    // ------------------------------------------ Overridden methods.

    /**
     * {@inheritDoc}
     */
    @Override
    public JWKSet get() {
        try {
            return JWKSet.parse(getJSONString());
        } catch (ParseException e) {
            throw new TokenNotParseableJOSEJWTException(e);
        }
    }

    // ------------------------------------------ Fetch URL.

    /**
     * Refresh the JSON string.
     */
    private String getJSONString() {
        if ((this.expiryDateTime == null
                        || DateUtility.dateTimeBeforeNow(this.expiryDateTime).booleanValue())
                && DateUtility.dateTimeAfterNow(this.notRetryBeforeDateTime).booleanValue()) {
            try {
                LOGGER.debug("Fetching the JWKS from url {}.", this.url);
                this.lastJSONString = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
                this.notRetryBeforeDateTime = null;
                this.expiryDateTime = DateUtility.dateTimePlus(this.cacheValidityInSeconds, ChronoUnit.SECONDS);
            } catch (IOException e) {
                LOGGER.error("Unable to fetch the JWKS from the given URL.", e);
                this.notRetryBeforeDateTime =
                        DateUtility.dateTimePlus(this.notRetryBeforeIntervalInSeconds, ChronoUnit.SECONDS);
            }
        }
        return this.lastJSONString;
    }

    /**
     * The builder for JWKS.
     * <br>
     * @author Julb.
     */
    public static class Builder {

        /**
         * The built instance
         */
        private RemoteUrlJWKSetProvider instance;

        // ------------------------------------------ Constructors.

        /**
         * Constructor.
         */
        public Builder() {
            super();
            this.instance = new RemoteUrlJWKSetProvider();
        }

        // ------------------------------------------ Getters/Setters.

        /**
         * Setter for property url.
         * @param url New value of property url.
         * @return the builder instance.
         */
        public Builder url(String url) {
            this.instance.url = url;
            return this;
        }

        /**
         * Setter for property cacheValidityInSeconds.
         * @param cacheValidityInSeconds New value of property cacheValidityInSeconds.
         * @return the builder instance.
         */
        public Builder cacheValidity(Integer cacheValidityInSeconds) {
            this.instance.cacheValidityInSeconds = cacheValidityInSeconds;
            return this;
        }

        /**
         * Returns the built instance.
         * @return the instance.
         */
        public RemoteUrlJWKSetProvider build() {
            return this.instance;
        }
    }
}
