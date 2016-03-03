package com.cuit.web.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cuit.web.annotation.PagerResolver;
import com.cuit.web.bean.PagerInfo;;

public class PagerArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String PAGE = "page";
    private static final String LIMIT = "limit";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //仅作用于添加了注解PagerResolver的参数
        return parameter.getParameterAnnotation(PagerResolver.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        PagerResolver pagerResolver = parameter.getParameterAnnotation(PagerResolver.class);
        Boolean required = pagerResolver.required();
        String page = webRequest.getParameter(PAGE);
        String limit = webRequest.getParameter(LIMIT);
        if(required) {
             if(page == null || limit == null) {
                 throw new Exception("page or limit param do not exist!");
             }
        }
        PagerInfo pager = new PagerInfo();
        if(page != null) {
            pager.setPage(Integer.parseInt(page));
        }
        if(limit != null) {
            pager.setSize(Integer.parseInt(limit));
        }
        return pager;
    }

}
