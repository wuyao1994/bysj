package com.cuit.admin.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminMenu;
import com.cuit.admin.bean.AdminRole;
import com.cuit.web.bean.PagerInfo;

public interface SystemManageDao {
    // 1.admin user info
    public void addAdminUser(AdminInfo admin) throws Exception;

    public AdminInfo getAdminByUserName(String useName);

    public AdminInfo getAdminByUserId(Long userId);

    public void updateAdminUser(AdminInfo admin) throws Exception;

    // public void updateAdminRoles(AdminInfo admin) throws Exception;
    public void deleteAdminUserRoles(AdminInfo admin) throws Exception;

    public void addAdminUserRoles(AdminInfo admin) throws Exception;

    public List<AdminInfo> getAdminUserList(Map<String, Object> params, PagerInfo pagerInfo); // ?

    public int getAdminUserListCnt(LinkedHashMap<String, Object> param);

    // 2.admin menu
    public void addAdminMenu(AdminMenu adminMenu) throws Exception;

    public AdminMenu getAdminMenu(Long no) throws Exception;

    public void updateAdminMenu(AdminMenu adminMenu) throws Exception;

    public List<AdminMenu> getAdminMenuList(Map<String, Object> params, PagerInfo pagerParam) throws Exception;

    public int getAdminMenuListCnt(Map<String, Object> params) throws Exception;

    public List<AdminMenu> getAllAdminMenus() throws Exception;

    public List<AdminMenu> getAdminMenusByParentMenu(AdminMenu parentMenu) throws Exception;
    // 3 admin grants
    // public List<AdminGrant> getAllAdminGrants() throws Exception; //无

    public List<AdminGrant> getAdminGrantsByRole(AdminRole role) throws Exception;

    // public void updateAdminRoleGrants(AdminRole adminRole) throws Exception;
    public void deleteAdminRoleGrants(AdminRole adminRole) throws Exception;

    public void insertAdminRoleGrants(AdminRole adminRole) throws Exception;

    // 4 admin role
    public void addAdminRole(AdminRole adminUser) throws Exception;

    public AdminRole getAdminRole(Long no) throws Exception;

    public void updateAdminRole(AdminRole adminUser) throws Exception;

    public List<AdminRole> getAllAdminRoles() throws Exception;

    public List<AdminRole> getAdminUserRoles(AdminInfo adminUser) throws Exception;

    public List<AdminInfo> getAdminUsersByRole(AdminRole role) throws Exception;

    public List<AdminRole> getAdminRoleList(Map<String, Object> params, PagerInfo pagerParam) throws Exception;

    public int getAdminRoleListCnt(Map<String, Object> params) throws Exception;

}
