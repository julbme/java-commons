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

/**
 * Unit test class for {@link DateISO8601Validator}.
 * <br>
 *
 * @author Julb.
 */
class DateISO8601ValidatorTest {

    /**
     * The instance under test.
     */
    private DateISO8601Validator validator;

    /**
     * Reinitialize the validator.
     */
    @BeforeEach
    void setUp() {
        validator = new DateISO8601Validator();
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
    void whenIsValidatingValidDate_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid("2000-01-01", null));
        Assertions.assertTrue(validator.isValid("2000-02-29", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingInvalidDate_thenReturnFalse() throws Exception {
        Assertions.assertFalse(validator.isValid("abcdefghij", null));
        Assertions.assertFalse(validator.isValid("2000-02-30", null));
        Assertions.assertFalse(validator.isValid("2001-02-29", null));
    }
}
