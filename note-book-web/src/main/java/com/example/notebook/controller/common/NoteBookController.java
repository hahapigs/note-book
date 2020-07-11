package com.example.notebook.controller.common;

import com.example.notebook.profile.BookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NoteBookController
 * @author zhaohongliang
 * @description
 * @date 17:07:00 2019-03-20
 */
@Controller
@RequestMapping("")
@Slf4j
@EnableConfigurationProperties(BookProperties.class)
public class NoteBookController {

    @Autowired
    private BookProperties bookProperties;

    @RequestMapping(value = "/notebook")
    public void getBookInfo(){
        log.info("bookName:{}", bookProperties.getName());
        log.info("bookAge:{}", bookProperties.getAge());
        log.info("bookAddress:{}", bookProperties.getAddress());
    }

}
