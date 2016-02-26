
package com.cuit.admin.bean;

import java.util.List;

public class AdminRole extends MasterData{
    private String name;
    private String description;
    private List<AdminGrant> grants;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<AdminGrant> getGrants() {
        return grants;
    }
    public void setGrants(List<AdminGrant> grants) {
        this.grants = grants;
    }
}
