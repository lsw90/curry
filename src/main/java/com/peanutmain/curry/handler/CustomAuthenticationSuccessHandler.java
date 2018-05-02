package com.peanutmain.curry.handler;

import com.peanutmain.curry.filter.CustomUsernamePasswordAuthenticationFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String APPLICATION_JSON = CustomUsernamePasswordAuthenticationFilter.APPLICATION_JSON;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isNotEmpty(contentType) && contentType.startsWith(APPLICATION_JSON)) {
            if (!response.isCommitted()) {
                response.setStatus(HttpStatus.OK.value());
                response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
                response.getWriter().write("{\"responseCode\":\"SUCCESS\"}");
                response.getWriter().flush();
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
