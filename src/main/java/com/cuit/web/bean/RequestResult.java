package com.cuit.web.bean;

/**
 * 处理结果返回类
 */
public class RequestResult<T> {
    /**
     * 处理结果编号0代表成功，其他代表失败
     */
    int code = 0;
    /**
     * 如果出错的信息
     */
    T message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
