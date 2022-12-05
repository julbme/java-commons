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

import me.julb.commons.interfaces.Intervallable;
import me.julb.commons.time.date.DateUtility;
import me.julb.commons.validation.constraints.DateTimeInFuture;

/**
 * Validator to check that the given object is a valid interval.
 * <br>
 * @author Julb.
 */
public class DateTimeInFutureValidator implements ConstraintValidator<DateTimeInFuture, Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"rawtypes"})
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Case null
        if (value == null) {
            return true;
        }

        // Get target value.
        var dateTimeNow = DateUtility.dateTimeNow();
        if (value instanceof Intervallable intervallable) {
            // Skipping the check if one is null.
            var to = (String) intervallable.getTo();
            if (to == null) {
                return true;
            }

            // Compare from and to.
            return dateTimeNow.compareTo(to) <= 0;
        } else if (value instanceof String dateTime) {
            return dateTimeNow.compareTo(dateTime) <= 0;
        } else {
            return false;
        }
    }
}
