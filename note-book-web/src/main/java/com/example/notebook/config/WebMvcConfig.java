package com.example.notebook.config;

import com.example.notebook.filter.ErrorFilter;
import com.example.notebook.filter.MyFilter;
import com.example.notebook.intercept.LoginInterceptor;
import com.example.notebook.listener.MyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zhaohongliang
 * @description 在SpringBoot2.0及Spring 5.0 WebMvcConfigurerAdapter已被废弃
 * 实现自定义mvc有两种方式
 * 1、JavaBean方式配置（官方推荐）
 * 2、直接实现WebMvcConfigurer（官方推荐）
 * 3、直接继承WebMvcConfigurationSupport
 * 注意：springboot的自动配置和自定义mvc同时有效
 * @date 17:16 2018/7/2
 */
@Configuration
public class WebMvcConfig {

    /**
     * 登录拦截器
     * @Autowired 、@Resource 、@Bean 或 new Object() 方式都可以
     *
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * 跨域请求处理器
             *
             * @param registry
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }

            /**
             * 便捷式视图控制器
             *
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login.html");
                registry.addViewController("/error/page_500").setViewName("pages/error/page_500.html");
            }

            /**
             * 拦截器
             *
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 多个拦截器时 以此添加 执行顺序按添加顺序
                // 登录拦截所有地址，但不包括（"/", "/static/**"）
                registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/", "/static/**");
            }

            /**
             * 静态资源处理器
             *
             * 手动配置其搜索静态资源文件的文件夹位置
             * 注意：
             * 如果在application配置文件中配置了 spring.mvc.static-path-pattern 和 spring.resources.static-locations 属性，此初可不添加自定义配置
             *
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // 当前项目下的资源路径，addResourceLocations("classpath:/xx/")
                // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                // 注:如果是Linux的话，直接指定文件夹路径即可，不需要指定哪个盘(Linux就一个可用盘)
                // registry.addResourceHandler("/static/**").addResourceLocations("file:D:/static/");

            }
        };
    }


    /**
     * MyFilter 过滤器
     *
     * FilterRegistrationBean是springboot提供的，此类提供setOrder方法，可以为filter设置排序值，让spring在注册web filter之前排序后再依次注册。
     * 注册多个时不通的过滤器，就注册多个FilterRegistrationBean即可
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<MyFilter>();
        // 当过滤器有注入其他bean类时，可直接通过@bean的方式进行实体类过滤器，这样不可自动注入过滤器使用的其他bean类。
        // 当然，若无其他bean需要获取时，可直接new MyFilter()，也可使用getBean的方式: registrationBean.setFilter(new MyFilter());
        /*
        @Bean
        public Filter myfilter() {
            return new MyFilter();
        }
        */
        registrationBean.setFilter(new MyFilter());
        registrationBean.setName("MyFilter");
        Collection collection = new ArrayList<>();
        collection.add("/*");
        collection.add("/test/*");
        registrationBean.setUrlPatterns(collection);
        registrationBean.addInitParameter("charSet", "UTF-8");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * ErrorFilter 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<ErrorFilter> errorFilter() {
        FilterRegistrationBean<ErrorFilter> registrationBean = new FilterRegistrationBean<ErrorFilter>();
        registrationBean.setFilter(new ErrorFilter());
        registrationBean.setName("ErrorFilter");
        registrationBean.addUrlPatterns("/error/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    /**
     * MyListener 监听器
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener() {
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<MyListener>();
        registrationBean.setListener(new MyListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
