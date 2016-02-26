package com.cuit.web.bean;

import java.util.List;

/**
 * 返回数据的分页结构
 * @author ChenMx
 *
 */
public class PagerStruct<T> {
    /**
     * 主要数据列表
     */
    List<T> rows;
    /**
     * 总数量
     */
    int total;
    /**
     * 页码
     */
    int page;
    
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> dataList) {
        this.rows = dataList;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int totalSize) {
        this.total = totalSize;
    }
}
