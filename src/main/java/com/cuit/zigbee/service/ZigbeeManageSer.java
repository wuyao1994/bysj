package com.cuit.zigbee.service;

import java.util.List;


import com.cuit.zigbee.bean.SensorInfo;

public interface ZigbeeManageSer {
    public void updateSensorDataInfo(SensorInfo sensor) throws Exception;

    public void updateSensorStatuInfo(SensorInfo sensor) throws Exception;

    public List<SensorInfo> getSensorInfoList() throws Exception;
}
