package com.example.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 启动类
 * @author zhaohongliang
 * @description
 * @date 15:57:23 2018-06-27
 */
@SpringBootApplication
// @ServletComponentScan   // 扫描特定注解，配合@WebFilter、@WebListener使用
public class NoteBookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteBookWebApplication.class, args);
    }
}
