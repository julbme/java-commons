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
package me.julb.commons.validation.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.julb.commons.interfaces.Intervallable;

/**
 * Unit test class for {@link IntervalValidator}.
 * <br>
 *
 * @author Julb.
 */
class IntervalValidatorTest {

    /**
     * The instance under test.
     */
    private IntervalValidator validator;

    /**
     * Reinitialize the validator.
     */
    @BeforeEach
    void setUp() {
        validator = new IntervalValidator();
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingNull_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid(null, null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingValidInterval_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid(
                new Intervallable<String>() {
                    @Override
                    public String getFrom() {
                        return "a";
                    }

                    @Override
                    public String getTo() {
                        return "b";
                    }
                },
                null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingInvalidInterval_thenReturnFalse() throws Exception {
        Assertions.assertFalse(validator.isValid(
                new Intervallable<String>() {
                    @Override
                    public String getFrom() {
                        return "c";
                    }

                    @Override
                    public String getTo() {
                        return "b";
                    }
                },
                null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingNotAnInterval_thenReturnFalse() throws Exception {
        Assertions.assertFalse(validator.isValid("hello", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingWithZeroBounds_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid(
                new Intervallable<String>() {
                    @Override
                    public String getFrom() {
                        return null;
                    }

                    @Override
                    public String getTo() {
                        return null;
                    }
                },
                null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingWithOneBound_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid(
                new Intervallable<String>() {
                    @Override
                    public String getFrom() {
                        return "a";
                    }

                    @Override
                    public String getTo() {
                        return null;
                    }
                },
                null));

        Assertions.assertTrue(validator.isValid(
                new Intervallable<String>() {
                    @Override
                    public String getFrom() {
                        return null;
                    }

                    @Override
                    public String getTo() {
                        return "b";
                    }
                },
                null));
    }
}
