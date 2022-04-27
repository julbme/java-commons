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
package me.julb.commons.net.grpc.logging;

import java.util.function.Supplier;

import io.grpc.Context;
import io.grpc.Metadata;

import me.julb.commons.constants.Strings;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * A item used to propagate MDC between client and server.
 * <br>
 *
 * @author Julb.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"mdcKey"})
public class MDCContextItem {

    /**
     * The value to set in the MDC.
     */
    private String mdcKey;

    /**
     * The value to set in the MDC.
     */
    private Context.Key<String> contextKey;

    /**
     * The value to set in the MDC.
     */
    private Metadata.Key<String> metadataKey;

    /**
     * A default value for the item if not in the context.
     */
    private Supplier<String> defaultValueSupplier;

    /**
     * Gets the value from the metadata.
     * @param metadata the metadata.
     * @return the value.
     */
    public String getValue(@NonNull Metadata metadata) {
        if (metadata.containsKey(metadataKey)) {
            return metadata.get(metadataKey);
        } else if (defaultValueSupplier != null) {
            return defaultValueSupplier.get();
        } else {
            return Strings.EMPTY;
        }
    }

    /**
     * Gets the value.
     * @return the value.
     */
    public String getValue() {
        var value = contextKey.get();
        if (value == null && defaultValueSupplier != null) {
            value = defaultValueSupplier.get();
        }
        if (value == null) {
            value = Strings.EMPTY;
        }
        return value;
    }
}
