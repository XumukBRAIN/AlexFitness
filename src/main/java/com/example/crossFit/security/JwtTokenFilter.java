package com.example.crossFit.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final static String ENCODE_UTF_8 = "UTF-8";
    private final static String RESPONSE_INVALID_JSON = "{\n \"message\" : ";
    private final static String RESPONSE_INVALID = "\n \"statusCode\" : \"401\" \n }";
    private final static String CONTENT_TYPE_JSON = "application/json";
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    if (authentication.getAuthorities().toString().equals("[ROLE_USER]")) {
                        ClientDetails clientDetails = (ClientDetails) SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal();
                        if (clientDetails.getClient().getAccountIsLocked()) {
                            throw new JwtAuthenticationException("Пользователь заблокирован!");
                        }
                    }
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            servletResponse.setContentType(CONTENT_TYPE_JSON);
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.setCharacterEncoding(ENCODE_UTF_8);
            servletResponse.getWriter().write(RESPONSE_INVALID_JSON + e.getMessage() + "," + RESPONSE_INVALID);

            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
