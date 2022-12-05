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
package me.julb.commons.dto.rssfeed;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The RSS feed.
 * <br>
 * @author Julb.
 */
@Getter
@Setter
public class RSSFeedDTO {

    // @formatter:off
    /**
     * The title attribute.
     * -- GETTER --
     * Getter for {@link #title} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #title} property.
     * @param title the value to set.
     */
    // @formatter:on
    private String title;

    // @formatter:off
    /**
     * The description attribute.
     * -- GETTER --
     * Getter for {@link #description} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #description} property.
     * @param description the value to set.
     */
    // @formatter:on
    private String description;

    // @formatter:off
    /**
     * The link attribute.
     * -- GETTER --
     * Getter for {@link #link} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #link} property.
     * @param link the value to set.
     */
    // @formatter:on
    private String link;

    // @formatter:off
    /**
     * The language attribute.
     * -- GETTER --
     * Getter for {@link #language} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #language} property.
     * @param language the value to set.
     */
    // @formatter:on
    private String language;

    // @formatter:off
    /**
     * The items attribute.
     * -- GETTER --
     * Getter for {@link #items} property.
     * @return the value.
     * -- SETTER --
     * Setter for {@link #items} property.
     * @param items the value to set.
     */
    // @formatter:on
    private List<RSSFeedItemDTO> items = new ArrayList<>();
}
