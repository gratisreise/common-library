package com.rebook.common.auth;

import com.rebook.common.auth.PassportProto.Passport;

public class PassportDecoder {

    private final HmacUtil hmacUtil;

    public PassportDecoder(HmacUtil hmacUtil) {
        this.hmacUtil = hmacUtil;
    }

    public Passport decode(String encoded) {
        if (encoded == null || encoded.isBlank()) {
            throw new PassportException("passport가 존재하지 않습니다.");
        }

        byte[] raw = hmacUtil.verify(encoded);

        try {
            return Passport.parseFrom(raw);
        } catch (Exception e) {
            throw new PassportException("protobuf를 파싱했는데 실패했습니다.", e);
        }
    }
}
