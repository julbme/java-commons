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
package me.julb.commons.search.predicates;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import me.julb.commons.constants.Strings;
import me.julb.commons.search.predicates.attributes.MultipleValuesAttributePredicate;
import me.julb.commons.search.predicates.attributes.NoValueAttributePredicate;
import me.julb.commons.search.predicates.attributes.SingleValueAttributePredicate;
import me.julb.commons.search.predicates.joins.AndPredicate;
import me.julb.commons.search.predicates.joins.OrPredicate;
import me.julb.commons.search.predicates.modifiers.NotPredicate;

/**
 * The predicate interface. <br>
 * @author Julb.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @Type(value = OrPredicate.class, name = "OR_PREDICATE"),
    @Type(value = AndPredicate.class, name = "AND_PREDICATE"),
    @Type(value = NotPredicate.class, name = "NOT_PREDICATE"),
    @Type(value = SingleValueAttributePredicate.class, name = "SINGLE_VALUE_ATTRIBUTE_PREDICATE"),
    @Type(value = MultipleValuesAttributePredicate.class, name = "MULTIPLE_VALUES_ATTRIBUTE_PREDICATE"),
    @Type(value = NoValueAttributePredicate.class, name = "NO_VALUE_ATTRIBUTE_PREDICATE")
})
public interface SearchPredicate {

    /**
     * Prints a pretty string representation of the predicate.
     * @return a pretty string representation of the predicate.
     */
    default String toPrettyString() {
        return Strings.EMPTY;
    }
}
