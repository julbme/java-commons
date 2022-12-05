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

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * This exception is thrown in case a resource is not found.
 * <P>
 * The resource is identified by a class and an identifier, both exposed.
 * <br>
 * @author Julb.
 */
@Getter
public class ResourceAlreadyExistsException extends ConflictException {

    // @formatter:off
    /**
     * The resourceClass attribute.
     * -- GETTER --
     * Getter for {@link #resourceClass} property.
     * @return the value.
     */
    // @formatter:on
    private final Class<?> resourceClass;

    // @formatter:off
    /**
     * The attributes attribute.
     * -- GETTER --
     * Getter for {@link #attributes} property.
     * @return the value.
     */
    // @formatter:on
    private final Map<String, String> attributes = new HashMap<>();

    /**
     * Constructor.
     * @param resourceClass the resource class.
     * @param businessKeyAttributeName the attribute name.
     * @param businessKeyAttributeValue the attribute value.
     */
    public ResourceAlreadyExistsException(
            Class<?> resourceClass, String businessKeyAttributeName, String businessKeyAttributeValue) {
        super();
        this.resourceClass = resourceClass;
        this.attributes.put(businessKeyAttributeName, businessKeyAttributeValue);
    }

    /**
     * Constructor.
     * @param resourceClass the resource class.
     * @param businessKeys the business keys.
     */
    public ResourceAlreadyExistsException(Class<?> resourceClass, Map<String, String> businessKeys) {
        super();
        this.resourceClass = resourceClass;
        this.attributes.putAll(businessKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        var sb = new StringBuilder();
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            sb.append(String.format("%s=%s,", attribute.getKey(), attribute.getValue()));
        }

        return String.format("Resource already exists: [%s={%s}]", resourceClass.getSimpleName(), sb.toString());
    }
}
