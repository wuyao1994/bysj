
package com.cuit.admin.bean;

public class AdminGrant {
    private AdminMenu adminMenu;
    private Boolean view;
    private Boolean create;
    private Boolean update;
    private Boolean delete;
    private Boolean active;
    public AdminMenu getAdminMenu() {
        return adminMenu;
    }
    public void setAdminMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }
    public Boolean getView() {
        return view;
    }
    public void setView(Boolean view) {
        this.view = view;
    }
    public Boolean getCreate() {
        return create;
    }
    public void setCreate(Boolean create) {
        this.create = create;
    }
    public Boolean getUpdate() {
        return update;
    }
    public void setUpdate(Boolean update) {
        this.update = update;
    }
    public Boolean getDelete() {
        return delete;
    }
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
    public Boolean getActive() {
        if(active == null){
            return false;
        }else{
            return active;
        }
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
