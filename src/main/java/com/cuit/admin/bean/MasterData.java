package com.cuit.admin.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.cuit.web.util.JsonDateSerializer;

public class MasterData {
    protected Long no;
    protected String id;
    protected Boolean active;
    protected Date createDate;
    protected Date updateDate;
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
