package cn.com.kaituo.smart_community.common.http;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by zhengjun.jing on 9/15/2017.
 */
public class AppContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getAppContext(){
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
