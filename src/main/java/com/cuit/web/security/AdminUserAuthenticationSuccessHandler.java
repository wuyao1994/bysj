package com.cuit.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AdminUserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
    private String adminHome = "/admin/manage/main";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.isAuthenticated()){
            setDefaultTargetUrl(adminHome);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
    public String getAdminHome() {
        return adminHome;
    }
    public void setAdminHome(String adminHome) {
        this.adminHome = adminHome;
    }
}
