package com.example.notebook.user.service;

import com.example.notebook.user.model.User;

/**
 * @author zhahongliang
 * @description 用户业务层接口
 * @date 16:04:23 2018-06-27
 */
public interface IUserService {

    /**
     * 通过姓名找找
     * @param name
     * @return
     */
    String findByName(String name);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User save(User user);
}
