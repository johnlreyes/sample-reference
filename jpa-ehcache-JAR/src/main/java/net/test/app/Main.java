package net.test.app;

import net.test.model.InfoDynamicModel;
import net.test.model.InfoModel;
import net.test.model.StatusModel;
import net.test.service.Service;
import net.test.spring.FileContextUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static int LIMIT = 2;

    public Main() {
        Service service = FileContextUtil.getInstance().getService();

        createStatus(service);
        
//        long timeStart = System.currentTimeMillis();
//        insert(service);
//        long timeStopInsert = System.currentTimeMillis();
//        long timeStartSelect = System.currentTimeMillis();
//        select(service);
//        long timeStop = System.currentTimeMillis();
//
//        System.out.println("Insert = "+(timeStopInsert-timeStart) +" ms");
//        System.out.println("Select = "+(timeStop-timeStartSelect) +" ms");
//        System.out.println("Insert = "+(timeStop-timeStart) +" ms");
    }

    private void createStatus(Service service) {
        System.out.println("["+getClass().getName()+":createStatus] START");
        StatusModel status = service.getStatus("LOADED");
        System.out.println("status="+status);
        if (status == null) {
            status = new StatusModel();
            status.setStatus("LOADED");
            service.addStatus(status);
        }
        status = service.getStatus("LOADED");
        System.out.println("status="+status);
        System.out.println("["+getClass().getName()+":createStatus] END");
    }

    private void select(Service service) {
        System.out.println("["+getClass().getName()+":select] START");
        List<InfoModel> list = service.getAllInfo();
        for (InfoModel info : list) {
            System.out.println(info.getId());
            System.out.println(info.getStatus().getStatus());
            for (InfoDynamicModel infoDynamic : info.getInfoDynamicList()) {
                System.out.println(infoDynamic.getCode());
                System.out.println(infoDynamic.getValue());
            }
        }
        System.out.println("["+getClass().getName()+":select] END");
    }

    private void insert(Service service) {
        System.out.println("["+getClass().getName()+":insert] START");
        StatusModel status = service.getStatus("LOADED");

        for (int i=0; i<LIMIT; i++) {
            List<InfoDynamicModel> list = createInfoDynamic(i);

            InfoModel info = new InfoModel();
            info.setStatus(status);
            info.setInfoDynamicList(list);

            service.addInfo(info);
        }
        System.out.println("["+getClass().getName()+":insert] END");
    }

    private List<InfoDynamicModel> createInfoDynamic(int i) {
        List<InfoDynamicModel> list = new ArrayList<InfoDynamicModel>();
        for (int h=0; h<2; h++) {
            InfoDynamicModel infoDynamic = new InfoDynamicModel();
            infoDynamic.setCode("code-"+i);
            infoDynamic.setValue("value-"+i);
            list.add(infoDynamic);
        }
        return list;
    }

    public static void main(String ... args) {
        new Main();
    }
}
