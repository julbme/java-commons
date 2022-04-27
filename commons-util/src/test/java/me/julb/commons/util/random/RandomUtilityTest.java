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
package me.julb.commons.util.random;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import me.julb.commons.constants.Integers;

/**
 * Test class for {@link RandomUtility} class. <br>
 * @author Julb.
 */
class RandomUtilityTest {

    /**
     * Test method.
     */
    @Test
    void whenGenerateNumericToken_thenReturnValidToken() {
        String id = RandomUtility.generateNumericToken(Integers.EIGHT);
        Assertions.assertEquals(Integers.EIGHT, id.length());
        Assertions.assertTrue(Pattern.matches("^[0-9]+$", id));
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 513})
    void whenGenerateNumericTokenInvalidLength_thenThrowIllegalArgumentException(int length) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RandomUtility.generateNumericToken(length));
    }

    /**
     * Test method.
     */
    @Test
    void whenGenerateAlphaNumericToken_thenReturnValidToken() {
        String id = RandomUtility.generateAlphaNumericToken(Integers.EIGHT);
        Assertions.assertEquals(Integers.EIGHT, id.length());
        Assertions.assertTrue(Pattern.matches("^[0-9a-zA-Z]+$", id));
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 513})
    void whenGenerateAlphaNumericTokenInvalidLength_thenThrowIllegalArgumentException(int length) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RandomUtility.generateAlphaNumericToken(length));
    }
}
