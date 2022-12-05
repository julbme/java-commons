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
package me.julb.commons.enums;

/**
 * HTTP protocol. <br>
 * @author Julb.
 */
public enum HttpProtocol {

    /**
     * The HTTP protocol.
     */
    HTTP(80, "http", "http://"),

    /**
     * The HTTPS protocol.
     */
    HTTPS(443, "https", "https://");

    /**
     * The standard port.
     */
    private Integer standardPort;

    /**
     * The protocol.
     */
    private String protocol;

    /**
     * The URL prefix
     */
    private String urlPrefix;

    /**
     * Constructor.
     * @param standardPort the standard port.
     */
    HttpProtocol(Integer standardPort, String protocol, String urlPrefix) {
        this.standardPort = standardPort;
        this.protocol = protocol;
        this.urlPrefix = urlPrefix;
    }

    /**
     * Getter for property standardPort.
     * @return Value of property standardPort.
     */
    public Integer standardPort() {
        return standardPort;
    }

    /**
     * Getter for property protocol.
     * @return Value of property protocol.
     */
    public String protocol() {
        return protocol;
    }

    /**
     * Getter for property urlPrefix.
     * @return Value of property urlPrefix.
     */
    public String urlPrefix() {
        return urlPrefix;
    }
}
