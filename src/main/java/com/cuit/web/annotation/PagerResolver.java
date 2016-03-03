package com.cuit.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 将页面分页请求的参数page与limit封装成pagerInfo
 * @author Sipingsoft
 * see PagerArgumentResolver.java
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PagerResolver {
    boolean required() default false;
}
