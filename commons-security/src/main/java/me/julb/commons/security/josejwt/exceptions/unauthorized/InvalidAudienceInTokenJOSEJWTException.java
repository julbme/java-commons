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
package me.julb.commons.security.josejwt.exceptions.unauthorized;

import java.util.ArrayList;
import java.util.Collection;

import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;

/**
 * This exception is thrown when the token has an invalid audience.
 * <br>
 * @author Julb.
 */
public class InvalidAudienceInTokenJOSEJWTException extends JOSEJWTException {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = -128234853992116297L;

    /**
     * The expected audience.
     */
    private final String expected;

    /**
     * The actual audiences.
     */
    private final Collection<String> actuals;

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     * @param expected the expected audience.
     * @param actuals the actual audience.
     */
    public InvalidAudienceInTokenJOSEJWTException(String expected, Collection<String> actuals) {
        super();
        this.expected = expected;
        this.actuals = new ArrayList<>(actuals);
    }

    // ------------------------------------------ Overridden methods.

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return String.format("Invalid audience in token. Expected: <%s> / Actuals: <%s>.", expected, actuals);
    }
}
