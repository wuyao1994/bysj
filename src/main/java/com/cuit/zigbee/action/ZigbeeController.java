package com.cuit.zigbee.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cuit.web.util.RequestSessionUtil;
import com.cuit.zigbee.bean.SensorInfo;
import com.cuit.zigbee.main.Test;
import com.cuit.zigbee.service.ZigbeeManageSer;

@Controller
@RequestMapping(value = "/zigbee")
public class ZigbeeController {
    @Autowired
    ZigbeeManageSer zigbeeManageser;
    Test test = new Test();

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

    @RequestMapping(value = "/changeStatu", method = RequestMethod.POST)
    public void LightNoOrOff(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("statu") int statu) {
        String message = "&" + id + ";" + statu + "$";
        SensorInfo sensorinfo = new SensorInfo();
        sensorinfo.setId(id);
        sensorinfo.setStatu(statu);

        try {
            zigbeeManageser.updateSensorStatuInfo(sensorinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        test.send(message);
    }

    @RequestMapping(value = "/getSensorInfoList", method = RequestMethod.POST)
    @ResponseBody
    public List<SensorInfo> getSensorInfoList(HttpServletRequest request) {
        List<SensorInfo> sensorinfo = null;
        try {
            sensorinfo = this.zigbeeManageser.getSensorInfoList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.openSerialPort();
        return sensorinfo;
    }

}
