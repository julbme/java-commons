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
package me.julb.commons.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * This exception is thrown in case a request to a remote system fails.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
public abstract class AbstractRemoteSystemException extends BaseException {

    /**
     * Constructor.
     */
    protected AbstractRemoteSystemException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message the message.
     */
    protected AbstractRemoteSystemException(String message) {
        super(message);
    }

    /**
     * Constructor with exception.
     * @param e the cause.
     */
    protected AbstractRemoteSystemException(Throwable e) {
        super(e);
    }

    /**
     * Constructor with message and exception.
     * @param message the message.
     * @param e the cause.
     */
    protected AbstractRemoteSystemException(String message, Throwable e) {
        super(message, e);
    }
}
