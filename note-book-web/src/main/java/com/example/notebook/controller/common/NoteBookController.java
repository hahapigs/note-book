package com.example.notebook.controller.common;

import com.example.notebook.core.BaseError;
import com.example.notebook.core.CommonErrorCodeEnum;
import com.example.notebook.profile.BookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * NoteBookController
 *
 * @RestController 是Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，
 * 如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
 * 而@Controller是用来创建处理http请求的对象，一般结合@RequestMapping使用
 *
 * @author zhaohongliang
 * @description
 * @date 17:07:00 2019-03-20
 */
@RequestMapping(value = "/notebook")
@Slf4j
@EnableConfigurationProperties(BookProperties.class)
@Controller
public class NoteBookController {

    @Autowired
    private BookProperties bookProperties;

    /**
     * @GetMapping 使用
     * 注： @GetMapping 等同于 @RequestMapping(method = RequestMethod.GET)
     */
    @GetMapping("/demo01")
    public void demo01() {
        log.info("hello, notebook, demo01");
    }

    /**
     * @PostMapping 使用
     * 注：@PostMapping 等同于 @RequestMapping(method = RequestMethod.POST)
     * @RequestMapping(method = RequestMethod.POST, value = "/demo02")
     * method 指定请求的method类型， GET、POST、PUT、DELETE 等
     * value 指定请求的实际地址，指定的地址可以是URI Template 模式
     */
    @PostMapping(value = "/demo02")
    public void demo02() {
        log.info("hello, notebook, demo02");
    }

    /**
     * @PutMapping 使用
     *
     * 注：@PutMapping 等同于 @RequestMapping(method = RequestMethod.PUT)
     * @PathVariable用来接收参数,如/path/001,可接收001作为参数
     *
     * @param id
     */
    @PutMapping("/demo03/{id}")
    public void demo03(@PathVariable("id") String id) {
        log.info(id);
    }

    /**
     * @DeleteMapping 使用
     * 注：@DeleteMapping 等同于 @RequestMapping(method = RequestMethod.DELETE)
     * @RequestParam 用来接收URL中的参数,如/param?id=001,可接收001作为参数
     *
     * @param id
     */
    @DeleteMapping("/demo04")
    public void demo04(@RequestParam("id") String id) {
        log.info(id);
    }

    /**
     * @PatchMapping 使用
     * 注：@PatchMapping 等同于 @RequestMapping(method = RequestMethod.PATCH)
     * @RequestAttribute用于访问由过滤器或拦截器创建的、预先存在的请求属性，效果等同与request.getAttrbute().
     *
     * @param id
     */
    @PatchMapping("/demo05")
    public void demo05(@RequestAttribute("id") String id) {
        log.info(id);
    }

    /**
     * consumes 使用
     * 注：指定处理请求的提交内容类型（Content-Type）
     * 方法仅处理request Content-Type为 "application/xml" 、"application/json" 类型的请求。
     *
     * @return
     */
    @PostMapping(value = "/demo10", consumes = {"application/xml", "application/json"})
    @ResponseBody
    public Object demo10() {
        return null;
    }

    /**
     * produces 使用
     * 注：指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
     * 方法仅处理request请求中Accept头中包含了 "application/json" 的请求，同时暗示了返回的内容类型为application/json;
     *
     * @return
     */
    @PostMapping(value = "/demo11", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object demo11() {
        return null;
    }

    /**
     * param 使用
     * 注：指定request中必须包含某些参数值是，才让该方法处理
     *
     * @return
     */
    @PostMapping(value = "/demo12", params="myParam=myValue")
    public void demo12() {
        log.info("hello, notebook, demo12");
    }

    /**
     * headers 使用
     * 注：指定request中必须包含某些指定的header值，才能让该方法处理请求
     *
     * @return
     */
    @GetMapping(value = "/demo13", headers = "Referer=http://www.notebook.com/")
    public void demo13() {
        log.info("hello, notebook, demo13");
    }

    // @ModelAttribute
    // 主要是绑定请求参数到指定对象上。此注解可被用于方法、参数上
    // 1.运用在参数上，会将客户端传递过来的参数按名称注入到指定对象中，并且会将这个对象自动加入ModelMap中，便于View层使用；
    // 2.运用在方法上，会在每一个@RequestMapping标注的方法前执行，如果有返回值，则自动将该返回值加入到ModelMap中
    // 注：由于现在都采用前后端分离开发，故此注解相对用的较少了，但对于一些在每次请求前需要进行一些额外操作时。
    // 使用此注解依然是个选择，比如进行统一的业务校验等，但使用此注解实现类似功能时需要注意，使用异步调用时，
    // 比如callable或者DeferredResult时，被此注解的方法会执行两次，因为异步请求时，是挂起另一个线程去重新执行，
    // 对于配置了拦截器而已，它们的执行顺序为：
    // preHandle ---->afterConcurrentHandlingStarted ----> Controller---->preHandler----> postHandler ----> afterCompletion
    // 解决方案的话可简单根据DispatcherType类型进行判断，异步时对应类型为：ASYNC，第一次请求正常为：REQUEST

    /**
     * 使用@ModelAttribute注解无返回值的方法
     * 注：被@ModelAttribute注解的方法会在Controller每个方法执行之前都执行，因此对于一个Controller中包含多个URL的时候，要谨慎使用
     * 请求任该controller下任一地址，都会先执行demo14(),再执行请求地址的方法，参数会被带到model中
     *
     * @param abc
     * @param model
     */
    @ModelAttribute
    public void demo14(@RequestParam(required = false) String abc, Model model) {
        model.addAttribute("attributeName", abc);
    }

    /**
     * 使用@ModelAttribute注解带有返回值的方法
     * 注: 返回值对象会被默认放到隐含的Model中，在Model中的key为返回值首字母小写，value为返回的值
     * 等同于 model.addAttribute("string", abc), 参数是什么类型，model中就设置什么类型的 key
     *
     * @param abc
     * @return
     */
    @ModelAttribute
    public String demo15(@RequestParam(required = false) String abc) {
        return abc;
    }

    /**
     * 使用@ModelAttribute带value参数注解
     * 注: value值相当于 model.addAttribute() 中的 key
     *
     * @param abc
     * @return
     */
    @ModelAttribute(value = "attributeName")
    public String demo16(@RequestParam(required = false) String abc) {
        return abc;
    }

    /**
     * 使用@ModelAttribute注解的参数
     * 注：请求地址前，使用@ModelAttribute注解带有返回值的方法先执行，会把参数的key和value放入model中，
     * 本方法执行，会从model中提取对应名称的属性
     *
     * @param str
     * @param str2
     * @param str3
     * @return
     */
    @GetMapping(value = "/param")
    public String demo17(@ModelAttribute("attributeName") String str,
                        @ModelAttribute("name") String str2,
                        @ModelAttribute("age") int str3) {
        return "str:" + str + ",str2:" + str2 + ",str3:" + str3;
    }

    @RequestMapping(value = "/notebook")
    public void getBookInfo(){
        BaseError baseError = CommonErrorCodeEnum.USER_NOT_FOUND;
        log.info("baseError:{}{}", baseError.getCode(),baseError.getMsg());
        log.info("bookName:{}", bookProperties.getName());
        log.info("bookAge:{}", bookProperties.getAge());
        log.info("bookAddress:{}", bookProperties.getAddress());
    }
}
