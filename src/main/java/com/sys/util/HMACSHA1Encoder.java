package com.sys.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import org.apache.commons.codec.binary.Base64;
public class HMACSHA1Encoder {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param key
     *            The signing key.
     * @param data
     *            The data to be signed.
     *
     *
     * @return The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws SignatureException
     *             when signature generation fails
     */
    public static String calculateRFC2104HMAC(String key, String data) throws SignatureException {
        String result;
        try {

            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));

            // base64-encode the hmac
            result = Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

}