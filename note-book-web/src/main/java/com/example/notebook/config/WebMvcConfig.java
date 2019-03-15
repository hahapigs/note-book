package com.example.notebook.config;

import com.example.notebook.filter.MyFilter;
import com.example.notebook.intercept.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
             * @param registry
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }

            /**
             * 便捷式视图控制器
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login.html");
                registry.addViewController("/error/page_500").setViewName("pages/error/page_500.html");
            }

            /**
             * 拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // 登录拦截所有地址，但不包括（"/", "/static/**"）
                registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/", "/static/**");
            }

            /**
             * 静态资源处理器
             * 注意：
             * 如果在application配置文件中配置了 spring.mvc.static-path-pattern 和 spring.resources.static-locations 属性，此初可不添加自定义配置
             *
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
        };
    }

    /**
     * 过滤器
     * @return
     */
    // @Bean
    public FilterRegistrationBean<MyFilter> filter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setName("MyFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
