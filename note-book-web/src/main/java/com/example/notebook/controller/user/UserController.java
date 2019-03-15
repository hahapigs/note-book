package com.example.notebook.controller.user;

import com.example.notebook.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaohongliang
 * @description
 * @date 16:02:00 2018-06-27
 */
// @RestController
@Controller
@RequestMapping(value = "/user")
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

}
