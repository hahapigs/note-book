package com.example.notebook.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhaohongliang
 * @description
 *      HandlerInterceptor 接口定义的是默认方法，这是jdk1.8的新特性，
 *      1.8以前推荐使用继承 HandlerInterceptorAdapter 抽象类 ，
 *      1.8以后推荐使用实现 HandlerInterceptor 接口
 *      HandlerInterceptor 只需要重写需要实现的方法就可以了，
 *      相比之前重写 HandlerInterceptor 或继承 HandlerInterceptorAdapter 类有更好的效果。
 * @date 19:15 2018/7/2
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * logger instance
     */
    public static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        logger.info("请求路径：{}", requestURI);
        logger.info("请求Contex-Path：{}", contextPath);
        logger.info("ServletPath：{}", servletPath);

        if(servletPath.equals("/error")) {
            // response.sendRedirect("/notebook");
            response.sendError(404, "您访问的页面不存在！");
            return false;
        }

        Object userName = request.getSession().getAttribute("userName");
//        if (null != userName) {
//            return true;
//        }
//        response.sendRedirect(request.getContextPath());
//        return false;
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
