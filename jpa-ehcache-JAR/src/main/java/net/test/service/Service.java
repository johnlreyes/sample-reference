package net.test.service;

import net.test.model.InfoDynamicModel;
import net.test.model.InfoModel;
import net.test.model.StatusModel;

import java.util.List;

public interface Service {

    void addInfo(InfoModel info);
    List<InfoModel> getAllInfo();
    void addStatus(StatusModel status);
    StatusModel getStatus(String status);
    void addInfoDynamic(InfoDynamicModel infoDynamic);
    InfoModel getInfo(int infoId);
    long getTotalInfo();
}