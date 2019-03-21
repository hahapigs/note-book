package com.example.notebook.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * @author zhaohongliang
 * @descrition
 * @date 13:00:00 2019-03-21
 */
@Profile("local")       // 支持数组：@Profile({"dev", "local"})
@Configuration
@Slf4j
public class ProfileBean {

    @PostConstruct
    public void init() {
        log.info("local环境下激活!");
    }
}
