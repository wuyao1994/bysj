package com.cuit.admin.service;

import java.util.List;
import java.util.Map;


import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminMenu;
import com.cuit.admin.bean.AdminRole;
import com.cuit.web.bean.PagerInfo;

public interface SystemManageSer {
//    public void setSystemManageDao(SystemManageDao systemManageDao);

    /****************************
     * user operate
     ************************************/
    void updateAdminPassword(AdminInfo admin, String oldPassword, String newPassword) throws Exception;

    AdminInfo getAdminUserInfo(String userId) throws Exception;

    void addAdminUser(AdminInfo admin) throws Exception;

    void updateAdminUser(AdminInfo admin) throws Exception;

    int getAdminUserListCnt(String keyWord);

    List<AdminInfo> getAdminUserList(String keyWord, PagerInfo pagerParam);

    public List<AdminRole> getAdminUserRoles(AdminInfo adminUser) throws Exception;

    public List<AdminMenu> getAdminUserMenus(AdminInfo adminUser) throws Exception;
    public AdminInfo getAdminByUserName(String userName) throws Exception;

    /**************************** menu operate ****************************/
    public void addAdminMenu(AdminMenu adminMenu) throws Exception;

    public AdminMenu getAdminMenu(Long no) throws Exception;

    public void updateAdminMenu(AdminMenu adminMenu) throws Exception;

    public List<AdminMenu> getAllAdminMenus() throws Exception;

    public List<AdminMenu> getAdminMenusByParentMenu(AdminMenu parentMenu) throws Exception;

    public List<AdminMenu> getAdminMenuList(Map<String, Object> params, PagerInfo pagerParam) throws Exception;

    public int getAdminMenuListCnt(Map<String, Object> params) throws Exception;

    /************************* role operate ***********************/
    public void addAdminRole(AdminRole adminRole) throws Exception;

    public AdminRole getAdminRole(Long no) throws Exception;

    public void updateAdminRole(AdminRole adminRole) throws Exception;

    public List<AdminGrant> getAdminGrantsByRole(AdminRole adminRole) throws Exception;

    public void updateAdminRoleGrants(AdminRole adminRole) throws Exception;

    public List<AdminRole> getAllAdminRoles() throws Exception;

    public List<AdminRole> getAdminRoleList(Map<String, Object> params, PagerInfo pagerParam) throws Exception;

    public int getAdminRoleListCnt(Map<String, Object> params) throws Exception;

}
