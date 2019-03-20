package com.example.notebook.filter;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 * 描述：将此Filter交给Spring容器管理，@注解形式 和 @Bean一样
 * 注意：
 * 如果在 @Configuration 的 WebConfig 配置类添加了 filter 的 @Bean 和 FilterRegistrationBean 的 @Bean
 * 此处@Autowired 或 @Resource 获取实例不会发生 NullPointExcepton，只添加FilterRegistrationBean.setFilter(new Filter())会发生空指针异常
 * 如果@WebFilter实现过滤器，在spring boot中使用@Component，filterName属性value首字母大写，都可能会导致urlPatterns失效问题，
 * 推荐使用：filter的@Bean + FilterRegistrationBean的@Bean形式
 * 1、filter的@Bean + FilterRegistrationBean的@Bean 在 @Configuration总配置中注入
 * 2、@WebFilter注解，在启动类上增加@ServletComponentScan注解，自动扫描带有过滤器注解@WebFilter的包，
 * 3、@WebFilter和@Component注解共同使用（注意：urlPatterns属性value首字母要小写，避免发生urlPatterns路径失效而过滤所有地址）
 * 但是本身@WebFilter和@Component共同使用就是有点不正确的，@Component本身是要把当前类当spring的组件来处理的，它初始化的过程和@WebFilter的初始化过程冲突
 *
 */
//@Component
//@WebFilter(filterName = "myFilter",                 // Filter名称
//        urlPatterns = { "/*", "/test/*"},           // urlPatterns/value 两个属性作用相同，指定拦截的路径，支持多路径拦截
//        initParams = {                              // 配置参数
//                @WebInitParam(name = "noLoginPaths", value = "index.jsp;fail.jsp;/LoginServlet"),
//                @WebInitParam(name = "charSet", value = "utf-8")
//        }
//)
//@Order(1)  // 指定过滤器的执行顺序,值越大越靠后执行
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter过滤器创建!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        log.info("过滤器请求地址:" + requestURI);
        // 链路，直接传递给下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilter过滤器销毁!");
    }
}
