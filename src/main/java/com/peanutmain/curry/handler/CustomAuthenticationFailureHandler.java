package com.peanutmain.curry.handler;

import com.peanutmain.curry.filter.CustomUsernamePasswordAuthenticationFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final String APPLICATION_JSON = CustomUsernamePasswordAuthenticationFilter.APPLICATION_JSON;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isNotEmpty(contentType) && contentType.startsWith(APPLICATION_JSON)) {
            if (!response.isCommitted()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
                response.getWriter().write("{\"responseCode\":\"FAILURE\"}");
                response.getWriter().flush();
            }
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
