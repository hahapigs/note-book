package com.example.notebook.filter;


import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ErrorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Error过滤器创建!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("error")) {
            servletRequest.getRequestDispatcher("/error/page_500").forward(servletRequest, servletResponse);
        } else {
            // 链路，直接传递给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("Error过滤器销毁!");
    }
}
