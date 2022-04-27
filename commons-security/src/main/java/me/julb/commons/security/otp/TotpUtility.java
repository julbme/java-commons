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
package me.julb.commons.security.otp;

import java.util.ArrayList;

import me.julb.commons.constants.Integers;
import me.julb.commons.exceptions.InternalServerErrorException;

import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * A TOTP utility.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TotpUtility {

    /**
     * The TOTP time period.
     */
    private static final Integer TOTP_TIME_PERIOD = Integers.THIRTY;

    /**
     * The TOTP secret length.
     */
    private static final Integer TOTP_SECRET_LENGTH = Integers.THIRTY_TWO;

    /**
     * The TOTP hashing algorithm.
     */
    private static final HashingAlgorithm TOTP_CODE_HASHING_ALGORITHM = HashingAlgorithm.SHA1;

    /**
     * The TOTP secret length.
     */
    private static final Integer TOTP_CODE_DIGIT_LENGTH = Integers.SIX;

    /**
     * Generates a random TOTP secret.
     * @return the random TOTP secret.
     */
    public static String generateRandomTotpSecret() {
        return new DefaultSecretGenerator(TOTP_SECRET_LENGTH).generate();
    }

    /**
     * Generates a QRCode URI for this TOTP secret.
     * @param label the label.
     * @param issuer the issuer.
     * @param secret the secret.
     * @return the QRCode URI.
     */
    public static String generateQrcodeUri(String label, String issuer, String secret) {
        // @formatter:off
        return new QrData.Builder()
                .label(label)
                .secret(secret)
                .issuer(issuer)
                .algorithm(TOTP_CODE_HASHING_ALGORITHM)
                .digits(TOTP_CODE_DIGIT_LENGTH)
                .period(TOTP_TIME_PERIOD)
                .build()
                .getUri();
        // @formatter:on
    }

    /**
     * Generates the TOTP corresponding to the secret.
     * @param secret the secret.
     * @return the TOTP corresponding to the secret.
     */
    public static String generateValidTotp(String secret) {
        var generateValidTotps = generateValidTotps(secret, Integers.ZERO);
        return generateValidTotps.iterator().next();
    }

    /**
     * Generates valid TOTPs corresponding to the secret (+/- number if discrepancy).
     * @param secret the secret.
     * @param totpAllowedTimePeriodDiscrepancy the TOTP allowed time period discrepancy.
     * @return the TOTP corresponding to the secret.
     */
    public static Iterable<String> generateValidTotps(String secret, int totpAllowedTimePeriodDiscrepancy) {
        var totpTimeProvider = new SystemTimeProvider();
        var codeGenerator = new DefaultCodeGenerator(TOTP_CODE_HASHING_ALGORITHM, TOTP_CODE_DIGIT_LENGTH);

        try {
            // Get current bucket.
            var currentBucket = Math.floorDiv(totpTimeProvider.getTime(), TOTP_TIME_PERIOD);

            // Build a list of valid TOTPs.
            var validTotps = new ArrayList<String>();
            for (int i = -totpAllowedTimePeriodDiscrepancy; i <= totpAllowedTimePeriodDiscrepancy; i++) {
                validTotps.add(codeGenerator.generate(secret, currentBucket + i));
            }

            return validTotps;
        } catch (CodeGenerationException e) {
            throw new InternalServerErrorException(e);
        }
    }
}
