package com.cuit.web.util;

import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

public class RequestSessionUtil {

    /**
     * 从访问中获取所有的参数
     * @param req
     * @return LinkedHashMap
     */
    @SuppressWarnings({ "rawtypes" })
    public static LinkedHashMap<String, Object> getRequestParamData(HttpServletRequest request) {
        LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            data.put(key, request.getParameter(key));
        }
        return data;
    }

}
