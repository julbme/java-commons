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
package me.julb.commons.exceptions.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A generic exception handler which converts an exception to the chosen type.
 * <br>
 *
 * @author Julb.
 */
public class ExceptionConverter<R> {

    /**
     * A exception registry with:
     * - Key: the class of the exception to handle
     * - Value: the function to apply.
     */
    private Map<Class<? extends Throwable>, Function<Throwable, R>> exceptionRegistry = new HashMap<>();

    /**
     * The method to call in case no conversion is available in the registry.
     */
    private Function<Throwable, R> defaultHandler;

    /**
     * Add handler function for the given exception type.
     * @param <E> the exception generic type.
     * @param exceptionType the exception type.
     * @param handlerFn the handler function.
     */
    <E extends Throwable> void register(@NonNull Class<E> exceptionType, @NonNull Function<Throwable, R> handlerFn) {
        exceptionRegistry.put(exceptionType, handlerFn);
    }

    /**
     * Sets a default handler.
     * @param defaultHandler the handler to set as default.
     */
    void setDefaultHandler(@NonNull Function<Throwable, R> defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    /**
     * Process the exception and handles the conversion logic.
     * @param exceptionToHandle the exception to handle.
     * @return the corresponding conversion result, or <code>null</code> if no handler has been found.
     */
    public R apply(@NonNull Throwable exceptionToHandle) {
        // Shortcut to return directly the result if in the registry.
        Class<?> clazz = exceptionToHandle.getClass();
        if (exceptionRegistry.containsKey(clazz)) {
            return exceptionRegistry.get(clazz).apply(exceptionToHandle);
        }

        // Otherwise: browse the class hierarchy to find an handler in the registry.
        while (!exceptionRegistry.containsKey(clazz) && clazz != null) {
            clazz = clazz.getSuperclass();
        }

        // Get the convert function.
        Function<Throwable, R> convertFunction = null;
        if (exceptionRegistry.containsKey(clazz)) {
            convertFunction = exceptionRegistry.get(clazz);
        } else {
            convertFunction = defaultHandler;
        }

        // Return the result
        if (convertFunction != null) {
            // Add found conversion to the registry to speed next handling.
            register(exceptionToHandle.getClass(), convertFunction);

            // Return result.
            return convertFunction.apply(exceptionToHandle);
        } else {
            return null;
        }
    }

    /**
     * A builder class for the {@link ExceptionConverter}.
     * <br>
     *
     * @author Julb.
     */
    public static class Builder<B> {

        /**
         * The exception converter to build.
         */
        private ExceptionConverter<B> exceptionConverter = new ExceptionConverter<>();

        /**
         * Defines a list of exceptions that will be handled by the same handler function.
         * @param classes the list of exception classes.
         * @return the sub-builder instance.
         */
        public ClassFnBuilder<B> when(@NonNull Class<? extends Throwable> clazz) {
            return whenAnyOf(List.of(clazz));
        }

        /**
         * Defines a list of exceptions that will be handled by the same handler function.
         * @param classes the list of exception classes.
         * @return the sub-builder instance.
         */
        public ClassFnBuilder<B> whenAnyOf(@NonNull Collection<Class<? extends Throwable>> classes) {
            return new ClassFnBuilder<>(this, classes);
        }

        /**
         * Defines a default handler for uncaught exception.
         * @return the sub-builder instance.
         */
        public ClassFnBuilder<B> whenUncaught() {
            return new ClassFnBuilder<>(this, null);
        }

        /**
         * Registers an handler for the given exception classes.
         * @param classes the exception classes.
         * @param handlerFn the handler function.
         */
        void register(
                @NonNull Collection<Class<? extends Throwable>> classes, @NonNull Function<Throwable, B> handlerFn) {
            for (Class<? extends Throwable> clazz : classes) {
                exceptionConverter.register(clazz, handlerFn);
            }
        }

        /**
         * Sets the default handler.
         * @param defaultHandler the default handler.
         */
        void setDefaultHandler(@NonNull Function<Throwable, B> defaultHandler) {
            exceptionConverter.setDefaultHandler(defaultHandler);
        }

        /**
         * Returns the exception converter built using this class.
         * @return the exception converter.
         */
        public ExceptionConverter<B> build() {
            return exceptionConverter;
        }
    }

    /**
     * A sub-builder class for the {@link ExceptionConverter}.
     * <br>
     *
     * @author Julb.
     */
    @RequiredArgsConstructor
    public static class ClassFnBuilder<S> {

        /**
         * The exception converter to build.
         */
        private final Builder<S> parent;

        /**
         * The exception converter to build.
         */
        private final Collection<Class<? extends Throwable>> classes;

        /**
         * Applies the given function for the specified classes.
         * @param handlerFn the handler function.
         * @return the original builder.
         */
        public Builder<S> thenApply(@NonNull Function<Throwable, S> handlerFn) {
            if (classes == null) {
                parent.setDefaultHandler(handlerFn);
            } else {
                parent.register(classes, handlerFn);
            }
            return parent;
        }
    }
}
