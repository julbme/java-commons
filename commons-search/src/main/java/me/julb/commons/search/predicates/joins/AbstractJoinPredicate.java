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
package me.julb.commons.search.predicates.joins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.julb.commons.constants.Chars;
import me.julb.commons.constants.Integers;
import me.julb.commons.constants.Strings;
import me.julb.commons.search.predicates.SearchPredicate;

import lombok.Getter;
import lombok.Setter;

/**
 * The join predicate. <br>
 * @author Julb.
 */
@Getter
@Setter
public abstract class AbstractJoinPredicate implements SearchPredicate {

    // @formatter:off
    /**
     * The predicates attribute.
     * -- GETTER --
     * Getter for {@link #predicates} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #predicates} property.
     * @param predicates the value to set.
     */
    // @formatter:on
    private List<SearchPredicate> predicates = new ArrayList<>();

    /**
     * Constructor.
     * @param predicates the predicates.
     */
    protected AbstractJoinPredicate(SearchPredicate... predicates) {
        if (predicates != null) {
            this.predicates.addAll(Arrays.asList(predicates));
        }
    }

    /**
     * Adds a predicate to the list.
     * @param predicate the predicate to add.
     */
    public void addPredicate(SearchPredicate predicate) {
        this.predicates.add(predicate);
    }

    /**
     * Returns <code>true</code> if the join predicate is a "and", <code>false</code> otherwise.
     * @return <code>true</code> if the join predicate is a "and", <code>false</code> otherwise.
     */
    @JsonIgnore
    public boolean isAnd() {
        return false;
    }

    /**
     * Returns <code>true</code> if the join predicate is a "or", <code>false</code> otherwise.
     * @return <code>true</code> if the join predicate is a "or", <code>false</code> otherwise.
     */
    @JsonIgnore
    public boolean isOr() {
        return false;
    }

    /**
     * Gets the separator used by the predicate.
     * @return the separator.
     */
    @JsonIgnore
    protected abstract String getSeparator();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (predicates.isEmpty()) {
            return StringUtils.EMPTY;
        } else if (predicates.size() == Integers.ONE) {
            return predicates.get(0).toString();
        } else {
            var stringPredicates = new ArrayList<String>();
            for (SearchPredicate predicate : predicates) {
                stringPredicates.add(predicate.toString());
            }
            return Strings.LEFT_PARENTHESIS
                    + StringUtils.join(stringPredicates, Chars.SPACE + getSeparator() + Chars.SPACE)
                    + Strings.RIGHT_PARENTHESIS;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toPrettyString() {
        if (predicates.isEmpty()) {
            return StringUtils.EMPTY;
        } else if (predicates.size() == Integers.ONE) {
            return predicates.get(0).toPrettyString();
        } else {
            var stringPredicates = new ArrayList<String>();
            for (SearchPredicate predicate : predicates) {
                stringPredicates.add(predicate.toPrettyString());
            }
            return Strings.LEFT_PARENTHESIS
                    + StringUtils.join(stringPredicates, Chars.SPACE + getSeparator() + Chars.SPACE)
                    + Strings.RIGHT_PARENTHESIS;
        }
    }
}
