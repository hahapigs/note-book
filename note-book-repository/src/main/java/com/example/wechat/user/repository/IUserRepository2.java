package com.example.wechat.user.repository;

import com.example.wechat.user.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhaohongliang
 * @description userRepository
 * @date 15:54:00 2018-06-27
 */
public interface IUserRepository2 extends CrudRepository<User, Long> {

    /**
     * 通过姓名找找
     * @param name
     * @return
     */
    User findByName(String name);

}
