package com.cuit.admin.bean;


public class AdminMenu extends MasterData{
    private String name;
    private String url;
    private Short level;
    private Short sequence;
    private AdminMenu parentMenu;
    private String modelId;

    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public AdminMenu getParentMenu() {
        return parentMenu;
    }
    public void setParentMenu(AdminMenu parentMenu) {
        this.parentMenu = parentMenu;
    }
    public Short getLevel() {
        return level;
    }
    public void setLevel(Short level) {
        this.level = level;
    }
    public Short getSequence() {
        return sequence;
    }
    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }
    public String getModelId() {
        return modelId;
    }
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
