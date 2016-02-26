package com.cuit.admin.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuit.admin.bean.AdminGrant;
import com.cuit.admin.bean.AdminInfo;
import com.cuit.admin.bean.AdminMenu;
import com.cuit.admin.bean.AdminRole;
import com.cuit.admin.dao.SystemManageDao;
import com.cuit.admin.service.SystemManageSer;
import com.cuit.web.bean.PagerInfo;
import com.cuit.web.util.EncryptUtil;

@Service
public class SystemManageSerImpl implements SystemManageSer {
    @Autowired
    private SystemManageDao systemManageDao;

    public void setSystemManageDao(SystemManageDao systemManageDao) {
        this.systemManageDao = systemManageDao;
    }

    /**   
     * <p>Title: updateAdminPassword</p>   
     * <p>Description: 密码修改</p>   
     * @param admin
     * @param oldPassword
     * @param newPassword
     * @throws Exception   
     */  
    @Override
    public void updateAdminPassword(AdminInfo admin, String oldPassword, String newPassword) throws Exception { if (!EncryptUtil.MD5Encode(oldPassword).equals(admin.getUserPass())) {
            throw new Exception("未发现账号，请检查您的的账号和密码");
        }
        admin.setUserPass(EncryptUtil.MD5Encode(newPassword));
        try {
            this.systemManageDao.updateAdminUser(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("数据库更新用户密码出错");
        }
    }

    /**   
     * <p>Title: getAdminUserList</p>   
     * <p>Description: 系统用户</p>   
     * @param keyWord
     * @param pagerParam
     * @return   
     */  
    @Override
    public List<AdminInfo> getAdminUserList(String keyWord, PagerInfo pagerParam) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("keyWord", keyWord);

        return this.systemManageDao.getAdminUserList(params, pagerParam);
    }

    /**   
     * <p>Title: addAdminUser</p>   
     * <p>Description: 新增用户</p>   
     * @param admin
     * @throws Exception   
     */  
    @Override
    public void addAdminUser(AdminInfo admin) throws Exception {
        this.systemManageDao.addAdminUser(admin);
        this.systemManageDao.deleteAdminUserRoles(admin);
        this.systemManageDao.addAdminUserRoles(admin);
    }

    /**   
     * <p>Title: updateAdminUser</p>   
     * <p>Description: 修改用户</p>   
     * @param admin
     * @throws Exception   
     */  
    @Override
    public void updateAdminUser(AdminInfo admin) throws Exception {
        this.systemManageDao.updateAdminUser(admin);
        this.systemManageDao.deleteAdminUserRoles(admin);
        this.systemManageDao.addAdminUserRoles(admin);
    }

    @Override
    public AdminInfo getAdminUserInfo(String userId) throws Exception {
        Long id = Long.parseLong(userId);
        AdminInfo adminUser = this.systemManageDao.getAdminByUserId(id);
        List<AdminRole> roles = this.systemManageDao.getAdminUserRoles(adminUser);
        adminUser.setRoles(roles);
        return adminUser;
    }

    @Override
    public int getAdminUserListCnt(String keyWord) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("keyWord", keyWord);
        return this.systemManageDao.getAdminUserListCnt(params);
    }

    @Override
    public List<AdminRole> getAdminUserRoles(AdminInfo adminUser) throws Exception {
        return this.systemManageDao.getAdminUserRoles(adminUser);
    }

    @Override
    public List<AdminMenu> getAdminUserMenus(AdminInfo adminUser) throws Exception {
        List<AdminMenu> menus = new ArrayList<AdminMenu>();
        List<AdminRole> roles = this.systemManageDao.getAdminUserRoles(adminUser);
        if (roles == null)
            return null;
        for (AdminRole role : roles) {
            List<AdminGrant> grants = this.systemManageDao.getAdminGrantsByRole(role);
            if (grants == null) {
                continue;
            }
            for (AdminGrant grant : grants) {
                if (grant.getActive() && grant.getAdminMenu().getActive())
                    menus.add(grant.getAdminMenu());
            }
        }
        return menus;
    }

    @Override
    public void addAdminMenu(AdminMenu adminMenu) throws Exception {
        this.systemManageDao.addAdminMenu(adminMenu);
    }

    public AdminMenu getAdminMenu(Long no) throws Exception {
        return this.systemManageDao.getAdminMenu(no);
    }

    @Override
    public void updateAdminMenu(AdminMenu adminMenu) throws Exception {
        this.systemManageDao.updateAdminMenu(adminMenu);
    }

    @Override
    public List<AdminMenu> getAllAdminMenus() throws Exception {
        return this.systemManageDao.getAllAdminMenus();
    }

    @Override
    public List<AdminMenu> getAdminMenusByParentMenu(AdminMenu parentMenu) throws Exception {
        return this.systemManageDao.getAdminMenusByParentMenu(parentMenu);
    }

    public List<AdminMenu> getAdminMenuList(Map<String, Object> params, PagerInfo pagerParam) throws Exception {
        return this.systemManageDao.getAdminMenuList(params, pagerParam);
    }

    public int getAdminMenuListCnt(Map<String, Object> params) throws Exception {
        return this.systemManageDao.getAdminMenuListCnt(params);
    }

    @Override
    public void addAdminRole(AdminRole admiRole) throws Exception {
        this.systemManageDao.addAdminRole(admiRole);
    }

    public AdminRole getAdminRole(Long no) throws Exception {
        return this.systemManageDao.getAdminRole(no);
    }

    @Override
    public void updateAdminRole(AdminRole adminRole) throws Exception {
        this.systemManageDao.updateAdminRole(adminRole);
    }

    public void updateAdminRoleGrants(AdminRole adminRole) throws Exception {
        if (adminRole.getNo() == null) {
            throw new Exception("adminRole's no cannot be null when to update grants");
        }
        List<AdminGrant> grants = adminRole.getGrants();
        if (grants != null) {
            for (AdminGrant grant : grants) {
                if (grant.getActive() == null || !grant.getActive()) {
                    grants.remove(grant);
                }
            }
        }
        this.systemManageDao.deleteAdminRoleGrants(adminRole);
        this.systemManageDao.insertAdminRoleGrants(adminRole);
    }

    public List<AdminGrant> getAdminGrantsByRole(AdminRole adminRole) throws Exception {
        List<AdminGrant> resultGrants = new ArrayList<AdminGrant>();
        List<AdminGrant> grants = this.systemManageDao.getAdminGrantsByRole(adminRole);
        for (AdminGrant grant : grants) {
            if (grant.getAdminMenu().getActive()) {
                resultGrants.add(grant);
            }
        }
        return resultGrants;
    }

    @Override
    public List<AdminRole> getAllAdminRoles() throws Exception {
        return this.systemManageDao.getAllAdminRoles();
    }

    @Override
    public List<AdminRole> getAdminRoleList(Map<String, Object> params, PagerInfo pagerParam) throws Exception {
        return this.systemManageDao.getAdminRoleList(params, pagerParam);
    }

    @Override
    public int getAdminRoleListCnt(Map<String, Object> params) throws Exception {
        return this.systemManageDao.getAdminRoleListCnt(params);
    }

}
