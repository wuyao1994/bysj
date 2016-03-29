package com.cuit.zigbee.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cuit.web.util.RequestSessionUtil;
import com.cuit.zigbee.main.Test;
import com.cuit.zigbee.util.SerialReader;

@Controller
@RequestMapping(value = "/zigbee")
public class ZigbeeController {
    /**
     * 页面跳转，将参数传递到页面解析
     * @param request
     * @param pageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{pageName}", method = RequestMethod.GET)
    public ModelAndView viewAdminPages(HttpServletRequest request, @PathVariable("pageName") String pageName) throws Exception {
        return new ModelAndView("/zigbee/" + pageName, RequestSessionUtil.getRequestParamData(request));
    }
    @RequestMapping(value = "/changeStatu",method = RequestMethod.POST)
    public void LightNoOrOff(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("statu") int statu) {
        String message = id + "&" + statu;
        Test test = new Test();
        test.openSerialPort(message);
        test.close();
    }
}
