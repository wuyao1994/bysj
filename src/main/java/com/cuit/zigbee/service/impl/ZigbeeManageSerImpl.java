package com.cuit.zigbee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuit.zigbee.bean.SensorInfo;
import com.cuit.zigbee.dao.ZigbeeManageDao;
import com.cuit.zigbee.service.ZigbeeManageSer;

@Service
public class ZigbeeManageSerImpl implements ZigbeeManageSer {
    @Autowired
    ZigbeeManageDao zigbeeManageDao;

    @Override
    public void updateSensorDataInfo(SensorInfo sensor) throws Exception {
        try {
            this.zigbeeManageDao.updateSensorDataInfo(sensor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSensorStatuInfo(SensorInfo sensor) throws Exception {
        try {
            this.zigbeeManageDao.updateSensorStatuInfo(sensor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SensorInfo> getSensorInfoList() throws Exception {
        return this.zigbeeManageDao.getSensorInfoList();
    }

}
