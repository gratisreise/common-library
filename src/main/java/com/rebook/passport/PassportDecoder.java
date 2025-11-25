package com.rebook.passport;

import com.rebook.passport.PassportProto.Passport;

public class PassportDecoder {

    private final HmacUtil hmacUtil;

    public PassportDecoder(HmacUtil hmacUtil) {
        this.hmacUtil = hmacUtil;
    }

    public Passport decode(String encoded) {
        if (encoded == null || encoded.isBlank()) {
            throw new PassportException("Missing passport header");
        }

        byte[] raw = hmacUtil.verify(encoded);

        try {
            return Passport.parseFrom(raw);
        } catch (Exception e) {
            throw new PassportException("Failed to parse passport proto", e);
        }
    }
}
