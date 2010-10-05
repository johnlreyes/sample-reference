package net.test.app;

import net.test.model.InfoDynamicModel;
import net.test.model.InfoModel;
import net.test.model.StatusModel;
import net.test.service.Service;
import net.test.service.impl.ServiceImpl;
import net.test.spring.FileContextUtil;

import java.util.List;

public class Main {

    private static final int LIMIT_INFO_DYNAMIC = 10;//Integer.MAX_VALUE;
    private final static int LIMIT_INFO = 20000;//Integer.MAX_VALUE;

    static {
        System.out.println("LIMIT_INFO = "+LIMIT_INFO);
        System.out.println("LIMIT_INFO_DYNAMIC = "+LIMIT_INFO_DYNAMIC);
        ServiceImpl.DEBUG = false;
    }

    public Main() {
        Service service = FileContextUtil.getInstance().getService();

        createStatus(service);
        
        long timeStart = System.currentTimeMillis();

        long timeStartInsert = System.currentTimeMillis();
        insert(service);
        long timeStopInsert = System.currentTimeMillis();
        long timeStartCount = System.currentTimeMillis();
        count(service);
        long timeStopCount = System.currentTimeMillis();
        long timeStartSelect = System.currentTimeMillis();
        select(service);
        long timeStopSelect = System.currentTimeMillis();

        long timeStop = System.currentTimeMillis();

        System.out.println("Insert = "+(timeStopInsert-timeStartInsert) +" ms");
        System.out.println("Count = "+(timeStopCount-timeStartCount) +" ms");
        System.out.println("Select = "+(timeStopSelect-timeStartSelect) +" ms");
        System.out.println("TOTAL = "+(timeStop-timeStart) +" ms");
    }

    private void createStatus(Service service) {
        System.out.println("["+getClass().getName()+":createStatus] START");
        StatusModel status = service.getStatus("LOADED");
//        System.out.println("status="+status);
        if (status == null) {
            status = new StatusModel();
            status.setStatus("LOADED");
            service.addStatus(status);
        }
        status = service.getStatus("LOADED");
//        System.out.println("status="+status);
        System.out.println("["+getClass().getName()+":createStatus] END");
    }

    private void count(Service service) {
        System.out.println("["+getClass().getName()+":count] START");
        long total = service.getTotalInfo();
        System.out.println("total = "+total);
        System.out.println("["+getClass().getName()+":count] END");
    }
    private void select(Service service) {
        System.out.println("["+getClass().getName()+":select] START");
        List<InfoModel> list = service.getAllInfo();
        for (InfoModel info : list) {
            Integer id = info.getId();
//            System.out.print(id + " - ");
            String s = info.getStatus().getStatus();
//            System.out.println(s);
            for (InfoDynamicModel infoDynamic : info.getInfoDynamicList()) {
                String code = infoDynamic.getCode();
//                System.out.println(code);
                String value = infoDynamic.getValue();
//                System.out.println(value);
            }
        }
        System.out.println("["+getClass().getName()+":select] END");
    }

    private void insert(Service service) {
        System.out.println("["+getClass().getName()+":insert] START");

        for (int i=0; i< LIMIT_INFO; i++) {
            StatusModel status = service.getStatus("LOADED");
            InfoModel info = new InfoModel();
            info.setStatus(status);
            service.addInfo(info);

            info = service.getInfo(info.getId());
            insertInfoDynamic(service, info);
        }
        System.out.println("["+getClass().getName()+":insert] END");
    }

    private void insertInfoDynamic(Service service, InfoModel info) {
        for (int h=0; h< LIMIT_INFO_DYNAMIC; h++) {
            InfoDynamicModel infoDynamic = new InfoDynamicModel();
            infoDynamic.setInfo(info);
            infoDynamic.setCode("code-"+h);
            infoDynamic.setValue("value-"+h);
            service.addInfoDynamic(infoDynamic);
        }
    }

    public static void main(String ... args) {
        new Main();
    }
}
