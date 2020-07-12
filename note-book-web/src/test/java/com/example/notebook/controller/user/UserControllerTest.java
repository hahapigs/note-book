package com.example.notebook.controller.user;

import com.example.notebook.user.model.User;
import com.example.notebook.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @runWith注解作用：
 * --@RunWith就是一个运行器
 * --@RunWith(JUnit4.class)就是指用JUnit4来运行
 * --@RunWith(SpringRunner.class) 让测试运行于Spring测试环境，以便在测试开始的时候自动创建Spring的应用上下文
 * --@RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环境，以便在测试开始的时候自动创建Spring的应用上下文
 * SpringRunner is an alias for the SpringJUnit4ClassRunner.
 * To use this class, simply annotate a JUnit 4 based test class with @RunWith(SpringRunner.class).
 * SpringRunner 继承了SpringJUnit4ClassRunner，没有扩展任何功能；使用前者，名字简短而已.
 * --@RunWith(Suite.class)的话就是一套测试集合
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

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
        user.setName("赵钱孙");
        user.setAge(28);
        user.setAddress("北京市朝阳区");
        user = userService.save(user);
        log.info(user.toString());
    }
}