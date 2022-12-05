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
package me.julb.commons.security.josejwt.keyloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Security;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import me.julb.commons.security.josejwt.exceptions.JOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.internalservererror.InvalidPEMKeyFormatException;
import me.julb.commons.security.josejwt.exceptions.internalservererror.InvalidPasswordPEMKeyException;
import me.julb.commons.security.josejwt.exceptions.internalservererror.MissingPasswordPEMKeyException;

import lombok.extern.slf4j.Slf4j;

/**
 * A loader to build {@link java.security.PublicKey} and {@link java.security.PrivateKey} from PEM files.
 * <br>
 * @author Julb.
 */
@Slf4j
public final class PEMKeyLoader {

    // ------------------------------------------ Constructors.

    /**
     * Constructor.
     */
    private PEMKeyLoader() {
        super();
    }

    // ------------------------------------------ Utility methods.

    /**
     * Loads a public key.
     * @param inputStream the input stream.
     * @return the public key.
     * @throws JOSEJWTException if an error occurs.
     */
    public static PublicKey loadPublicKey(InputStream inputStream) throws JOSEJWTException {
        Security.addProvider(new BouncyCastleProvider());

        LOGGER.debug("Loading PEM public key from input stream.");

        try (var pemParser = new PEMParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            var o = pemParser.readObject();

            // If null, it means that the provided element is not a PEM key.
            if (o == null) {
                throw new InvalidPEMKeyFormatException();
            }

            if (o instanceof PEMKeyPair pemkeyPair) {
                // Convert to Java (JCA) format
                var converter = new JcaPEMKeyConverter();
                var publicKey = converter.getKeyPair(pemkeyPair).getPublic();

                LOGGER.debug("Public key successfully loaded from a keypair.");
                return publicKey;
            } else if (o instanceof SubjectPublicKeyInfo subjectPublicKeyInfo) {
                // Convert to Java (JCA) format
                var converter = new JcaPEMKeyConverter();
                var publicKey = converter.getPublicKey(subjectPublicKeyInfo);

                LOGGER.debug("Public key successfully loaded from a pubkey.");
                return publicKey;
            } else {
                LOGGER.error(
                        "Unable to determine private key type from instance {}",
                        o.getClass().getName());
                throw new JOSEJWTException("Unable to determine private key type from instance.");
            }
        } catch (IOException e) {
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Loads a key from a file not protected by password.
     * @param inputStream the private key file.
     * @return the key pair
     * @throws JOSEJWTException if an error occurs.
     */
    public static KeyPair loadKey(InputStream inputStream) throws JOSEJWTException {
        Security.addProvider(new BouncyCastleProvider());

        LOGGER.debug("Loading PEM unprotected private key from input stream.");

        try (var pemParserSign = new PEMParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            var o = pemParserSign.readObject();

            // If null, it means that the provided element is not a PEM key.
            if (o == null) {
                throw new InvalidPEMKeyFormatException();
            }

            if (o instanceof PEMKeyPair pemkeyPair) {
                // Convert to Java (JCA) format
                var converter = new JcaPEMKeyConverter();
                return converter.getKeyPair(pemkeyPair);
            } else if (o instanceof SubjectPublicKeyInfo subjectPublicKeyInfo) {
                // Convert to Java (JCA) format
                var converter = new JcaPEMKeyConverter();
                var publicKey = converter.getPublicKey(subjectPublicKeyInfo);
                LOGGER.debug("Public key successfully loaded from a pubkey.");
                return new KeyPair(publicKey, null);
            } else if (o instanceof PEMEncryptedKeyPair) {
                LOGGER.error("Private key is encrypted and no password provided.");
                throw new MissingPasswordPEMKeyException();
            } else {
                LOGGER.error(
                        "Unable to determine private key type from instance {}",
                        o.getClass().getName());
                throw new JOSEJWTException("Unable to determine private key type from instance.");
            }
        } catch (IOException e) {
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Loads a private key from a file not protected by password.
     * @param inputStream the private key file.
     * @return the key pair
     * @throws JOSEJWTException if an error occurs.
     */
    public static KeyPair loadPrivateKey(InputStream inputStream) throws JOSEJWTException {
        Security.addProvider(new BouncyCastleProvider());

        LOGGER.debug("Loading PEM unprotected private key from input stream.");

        try (var pemParserSign = new PEMParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            var o = pemParserSign.readObject();

            // If null, it means that the provided element is not a PEM key.
            if (o == null) {
                throw new InvalidPEMKeyFormatException();
            }

            if (o instanceof PEMKeyPair pemkeyPair) {
                // Convert to Java (JCA) format
                var converter = new JcaPEMKeyConverter();
                var keyPair = converter.getKeyPair(pemkeyPair);

                LOGGER.debug("PEM unprotected private key loaded from a keypair.");
                return keyPair;
            } else if (o instanceof PEMEncryptedKeyPair) {
                LOGGER.error("Private key is encrypted and no password provided.");
                throw new MissingPasswordPEMKeyException();
            } else if (o instanceof SubjectPublicKeyInfo) {
                LOGGER.error("Provided key is a public one.");
                throw new InvalidPEMKeyFormatException("Public key provided. Expected private one.");
            } else {
                LOGGER.error(
                        "Unable to determine private key type from instance {}",
                        o.getClass().getName());
                throw new JOSEJWTException("Unable to determine private key type from instance.");
            }
        } catch (IOException e) {
            throw new JOSEJWTException(e);
        }
    }

    /**
     * Loads a private key from an input stream protected by a password.
     * @param inputStream the input stream.
     * @param password the password.
     * @return the keypair.
     * @throws JOSEJWTException if an error occurs.
     */
    public static KeyPair loadPasswordProtectedPrivateKey(InputStream inputStream, String password)
            throws JOSEJWTException {
        Security.addProvider(new BouncyCastleProvider());

        LOGGER.debug("Loading PEM password-protected private key from input stream.");

        try (var pemParserSign = new PEMParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            var o = pemParserSign.readObject();

            // If null, it means that the provided element is not a PEM key.
            if (o == null) {
                throw new InvalidPEMKeyFormatException();
            }

            // Build a converter.
            var converter = new JcaPEMKeyConverter();

            if (o instanceof PEMKeyPair pemkeyPair) {
                LOGGER.warn("Private key should be encrypted but this is not the case. Ignoring password parameter.");

                // Convert to Java (JCA) format
                var keyPair = converter.getKeyPair(pemkeyPair);
                LOGGER.debug("PEM Private key loaded from a keypair.");
                return keyPair;
            } else if (o instanceof PEMEncryptedKeyPair pemEncryptedKeyPair) {
                LOGGER.debug("Uncrypting private key with provided password.");

                // Build a PEM decryptor with the password.
                var pemDecryptorProvider = new JcePEMDecryptorProviderBuilder().build(password.toCharArray());

                // Decrypt the password file.
                var decryptedKeyPair = pemEncryptedKeyPair.decryptKeyPair(pemDecryptorProvider);

                // Extract private key.
                var keyPair = converter.getKeyPair(decryptedKeyPair);

                LOGGER.debug("PEM Private key loaded from a protected file.");
                return keyPair;
            } else if (o instanceof SubjectPublicKeyInfo) {
                LOGGER.error("Provided key is a public one.");
                throw new InvalidPEMKeyFormatException("Public key provided. Expected private one.");
            } else {
                LOGGER.error(
                        "Unable to determine private key type from instance {}",
                        o.getClass().getName());
                throw new JOSEJWTException("Unable to determine private key type from instance.");
            }
        } catch (EncryptionException e) {
            throw new InvalidPasswordPEMKeyException(e);
        } catch (IOException e) {
            throw new JOSEJWTException(e);
        }
    }
}
