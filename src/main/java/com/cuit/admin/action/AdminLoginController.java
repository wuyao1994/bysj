package com.cuit.admin.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cuit.web.util.RequestSessionUtil;

@Controller
@RequestMapping(value="/admin")
public class AdminLoginController {
    /**
     * 页面跳转，将参数传递到页面解析
     * @param request
     * @param pageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminPages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        return new ModelAndView("/admin/" + pageName, RequestSessionUtil.getRequestParamData(request));
    }
}
