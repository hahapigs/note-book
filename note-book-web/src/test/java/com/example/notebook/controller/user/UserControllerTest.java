package com.example.notebook.controller.user;

import com.example.notebook.user.model.User;
import com.example.notebook.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

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
        user.setName("赵钱孙");
        user.setAge(28);
        user.setAddress("北京市朝阳区");
        user = userService.save(user);
        logger.info(user.toString());
    }
}