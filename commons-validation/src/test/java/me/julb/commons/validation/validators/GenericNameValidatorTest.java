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
 * Unit test class for {@link GenericNameValidator}.
 * <br>
 *
 * @author Julb.
 */
class GenericNameValidatorTest {

    /**
     * The instance under test.
     */
    private GenericNameValidator validator;

    /**
     * Reinitialize the validator.
     */
    @BeforeEach
    void setUp() {
        validator = new GenericNameValidator();
        validator.setMin(2);
        validator.setMax(128);
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
    void whenIsValidatingValidGenericName_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid("abcd", null));
        Assertions.assertTrue(validator.isValid("ABCD-EFGH", null));
        Assertions.assertTrue(validator.isValid("a".repeat(validator.getMin()), null));
        Assertions.assertTrue(validator.isValid("a".repeat(validator.getMax()), null));

        validator.setMin(0);
        Assertions.assertTrue(validator.isValid("a", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingInvalidGenericNames_thenReturnFalse() throws Exception {
        Assertions.assertFalse(validator.isValid("a".repeat(validator.getMin() - 1), null));
        Assertions.assertFalse(validator.isValid("a".repeat(validator.getMax() + 1), null));
    }
}
