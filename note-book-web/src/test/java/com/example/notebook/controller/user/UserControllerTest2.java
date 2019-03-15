package com.example.notebook.controller.user;

import com.example.notebook.config.DataSourceConfig;
import com.example.notebook.config.PrimaryConfig;
import com.example.notebook.user.model.User;
import com.example.notebook.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class, PrimaryConfig.class })
public class UserControllerTest2 {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(UserControllerTest2.class);

    @Resource
    private IUserService userService;

    @Test
    public void findByName() {
        String str = userService.findByName("赵钱孙");
        logger.info(str);
    }

    @Test
    @Transactional
    public void save() {
        User user = new User();
        user.setName("王大帅");
        user.setAge(26);
        user.setAddress("北京市朝阳区");
        user = userService.save(user);
        logger.info(user.toString());
    }
}