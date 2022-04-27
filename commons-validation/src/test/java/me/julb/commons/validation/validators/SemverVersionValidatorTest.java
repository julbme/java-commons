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
 * Unit test class for {@link SemverVersionValidator}.
 * <br>
 *
 * @author Julb.
 */
class SemverVersionValidatorTest {

    /**
     * The instance under test.
     */
    private SemverVersionValidator validator;

    /**
     * Reinitialize the validator.
     */
    @BeforeEach
    void setUp() {
        validator = new SemverVersionValidator();
        validator.setAllowSnapshots(true);
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
    void whenIsValidatingValidSemverVersion_thenReturnTrue() throws Exception {
        Assertions.assertTrue(validator.isValid("1.2.3", null));
        Assertions.assertTrue(validator.isValid("1.2.3-rc.1", null));
        Assertions.assertTrue(validator.isValid("1.2.3-rc.1+abcdef", null));
        Assertions.assertTrue(validator.isValid("1.2.3-SNAPSHOT", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingValidSemverVersionWithDenySnapshots_thenReturnTrue() throws Exception {
        validator.setAllowSnapshots(false);
        Assertions.assertTrue(validator.isValid("1.2.3", null));
        Assertions.assertTrue(validator.isValid("1.2.3-rc.1", null));
        Assertions.assertTrue(validator.isValid("1.2.3-rc.1+abcdef", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingSnapshotVersionWithDenySnapshots_thenReturnFalse() throws Exception {
        validator.setAllowSnapshots(false);
        Assertions.assertFalse(validator.isValid("1.2.3-SNAPSHOT", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenIsValidatingInvalidSemverVersions_thenReturnFalse() throws Exception {
        Assertions.assertFalse(validator.isValid("abcdef", null));
        Assertions.assertFalse(validator.isValid("1", null));
        Assertions.assertFalse(validator.isValid("1.2", null));
    }
}
