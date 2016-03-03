package com.cuit.web.bean;

/**
 * 分页访问参数类
 * @author Mx
 *
 */
public class PagerInfo{
    /**
     * 每页数量
     */
    Integer size;
    /**
     * 页码
     */
    Integer page;
    Long total;
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public Boolean supportPage() {
        if (this.size == null || this.page == null) {
            return false;
        }
        return true;
    }
}
