package com.cuit.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface AuthenticationTokenResolver {
    boolean support(HttpServletRequest request);

    Authentication resolve(HttpServletRequest request);
}
