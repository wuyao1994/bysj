package com.cuit.admin.bean;

import java.io.Serializable;
import java.util.List;
/**
 * AdminInfo是后台用户账号数据，它实现了来自Spring-Security的UserDetails接口，实现获取角色的函数，作为用户的权限识别
 * 具体请参照Spring-Security使用手册和/WEB-INF/conf/spring-security.xml
 * @author Mx
 *
 */
public class AdminInfo extends MasterData implements Serializable{
    private static final long serialVersionUID = -2034432429142674566L;
    private Integer userId;

    /**
     * 用户登陆名称
     */
    private String userName;
    /**
     * 用户密码，通常会通过md5加密
     */
    private String userPass;
    private String email;
    private List<AdminRole> roles;

    public Long getNo() {
        return (long) getUserId();
    }
    public void setNo(Long no) {
        this.userId = no.intValue();
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName.trim();
    }
    public String getUserPass() {
        return userPass;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass.trim();
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<AdminRole> getRoles() {
        return roles;
    }
    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AdminInfo)) {
            return false;
        }
        AdminInfo targetUser = (AdminInfo) obj;
        if (!targetUser.getNo().equals(this.getNo())) {
            return false;
        }
        return true;
    }
}
