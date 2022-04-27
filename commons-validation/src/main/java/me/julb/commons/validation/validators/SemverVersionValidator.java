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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vdurmont.semver4j.Semver;
import com.vdurmont.semver4j.SemverException;

import me.julb.commons.validation.constraints.SemverVersion;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * Validator to check that a String respects a versioning pattern.
 * <br>
 * @author Julb.
 */
public class SemverVersionValidator implements ConstraintValidator<SemverVersion, String> {

    /**
     * The snapshot suffix.
     */
    private static final String SNAPSHOT_SUFFIX = "SNAPSHOT";

    /**
     * The flag to allow snapshots
     */
    @Setter(AccessLevel.PACKAGE)
    private boolean allowSnapshots;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(SemverVersion constraintAnnotation) {
        this.allowSnapshots = constraintAnnotation.allowSnapshots();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Case null
        if (value == null) {
            return true;
        }

        // parse version
        try {
            var semanticVersion = new Semver(value);

            // If we are at this point, semantic version is valid.
            if (this.allowSnapshots) {
                return true;
            }

            // Check if it is a snapshot version or not.
            return semanticVersion.getSuffixTokens().length == 0
                    || !SNAPSHOT_SUFFIX.equals(semanticVersion.getSuffixTokens()[0]);
        } catch (SemverException e) {
            return false;
        }
    }
}
