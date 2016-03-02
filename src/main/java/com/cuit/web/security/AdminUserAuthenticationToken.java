package com.cuit.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminRole;

public class AdminUserAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;
    private AdminInfo adminInfo = new AdminInfo();

    public AdminUserAuthenticationToken(String account, String password) {
        super(null);
        this.adminInfo.setUserName(account);
        this.adminInfo.setUserPass(password);
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public Object getCredentials() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (this.isAuthenticated()) {
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            List<AdminRole> roles = this.getAdminInfo().getRoles();
            if (roles != null) {
                for (AdminRole role : roles) {
                    authList.add(new GrantedAuthorityImpl(role.getName()));
                }
            }
            return authList;
        } else {
            return Collections.emptyList();
        }
    }

    public Object getPrincipal() {
        return adminInfo;
    }
}
