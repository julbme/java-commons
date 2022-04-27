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
package me.julb.commons.size.capacity;

import java.math.BigInteger;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A parser utility for Bytes Capacity values. <br>
 * @author Julb.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BytesCapacityValueParser {

    /**
     * The pattern to match a capacity bytes value.
     */
    private static final Pattern BYTES_CAPACITY_PATTERN =
            Pattern.compile("^(?<value>\\d+)\\s?(?<symbol>[KMGTPE]?[i]?[B]?)$");

    /**
     * Returns the bytes capacity value as a numeric value.
     * @param bytesCapacityValue the stringified bytes capacity value.
     * @return the bytes capacity value.
     */
    public static BytesCapacityValueDTO parse(@NonNull String bytesCapacityValue) {
        var matcher = BYTES_CAPACITY_PATTERN.matcher(bytesCapacityValue);
        if (matcher.matches()) {
            var value = new BigInteger(matcher.group("value"));
            var unit = BytesCapacityUnit.fromSymbol(matcher.group("symbol"));
            return new BytesCapacityValueDTO(value, unit);
        } else {
            throw new IllegalArgumentException(bytesCapacityValue);
        }
    }
}
