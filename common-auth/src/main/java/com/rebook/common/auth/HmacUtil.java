package com.rebook.common.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class HmacUtil {

    private final String secretKey;
    private final String hmacAlgorithm;
    private static final int HMAC_LENGTH = 32;

    public HmacUtil(String secretKey, String hmacAlgorithm) {
        this.secretKey = secretKey;
        this.hmacAlgorithm = hmacAlgorithm;
    }

    public String sign(byte[] rawData) {
        try {
            byte[] hmac = compute(rawData);
            byte[] combined = new byte[rawData.length + hmac.length];

            System.arraycopy(rawData, 0, combined, 0, rawData.length);
            System.arraycopy(hmac, 0, combined, rawData.length, hmac.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new PassportException("Passport signing failed", e);
        }
    }

    public byte[] verify(String base64) {
        try {
            byte[] combined = Base64.getDecoder().decode(base64);

            if (combined.length < HMAC_LENGTH) {
                throw new PassportException("Invalid passport length");
            }

            int dataLength = combined.length - HMAC_LENGTH;
            byte[] raw = new byte[dataLength];
            byte[] hmac = new byte[HMAC_LENGTH];

            System.arraycopy(combined, 0, raw, 0, dataLength);
            System.arraycopy(combined, dataLength, hmac, 0, HMAC_LENGTH);

            byte[] expected = compute(raw);

            if (!MessageDigest.isEqual(expected, hmac)) {
                throw new PassportException("Invalid passport signature");
            }

            return raw;
        } catch (Exception e) {
            throw new PassportException("Invalid passport format", e);
        }
    }

    private byte[] compute(byte[] data) throws Exception {
        Mac mac = Mac.getInstance(hmacAlgorithm);
        mac.init(new SecretKeySpec(secretKey.getBytes(), hmacAlgorithm));
        return mac.doFinal(data);
    }
}
