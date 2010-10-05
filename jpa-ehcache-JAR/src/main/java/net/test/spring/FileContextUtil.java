package net.test.spring;

import net.test.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class FileContextUtil {

    private static FileContextUtil INSTANCE = null;

    private Service service;

    public Service getService() {
        return service;
    }

    private FileContextUtil() {
        String[] locationArr = { "/spring/application.xml", "/spring/my-beans.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(locationArr);

        this.service = (Service)context.getBean("service");
    }

    public static FileContextUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileContextUtil();
        }
        return INSTANCE;
    }     
}
