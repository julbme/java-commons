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
package me.julb.commons.persistence.mongodb.entities.moneyamount;

import javax.validation.constraints.NotNull;

import me.julb.commons.enums.ISO4217Currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The money amount entity.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyAmountEntity {

    // @formatter:off
    /**
     * The value attribute expressed in cents.
     * -- GETTER --
     * Getter for {@link #value} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #value} property.
     * @param value the value to set.
     */
    // @formatter:on
    @NotNull
    private Long value;

    // @formatter:off
    /**
     * The currency attribute.
     * -- GETTER --
     * Getter for {@link #currency} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #currency} property.
     * @param currency the value to set.
     */
    // @formatter:on
    @NotNull
    private ISO4217Currency currency;

    /**
     * Returns the negative value of this amount.
     * @return the negative value.
     */
    public MoneyAmountEntity toNegative() {
        return new MoneyAmountEntity(-this.value, currency);
    }
}
