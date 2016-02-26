package com.cuit.web.bean;

/**
 * 分页访问参数类
 * @author Mx
 *
 */
public class PagerInfo {
    /**
     * 每页数量
     */
    int size;
    /**
     * 页码
     */
    int page;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    
}
