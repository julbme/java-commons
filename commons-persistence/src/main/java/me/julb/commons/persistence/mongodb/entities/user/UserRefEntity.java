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
package me.julb.commons.persistence.mongodb.entities.user;

import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import me.julb.commons.validation.constraints.E164Number;
import me.julb.commons.validation.constraints.Identifier;

import lombok.Getter;
import lombok.Setter;

/**
 * The user entity.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
public class UserRefEntity {

    // @formatter:off
    /**
     * The id attribute.
     * -- GETTER --
     * Getter for {@link #id} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #id} property.
     * @param id the value to set.
     */
    // @formatter:on
    @NotNull
    @NotBlank
    @Identifier
    private String id;

    // @formatter:off
    /**
     * The firstName attribute.
     * -- GETTER --
     * Getter for {@link #firstName} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #firstName} property.
     * @param firstName the value to set.
     */
    // @formatter:on
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String firstName;

    // @formatter:off
    /**
     * The lastName attribute.
     * -- GETTER --
     * Getter for {@link #lastName} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #lastName} property.
     * @param lastName the value to set.
     */
    // @formatter:on
    @NotNull
    @NotBlank
    @Size(max = 128)
    private String lastName;

    // @formatter:off
    /**
     * The mail attribute.
     * -- GETTER --
     * Getter for {@link #mail} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #mail} property.
     * @param mail the value to set.
     */
    // @formatter:on
    @NotNull
    @NotBlank
    @Email
    private String mail;

    // @formatter:off
    /**
     * The e164Number attribute.
     * -- GETTER --
     * Getter for {@link #e164Number} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #e164Number} property.
     * @param e164Number the value to set.
     */
    // @formatter:on
    @NotNull
    @NotBlank
    @E164Number
    private String e164Number;

    // @formatter:off
    /**
     * The locale attribute.
     * -- GETTER --
     * Getter for {@link #locale} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #locale} property.
     * @param locale the value to set.
     */
    // @formatter:on
    @NotNull
    private Locale locale;

    // @formatter:off
    /**
     * The displayName attribute.
     * -- GETTER --
     * Getter for {@link #displayName} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #displayName} property.
     * @param displayName the value to set.
     */
    // @formatter:on
    @NotBlank
    @NotNull
    private String displayName;
}
