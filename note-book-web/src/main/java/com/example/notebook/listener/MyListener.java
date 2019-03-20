package com.example.notebook.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 监听器
 * @author zhaohongliang
 * @description
 * @date 15:00:23 2019-03-20
 */
// @WebListener
public class MyListener implements ServletRequestListener {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("MyListener监听器销毁!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        logger.info("MyListener监听器初始化!");
    }
}
