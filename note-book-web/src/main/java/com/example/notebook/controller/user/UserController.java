package com.example.notebook.controller.user;

import com.example.notebook.annotation.MyValid;
import com.example.notebook.base.utils.ValidatorUtil;
import com.example.notebook.exception.CommonException;
import com.example.notebook.user.model.User;
import com.example.notebook.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author zhaohongliang
 * @description
 * @date 16:02:00 2018-06-27
 */
// @RestController
@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 测试方法
     * @param name
     * @return
     */
    @RequestMapping(value = "/test")
    public String getUser(String name, Model model) {
        String str = userService.findByName(name);
        model.addAttribute("str", str);
        return "pages/index";
    }

    /**
     * javax.validation.Valid 校验
     * @param user
     * @return
     */
    @GetMapping(value = "/valid/test_1")
    @ResponseBody
    public String test_1(@Valid User user) {
        return user.toString();
    }

    /**
     * javax.validation.Valid 手动校验
     * @param user
     * @return
     * @throws CommonException
     */
    @GetMapping(value = "/valid/test_2")
    @ResponseBody
    public String test_2(User user) throws CommonException {
        // 手动校验
        ValidatorUtil.validate(user);
        return user.toString();
    }

    /**
     * 自定义注解校验
     * @param name
     * @return
     */
    @GetMapping(value = "/valid/test_3")
    @ResponseBody
    public String test_3(@MyValid(message = "adadada", value = "1.0") String name) {
        return name.toString();
    }

}
