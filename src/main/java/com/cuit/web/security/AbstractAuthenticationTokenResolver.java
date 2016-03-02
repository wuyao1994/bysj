package com.cuit.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public abstract class AbstractAuthenticationTokenResolver implements AuthenticationTokenResolver {
    protected String parameterName;
    protected String parameterValue;

    protected AbstractAuthenticationTokenResolver() {
    }

    protected AbstractAuthenticationTokenResolver(String parameterName) {
        this.parameterName = parameterName;
    }

    public boolean support(HttpServletRequest request) {
        String parameterValue = request.getParameter(parameterName);
        return parameterValue.equals(this.parameterValue);
    }

    public abstract Authentication resolve(HttpServletRequest request);

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}
