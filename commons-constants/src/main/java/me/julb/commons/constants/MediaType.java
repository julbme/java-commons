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
package me.julb.commons.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The media type. <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MediaType {

    /**
     * The text/plain media type.
     */
    public static final String TEXT_PLAIN = "text/plain";

    /**
     * The text/html media type.
     */
    public static final String TEXT_HTML = "text/html";

    /**
     * The text/html with UTF8 charset media type.
     */
    public static final String TEXT_HTML_UTF8 = "text/html; charset=utf-8";

    /**
     * The text/markdown media type.
     */
    public static final String TEXT_MARKDOWN = "text/markdown";

    /**
     * The text/calendar media type.
     */
    public static final String TEXT_CALENDAR = "text/calendar";
}
