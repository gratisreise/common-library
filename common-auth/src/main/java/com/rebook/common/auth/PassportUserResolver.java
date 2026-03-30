package com.rebook.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
@RequiredArgsConstructor
public class PassportUserResolver implements HandlerMethodArgumentResolver {

    private final PassportDecoder passportDecoder;
    private final String headerName;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PassportUser.class)
                && parameter.getParameterType() == String.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String passport = request.getHeader(headerName);
        PassportProto.Passport decoded = passportDecoder.decode(passport);
        return decoded.getUserId();
    }
}
