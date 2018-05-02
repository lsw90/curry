package com.peanutmain.curry.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peanutmain.curry.model.LoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String USERNAME = CustomUsernamePasswordAuthenticationFilter.class + ".USERNAME";
    private static final String PASSWORD = CustomUsernamePasswordAuthenticationFilter.class + ".PASSWORD";
    public static final String APPLICATION_JSON = "application/json";

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isNotEmpty(contentType) && contentType.startsWith(APPLICATION_JSON)) {
            return (String) request.getAttribute(PASSWORD);
        } else {
            return super.obtainPassword(request);
        }
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isNotEmpty(contentType) && contentType.startsWith(APPLICATION_JSON)) {
            return (String) request.getAttribute(USERNAME);
        } else {
            return super.obtainUsername(request);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

        if (StringUtils.isNotEmpty(contentType) && contentType.startsWith(APPLICATION_JSON)) {
            try {
                StringBuffer sb = new StringBuffer();
                String line;

                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

                request.setAttribute(USERNAME, loginRequest.getUsername());
                request.setAttribute(PASSWORD, loginRequest.getPassword());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
