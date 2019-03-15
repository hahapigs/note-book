package com.example.notebook.user.repository;

import com.example.notebook.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhaohongliang
 * @description userRepository
 * @date 15:54:00 2018-06-27
 */
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * 通过姓名找找
     * @param name
     * @return
     */
    User findByName(String name);

}
