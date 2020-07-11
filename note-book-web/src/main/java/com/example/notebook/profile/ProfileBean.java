package com.example.notebook.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @author zhaohongliang
 * @descrition
 * @date 13:00:00 2019-03-21
 */
@Profile("local")       // 支持数组：@Profile({"dev", "local"})
@Configuration
@Slf4j
@PropertySource(value = {"classpath:book.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "book.properties")
public class ProfileBean {

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private BookProperties bookProperties;

    @PostConstruct
    public void init() {
        log.info("local环境下激活!");
        log.info("bookName:{}", bookName);
        log.info("bookName:{}", bookProperties.getName());
        log.info("bookAge:{}", bookProperties.getAge());
        log.info("bookAddress:{}", bookProperties.getAddress());
    }
}
