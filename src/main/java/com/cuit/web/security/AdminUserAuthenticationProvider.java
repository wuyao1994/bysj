package com.cuit.web.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminRole;
import com.cuit.admin.service.SystemManageSer;
import com.cuit.web.util.EncryptUtil;

public class AdminUserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SystemManageSer systemManageSer;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminUserAuthenticationToken authenticationToken = (AdminUserAuthenticationToken) authentication;

        AdminInfo adminUser = authenticationToken.getAdminInfo();
        AdminInfo resultUser = null;
        try {
            resultUser = this.systemManageSer.getAdminByUserName(adminUser.getUserName());
        } catch (Exception e) {
            throw new BadCredentialsException("Can not find User");
        }

        if (resultUser == null) {
            throw new AuthenticationCredentialsNotFoundException("UserName does not exist!");
        }
        if (!resultUser.getUserPass().equals(EncryptUtil.MD5Encode(adminUser.getUserPass()))) {
            throw new AuthenticationCredentialsNotFoundException("Password does not match!");
        }
        if (!resultUser.getActive()) {
            throw new AuthenticationCredentialsNotFoundException("The account is not active!");
        }
        try {
            List<AdminRole> roles = this.systemManageSer.getAdminUserRoles(resultUser);
            if (roles != null) {
                for (AdminRole role : roles) {
                    List<AdminGrant> grants = systemManageSer.getAdminGrantsByRole(role);
                    if (grants != null) {
                        role.setGrants(grants);
                    }
                }
            }
            resultUser.setRoles(roles);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Fail to load user Roles!");
        }
        authenticationToken.setAuthenticated(true);
        authenticationToken.setAdminInfo(resultUser);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return authenticationToken;
    }

    public boolean supports(Class<?> authentication) {
        return AdminUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
