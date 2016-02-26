package com.cuit.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminRole;
import com.cuit.admin.dao.SystemManageDao;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    public SystemManageDao systemManageDao;
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MySecurityMetadataSource(SystemManageDao systemManageDao) {
        this.systemManageDao = systemManageDao;
        try {
            loadResourceDefine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadResourceDefine() throws Exception {
        List<AdminRole> roles = systemManageDao.getAllAdminRoles();
        /**
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
         * sparta
         */
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

        for (AdminRole role : roles) {
            ConfigAttribute ca = new SecurityConfig(role.getName());

            List<AdminGrant> grants = systemManageDao.getAdminGrantsByRole(role);

            for (AdminGrant grant : grants) {
                if(grant.getActive() == null || !grant.getActive()){
                    continue;
                }
                String url = grant.getAdminMenu().getUrl();
                if(url == null || url.trim().equals("")){
                    continue;
                }
                /**
                 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，
                 * 将权限增加到权限集合中。
                 */
                if (resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(url, resURL)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
