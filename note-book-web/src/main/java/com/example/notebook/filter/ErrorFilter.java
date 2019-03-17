package com.example.notebook.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 容错处理过滤器
 * @author zhaohongliang
 *
 */
public class ErrorFilter implements Filter {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Error过滤器被创建!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("error")) {
            servletRequest.getRequestDispatcher("/error/page_500").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("Error过滤器被销毁!");
    }
}
