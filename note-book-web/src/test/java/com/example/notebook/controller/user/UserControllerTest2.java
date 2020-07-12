package com.example.notebook.controller.user;

import com.example.notebook.config.DataSourceConfig;
import com.example.notebook.config.PrimaryConfig;
import com.example.notebook.user.model.User;
import com.example.notebook.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class, PrimaryConfig.class })
// @RunWith(SpringRunner.class)
// @SpringBootTest
@Slf4j
public class UserControllerTest2 {

    @Resource
    private IUserService userService;

    @Test
    public void findByName() {
        String str = userService.findByName("赵钱孙");
        log.info(str);
    }

    @Test
    @Transactional
    public void save() {
        User user = new User();
        user.setName("王大帅");
        user.setAge(26);
        user.setAddress("北京市朝阳区");
        user = userService.save(user);
        log.info(user.toString());
    }
}