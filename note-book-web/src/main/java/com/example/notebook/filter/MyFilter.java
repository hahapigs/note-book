package com.example.notebook.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 * 描述：@注解形式 和 @Bean一样
 */
@Component                                          // 将此Filter交给Spring容器管理
@WebFilter(filterName = "MyFilter",                 // Filter名称
        urlPatterns = {"/notebook/*", "/test/*"},   // urlPatterns/value 两个属性作用相同，指定拦截的路径，支持多路径拦截
        initParams = {                              // 配置参数
                @WebInitParam(name = "noLoginPaths", value = "index.jsp;fail.jsp;/LoginServlet"),
                @WebInitParam(name = "charSet", value = "utf-8")
        }
)
@Order(1)  // 指定过滤器的执行顺序,值越大越靠后执行
public class MyFilter implements Filter {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器被创建!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        logger.info("过滤器请求地址：" + requestURI);
        if(requestURI.contains("error")) {
            servletRequest.getRequestDispatcher("/error/page_500").forward(servletRequest, servletResponse);
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("过滤器被销毁!");
    }
}
