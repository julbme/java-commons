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
package me.julb.commons.dto.notification.events.parts;

import java.util.Locale;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO targeting a mail recipient.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"mail"})
@NoArgsConstructor
@AllArgsConstructor
public class MailRecipientDTO {

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
}
