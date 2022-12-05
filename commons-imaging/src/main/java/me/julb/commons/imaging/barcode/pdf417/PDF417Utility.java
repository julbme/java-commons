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
package me.julb.commons.imaging.barcode.pdf417;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.pdf417.PDF417Writer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The PDF417 utility.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PDF417Utility {

    /**
     * Generates a PDF417 image and write it to the given output stream.
     * @param value the value.
     * @param imageFormat the image format.
     * @param width the width of the generated image.
     * @param height the height of the generated image.
     * @param outputStream the stream to write the result to.
     * @throws IOException if an error occurs
     */
    public static void write(String value, String imageFormat, int width, int height, OutputStream outputStream)
            throws IOException {
        if (width <= 0) {
            throw new IllegalArgumentException("width should be positive.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height should be positive.");
        }
        try {
            var writer = new PDF417Writer();
            var bitMatrix = writer.encode(value, BarcodeFormat.PDF_417, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, outputStream);
        } catch (WriterException e) {
            throw new IOException(e);
        }
    }

    /**
     * Generates a PDF417 imagee and returns it as a buffered image.
     * @param value the value.
     * @param width the width of the generated image.
     * @param height the height of the generated image.
     * @throws IOException if an error occurs
     * @return the buffered image.
     */
    public static BufferedImage generate(String value, int width, int height) throws IOException {
        if (width <= 0) {
            throw new IllegalArgumentException("width should be positive.");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("height should be positive.");
        }
        try {
            var writer = new PDF417Writer();
            var bitMatrix = writer.encode(value, BarcodeFormat.PDF_417, width, height);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new IOException(e);
        }
    }
}
