package com.example.notebook.listener;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("MyListener监听器销毁!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("MyListener监听器初始化!");
    }
}
