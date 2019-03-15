package com.example.notebook.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaohongliang
 * @description
 * @date 15:11:00 2019-02-12
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    /**
     * 请求失败 500
     * @return
     */
    // @RequestMapping(value = "/page_500")
    public String failed() {
        return "pages/error/page_500.html";
    }
}
