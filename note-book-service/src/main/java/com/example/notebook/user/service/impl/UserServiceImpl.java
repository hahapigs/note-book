package com.example.notebook.user.service.impl;

import com.example.notebook.user.model.User;

import com.example.notebook.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhaohongliang
 * @description 用户业务层实现类
 * @date 16:10:55 2018-06-27
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private com.example.notebook.user.repository.IUserRepository userRepository;

    @Resource
    private com.example.wechat.user.repository.IUserRepository2 userRepository2;

    /**
     * 通过姓名找找
     * @param name
     * @return
     */
    @Override
    public String findByName(String name) {
        User user = userRepository.findByName(name);
//        com.example.wechat.user.model.User user2 = userRepository2.findByName(name);
        logger.info("数据源primary：" + user.toString());
//        logger.info("数据源secondary：" + user2.toString());
//        return user.toString() + user2.toString();
        return user.toString();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
