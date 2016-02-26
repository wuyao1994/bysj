package com.cuit.web.bean;

import java.util.LinkedHashMap;
/**
 * 分页访问参数（包含附带的查询参数）
 * @author Mx
 *
 */
@Deprecated
public class PagerRequest {
    /**
     * 查询参数
     */
    LinkedHashMap<String,Object> params;
    /**
     * 分页参数
     */
    PagerInfo pagerInfo;
    public LinkedHashMap<String, Object> getParams() {
        return params;
    }
    public void setParams(LinkedHashMap<String, Object> params) {
        this.params = params;
    }
    public PagerInfo getPagerInfo() {
        return pagerInfo;
    }
    public void setPagerInfo(PagerInfo pagerInfo) {
        this.pagerInfo = pagerInfo;
    }
    
}
