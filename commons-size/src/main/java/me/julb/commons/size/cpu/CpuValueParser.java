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
package me.julb.commons.size.cpu;

import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A parser utility for CPU values. <br>
 * @author Julb.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CpuValueParser {

    /**
     * The pattern to match a capacity bytes value.
     */
    private static final Pattern CPU_PATTERN = Pattern.compile("^(?<value>\\d+)\\s?(?<symbol>[m]?)$");

    /**
     * Returns the CPU value resolved from the given string.
     * @param cpuValue the stringified CPU value.
     * @return the CPU value.
     */
    public static CpuValueDTO parse(@NonNull String cpuValue) {
        var matcher = CPU_PATTERN.matcher(cpuValue);
        if (matcher.matches()) {
            var value = Long.parseLong(matcher.group("value"));
            var unit = CpuUnit.fromSymbol(matcher.group("symbol"));
            return new CpuValueDTO(value, unit);
        } else {
            throw new IllegalArgumentException(cpuValue);
        }
    }
}
