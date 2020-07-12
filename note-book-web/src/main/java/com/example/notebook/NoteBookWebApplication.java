package com.example.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 启动类
 *
 * @SpringBootConfiguration 继承至@Configuration，对于熟悉spring的开发者而言，此标注当前类是配置类，并会将当前类内声明的一个或多个以@Bean注解标记的方法的实例纳入到srping容器中，并且实例名就是方法名。
 * @EnableAutoConfiguration 这个注解就是springboot能自动进行配置的魔法所在了。主要是通过此注解，能所有符合自动配置条件的bean的定义加载到spring容器中，比如根据spring-boot-starter-web ，来判断你的项目是否需要添加了webmvc和tomcat，就会自动的帮你配置web项目中所需要的默认配置。具体的使用，会在后期自定义实现一个自动启动类时，会讲解到它的一些机制。此章节就不深入了，只需要它是这个用途即可，一般上也单独使用不要这个注解，但比如需要排除一些无需自动配置的类时，可利用exclude进行排除。
 * @ComponentScan 这个熟悉spring的开发者也应该熟悉，会扫描当前包及其子包下被@Component，@Controller，@Service，@Repository等注解标记的类并纳入到spring容器中进行管理。
 *
 * @author zhaohongliang
 * @description
 * @date 15:57:23 2018-06-27
 */
@SpringBootApplication
// @ServletComponentScan   // 扫描特定注解，配合@WebFilter、@WebListener使用
public class NoteBookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteBookWebApplication.class, args);
    }
}
